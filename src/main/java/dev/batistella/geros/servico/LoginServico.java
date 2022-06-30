package dev.batistella.geros.servico;

import dev.batistella.geros.entidade.Login;
import dev.batistella.geros.repositorio.LoginRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServico {

    @Autowired
    LoginRepositorio loginRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public Login save(Login login) {

        login.setSenha(bCryptPasswordEncoder.encode(login.getSenha()));

        return this.loginRepo.save(login);
    }

    public Optional<Login> findByEmail(String email) {

        return this.loginRepo.findByEmail(email);
    }
}
