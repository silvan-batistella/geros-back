package dev.batistella.geros.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.cadastro.ClienteDTO;
import dev.batistella.geros.enumerador.EstadoAtividade;
import dev.batistella.geros.enumerador.FormaContatoPreferencial;
import dev.batistella.geros.interfaces.IRespondivel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;


import java.sql.Timestamp;

import static dev.batistella.geros.constantes.Constantes.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = TABELA_CLIENTE)
public class Cliente implements IRespondivel {

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

    @Column(name = CLIENTE_EMAIL)
    private String email;

    @Column(name = CLIENTE_CPF_CNPJ, nullable = false)
    private String cpfCnpj;

    @Column(name = CLIENTE_TELEFONE)
    private String telefone;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = CLIENTE_EMPRESA_FK, nullable = false)
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = ESTADO, columnDefinition = "varchar(32) default 'ATIVO'")
    private EstadoAtividade estado;

    @Enumerated(EnumType.STRING)
    private FormaContatoPreferencial formaContatoPreferencial;

    public Cliente(ClienteDTO cliente) {

        this.setEstado(EstadoAtividade.ATIVO);

        this.setNome(cliente.getNome());
        this.setEmail(cliente.getEmail());
        this.setCpfCnpj(cliente.getCpfCnpj());
        this.setTelefone(cliente.getTelefone());
        this.setFormaContatoPreferencial(cliente.getFormaContato());
    }
}
