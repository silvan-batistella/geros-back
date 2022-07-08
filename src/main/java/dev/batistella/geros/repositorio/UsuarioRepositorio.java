package dev.batistella.geros.repositorio;

import dev.batistella.geros.entidade.Empresa;
import dev.batistella.geros.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByIdAndEmpresa(Integer id, Empresa empresa);
}
