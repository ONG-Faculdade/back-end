package br.com.adocao.api.repository.animal;

import br.com.adocao.api.entity.animal.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnimalRepository extends JpaRepository<AnimalModel, Long> {
    Optional<AnimalModel> findById(Long id);

}
