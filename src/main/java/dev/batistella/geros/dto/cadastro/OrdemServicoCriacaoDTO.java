package dev.batistella.geros.dto.cadastro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.Validavel;
import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.entidade.Empresa;
import dev.batistella.geros.entidade.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdemServicoCriacaoDTO extends Validavel {

    private Integer id;

    @JsonIgnore
    private Cliente cliente;

    @JsonIgnore
    private Empresa empresa;

    @JsonIgnore
    private Usuario usuario;

    public OrdemServicoCriacaoDTO(Integer id) {

        this.id = id;
    }
}
