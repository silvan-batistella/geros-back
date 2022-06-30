package dev.batistella.geros.dto.cadastro;

import dev.batistella.geros.dto.Validavel;
import dev.batistella.geros.enumerador.FormaContatoPreferencial;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class ClienteDTO extends Validavel {

    private String nome;

    private String email;

    private String cpfCnpj;

    private String telefone;

    private FormaContatoPreferencial formaContato;

    public String getCpfCnpjLimpo() {

        return Objects.requireNonNull(this.cpfCnpj).replaceAll("[^0-9]", "");
    }
}
