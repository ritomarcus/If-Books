/*
 * @Author: mrito
 */


package mrito.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name= "autor")
public class Autor {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String nome;

    @Column(length = 20)
    private String nacionalidade;

    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    
    
    // public Autor() {
    // }
    
    // public Autor(Long id, String nome, String nacionalidade, LocalDate dataNascimento) {
    //     this.id = id;
    //     this.nome = nome;
    //     this.nacionalidade = nacionalidade;
    //     this.dataNascimento = dataNascimento;
    // }
    /*
     * Getter and Setters
     */
    
    

    public Long getId( ) {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getNome( ) {
        return nome;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }

    public String getNacionalidade( ) {
        return nacionalidade;
    }

    public void setNacionalidade( String nacionalidade ) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDate getDataNascimento( ) {
        return dataNascimento;
    }

    public void setDataNascimento( LocalDate dataNascimento ) {
        this.dataNascimento = dataNascimento;
    }

    
    /*
    * Metodos de aprimoramento de persistencias
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
        Autor other = (Autor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
