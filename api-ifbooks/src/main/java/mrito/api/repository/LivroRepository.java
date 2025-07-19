package mrito.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import mrito.api.model.Livro;
//import java.util.List;


public interface LivroRepository extends JpaRepository<Livro, Long> {
    public Optional<Livro> findByNomeIgnoreCase(String nome);

    //Livro findByNome(String nome);
}
