package dev.batistella.geros.controller;

import dev.batistella.geros.dto.cadastro.RegistroDTO;
import dev.batistella.geros.dto.generico.LoginDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.dto.generico.TokenDTO;
import dev.batistella.geros.entidade.Login;
import dev.batistella.geros.interfaces.IRespondivel;
import dev.batistella.geros.servico.LoginServico;
import dev.batistella.geros.servico.RegistroServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.batistella.geros.servico.TokenService;

import java.util.Optional;

import static dev.batistella.geros.constantes.Constantes.ENDPOINT_AUTENTICACAO;
import static dev.batistella.geros.constantes.Constantes.ENDPOINT_REGISTRO;

@RestController
public class AutenticacaoController {
	
	@Autowired
	TokenService tokenService;

	@Autowired
	LoginServico loginServico;

	@Autowired
	RegistroServico registroServico;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;


	@PostMapping(value =  ENDPOINT_AUTENTICACAO)
	public ResponseEntity<IRespondivel> auth(@RequestBody @Validated LoginDTO loginDTO){

		String token;
		ResponseEntity<IRespondivel> response = ResponseEntity.status(401).build();

		try {

			Optional<Login> optLogin = this.loginServico.findByEmail(loginDTO.getEmail());
			if (optLogin.isPresent()) {

				Login login = optLogin.get();
				if (bCryptPasswordEncoder.matches(loginDTO.getSenha(), login.getPassword())) {

			 		token = tokenService.generateToken(login);

					 response = ResponseEntity.ok().body(TokenDTO.builder().tipo("Bearer").token(token).build());
				}
			}
		} catch (Exception e) {

			return  ResponseEntity.status(403).build();
		}
		
		return response;
	}

	@PostMapping(value = ENDPOINT_REGISTRO)
	public ResponseEntity<RespostaDTO> criarEmpresa(@RequestBody RegistroDTO registro) {

		return this.registroServico.registrar(registro);
	}
}
