package dev.batistella.geros.servico;

import dev.batistella.geros.controller.UsuarioLogadoController;
import dev.batistella.geros.dto.cadastro.OrdemServicoCriacaoDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.entidade.OrdemServico;
import dev.batistella.geros.entidade.Usuario;
import dev.batistella.geros.repositorio.OrdemServicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class OrdemServicoServico extends UsuarioLogadoController {

    @Autowired
    ClienteServico clienteServico;

    @Autowired
    EmpresaServico empresaServico;

    @Autowired
    UsuarioServico usuarioServico;

    @Autowired
    OrdemServicoRepositorio ordemServicoRepo;
    public ResponseEntity<RespostaDTO> gerarNovaOrdemServico (Integer idCliente) {

        OrdemServicoCriacaoDTO ordemServicoDto = new OrdemServicoCriacaoDTO(idCliente);

        if (validarNovaOrdemServico(ordemServicoDto)) {

            return ResponseEntity.ok().body(
                    RespostaDTO.builder()
                            .sucesso(true)
                            .erros(ordemServicoDto.getErros())
                            .mensagem(ordemServicoDto.getMensagemResposta())
                            .objeto(this.save(new OrdemServico(ordemServicoDto))).build());
        } else {

            return ResponseEntity.badRequest().body(
                    RespostaDTO.builder()
                            .sucesso(false)
                            .erros(ordemServicoDto.getErros())
                            .mensagem(ordemServicoDto.getMensagemResposta()).build());
        }
    }

    public OrdemServico save(OrdemServico ordemServico) {

        ordemServico.setUsuario(this.usuarioServico.sincronizar(ordemServico.getUsuario()));
        ordemServico.setCliente(this.clienteServico.sincronizar(ordemServico.getCliente()));
        ordemServico.setEmpresa(this.empresaServico.sincronizar(ordemServico.getEmpresa()));

        return this.ordemServicoRepo.save(ordemServico);
    }

    private boolean validarNovaOrdemServico(OrdemServicoCriacaoDTO ordemServicoDto) {

        if (Objects.isNull(ordemServicoDto.getId())) {

            ordemServicoDto.addErro("Id do cliente deve ser informado", "cliente_id");
        } else {
            if (ordemServicoDto.getId() < 1) {

                ordemServicoDto.addErro("Id do cliente não pode ser menor que 1", "cliente_id");
            } else {

                Optional<Cliente> optCliente = this.clienteServico.findById(ordemServicoDto.getId());
                if (optCliente.isEmpty()) {

                    ordemServicoDto.addErro("Cliente não cadastrado com o id: " + ordemServicoDto.getId(), "cliente_id");
                } else {

                    Optional<Usuario> optU = usuarioServico.findById(getUsuarioLogado().getUsuario().getId());
                    if (optU.isPresent()) {

                        Usuario usuario = optU.get();
                        ordemServicoDto.setUsuario(usuario);
                        ordemServicoDto.setEmpresa(usuario.getEmpresa());
                    }

                    ordemServicoDto.setCliente(optCliente.get());
                }
            }
        }

        return CollectionUtils.isEmpty(ordemServicoDto.getErros());
    }
}
