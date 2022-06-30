package dev.batistella.geros.repositorio;

import dev.batistella.geros.entidade.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Integer> {

    boolean existsByCpfCnpj(String cpfCnpj);
}
