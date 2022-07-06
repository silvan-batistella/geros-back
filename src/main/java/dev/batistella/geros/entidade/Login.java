package dev.batistella.geros.entidade;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.generico.LoginDTO;
import dev.batistella.geros.interfaces.IRespondivel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static dev.batistella.geros.constantes.Constantes.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABELA_LOGIN)
public class Login implements UserDetails, IRespondivel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID, nullable = false, updatable = false)
	private Integer id;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = DATA_CRIACAO, nullable = false, updatable = false)
	private Timestamp dataCriacao;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name = DATA_ATUALIZACAO, nullable = false)
	private Timestamp dataAtualizacao;

	@Column(name = LOGIN_EMAIL, nullable = false, unique = true)
	private String email;

	@JsonIgnore
	@Column(name = LOGIN_SENHA, nullable = false)
	private String senha;

	@ManyToOne
	@JoinColumn(name = EMPRESA_FK, nullable = false)
	private Empresa empresa;

	@OneToOne
	@JoinColumn(name = USUARIO_FK, referencedColumnName = ID)
	private Usuario usuario;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Permissao> permissoes;

    public Login(LoginDTO login, Empresa empresa, Usuario usuario) {

		this.empresa = empresa;
		this.usuario = usuario;
		this.email = login.getEmail();
		this.senha = login.getSenha();
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
