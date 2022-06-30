package dev.batistella.geros.controller;

import dev.batistella.geros.entidade.Login;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class UsuarioLogadoController {

    @Transactional
    public Login getUsuarioLogado() {

        return (Login) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
