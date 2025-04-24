package br.com.adocao.api.dto.user;

public record EditPasswordRequestDTO(String currentPassword, String newPassword) { }
