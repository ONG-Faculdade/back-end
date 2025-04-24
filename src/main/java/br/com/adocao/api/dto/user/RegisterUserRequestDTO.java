package br.com.adocao.api.dto.user;

import java.time.LocalDate;

public record RegisterUserRequestDTO(String name, String email, LocalDate birthDate, String passwordHash) {
}
