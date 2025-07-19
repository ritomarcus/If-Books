package mrito.api.exceptions;

public class EntidadeNomeJaExisteException extends NegocioException {
    private static final long serialVersionUID = 1L;
    
    public EntidadeNomeJaExisteException(String mensagem) {
        super(mensagem);
    }

    public EntidadeNomeJaExisteException(String conteudoNome, Long id) {
        this(String.format("Atenção!! O nome: %s, já existe no cadastro com o código: %d",conteudoNome, id));
    }
}