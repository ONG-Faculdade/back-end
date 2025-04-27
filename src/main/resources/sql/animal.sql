CREATE TABLE animals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    adopted BOOLEAN,
    age INT,
    breed VARCHAR(255),
    gender VARCHAR(50),
    type VARCHAR(100),
    location VARCHAR(255),
    size VARCHAR(100),
    main_image LONGBLOB
);

CREATE TABLE animal_images (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    animal_id BIGINT,
    image_data LONGBLOB,
    FOREIGN KEY (animal_id) REFERENCES animals(id)
);