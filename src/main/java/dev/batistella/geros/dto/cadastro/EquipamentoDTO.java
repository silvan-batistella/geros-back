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
public class EquipamentoDTO extends Validavel {

    private String marca;

    private String modelo;

    private Integer clienteId;

    private String numeroSerie;

    private String obs;

    @JsonIgnore
    private Usuario usuario;

    @JsonIgnore
    private Cliente cliente;

    @JsonIgnore
    private Empresa empresa;

}
