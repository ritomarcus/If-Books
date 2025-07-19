package mrito.api.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mrito.api.exceptions.AutorNaoEncontradoException;
import mrito.api.exceptions.EntidadeNomeJaExisteException;
import mrito.api.model.Autor;
import mrito.api.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor buscar(Long id) {

        Optional<Autor> autorOptional = autorRepository.findById(id);
        
        return autorOptional.get();
    }

    
    public Autor buscarOuFalhar(Long id) {   
        return  autorRepository.findById(id).orElseThrow(() -> new AutorNaoEncontradoException("Autor Inexistente"));
    }
    // public ResponseEntity<?> buscarOuFalhar(Long id) {    
        // Autor autorEncontrado;
        // try {
        //     Optional<Autor> autorOptional = autorRepository.findById(id);
        //     autorEncontrado = autorOptional.get();
        // } catch (NoSuchElementException e) {
        //     // TODO: handle exception
        //     return ResponseEntity.notFound().build();
        // } catch(Exception e) {
        //     e.printStackTrace();
        //     return ResponseEntity.badRequest().build();
        // }

        // return ResponseEntity.status(HttpStatus.OK).body(autorEncontrado);
    // }

    public Autor salvar(Autor autor) {
        // To Do - Fazer Checagem de 2 nomes iguais !!! CuiDado!!! Não gerar 2 iguais
        var autorOptional = autorRepository.findByNomeIgnoreCase(autor.getNome());

        if( autorOptional.isPresent() && !autorOptional.get().equals(autor)) {
            throw new EntidadeNomeJaExisteException(autor.getNome(), autorOptional.get().getId());
        }
        
        return autorRepository.saveAndFlush(autor);
        // return autorRepository.save(autor);
    }

    public void deletar(Long id) {
        buscarOuFalhar(id);
        autorRepository.deleteById(id);
    }

    // public ResponseEntity<Void> deletar(Long id) {
    //     try {
    //         autorRepository.deleteById(id);
    //     } catch (EmptyResultDataAccessException e) {
    //         // TODO: handle exception
    //         return ResponseEntity.notFound().build();
    //     } catch(Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().build();
    //     }

    //     return ResponseEntity.noContent().build();
    // }
    
    public void atualizar(Autor autor) {
        buscarOuFalhar(autor.getId());
        salvar(autor);
    }
}
