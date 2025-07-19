package mrito.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import mrito.api.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    public Optional<Autor> findByNomeIgnoreCase(String nome);
    // Autor findByNome(String nome);
}
