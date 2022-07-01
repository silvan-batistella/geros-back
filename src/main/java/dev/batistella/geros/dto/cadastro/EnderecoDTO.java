package dev.batistella.geros.dto.cadastro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.Validavel;
import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.entidade.Empresa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO extends Validavel {

    private String cep;

    private String rua;

    private String cidade;

    private String numero;

    private String complemento;

    private String infoAdicional;

    private int clienteId;

    @JsonIgnore
    private Cliente cliente;

    @JsonIgnore
    private Empresa empresa;
}
