package dev.batistella.geros.controller;

import dev.batistella.geros.entidade.Login;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logado")
public class LoggedController extends UsuarioLogadoController {

    @GetMapping
    public Login getLogin() {

        return this.getUsuarioLogado();
    }

}
