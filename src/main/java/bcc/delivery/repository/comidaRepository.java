package bcc.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.delivery.model.comida;
@Repository
public interface comidaRepository extends JpaRepository<comida, Long>{
    
}
