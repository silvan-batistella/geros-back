package dev.batistella.geros.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.cadastro.EnderecoDTO;
import dev.batistella.geros.interfaces.IRespondivel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static dev.batistella.geros.constantes.Constantes.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = TABELA_ENDERECO)
public class Endereco implements IRespondivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, updatable = false)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = ENDERECO_EMPRESA_FK, nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = ENDERECO_CLIENTE_FK, nullable = false)
    private Cliente cliente;

    @Column(name = ENDERECO_CEP, nullable = false)
    private String cep;

    @Column(name = ENDERECO_RUA, nullable = false)
    private String rua;

    @Column(name = ENDERECO_CIDADE, nullable = false)
    private String cidade;

    @Column(name = ENDERECO_NUMERO, nullable = false)
    private String numero;

    @Column(name = ENDERECO_COMPLEMENTO)
    private String complemento;

    @Column(name = ENDERECO_INFOS_COMPLEMENTARES)
    private String infosComplementares;

    public Endereco(EnderecoDTO endereco) {

        this.setEmpresa(endereco.getEmpresa());
        this.setCliente(endereco.getCliente());

        this.setCep(endereco.getCep());
        this.setRua(endereco.getRua());
        this.setCidade(endereco.getCidade());
        this.setNumero(endereco.getNumero());
        this.setComplemento(endereco.getComplemento());
        this.setInfosComplementares(endereco.getInfoAdicional());
    }

}
