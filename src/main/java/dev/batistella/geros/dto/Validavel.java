package dev.batistella.geros.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.generico.ErroDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class Validavel {

    @JsonIgnore
    private List<ErroDTO> erros;

    private String mensagemResposta;

    public void addErro(String mensagem, String campo) {

        if (Objects.isNull(this.erros)) {
            this.erros = new ArrayList<>();
        }

        this.erros.add(ErroDTO.builder().mensagem(mensagem).campo(campo).build());
    }

    public void setMensagemResposta(String mensagem) {

        this.mensagemResposta = mensagem.trim();
    }
}
