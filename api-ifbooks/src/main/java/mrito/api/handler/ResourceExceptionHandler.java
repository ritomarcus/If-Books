package mrito.api.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import mrito.api.exceptions.AutorNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(AutorNaoEncontradoException.class)
    public ResponseEntity<Void> handleAutorNaoEncontrato(AutorNaoEncontradoException e, HttpServletRequest request) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleDemaisExceptions(Exception e, HttpServletRequest request) {
        String detail = "Favor informar o problema acessando: www.problem.com.br";
        detail += "Informar o código do erro: ";
        detail += e.getMessage();
        return ResponseEntity.badRequest().body(detail);
    }
}
