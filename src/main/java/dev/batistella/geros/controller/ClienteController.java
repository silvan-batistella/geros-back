package dev.batistella.geros.controller;

import dev.batistella.geros.dto.cadastro.ClienteDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.servico.ClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.batistella.geros.constantes.Constantes.ENDPOINT_CLIENTE;

@RestController
@RequestMapping(value = ENDPOINT_CLIENTE)
public class ClienteController {

    @Autowired
    ClienteServico clienteServico;

    @PostMapping
    public ResponseEntity<RespostaDTO> criarCliente(@RequestBody ClienteDTO cliente) {

        return clienteServico.registrar(cliente);
    }
}
