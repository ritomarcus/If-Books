package mrito.api.exceptions;

public class LivroNaoEncontradoException extends NegocioException {
    private static final long serialVersionUID = 1L;
    
    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

