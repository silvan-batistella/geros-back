package dev.batistella.geros.servico;

import dev.batistella.geros.entidade.Empresa;
import dev.batistella.geros.repositorio.EmpresaRepositorio;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class EmpresaServico {

    @Autowired
    EntityManager entityManager;

    @Autowired
    EmpresaRepositorio empresaRepo;

    public boolean existeEmpresaComCPFCNPJ(String cpfCnpj) {

        if (StringUtils.isEmpty(cpfCnpj)) {

            return true;
        }

        return this.empresaRepo.existsByCpfCnpj(cpfCnpj);
    }

    public Empresa save(Empresa empresa) {

        return this.empresaRepo.save(empresa);
    }

    @Transactional
    public Empresa sincronizar(Empresa empresa) {

        return this.entityManager.merge(empresa);
    }
}
