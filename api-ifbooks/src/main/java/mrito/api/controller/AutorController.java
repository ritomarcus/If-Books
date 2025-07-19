package mrito.api.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mrito.api.model.Autor;
import mrito.api.repository.AutorRepository;
import mrito.api.repository.AutoresFixCollections;
import mrito.api.service.AutorService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



/*
 * @Author mrito - 04/2025
 * End Point de Autores 
 */

@RestController
@RequestMapping("/autores")
@CrossOrigin
public class AutorController {
    
    @Autowired 
    // private AutoresFixCollections repository;
    private AutorRepository repository;
    // private AutoresFixCollections repository = new AutoresFixCollections();

    @Autowired
    private AutorService autorService;

    // @RequestMapping(value = "/autores", method=RequestMethod.GET)
    @GetMapping
    public List<Autor> listaTodos() {

        // Autor autor1 = new Autor(1, "Robert Martin", "USA", LocalDate.of(1983, 4, 4) );
        // Autor autor2 = new Autor(2, "Andrew Tanenbauhn", "USA", LocalDate.of(1992, 7, 22) );
        // Autor autor3 = new Autor(3, "Francisco Pimentel", "BR", LocalDate.of(1997, 9, 19) );

        // Autor[] autores = {autor1, autor2, autor3};
        
        // return Arrays.asList(autores);

        // return repository.listarTodos();
        return repository.findAll();
    }
    

    // @RequestMapping(value = "/autores/{id}", method=RequestMethod.GET)    
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Autor autorEncontrado = autorService.buscarOuFalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(autorEncontrado);
        
        // return autorService.buscar(id);
        // return autorService.buscarOuFalhar(id);
        // return autorService.getReferenceById(id);
        // return repository.buscar(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Autor>> listar() {
        // return autorService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        // return autorService.getReferenceById(id);
        // return repository.buscar(id);
    }


    
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Autor autor) {
        autor = autorService.salvar(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        autorService.deletar(id);
        return ResponseEntity.noContent().build();
        
        // return autorService.deletar(id);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable Long id,@RequestBody Autor autor) {
        autor.setId(id);
        autorService.atualizar(autor);
        return ResponseEntity.noContent().build();
    }
}