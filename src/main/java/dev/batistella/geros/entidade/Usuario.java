package dev.batistella.geros.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.batistella.geros.dto.cadastro.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static dev.batistella.geros.constantes.Constantes.*;
import static dev.batistella.geros.constantes.Constantes.NOME;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABELA_USUARIO)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, updatable = false)
    private Integer id;

    @Column(name = NOME, nullable = false)
    private String nome;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = USUARIO_EMPRESA_FK, nullable = false)
    private Empresa empresa;

    @Column(name = USUARIO_CPF_CNPJ, nullable = false)
    private String cpfCnpj;

    public Usuario(UsuarioDTO usuario, Empresa empresa) {

        this.setEmpresa(empresa);
        this.setNome(usuario.getNome());
        this.setCpfCnpj(usuario.getCpfCnpjLimpo());
    }
}
