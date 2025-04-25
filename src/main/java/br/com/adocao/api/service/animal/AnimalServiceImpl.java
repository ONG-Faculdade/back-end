package br.com.adocao.api.service.animal;

import br.com.adocao.api.dto.animal.*;
import br.com.adocao.api.entity.animal.AnimalModel;
import br.com.adocao.api.exceptions.EventNotFoundException;
import br.com.adocao.api.repository.animal.IAnimalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImpl implements IAnimalService {

    private final IAnimalRepository repository;

    public AnimalServiceImpl(IAnimalRepository repository) {
        this.repository = repository;
    }

    private AnimalModel validateAnimal(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Animal not found"));
    }

    @Override
    public ResponseEntity<?> registerAnimal(RegisterAnimalRequestDTO request) {
        AnimalModel animal = new AnimalModel();
        animal.setName(request.name());
        animal.setDescription(request.description());
        animal.setAdopted(false);  // Initially not adopted
        animal.setAge(request.age());
        animal.setBreed(request.breed());
        animal.setGender(request.gender());
        animal.setType(request.type());
        animal.setLocation(request.location());
        animal.setSize(request.size());
        animal.setImageUrls(request.imageUrls());
        animal.setMainImage(request.mainImage());  // Save the main image (byte[])

        repository.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body("Animal created successfully.");
    }

    @Override
    public ResponseEntity<AnimalResponseDTO> findById(Long id) {
        AnimalModel animal = validateAnimal(id);
        AnimalResponseDTO responseDTO = new AnimalResponseDTO(
                animal.getId(), animal.getName(), animal.getDescription(), animal.isAdopted(),
                animal.getAge(), animal.getBreed(), animal.getGender(), animal.getType(),
                animal.getLocation(), animal.getSize(), animal.getImageUrls(), animal.getMainImage()
        );
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    public ResponseEntity<List<AnimalResponseDTO>> getAllAnimals() {
        List<AnimalModel> allAnimals = repository.findAll();
        if (allAnimals.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        List<AnimalResponseDTO> response = new ArrayList<>();
        for (AnimalModel animal : allAnimals) {
            response.add(new AnimalResponseDTO(
                    animal.getId(), animal.getName(), animal.getDescription(), animal.isAdopted(),
                    animal.getAge(), animal.getBreed(), animal.getGender(), animal.getType(),
                    animal.getLocation(), animal.getSize(), animal.getImageUrls(), animal.getMainImage()
            ));
        }

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> adoptAnimal(Long id) {
        AnimalModel animal = validateAnimal(id);
        if (animal.isAdopted()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Animal already adopted.");
        }
        animal.setAdopted(true);
        repository.save(animal);
        return ResponseEntity.status(HttpStatus.OK).body("Animal successfully adopted.");
    }

    @Override
    public ResponseEntity<?> updateAnimal(Long id, UpdateAnimalRequestDTO request) {
        AnimalModel animal = validateAnimal(id);
        animal.setName(request.name());
        animal.setDescription(request.description());
        animal.setAdopted(request.adopted());
        animal.setAge(request.age());
        animal.setBreed(request.breed());
        animal.setGender(request.gender());
        animal.setType(request.type());
        animal.setLocation(request.location());
        animal.setSize(request.size());
        animal.setImageUrls(request.imageUrls());
        animal.setMainImage(request.mainImage());

        repository.save(animal);
        return ResponseEntity.status(HttpStatus.OK).body("Animal updated successfully.");
    }

    @Override
    public ResponseEntity<?> deleteAnimal(Long id) {
        AnimalModel animal = validateAnimal(id);
        repository.delete(animal);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
