package br.com.srh.Patrivago.controller;

import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.usecase.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;


    //criacao de conta cliente;
    @PostMapping("/add")
    public ResponseEntity<String> postConta(@RequestBody ContaRequest contaRequest) {

        ContaResponse contaResponse = contaService.addConta(contaRequest);
       // ResponseEntity<StandardResponse> ok = ResponseEntity.ok(StandardResponse.builder().message("CONTA CADASTRADA COM SUCESSO").build());
        // System.out.println(contaRequest);
        if (contaResponse != null) {
            return new ResponseEntity<>("CONTA criada com sucesso!", HttpStatus.CREATED);
            //return ok;
        } else {
            return new ResponseEntity<>("Falha ao criar a conta!", HttpStatus.NOT_ACCEPTABLE);

        }

    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody ContaRequest contaRequest) {
//         contaService.loginConta(contaRequest);
//        return new ResponseEntity<>("Logado com sucesso!", HttpStatus.ACCEPTED);
//    }


}



