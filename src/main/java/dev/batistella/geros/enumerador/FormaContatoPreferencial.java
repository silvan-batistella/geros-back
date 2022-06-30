package dev.batistella.geros.enumerador;

import lombok.Getter;

@Getter
public enum FormaContatoPreferencial {

    SMS("SMS", "SMS"),
    EMAIL("E-mail", "EMAIL"),
    WHATS("WhatsApp", "WHATS"),
    LIGACAO("Ligação", "LIGACAO");


    private final String definicao;
    private final String exibicao;


        FormaContatoPreferencial(String nomeExibicao, String nomeDefinicao) {

        this.exibicao = nomeExibicao;
        this.definicao = nomeDefinicao;
    }
}
