package dev.batistella.geros.servico;

import java.util.Date;

import com.google.gson.Gson;
import dev.batistella.geros.entidade.Login;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	String expiration;

	@Value("${jwt.secret}")
	String secret;

	public String generateToken(Login login) {

		Date now = new Date();
		Date exp = new Date(now.getTime() + Long.parseLong(expiration));

		return Jwts.builder()
				.setIssuer("GEROS")
				.setExpiration(exp)
				.setIssuedAt(new Date())
				.setSubject(new Gson().toJson(login))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {

		try {

			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public Integer getTokenId(String token) {

		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();


		JSONObject obj = new JSONObject(body.getSubject());
		if (obj.has("id")) {

			return obj.optInt("id");
		}
		return 0;
	}

}
