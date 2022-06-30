package dev.batistella.geros.entidade;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static dev.batistella.geros.constantes.Constantes.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABELA_PERMISSAO)
public class Permissao implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID, nullable = false, updatable = false)
	private Integer id;

	@Column(name = NOME, nullable = false, updatable = false, unique = true)
	private String nome;

	@Override
	public String getAuthority() {

		return this.nome;
	}
}
