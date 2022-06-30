package dev.batistella.geros.repositorio;

import java.util.Optional;

import dev.batistella.geros.entidade.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepositorio extends CrudRepository<Login, Integer>{
	
	Optional<Login> findByEmail(String email);

}
