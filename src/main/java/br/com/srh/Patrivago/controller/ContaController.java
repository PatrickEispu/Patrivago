package br.com.srh.Patrivago.controller;

import br.com.srh.Patrivago.dto.ContaClienteResponse;
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
    public List<ContaResponse> getAllContaList()
    {
        return contaService.getAllContaList();
    }

    @GetMapping("/getAllCliente")
    public List<ContaClienteResponse> getAllCliente()
    {
        return contaService.getAllClienteList();
    }

    @GetMapping("/getCliente/{cpf}")
    public List<ContaClienteResponse> getClienteList(@PathVariable("cpf")String cpf)
    {
        return contaService.getClienteList(cpf);
    }

    @PutMapping("/{cpf}/updateCliente")
    public ResponseEntity<String> updateContaCliente(@RequestBody ContaRequest contaRequest, @PathVariable("cpf") String cpfCnpj)
    {
        contaService.updateContaCliente(contaRequest, cpfCnpj);
        return new ResponseEntity<>("CONTA atualizada com sucesso!",HttpStatus.OK);
    }
    //TODO fazer listagem de conta empresa e update


}



