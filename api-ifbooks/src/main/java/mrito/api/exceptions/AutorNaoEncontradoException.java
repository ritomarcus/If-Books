package mrito.api.exceptions;

public class AutorNaoEncontradoException extends NegocioException {
    private static final long serialVersionUID = 1L;
    public AutorNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
