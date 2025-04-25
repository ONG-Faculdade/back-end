package br.com.adocao.api.entity.animal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animals")
public class AnimalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private boolean adopted;

    private int age;

    private String breed;

    private String gender;

    private String type;

    private String location;

    private String size;

    @ElementCollection
    @CollectionTable(name = "animal_images", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    @Lob
    @Column(name = "main_image", columnDefinition = "LONGBLOB")
    private byte[] mainImage;
}
