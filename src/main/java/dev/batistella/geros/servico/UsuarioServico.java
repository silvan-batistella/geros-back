package dev.batistella.geros.servico;

import dev.batistella.geros.entidade.Usuario;
import dev.batistella.geros.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServico {

    @Autowired
    UsuarioRepositorio usuarioRepo;

    public Usuario save(Usuario usuario) {

        return this.usuarioRepo.save(usuario);
    }
}
