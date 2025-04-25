package br.com.adocao.api.repository.animal;

import br.com.adocao.api.entity.animal.AnimalImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnimalImageRepository extends JpaRepository<AnimalImageModel, Long> {
    List<AnimalImageModel> findByAnimalId(Long animalId);
}
