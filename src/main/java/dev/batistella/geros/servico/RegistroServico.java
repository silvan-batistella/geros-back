package dev.batistella.geros.servico;

import dev.batistella.geros.dto.cadastro.EmpresaDTO;
import dev.batistella.geros.dto.cadastro.RegistroDTO;
import dev.batistella.geros.dto.cadastro.UsuarioDTO;
import dev.batistella.geros.dto.generico.ErroDTO;
import dev.batistella.geros.dto.generico.LoginDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.entidade.Empresa;
import dev.batistella.geros.entidade.Login;
import dev.batistella.geros.entidade.Usuario;
import dev.batistella.geros.util.EmailUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RegistroServico {

    @Autowired
    LoginServico loginServico;

    @Autowired
    EmpresaServico empresaServico;

    @Autowired
    UsuarioServico usuarioServico;


    private boolean validarRegistro(RegistroDTO registro) {

        if (Objects.isNull(registro.getEmpresa())) {

            registro.addErro("Objeto Empresa não reconhecido", "empresa");
        } else {

            this.validarEmpresa(registro.getEmpresa());
        }

        if (Objects.isNull(registro.getUsuario())) {

            registro.addErro("Objeto Usuário não reconhecido", "empresa");
        } else {

            this.validarUsuario(registro.getUsuario());
        }

        if (Objects.isNull(registro.getLogin())) {

            registro.addErro("Objeto Login não reconhecido", "login");
        } else {

            this.validarLogin(registro.getLogin());
        }

        return validarAposConferencia(registro);
    }

    private boolean validarAposConferencia(RegistroDTO registro) {

        List<ErroDTO> erros = new ArrayList<>();

        if (Objects.nonNull(registro.getErros())) {

            erros.addAll(registro.getErros());
        }

        if (Objects.nonNull(registro.getEmpresa())) {
            if (Objects.nonNull(registro.getEmpresa().getErros())) {

                erros.addAll(registro.getEmpresa().getErros());
            }
        }

        if (Objects.nonNull(registro.getUsuario())) {
            if (Objects.nonNull(registro.getUsuario().getErros())) {

                erros.addAll(registro.getUsuario().getErros());
            }
        }

        if (Objects.nonNull(registro.getLogin())) {
            if (Objects.nonNull(registro.getLogin().getErros())) {

                erros.addAll(registro.getLogin().getErros());
            }
        }

        registro.setErros(erros);
        registro.determinarMensagemResposta();

        return  registro.getErros().isEmpty();
    }

    private void validarEmpresa(EmpresaDTO empresa) {

        if (StringUtils.isBlank(empresa.getNome())) {

            empresa.addErro("Nome da Empresa é Obrigatório.", "empresa_nome");
        }

        if (StringUtils.isBlank(empresa.getCpfCnpj())) {

            empresa.addErro("CPF/CNPJ da Empresa é Obrigatório.", "empresa_CPFCNPJ");
        } else {

            if (this.empresaServico.existeEmpresaComCPFCNPJ(empresa.getCpfCnpjLimpo())) {

                empresa.addErro("CPF/CNPJ da Empresa já existe na Base de dados.", "empresa_CPFCNPJ");
            }
        }
    }

    private void validarLogin(LoginDTO login) {

        if (StringUtils.isBlank(login.getEmail())) {

            login.addErro("E-mail é Obrigatório.", "login_email");
        } else {
            if (!EmailUtils.validarEmail(login.getEmail())) {

                login.addErro("E-mail inválido", "login_email");
            }
        }

        if (StringUtils.isBlank(login.getSenha())) {

            login.addErro("Senha é Obrigatória.", "login_senha");
        } else {

            if (login.getSenha().length() < 8) {

                login.addErro("Senha deve possuir ao menos 8 Caractéres.", "login_senha");
            }
        }
    }

    private void validarUsuario(UsuarioDTO usuario) {

        if (StringUtils.isBlank(usuario.getNome())) {

            usuario.addErro("Nome de Usuário é Obrigatório.", "usuario_nome");
        }

        if (StringUtils.isBlank(usuario.getCpfCnpj())) {

            usuario.addErro("CPF/CNPJ do Usuário é Obrigatório.", "usuario_CPFCNPJ");
        }
    }

    public ResponseEntity<RespostaDTO> registrar(RegistroDTO registro) {

        if (validarRegistro(registro)) {

            Empresa empresa = new Empresa(registro.getEmpresa());
            empresa = this.empresaServico.save(empresa);
            Usuario usuario = new Usuario(registro.getUsuario(), empresa);
            usuario = this.usuarioServico.save(usuario);
            Login login = new Login(registro.getLogin(), empresa, usuario);
            login = this.loginServico.save(login);

            return ResponseEntity.ok().body(
                    RespostaDTO.builder()
                    .sucesso(true)
                    .objeto(login)
                    .erros(registro.getErros())
                    .mensagem(registro.getMensagemResposta()).build());

        } else {

            return ResponseEntity.badRequest().body(
                    RespostaDTO.builder()
                            .sucesso(false)
                            .erros(registro.getErros())
                            .mensagem(registro.getMensagemResposta()).build());
        }
    }
}
