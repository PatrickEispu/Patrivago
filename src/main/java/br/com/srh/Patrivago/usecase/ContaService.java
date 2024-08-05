package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.constante.ErrorMessage;
import br.com.srh.Patrivago.dao.conta.ContaRepository;
import br.com.srh.Patrivago.dto.ContaClienteResponse;
import br.com.srh.Patrivago.dto.ContaEmpresaResponse;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.enuns.TipoContaEnum;
import br.com.srh.Patrivago.exception.*;
import br.com.srh.Patrivago.model.conta.ContaClienteEntity;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    CnpjService cnpjService;
    @Autowired
    CpfService cpfService;
    @Autowired
    EmailService emailService;
    @Autowired
    NomeService nomeService;

    @Autowired
    SenhaService senhaService;

    public ContaResponse addConta(ContaRequest contaRequest) {
        contaRequestNullCheck(contaRequest);
        if (contaRequest.getTipoConta() == TipoContaEnum.CLIENTE) {
            return addContaCliente(contaRequest);
        } else if (contaRequest.getTipoConta() == TipoContaEnum.EMPRESA) {
            return addContaEmpresa(contaRequest);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CATEGORIA INEXISTENTE");
        }


    }

    private void contaRequestNullCheck(ContaRequest contaRequest) {
        if (
                contaRequest.getNome() == null ||
                        contaRequest.getTipoConta() == null ||
                        contaRequest.getSenha() == null ||
                        contaRequest.getEmail() == null) {
            throw new NullPointerException();
        }
        if (contaRequest.getTipoConta() == TipoContaEnum.CLIENTE) {
            if (contaRequest.getCpf() == null) {
                throw new NullPointerException();
            }
        } else {
            if (contaRequest.getCnpj() == null) {
                throw new NullPointerException();
            }
        }
    }

    public ContaResponse addContaCliente(ContaRequest contaRequest) {

        contaClienteCheckRequest(contaRequest);

        ContaClienteEntity contaCliente = ContaClienteEntity.builder()
                .nome(contaRequest.getNome())
                .senha(contaRequest.getSenha())
                .email(contaRequest.getEmail())
                .tipoConta(contaRequest.getTipoConta())
                .cpf(cpfService.converteCpf(contaRequest.getCpf()))
                .build();

        ContaClienteEntity contaClienteSalva = contaRepository.saveCliente(contaCliente);

        return mapearContaCliente(contaClienteSalva);


    }

    private void contaClienteCheckRequest(ContaRequest contaRequest) {

        if (!nomeService.isValidName(contaRequest.getNome())) {
            throw new NomeException();
        }

        if (!cpfService.isValidCPF(contaRequest.getCpf())) {
            throw new CpfException();
        }
        if (clienteCpfExist(contaRequest.getCpf())) {
            throw new CpfExistException();
        }

        if (!emailService.isValidEmail(contaRequest.getEmail())) {
            throw new EmailException();
        }
        if (emailExist(contaRequest)) {
            throw new EmailExistException();
        }

        if (!senhaService.isSenhaValid(contaRequest.getSenha())) {
            throw new SenhaException();
        }
    }

    private void contaClienteCheckUpdateRequest(ContaRequest contaRequest, String cpf) {
        if (
                contaRequest.getNome() == null ||
                contaRequest.getEmail() == null ||
                contaRequest.getSenha() == null ||
                contaRequest.getCpf() == null)
        {
            throw new NullPointerException();
        }

        if (!nomeService.isValidName(contaRequest.getNome())) {
            throw new NomeException();
        }

        if (!cpfService.isValidCPF(cpf)) {
            throw new CpfException();
        }
        if (!clienteCpfExist(cpf)) {
            throw new CpfDontExistException();
        }

        if (!cpfService.isValidCPF(contaRequest.getCpf())) {
            throw new CpfException();
        }
        if (clienteCpfExist(contaRequest.getCpf()) && !contaRequest.getCpf().equals(cpf)) {
            throw new CpfExistException();
        }

        if (!emailService.isValidEmail(contaRequest.getEmail())) {
            throw new EmailException();
        }
        String clienteEmail = contaRepository.getClienteEmail(cpf);
        if (emailExist(contaRequest) && !contaRequest.getEmail().equals(clienteEmail)) {
            throw new EmailExistException();
        }


        if (!senhaService.isSenhaValid(contaRequest.getSenha())) {
            throw new SenhaException();
        }
    }

    private void contaEmpresaCheckUpdateRequest(ContaRequest contaRequest, String cnpj) {

        if (
                        contaRequest.getNome() == null ||
                        contaRequest.getEmail() == null ||
                        contaRequest.getSenha() == null ||
                        contaRequest.getCnpj() == null)
        {
            throw new NullPointerException();
        }

        if (!nomeService.isValidName(contaRequest.getNome())) {
            throw new NomeException();
        }

        if (!cnpjService.isValidCNPJ(cnpj)) {
            throw new CnpjException();
        }
        if (!empresaCnpjExist(cnpj)) {
            throw new CpfDontExistException();
        }

        if (!cnpjService.isValidCNPJ(contaRequest.getCnpj())) {
            throw new CnpjException();
        }
        if (empresaCnpjExist(contaRequest.getCnpj()) && !contaRequest.getCnpj().equals(cnpj)) {
            throw new CnpjExistException();
        }

        if (!emailService.isValidEmail(contaRequest.getEmail())) {
            throw new EmailException();
        }
        String empresaEmail = contaRepository.getEmpresaEmail(cnpj);
        if (emailExist(contaRequest) && !contaRequest.getEmail().equals(empresaEmail)) {
            throw new EmailExistException();
        }

        if (!senhaService.isSenhaValid(contaRequest.getSenha())) {
            throw new SenhaException();
        }
    }

    private void contaEmpresaCheckRequest(ContaRequest contaRequest) {

        if (!cnpjService.isValidCNPJ(contaRequest.getCnpj())) {
            throw new CnpjException();
        }
        if (empresaCnpjExist(contaRequest.getCnpj())) {
            throw new CnpjExistException();
        }

        if (!emailService.isValidEmail(contaRequest.getEmail())) {
            throw new EmailException();
        }
        if (emailExist(contaRequest)) {
            throw new EmailExistException();
        }


        if (!senhaService.isSenhaValid(contaRequest.getSenha())) {
            throw new SenhaException();
        }
    }


    public ContaResponse addContaEmpresa(ContaRequest contaRequest) {
        contaEmpresaCheckRequest(contaRequest);

        ContaEmpresaEntity contaEmpresa = ContaEmpresaEntity.builder()
                .nome(contaRequest.getNome())
                .senha(contaRequest.getSenha())
                .email(contaRequest.getEmail())
                .tipoConta(contaRequest.getTipoConta())
                .cnpj(cnpjService.converteCnpj(contaRequest.getCnpj()))
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
                //        .senha(contaEmpresaSalva.getSenha())
                .email(contaEmpresaSalva.getEmail())
                //  .tipoConta(contaEmpresaSalva.getTipoConta())
                // .cnpj(contaEmpresaSalva.getCnpj())
                .build();
    }

    public List<ContaResponse> getAllContaList() {
        return contaRepository.getAllContaList();
    }


    public List<ContaClienteResponse> getAllClienteList() {
        if (contaRepository.getAllClienteList() == null) {
            throw new NullPointerException("Não há clientes");
        }

        return contaRepository.getAllClienteList();
    }

    public List<ContaClienteResponse> getClienteList(String cpf) {
        if (!cpfService.isValidCPF(cpf)) {
            throw new CpfException();
        }
        if (!clienteCpfExist(cpf)) {
            throw new CpfDontExistException();
        }
        return contaRepository.getClienteList(cpf);
    }

    public ContaClienteResponse updateContaCliente(ContaRequest contaRequest, String cpf) {

        contaClienteCheckUpdateRequest(contaRequest, cpf);

        ContaClienteEntity contaSalva = contaRepository.getClienteInfo(cpf);
        contaSalva.setNome(contaRequest.getNome());
        contaSalva.setEmail(contaRequest.getEmail());
        contaSalva.setCpf(contaRequest.getCpf());
        contaSalva.setSenha(contaRequest.getSenha());

        ContaClienteEntity contaAtualizada = contaRepository.updateCliente(contaSalva, cpf);
        return mapearContaCliente(contaAtualizada);

    }

    public List<ContaEmpresaResponse> getAllEmpresaList() {
        if (contaRepository.getAllEmpresaList() == null) {
            throw new NullPointerException("Não há empresas");
        }
        return contaRepository.getAllEmpresaList();
    }

    public List<ContaEmpresaResponse> getEmpresaList(String cnpj) {
        if (!cnpjService.isValidCNPJ(cnpj)) {
            throw new CnpjException();
        }
        if (!empresaCnpjExist(cnpj)) {
            throw new CnpjDontExistException();
        }
        return contaRepository.getEmpresaList(cnpj);
    }

    public ContaEmpresaResponse updateContaEmpresa(ContaRequest contaRequest, String cnpj) {
        contaEmpresaCheckUpdateRequest(contaRequest, cnpj);

        ContaEmpresaEntity contaSalva = contaRepository.getEmpresaInfo(cnpj);
        contaSalva.setNome(contaRequest.getNome());
        contaSalva.setEmail(contaRequest.getEmail());
        contaSalva.setCnpj(contaRequest.getCnpj());
        contaSalva.setSenha(contaRequest.getSenha());

        ContaEmpresaEntity contaAtualizada = contaRepository.updateEmpresa(contaSalva, cnpj);

        return mapearContaEmpresa(contaAtualizada);
    }

    private boolean emailExist(ContaRequest contaRequest) {
        return contaRepository.emailExist(contaRequest.getEmail());
    }

    public Long getIdCliente(String cpf) {
        return contaRepository.getIdCliente(cpf);
    }


    public boolean clienteCpfExist(String cpf) {
        return contaRepository.clienteCpfExist(cpf);
    }

    public boolean empresaCnpjExist(String cnpj) {
        return contaRepository.empresaCnpjExist(cnpj);
    }


}


