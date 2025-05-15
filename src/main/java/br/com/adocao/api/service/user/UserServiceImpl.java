package br.com.adocao.api.service.user;

import br.com.adocao.api.dto.TokenResponseDTO;
import br.com.adocao.api.dto.user.*;
import br.com.adocao.api.entity.user.UserModel;
import br.com.adocao.api.entity.user.UserPermission;
import br.com.adocao.api.exceptions.EventNotFoundException;
import br.com.adocao.api.repository.user.IUserRepository;
import br.com.adocao.api.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(IUserRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @Override
    public UserModel getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserModel) authentication.getPrincipal();
    }

    private UserModel validateEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EventNotFoundException("Usuário não encontrado."));
    }

    @Override
    public ResponseEntity<?> registerUser(RegisterUserRequestDTO request) {
        if (repository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RegisterUserResponseDTO(HttpStatus.CONFLICT, "Usuário com este e-mail já existe."));
        }

        UserModel user = new UserModel();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.passwordHash()));

        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterUserResponseDTO(HttpStatus.CREATED, "Usuário criado com sucesso."));
    }

    @Override
    public ResponseEntity<?> login(LoginRequestDTO data) {
        Optional<UserModel> userOptional = repository.findByEmail(data.email());

        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");

        UserModel user = userOptional.get();

        if (user.getEmail().equals(data.email()) &&
                passwordEncoder.matches(data.passwordHash(), user.getPassword()))
            return ResponseEntity.ok(new TokenResponseDTO(user.getEmail(), tokenService.generateToken(user), true));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email ou senha incorreto.");
    }

    @Override
    public ResponseEntity<UserResponseDTO> findByEmail(String email) {
        UserModel user = validateEmail(email);
        return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPasswordHash(), user.getPermission()));
    }

    @Override
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserModel> allUsers = repository.findAll();
        if (allUsers.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        List<UserResponseDTO> response = new ArrayList<>();
        for (UserModel user : allUsers)
            response.add(new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPasswordHash(), user.getPermission()));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> editPermission(String email, UserEditTypeRequestDTO request) {
        Optional<UserModel> userOptional = repository.findByEmail(email);
        if (userOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        UserModel user = userOptional.get();
        user.setPermission(request.admin() ? UserPermission.ADMIN : UserPermission.USER);
        repository.save(user);
        return ResponseEntity.ok("Permissão alterada com sucesso");
    }

    @Override
    public ResponseEntity<?> updateUser(String email, UpdateUserRequestDTO request) {
        Optional<UserModel> userOptional = repository.findByEmail(email);
        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UpdateUserResponseDTO(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        UserModel user = userOptional.get();

        if (!user.getEmail().equals(request.email()) &&
                repository.findByEmail(request.email()).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new UpdateUserResponseDTO(HttpStatus.CONFLICT, "O e-mail fornecido já está em uso."));

        user.setName(request.name());
        user.setEmail(request.email());

        repository.save(user);
        return ResponseEntity.ok(new UpdateUserResponseDTO(HttpStatus.OK, "Usuário atualizado com sucesso."));
    }
}
