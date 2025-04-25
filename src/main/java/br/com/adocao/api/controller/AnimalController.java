package br.com.adocao.api.controller;

import br.com.adocao.api.dto.animal.*;
import br.com.adocao.api.service.animal.AnimalServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalServiceImpl animalService;

    public AnimalController(AnimalServiceImpl animalService) {
        this.animalService = animalService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterAnimalRequestDTO dto) {
        return animalService.registerAnimal(dto);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnimal(@PathVariable Long id, @RequestBody UpdateAnimalRequestDTO dto) {
        return animalService.updateAnimal(id, dto);
    }

    @PutMapping("/adopt/{id}")
    public ResponseEntity<?> adoptAnimal(@PathVariable Long id) {
        return animalService.adoptAnimal(id);
    }
}
