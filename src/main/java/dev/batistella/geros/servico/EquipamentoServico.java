package dev.batistella.geros.servico;

import dev.batistella.geros.controller.UsuarioLogadoController;
import dev.batistella.geros.dto.cadastro.EquipamentoDTO;
import dev.batistella.geros.dto.generico.ErroDTO;
import dev.batistella.geros.dto.generico.RespostaDTO;
import dev.batistella.geros.entidade.Cliente;
import dev.batistella.geros.entidade.Equipamento;
import dev.batistella.geros.repositorio.EquipamentoRepositorio;
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
public class EquipamentoServico extends UsuarioLogadoController {

    @Autowired
    EquipamentoRepositorio equipamentoRepo;

    @Autowired
    ClienteServico clienteServico;

    @Autowired
    EmpresaServico empresaServico;

    @Autowired
    UsuarioServico usuarioServico;


    public ResponseEntity<RespostaDTO> criarEquipamento(EquipamentoDTO equipamentoDto) {

        if (validarEquipamento(equipamentoDto)) {

            return ResponseEntity.ok().body(
                    RespostaDTO.builder()
                            .sucesso(true)
                            .erros(equipamentoDto.getErros())
                            .mensagem(equipamentoDto.getMensagemResposta())
                            .objeto(this.save(new Equipamento(equipamentoDto))).build());
        } else {

            return ResponseEntity.badRequest().body(
                    RespostaDTO.builder()
                            .sucesso(false)
                            .erros(equipamentoDto.getErros())
                            .mensagem(equipamentoDto.getMensagemResposta()).build());
        }
    }

    private Equipamento save(Equipamento equipamento) {

        equipamento.setUsuario(this.usuarioServico.sincronizar(equipamento.getUsuario()));
        equipamento.setCliente(this.clienteServico.sincronizar(equipamento.getCliente()));
        equipamento.setEmpresa(this.empresaServico.sincronizar(equipamento.getEmpresa()));
        return this.equipamentoRepo.save(equipamento);
    }

    private boolean validarEquipamento(EquipamentoDTO equipamentoDTO) {

        if (Objects.nonNull(equipamentoDTO)) {

            equipamentoDTO.setEmpresa(getEmpresaLogado());
            equipamentoDTO.setUsuario(getUsuarioLogado().getUsuario());
            if (StringUtils.isBlank(equipamentoDTO.getMarca())) {

                equipamentoDTO.addErro("Marca é obrigatória.", "equipamento_marca");
            }
            if (StringUtils.isBlank(equipamentoDTO.getModelo())) {

                equipamentoDTO.addErro("Modelo é obrigatório.", "equipamento_modelo");
            }
            if (StringUtils.isBlank(equipamentoDTO.getNumeroSerie())) {

                equipamentoDTO.addErro("Número de Série é obrigatória.", "equipamento_numero_serie");
            }
            if (equipamentoDTO.getClienteId() <= 0) {

                equipamentoDTO.addErro("Cliente é obrigatório.", "equipamento_cliente");
            }
        }

        return validarAposConferencia(equipamentoDTO);
    }

    private boolean validarAposConferencia(EquipamentoDTO equipamentoDTO) {

        List<ErroDTO> erros = new ArrayList<>();

        if (Objects.isNull(equipamentoDTO)) {

            erros.add(ErroDTO.builder().mensagem("Objeto Cliente não reconhecido").campo("Endereço").build());
        }

        if (Objects.nonNull(equipamentoDTO.getErros())) {

            erros.addAll(equipamentoDTO.getErros());
        }

        Optional<Cliente> optCliente = this.clienteServico.findById(equipamentoDTO.getClienteId());

        if (optCliente.isEmpty()) {

            erros.add(ErroDTO.builder().mensagem("Cliente não identificado no Sistema").campo("cliente").build());
        } else {

            equipamentoDTO.setCliente(optCliente.get());
            Optional<Equipamento> optE = this.equipamentoRepo.findByClienteAndMarcaAndModeloAndNumeroSerie(
                    equipamentoDTO.getCliente(),
                    equipamentoDTO.getMarca(),
                    equipamentoDTO.getModelo(),
                    equipamentoDTO.getNumeroSerie());
            if (optE.isPresent()) {

                erros.add(ErroDTO.builder().mensagem("Equipamento já cadastrado").campo("equipamento").build());
            }
        }

        equipamentoDTO.setErros(erros);
        equipamentoDTO.determinarMensagemResposta();

        return CollectionUtils.isEmpty(equipamentoDTO.getErros());
    }

}
