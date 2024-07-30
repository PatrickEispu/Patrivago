package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.conta.ContaRepository;
import br.com.srh.Patrivago.dto.ContaClienteResponse;
import br.com.srh.Patrivago.dto.ContaEmpresaResponse;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.enuns.TipoContaEnum;
import br.com.srh.Patrivago.model.conta.ContaClienteEntity;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;

    public ContaResponse addConta(ContaRequest contaRequest) {
        if (contaRequest.getTipoConta() == TipoContaEnum.CLIENTE) {
            return addContaCliente(contaRequest);
        } else if (contaRequest.getTipoConta() == TipoContaEnum.EMPRESA) {
            return addContaEmpresa(contaRequest);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATEGORIA INEXISTENTE");
        }

    }

    public ContaResponse addContaCliente(ContaRequest contaRequest) {

        ContaClienteEntity contaCliente = ContaClienteEntity.builder()
                .nome(contaRequest.getNome())
                .senha(contaRequest.getSenha())
                .email(contaRequest.getEmail())
                .tipoConta(contaRequest.getTipoConta())
                .cpf(contaRequest.getCpf())
                .build();

        ContaClienteEntity contaClienteSalva = contaRepository.saveCliente(contaCliente);

        return mapearContaCliente(contaClienteSalva);


    }


    public ContaResponse addContaEmpresa(ContaRequest contaRequest) {

        ContaEmpresaEntity contaEmpresa = ContaEmpresaEntity.builder()
                .nome(contaRequest.getNome())
                .senha(contaRequest.getSenha())
                .email(contaRequest.getEmail())
               .tipoConta(contaRequest.getTipoConta())
                .cnpj(contaRequest.getCnpj())
                .build();

        ContaEmpresaEntity contaEmpresaSalva = contaRepository.saveEmpresa(contaEmpresa);
        return mapearContaEmpresa(contaEmpresaSalva);

    }

    private static ContaClienteResponse mapearContaCliente(ContaClienteEntity contaClienteSalva) {

        return ContaClienteResponse.builder()
                .nome(contaClienteSalva.getNome())
              //  .senha(contaClienteSalva.getSenha())
                .email(contaClienteSalva.getEmail())
               // .tipoConta(contaClienteSalva.getTipoConta())
               // .cpf(contaClienteSalva.getCpf())
                .build();

    }

    private static ContaEmpresaResponse mapearContaEmpresa(ContaEmpresaEntity contaEmpresaSalva) {

        return ContaEmpresaResponse.builder()
                .nome(contaEmpresaSalva.getNome())
            //    .senha(contaEmpresaSalva.getSenha())
                .email(contaEmpresaSalva.getEmail())
              //  .tipoConta(contaEmpresaSalva.getTipoConta())
               // .cnpj(contaEmpresaSalva.getCnpj())
                .build();
    }

    public List<ContaResponse> getAllContaList() {
        return contaRepository.getAllContaList();
    }


    public List<ContaClienteResponse> getAllClienteList() {
        return contaRepository.getAllClienteList();
    }

    public List<ContaClienteResponse> getClienteList(String cpf) {
        return contaRepository.getClienteList(cpf);
    }

    public ContaClienteResponse updateContaCliente(ContaRequest contaRequest, String cpf) {
       ContaClienteEntity contaSalva = contaRepository.getClienteInfo(cpf);
       contaSalva.setNome(contaRequest.getNome());
       contaSalva.setEmail(contaRequest.getEmail());
       contaSalva.setCpf(contaRequest.getCpf());
       contaSalva.setSenha(contaRequest.getSenha());

       ContaClienteEntity contaAtualizada = contaRepository.updateCliente(contaSalva,cpf);
       return mapearContaCliente(contaAtualizada);

    }

    public List<ContaEmpresaResponse> getAllEmpresaList() {
        return contaRepository.getAllEmpresaList();
    }

    public List<ContaEmpresaResponse> getEmpresaList(String cnpj) {
        return contaRepository.getEmpresaList(cnpj);
    }

    public ContaEmpresaResponse updateContaEmpresa(ContaRequest contaRequest, String cnpj) {
        ContaEmpresaEntity contaSalva = contaRepository.getEmpresaInfo(cnpj);
        contaSalva.setNome(contaRequest.getNome());
        contaSalva.setEmail(contaRequest.getEmail());
        contaSalva.setCnpj(contaRequest.getCnpj());
        contaSalva.setSenha(contaRequest.getSenha());

        ContaEmpresaEntity contaAtualizada = contaRepository.updateEmpresa(contaSalva,cnpj);

        return mapearContaEmpresa(contaAtualizada);
    }

    public Long getIdCliente(String cpf) {
       return contaRepository.getIdCliente(cpf);
    }
}

