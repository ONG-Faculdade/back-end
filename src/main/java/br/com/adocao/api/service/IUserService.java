package br.com.adocao.api.service;

import br.com.adocao.api.dto.TokenResponseDTO;
import br.com.adocao.api.dto.user.*;
import br.com.adocao.api.entity.UserModel;
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
