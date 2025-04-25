package br.com.adocao.api.entity.animal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal_images")
public class AnimalImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")  // Verifique se o nome está correto
    private AnimalModel animal;

    @Lob
    private byte[] imageData;

}
