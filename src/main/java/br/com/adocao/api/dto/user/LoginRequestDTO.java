package br.com.adocao.api.dto.user;

public record LoginRequestDTO(String email, String passwordHash) {
}
