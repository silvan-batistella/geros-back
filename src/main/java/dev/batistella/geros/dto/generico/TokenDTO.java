package dev.batistella.geros.dto.generico;

import dev.batistella.geros.interfaces.IRespondivel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO implements IRespondivel {
	
	private String tipo;

	private String token;
}
