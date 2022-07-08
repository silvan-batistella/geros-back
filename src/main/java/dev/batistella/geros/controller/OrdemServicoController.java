package dev.batistella.geros.controller;

import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.servico.OrdemServicoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.batistella.geros.constantes.Constantes.ENDPOINT_ORDEM_SERVICO;

@RestController
@RequestMapping(value = ENDPOINT_ORDEM_SERVICO)
public class OrdemServicoController {

    @Autowired
    OrdemServicoServico ordemServicoServico;

    @GetMapping(value = "/iniciar-nova-os/{id-cliente}")
    public ResponseEntity<RespostaDTO> getOrdemServico(@PathVariable("id-cliente") Integer idCliente) {

        return this.ordemServicoServico.gerarNovaOrdemServico(idCliente);
    }
}
