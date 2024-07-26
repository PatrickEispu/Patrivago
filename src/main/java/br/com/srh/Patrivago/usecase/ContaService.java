package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.conta.ContaRepository;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.enuns.TipoContaEnum;
import br.com.srh.Patrivago.model.conta.ContaClienteEntity;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.conta.ContaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    private static ContaResponse mapearContaCliente(ContaClienteEntity contaClienteSalva) {

        return ContaResponse.builder()
                .nome(contaClienteSalva.getNome())
                .senha(contaClienteSalva.getSenha())
                .email(contaClienteSalva.getEmail())
                .tipoConta(contaClienteSalva.getTipoConta())
                .cpf(contaClienteSalva.getCpf())
                .build();

    }

    private static ContaResponse mapearContaEmpresa(ContaEmpresaEntity contaEmpresaSalva) {

        return ContaResponse.builder()
                .nome(contaEmpresaSalva.getNome())
                .senha(contaEmpresaSalva.getSenha())
                .email(contaEmpresaSalva.getEmail())
                .tipoConta(contaEmpresaSalva.getTipoConta())
                .cnpj(contaEmpresaSalva.getCnpj())
                .build();
    }

//    public ContaResponse loginConta(ContaRequest contaRequest) {
//        if (contaRepository.loginCheck(contaRequest)) {
//            Long idConta = contaRepository.getContaId(contaRequest);
//            if (checkTipoConta(idConta) == TipoContaEnum.CLIENTE) {
//                ContaClienteEntity contaCliente = contaRepository.getContaCliente(idConta);
//                return mapearContaCliente(contaCliente);
//
//            } else {
//                ContaEmpresaEntity contaEmpresa = contaRepository.getContaEmpresa(idConta);
//                return mapearContaEmpresa(contaEmpresa);
//            }
//
//        }
//        else
//        {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"EMAIL ou SENHA incorreto!");
//        }
//    }

//    public TipoContaEnum checkTipoConta(Long idConta) {
//        Integer idTipoConta = contaRepository.getTipoConta(idConta);
//        if (idTipoConta == 1) {
//            return TipoContaEnum.CLIENTE;
//        } else {
//            return TipoContaEnum.EMPRESA;
//        }
//    }

}
