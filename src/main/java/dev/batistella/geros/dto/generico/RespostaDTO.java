package dev.batistella.geros.dto.generico;

import dev.batistella.geros.interfaces.IRespondivel;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class RespostaDTO {

    private boolean sucesso;

    private String mensagem;

    private List<ErroDTO> erros;

    private IRespondivel objeto;

    public RespostaDTO(boolean sucesso, String mensagem, List<ErroDTO> erros, IRespondivel objeto) {

        this.erros = erros;
        this.objeto = objeto;
        this.sucesso = sucesso;
        this.mensagem = mensagem;

        if (Objects.isNull(this.erros)) {

            this.erros = new ArrayList<>();
        }
    }
}
