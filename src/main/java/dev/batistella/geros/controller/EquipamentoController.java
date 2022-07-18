package dev.batistella.geros.controller;

import dev.batistella.geros.dto.cadastro.EquipamentoDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.servico.EquipamentoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.batistella.geros.constantes.Constantes.ENDPOINT_EQUIPAMENTO;

@RestController
@RequestMapping(value = ENDPOINT_EQUIPAMENTO)
public class EquipamentoController {

    @Autowired
    EquipamentoServico equipamentoServico;

    @PostMapping
    public ResponseEntity<RespostaDTO> criarEquipamento(@RequestBody EquipamentoDTO equipamentoDTO) {

        return this.equipamentoServico.criarEquipamento(equipamentoDTO);
    }
}
