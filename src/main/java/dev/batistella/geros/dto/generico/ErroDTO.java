package dev.batistella.geros.dto.generico;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErroDTO {

    private String campo;

    private String mensagem;
}
