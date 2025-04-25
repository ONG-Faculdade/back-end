package br.com.adocao.api.service.animal;

import br.com.adocao.api.dto.animal.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAnimalService {

    ResponseEntity<?> registerAnimal(RegisterAnimalRequestDTO request);
    ResponseEntity<AnimalResponseDTO> findById(Long id);
    ResponseEntity<List<AnimalResponseDTO>> getAllAnimals();
    ResponseEntity<?> adoptAnimal(Long id);
    ResponseEntity<?> updateAnimal(Long id, UpdateAnimalRequestDTO request);
    ResponseEntity<?> deleteAnimal(Long id);
}
