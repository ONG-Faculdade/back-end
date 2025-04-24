package br.com.adocao.api.repository;

import br.com.adocao.api.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}
