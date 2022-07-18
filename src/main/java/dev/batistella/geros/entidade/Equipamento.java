package dev.batistella.geros.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.cadastro.EquipamentoDTO;
import dev.batistella.geros.interfaces.IRespondivel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

import static dev.batistella.geros.constantes.Constantes.*;
import static dev.batistella.geros.constantes.Constantes.DATA_ATUALIZACAO;

@Data
@Entity
@NoArgsConstructor
@Table(name = TABELA_EQUIPAMENTO)
public class Equipamento implements IRespondivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, updatable = false)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = EMPRESA_FK, nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = CLIENTE_FK, nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = USUARIO_FK, nullable = false)
    private Usuario usuario;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = DATA_CRIACAO, nullable = false, updatable = false)
    private Timestamp dataCriacao;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = DATA_ATUALIZACAO, nullable = false)
    private Timestamp dataAtualizacao;

    @Column(name = EQUIPAMENTO_MARCA, nullable = false)
    private String marca;

    @Column(name = EQUIPAMENTO_MODELO, nullable = false)
    private String modelo;

    @Column(name = EQUIPAMENTO_NUMERO_SERIE, nullable = false, updatable = false)
    private String numeroSerie;

    @Column(name = EQUIPAMENTO_OBS)
    private String obs;

    public Equipamento(EquipamentoDTO equipamentoDto) {

        this.setEmpresa(equipamentoDto.getEmpresa());
        this.setCliente(equipamentoDto.getCliente());
        this.setUsuario(equipamentoDto.getUsuario());

        this.setObs(equipamentoDto.getObs());
        this.setMarca(equipamentoDto.getMarca());
        this.setModelo(equipamentoDto.getModelo());
        this.setNumeroSerie(equipamentoDto.getNumeroSerie());
    }
}
