package dev.batistella.geros.dto.cadastro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.Validavel;
import dev.batistella.geros.dto.generico.ErroDTO;
import dev.batistella.geros.dto.generico.LoginDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistroDTO extends Validavel {

    private LoginDTO login;

    private EmpresaDTO empresa;

    private UsuarioDTO usuario;

    @JsonIgnore
    private List<ErroDTO> erros;
}
