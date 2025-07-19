/*
 * @author: mrito
 * representação de livro do sistema
 * Criação da classe
 */

package mrito.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75)
    @NotBlank(message = "Nome do livro é obrigatório")
    private String nome;

    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_publicacao")
    // Comentario explicando o que a gente ta conversando aqui
    @NotNull(message = "Data de publicação do livro é obrigatória")
    private LocalDate dataPublicacao;

    @Column(length = 70)
    @NotBlank(message = "Editora tem que mostrar o caneco")
    private String editora;

    @Column(length = 512)
    @NotBlank(message = "O resumo precisa conter o touch a touch com o caneco")
    private String resumo;

    // Anotacoes hibernate que fazem o relacionamento livro c/ autor
    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonInclude(Include.NON_NULL)
    @NotNull(message = "Autor necessario")
    private Autor autor;

    /*
     * Getters and setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /*
     * Metodos de aprimoramento de persistencia
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Livro other = (Livro) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}