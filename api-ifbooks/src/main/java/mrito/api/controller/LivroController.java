package mrito.api.controller;

import java.net.URI;
// import java.time.LocalDate;
// import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mrito.api.model.Livro;
import mrito.api.repository.LivroRepository;
//import mrito.api.repository.livroesFixCollections;
import mrito.api.service.LivroService;

import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author mrito - 05/06/2025
 *         End Point de livros
 */

@RestController
@RequestMapping("/livros")
@CrossOrigin
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroService livroService;

    @RequestMapping

    public List<Livro> listarTodos() {

        return repository.findAll();

    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Livro livroEncontrado = livroService.buscarOuFalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(livroEncontrado);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
        livro = livroService.salvar(livro);

        // Captura no contexto do Spring a URI da requisição
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livro.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody Livro livro) {
        livro.setId(id);
        livroService.atualizar(livro);
        return ResponseEntity.noContent().build();
    }

}
