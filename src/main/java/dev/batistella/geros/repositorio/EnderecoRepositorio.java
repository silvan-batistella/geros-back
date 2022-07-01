package dev.batistella.geros.repositorio;

import dev.batistella.geros.entidade.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Integer> {
}
