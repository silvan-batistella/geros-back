package dev.batistella.geros.repositorio;

import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.entidade.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    Boolean existsByCpfCnpjAndEmpresa(String cpfCnpj, Empresa empresaLogado);

    Optional<Cliente> findByIdAndEmpresa(int id, Empresa empresa);
}
