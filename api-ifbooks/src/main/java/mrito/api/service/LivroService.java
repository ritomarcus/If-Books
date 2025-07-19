package mrito.api.service;

//import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.dao.EmptyResultDataAccessException;
// import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mrito.api.exceptions.LivroNaoEncontradoException;
import mrito.api.exceptions.EntidadeNomeJaExisteException;
import mrito.api.model.Livro;
import mrito.api.repository.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro buscar(Long id) {

        Optional<Livro> livroOptional = livroRepository.findById(id);
        return livroOptional.get();
    }

    public Livro buscarOuFalhar(Long id) { // public ResponseEntity<?> ...

        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro inexistente"));
    }

    public Livro salvar(Livro livro) {
        // To Do - Fazer checagem de 2 nomes iguais !!! Cuidado!!! Não gerar 2 iguais
        var livroOptional = livroRepository.findByNomeIgnoreCase(livro.getNome());

        if (livroOptional.isPresent() && !livroOptional.get().equals(livro)) {
            throw new EntidadeNomeJaExisteException(livro.getNome(), livroOptional.get().getId());
        }

        return livroRepository.saveAndFlush(livro);
    }

    public void deletar(Long id) {
        buscarOuFalhar(id);
        livroRepository.deleteById(id);
    }

    public void atualizar(Livro livro) {
        livroRepository.saveAndFlush(livro);
    }

}
