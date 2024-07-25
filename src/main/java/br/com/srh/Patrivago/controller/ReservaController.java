package br.com.srh.Patrivago.controller;


import br.com.srh.Patrivago.dto.ReservaRequest;
import br.com.srh.Patrivago.dto.ReservaResponse;
import br.com.srh.Patrivago.usecase.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/conta/{cpf}")
@RestController
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    @PostMapping("/addReserva")
    public ResponseEntity<String> addReserva(@RequestBody ReservaRequest reservaRequest, @PathVariable("cpf")String cpf)
    {
       ReservaResponse reservaResponse= reservaService.addReserva(reservaRequest,cpf);
       if (reservaResponse!=null)
       {
            return new ResponseEntity<>("RESERVA feita com Sucesso!", HttpStatus.CREATED);
       }
       else
       {
           return new ResponseEntity<>("FALHA ao realizar a reserva!", HttpStatus.NOT_ACCEPTABLE);

       }
    }
}
