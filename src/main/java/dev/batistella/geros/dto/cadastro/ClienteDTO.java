package dev.batistella.geros.dto.cadastro;

import dev.batistella.geros.dto.Validavel;
import lombok.Data;


@Data
public class ClienteDTO extends Validavel {

    private String nome;

    private String email;

    private String cpfCnpj;

    private String telefone;
}
