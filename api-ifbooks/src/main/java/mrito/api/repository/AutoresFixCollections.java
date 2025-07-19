package mrito.api.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mrito.api.model.Autor;

@Service
public class AutoresFixCollections {

    private List<Autor> autores = new ArrayList<>();
    private Autor autor1;
    private Autor autor2;
    private Autor autor3;

    public AutoresFixCollections() {
        autor1 = criar(1L, "Robert Martin", "USA", LocalDate.of(1983, 4, 4));
        autor2 = criar(2L, "Andrew Tanenbauhn", "USA", LocalDate.of(1992, 7, 22));
        autor3 = criar(3L, "Francisco Pimentel", "BR", LocalDate.of(1997, 9, 19));

        autores.add(autor1);
        autores.add(autor2);
        autores.add(autor3);
    }

    public List<Autor> listarTodos() {
        return autores;
    }

    // public List<Autor> listarArray() {
    // Autor[] autoresArray = {autor1, autor2, autor3};
    // return Arrays.asList(autores);
    // }
    /*
     * Usa o lambda implementado a partir do java 8.0 através do lambda expression,
     * method references e Streams API
     */
    public Autor buscar(Long id) {
        return autores.stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    private Autor criar(Long id, String nome, String nacionalidade, LocalDate dataNascimento) {
        Autor novoAutor = new Autor();
        novoAutor.setId(id);
        novoAutor.setNome(nome);
        novoAutor.setNacionalidade(nacionalidade);
        novoAutor.setDataNascimento(dataNascimento);

        return novoAutor;
    }
}