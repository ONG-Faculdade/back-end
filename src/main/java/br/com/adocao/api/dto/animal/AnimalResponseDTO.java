package br.com.adocao.api.dto.animal;

import java.util.List;

public record AnimalResponseDTO(
        Long id,
        String name,
        String description,
        boolean adopted,
        int age,
        String breed,
        String gender,
        String type,
        String location,
        String size,
        List<String> imageUrls,
        byte[] mainImage // Include main image in the response
) {}