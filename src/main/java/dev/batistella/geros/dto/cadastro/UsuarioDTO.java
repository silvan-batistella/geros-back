package dev.batistella.geros.dto.cadastro;

import dev.batistella.geros.dto.Validavel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UsuarioDTO extends Validavel {

    private String nome;
    private String cpfCnpj;

    public String getCpfCnpjLimpo() {

        return Objects.requireNonNull(this.cpfCnpj).replaceAll("[^0-9]", "");
    }
}
