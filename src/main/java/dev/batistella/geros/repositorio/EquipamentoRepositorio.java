package dev.batistella.geros.repositorio;

import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.entidade.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Integer> {

    Optional<Equipamento> findByClienteAndMarcaAndModeloAndNumeroSerie(Cliente cliente, String marca, String modelo, String numeroSerie);
}
