package dev.batistella.geros.controller;

import dev.batistella.geros.dto.cadastro.ClienteDTO;
import dev.batistella.geros.interfaces.IRespondivel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.batistella.geros.constantes.Constantes.ENDPOINT_CLIENTE;

@RestController
@RequestMapping(value = ENDPOINT_CLIENTE)
public class ClienteController {

    @PostMapping
    public ResponseEntity<IRespondivel> criarCliente(@RequestBody ClienteDTO cliente) {

        return ResponseEntity.unprocessableEntity().build();
    }
}
