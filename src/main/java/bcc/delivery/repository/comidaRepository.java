package bcc.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.delivery.model.comida;
@Repository
public interface comidaRepository extends JpaRepository<comida, Long>{
    public Optional<comida> findByTitle(String Title);
    public Optional<comida> findById(String Id);
}
