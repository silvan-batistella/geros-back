package dev.batistella.geros.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.cadastro.EmpresaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;

import static dev.batistella.geros.constantes.Constantes.*;
import static dev.batistella.geros.constantes.Constantes.NOME;

@Data
@Entity
@NoArgsConstructor
@Table(name = TABELA_EMPRESA)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, updatable = false)
    private Integer id;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = DATA_CRIACAO, nullable = false, updatable = false)
    private Timestamp dataCriacao;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = DATA_ATUALIZACAO, nullable = false)
    private Timestamp dataAtualizacao;

    @Column(name = NOME, nullable = false)
    private String nome;

    @Column(name = EMPRESA_CPF_CNPJ, nullable = false)
    private String cpfCnpj;

    public Empresa(EmpresaDTO empresa) {

        this.setNome(empresa.getNome());
        this.setCpfCnpj(empresa.getCpfCnpjLimpo());
    }
}
