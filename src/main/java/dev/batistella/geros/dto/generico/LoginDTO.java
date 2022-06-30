package dev.batistella.geros.dto.generico;

import dev.batistella.geros.dto.Validavel;
import lombok.Data;

@Data
public class LoginDTO extends Validavel {
	
	private String senha;

	private String email;
}
