package bcc.delivery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.delivery.model.usuario;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Long>{
    Optional<usuario> findByLogin(String login);
}
