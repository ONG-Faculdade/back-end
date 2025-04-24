package br.com.adocao.api.dto.user;

import java.time.LocalDate;

public record UpdateUserRequestDTO(String name, String email, LocalDate birthDate) {
}
