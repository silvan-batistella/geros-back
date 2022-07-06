package dev.batistella.geros.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.enumerador.EstadoOrdemServico;
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
@Table(name = TABELA_ORDEM_SERVICO)
public class OrdemServico {

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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = CLIENTE_FK, nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = EMPRESA_FK, nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = USUARIO_FK, nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = ORDEM_SERVICO_ESTADO, nullable = false)
    private EstadoOrdemServico estado;


    @Column(name = ORDEM_SERVICO_VALOR_ORCADO, nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    private double valorOrcado;

    @Column(name = ORDEM_SERVICO_VALOR_QUITADO, nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    private double valorQuitado;

    @Column(name = ORDEM_SERVICO_VALOR_CONTRATADO, nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    private double valorContratado;
}
