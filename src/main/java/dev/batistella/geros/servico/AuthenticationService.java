package dev.batistella.geros.servico;

import java.util.Optional;

import dev.batistella.geros.entidade.Login;
import dev.batistella.geros.repositorio.LoginRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	LoginRepositorio usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Login> optional = usuarioRepo.findByEmail(username);
		
		if(optional.isPresent()) {

			return optional.get();
		}
		
		throw new UsernameNotFoundException("USUARIO NAO ENCONTRADO!");
	}	

}
