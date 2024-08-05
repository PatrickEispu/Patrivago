package br.com.srh.Patrivago.controller;

import br.com.srh.Patrivago.dto.ContaClienteResponse;
import br.com.srh.Patrivago.dto.ContaEmpresaResponse;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.usecase.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Criação de conta (Cliente ou Empresa)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/add")
    public ResponseEntity<String> postConta(@RequestBody ContaRequest contaRequest) {

        contaService.addConta(contaRequest);
        return new ResponseEntity<>("CONTA criada com sucesso!", HttpStatus.CREATED);
    }


    //Listar todas as contas do banco
    @Operation(summary = "Listagem de TODAS as contas da aplicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de contas feito com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro de listagem"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/getAllConta")
    public List<ContaResponse> getAllContaList() {
        return contaService.getAllContaList();
    }



    //Listar todos os clientes
    @Operation(summary = "Listagem de informações de todas as contas cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno da lista de clientes!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível listar os clientes"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/getAllCliente")
    public List<ContaClienteResponse> getAllCliente() {
        return contaService.getAllClienteList();
    }



    @Operation(summary = "Listagem de informações de UM cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de informações do cliente!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível listar o cliente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/getCliente/{cpf}")
    public List<ContaClienteResponse> getClienteList(@PathVariable("cpf") String cpf) {
        return contaService.getClienteList(cpf);
    }



    @Operation(summary = "Update de informações do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível atualizar o cliente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{cpf}/updateCliente")
    public ResponseEntity<String> updateContaCliente(@RequestBody ContaRequest contaRequest, @PathVariable("cpf") String cpfCnpj) {
        contaService.updateContaCliente(contaRequest, cpfCnpj);
        return new ResponseEntity<>("CONTA atualizada com sucesso!", HttpStatus.OK);
    }



    //Listar todas as empresa
    @Operation(summary = "Listagem de informações de todas as conta empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno da lista de empresas!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível listar as empresas"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/getAllEmpresa")
    public List<ContaEmpresaResponse> getAllEmpresaList() {
        return contaService.getAllEmpresaList();
    }




    //Listar empresa especifica
    @Operation(summary = "Listagem de informações de UMA empresa específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de informações do cliente!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível listar o cliente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/getEmpresa/{cnpj}")
    public List<ContaEmpresaResponse> getEmpresaList(@PathVariable("cnpj") String cnpj) {
        return contaService.getEmpresaList(cnpj);
    }


    //Atualizar informacoes de empresa
    @Operation(summary = "Update de informações da empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Não foi possível atualizar a empresa"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{cnpj}/updateEmpresa")
    public ResponseEntity<String> updateEmpresa(@RequestBody ContaRequest contaRequest, @PathVariable("cnpj") String cnpj) {
        contaService.updateContaEmpresa(contaRequest, cnpj);
        return new ResponseEntity<>("CONTA atualizada com sucesso!", HttpStatus.OK);
    }
}




