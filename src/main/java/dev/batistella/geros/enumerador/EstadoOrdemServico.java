package dev.batistella.geros.enumerador;

import lombok.Getter;

@Getter
public enum EstadoOrdemServico {

    INCLUIDO(0, "Incluído", "INCLUIDO"),
    AGUARDANDO_ORCAMENTO(1, "Aguardando Orçamento", "AGUARDANDO_ORCAMENTO"),
    ORCAMENTO_APROVADO(2, "Orçamento Aprovado", "ORCAMENTO_APROVADO"),
    AGUARDANDO_PECAS(3, "Aguardando Peças", "AGUARDANDO_PECAS"),
    FILA_REPARO(4, "Aguardando Reparo", "FILA_REPARO"),
    EM_REPARO(5, "Aguardando Reparo", "EM_REPARO"),
    ORCAMENTO_REPROVADO(6, "Orçamento Reprovado", "ORCAMENTO_REPROVADO"),
    PRONTO_FINALIZADO(7, "Pronto / Aguardando Retirada", "PRONTO_FINALIZADO"),
    PRONTO_DEVOLUCAO(7, "Pronto / Pronto para Devolução", "PRONTO_DEVOLUCAO"),
    FINALIZADO(7, "Finalizado", "FINALIZADO");

    private final int ordem;
    private final String definicao;
    private final String exibicao;

    EstadoOrdemServico(int ordem, String nomeExibicao, String nomeDefinicao) {

        this.ordem = ordem;
        this.exibicao = nomeExibicao;
        this.definicao = nomeDefinicao;
    }
}
