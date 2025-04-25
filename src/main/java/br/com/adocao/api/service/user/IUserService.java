package br.com.adocao.api.service.user;

import br.com.adocao.api.dto.user.*;
import br.com.adocao.api.entity.user.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<?> registerUser(RegisterUserRequestDTO request);
    ResponseEntity<?> login(LoginRequestDTO data);
    ResponseEntity<UserResponseDTO> findByEmail(String email);
    ResponseEntity<List<UserResponseDTO>> getAllUsers();
    ResponseEntity<?> editPermission(String email, UserEditTypeRequestDTO request);
    ResponseEntity<?> updateUser(String email, UpdateUserRequestDTO request);
    UserModel getAuthenticatedUser();
}
