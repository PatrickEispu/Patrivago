package br.com.srh.Patrivago.controller;

import br.com.srh.Patrivago.dto.ContaClienteResponse;
import br.com.srh.Patrivago.dto.ContaEmpresaResponse;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.usecase.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;


    //criacao de conta conta;
    @PostMapping("/add")
    public ResponseEntity<String> postConta(@RequestBody ContaRequest contaRequest) {

        contaService.addConta(contaRequest);
        return new ResponseEntity<>("CONTA criada com sucesso!", HttpStatus.CREATED);


    }


    //Listar todas as contas do banco
    @GetMapping("/getAllConta")
    public List<ContaResponse> getAllContaList() {
        return contaService.getAllContaList();
    }

    //Listar todos os clientes
    @GetMapping("/getAllCliente")
    public List<ContaClienteResponse> getAllCliente() {
        return contaService.getAllClienteList();
    }

    //Listar cliente especifico
    @GetMapping("/getCliente/{cpf}")
    public List<ContaClienteResponse> getClienteList(@PathVariable("cpf") String cpf) {
        return contaService.getClienteList(cpf);
    }

    //Atualizar informacoes de cliente
    @PutMapping("/{cpf}/updateCliente")
    public ResponseEntity<String> updateContaCliente(@RequestBody ContaRequest contaRequest, @PathVariable("cpf") String cpfCnpj) {
        contaService.updateContaCliente(contaRequest, cpfCnpj);
        return new ResponseEntity<>("CONTA atualizada com sucesso!", HttpStatus.OK);
    }

    //Listar todas as empresa
    @GetMapping("/getAllEmpresa")
    public List<ContaEmpresaResponse> getAllEmpresaList() {
        return contaService.getAllEmpresaList();
    }

    //Listar empresa especifica
    @GetMapping("/getEmpresa/{cnpj}")
    public List<ContaEmpresaResponse> getEmpresaList(@PathVariable("cnpj") String cnpj) {
        return contaService.getEmpresaList(cnpj);
    }


    //Atualizar informacoes de empresa
    @PutMapping("/{cnpj}/updateEmpresa")
    public ResponseEntity<String> updateEmpresa(@RequestBody ContaRequest contaRequest, @PathVariable("cnpj") String cnpj) {
        contaService.updateContaEmpresa(contaRequest, cnpj);
        return new ResponseEntity<>("CONTA atualizada com sucesso!", HttpStatus.OK);
    }
}




