package dev.batistella.geros.controller;

import dev.batistella.geros.dto.cadastro.EnderecoDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.servico.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.batistella.geros.constantes.Constantes.ENDPOINT_ENDERECO;

@RestController
@RequestMapping(value = ENDPOINT_ENDERECO)
public class EnderecoController {

    @Autowired
    EnderecoServico enderecoServico;

    @PostMapping
    public ResponseEntity<RespostaDTO> criarEndereco(@RequestBody EnderecoDTO endereco) {

        return this.enderecoServico.criarEndereco(endereco);
    }
}
