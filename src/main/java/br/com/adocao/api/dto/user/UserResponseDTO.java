package br.com.adocao.api.dto.user;

import br.com.adocao.api.entity.user.UserPermission;

public record UserResponseDTO(Long id, String name, String email, String passwordHash, UserPermission role) {
}
