package br.com.adocao.api.dto.animal;

import java.util.List;

public record RegisterAnimalRequestDTO(
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
        byte[] mainImage // Add main image as byte array
) {}