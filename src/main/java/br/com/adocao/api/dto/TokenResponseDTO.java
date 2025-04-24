package br.com.adocao.api.dto;

public record TokenResponseDTO(String email, String token, boolean status) { }