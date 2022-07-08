package dev.batistella.geros.repositorio;

import dev.batistella.geros.entidade.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepositorio extends JpaRepository<OrdemServico, Integer> {
}
