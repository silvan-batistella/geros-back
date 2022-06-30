package dev.batistella.geros.entidade;

import dev.batistella.geros.enumerador.EstadoAtividade;
import dev.batistella.geros.enumerador.FormaContatoPreferencial;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static dev.batistella.geros.constantes.Constantes.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = TABELA_CLIENTE)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false, updatable = false)
    private Integer id;

    @Column(name = NOME, nullable = false)
    private String nome;

    @Column(name = CLIENTE_EMAIL)
    private String email;

    @Column(name = CLIENTE_CPF_CNPJ, nullable = false)
    private String cpfCnpj;

    @Column(name = CLIENTE_TELEFONE)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = CLIENTE_EMPRESA_FK, nullable = false)
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    @Column(name = ESTADO, columnDefinition = "varchar(32) default 'ATIVO'")
    private EstadoAtividade estado;

    @Enumerated(EnumType.STRING)
    private FormaContatoPreferencial formaContatoPreferencial;
}
