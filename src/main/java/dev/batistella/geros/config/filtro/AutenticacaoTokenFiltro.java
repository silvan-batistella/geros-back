package dev.batistella.geros.config.filtro;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.batistella.geros.entidade.Login;
import dev.batistella.geros.repositorio.LoginRepositorio;
import dev.batistella.geros.servico.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class AutenticacaoTokenFiltro extends OncePerRequestFilter {
	
	final TokenService tokenService;
	final LoginRepositorio usuarioRepo;
	
	public AutenticacaoTokenFiltro(TokenService tokenService, LoginRepositorio repository) {

		this.usuarioRepo = repository;
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String tokenFromHeader = getTokenFromHeader(request);
		boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
		if(tokenValid) {
			this.authenticate(tokenFromHeader);
		}

		filterChain.doFilter(request, response);
	}

	private void authenticate(String tokenFromHeader) {
		Integer id = tokenService.getTokenId(tokenFromHeader);
		
		Optional<Login> optionalLogin = usuarioRepo.findById(id);
		
		if(optionalLogin.isPresent()) {

			Login login = optionalLogin.get();
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login, null, login.getPermissoes());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	private String getTokenFromHeader(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (StringUtils.isBlank(token)) {

			return null;
		} else {
			if (token.toUpperCase().startsWith("BEARER")) {

				return token.substring(7);
			} else {

				return null;
			}
		}
	}
}
