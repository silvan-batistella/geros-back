package dev.batistella.geros.servico;

import dev.batistella.geros.controller.UsuarioLogadoController;
import dev.batistella.geros.dto.cadastro.ClienteDTO;
import dev.batistella.geros.dto.generico.ErroDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.repositorio.ClienteRepositorio;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteServico extends UsuarioLogadoController {

    @Autowired
    ClienteRepositorio clienteRepo;

    private boolean validarCliente(ClienteDTO cliente) {

        if (Objects.nonNull(cliente)) {
            if (StringUtils.isBlank(cliente.getNome())) {

                cliente.addErro("Nome do Cliente é Obrigatório.", "cliente_nome");
            }

            if (StringUtils.isBlank(cliente.getCpfCnpj())) {

                cliente.addErro("CPF/CNPJ do Cliente é Obrigatório.", "cliente_CPFCNPJ");
            } else {

                if (this.existeClienteComCPFCNPJ(cliente.getCpfCnpjLimpo())) {

                    cliente.addErro("CPF/CNPJ do Cliente já existe na Base de dados.", "cliente_CPFCNPJ");
                }
            }
        }


        return validarAposConferencia(cliente);
    }

    private boolean validarAposConferencia(ClienteDTO cliente) {

        List<ErroDTO> erros = new ArrayList<>();

        if (Objects.isNull(cliente)) {

            erros.add(ErroDTO.builder().mensagem("Objeto Cliente não reconhecido").campo("cliente").build());
        }

        if (Objects.nonNull(cliente.getErros())) {

            erros.addAll(cliente.getErros());
        }

        cliente.setErros(erros);
        cliente.setMensagemResposta(determinarMensagemResposta(cliente));

        return  cliente.getErros().isEmpty();
    }

    private String determinarMensagemResposta(ClienteDTO cliente) {

        StringBuilder sb;
        sb = new StringBuilder();
        if (cliente.getErros().isEmpty()) {

            sb.append("Tudo certo ao criar Empresa.");
        } else {
            if (cliente.getErros().size() == 1) {

                sb.append("Um problema para resolver antes de realizar o cadastro.");
            } else {

                sb.append("Alguns problemas para resolver antes de realizar o cadastro.");
            }
        }

        return sb.toString();
    }

    public Boolean existeClienteComCPFCNPJ(String cpfCnpj) {

        return this.clienteRepo.existsByCpfCnpjAndEmpresa(cpfCnpj, getEmpresaLogado());
    }

    public ResponseEntity<RespostaDTO> registrar(ClienteDTO clientedto) {

        if (validarCliente(clientedto)) {

            Cliente cliente = new Cliente(clientedto);

            cliente = this.save(cliente);

            return ResponseEntity.ok().body(RespostaDTO.builder()
                    .sucesso(true)
                    .objeto(cliente)
                    .erros(clientedto.getErros())
                    .mensagem(clientedto.getMensagemResposta()).build());

        } else {

            return ResponseEntity.badRequest().body(
                    RespostaDTO.builder()
                            .sucesso(false)
                            .erros(clientedto.getErros())
                            .mensagem(clientedto.getMensagemResposta()).build());
        }
    }

    private Cliente save(Cliente cliente) {

        cliente.setCpfCnpj(cliente.getCpfCnpj().replaceAll("[^0-9]",""));
        cliente.setEmpresa(getEmpresaLogado());

        return  this.clienteRepo.save(cliente);
    }
}
