package dev.batistella.geros.enumerador;

import lombok.Getter;

@Getter
public enum EstadoAtividade {

    ATIVO("Ativo", "ATIVO"),
    INATIVO("Inativo", "INATIVO");

    private final String definicao;
    private final String exibicao;


    EstadoAtividade(String nomeExibicao, String nomeDefinicao) {

        this.exibicao = nomeExibicao;
        this.definicao = nomeDefinicao;
    }
}
