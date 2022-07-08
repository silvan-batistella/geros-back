package dev.batistella.geros.servico;

import dev.batistella.geros.controller.UsuarioLogadoController;
import dev.batistella.geros.entidade.Usuario;
import dev.batistella.geros.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class UsuarioServico extends UsuarioLogadoController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuarioRepositorio usuarioRepo;

    public Usuario save(Usuario usuario) {

        return this.usuarioRepo.save(usuario);
    }

    public Optional<Usuario> findById(Integer id) {

        return this.usuarioRepo.findByIdAndEmpresa(id, getEmpresaLogado());
    }

    @Transactional
    public Usuario sincronizar(Usuario usuario) {

        return this.entityManager.merge(usuario);
    }
}
