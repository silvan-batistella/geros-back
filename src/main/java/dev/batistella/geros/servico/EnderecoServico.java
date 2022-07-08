package dev.batistella.geros.servico;

import dev.batistella.geros.controller.UsuarioLogadoController;
import dev.batistella.geros.dto.cadastro.EnderecoDTO;
import dev.batistella.geros.dto.generico.ErroDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.entidade.Endereco;
import dev.batistella.geros.repositorio.EnderecoRepositorio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnderecoServico extends UsuarioLogadoController {

    @Autowired
    ClienteServico clienteServico;

    @Autowired
    EnderecoRepositorio enderecoRepo;
    public ResponseEntity<RespostaDTO> criarEndereco(EnderecoDTO enderecoDto) {

        if (validarEndereco(enderecoDto)) {

            return ResponseEntity.ok().body(
                    RespostaDTO.builder()
                    .sucesso(true)
                    .erros(enderecoDto.getErros())
                    .mensagem(enderecoDto.getMensagemResposta())
                    .objeto(this.enderecoRepo.save(new Endereco(enderecoDto))).build());
        } else {

            return ResponseEntity.badRequest().body(
                    RespostaDTO.builder()
                            .sucesso(false)
                            .erros(enderecoDto.getErros())
                            .mensagem(enderecoDto.getMensagemResposta()).build());
        }
    }

    private boolean validarEndereco(EnderecoDTO enderecoDTO) {

        if (Objects.nonNull(enderecoDTO)) {

            enderecoDTO.setEmpresa(getEmpresaLogado());
            if (StringUtils.isBlank(enderecoDTO.getCep())) {

                enderecoDTO.addErro("CEP é obrigatório.", "endereco_cep");
            }
            if (StringUtils.isBlank(enderecoDTO.getRua())) {

                enderecoDTO.addErro("Rua é obrigatório.", "endereco_rua");
            }

            if (StringUtils.isBlank(enderecoDTO.getNumero())) {

                enderecoDTO.addErro("Número é obrigatório.", "endereco_numero");
            }

            if (StringUtils.isBlank(enderecoDTO.getCidade())) {

                enderecoDTO.addErro("Cidade é obrigatório.", "endereco_cidade");
            }

            if (enderecoDTO.getClienteId() <= 0) {

                enderecoDTO.addErro("Cliente é obrigatório.", "endereco_cliente");
            }
        }

        return validarAposConferencia(enderecoDTO);
    }

    private boolean validarAposConferencia(EnderecoDTO enderecoDTO) {

        List<ErroDTO> erros = new ArrayList<>();

        if (Objects.isNull(enderecoDTO)) {

            erros.add(ErroDTO.builder().mensagem("Objeto Cliente não reconhecido").campo("Endereço").build());
        }

        if (Objects.nonNull(enderecoDTO.getErros())) {

            erros.addAll(enderecoDTO.getErros());
        }

        Optional<Cliente> optCliente = this.clienteServico.findById(enderecoDTO.getClienteId());

        if (optCliente.isEmpty()) {

            erros.add(ErroDTO.builder().mensagem("Cliente não identificado no Sistema").campo("cliente").build());
        } else {

            enderecoDTO.setCliente(optCliente.get());
        }

        enderecoDTO.setErros(erros);
        enderecoDTO.determinarMensagemResposta();

        return CollectionUtils.isEmpty(enderecoDTO.getErros());
    }
}
