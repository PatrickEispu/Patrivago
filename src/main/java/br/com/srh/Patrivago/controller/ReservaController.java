package br.com.srh.Patrivago.controller;


import br.com.srh.Patrivago.dto.ReservaRequest;
import br.com.srh.Patrivago.dto.ReservaResponse;
import br.com.srh.Patrivago.usecase.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/conta/")
@RestController
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    //Criacao de reserva
    @PostMapping("/{cpf}/addReserva")
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

    //TODO fazer listagem de reservas (por conta cliente e por hotel) e fazer update de reserva(checkin e checkout)

    //Listagem de todas a reservas de um cliente
    @GetMapping("/{cpf}/getClienteReserva")
    public List<ReservaResponse> getClienteReserva(@PathVariable ("cpf") String cpf)
    {
        return reservaService.geClienteReserva(cpf);
    }

    //Listagem de todas as reservas que um hotel possui
    @GetMapping("/{cnpj}/{idHotel}/getHotelReserva")
    public List<ReservaResponse> getHotelReserva(@PathVariable("idHotel")Long idHotel)
    {
        return reservaService.getHotelReserva(idHotel);
    }

    //Update de reserva (check in e check out)
    @PutMapping("/{cpf}/{idReserva}/updateReserva")
    public ResponseEntity<String> updateReserva(@RequestBody ReservaRequest reservaRequest,
                                         @PathVariable("cpf")String cpf,
                                         @PathVariable("idReserva")Long idReserva)
    {
        reservaService.updateReserva(reservaRequest,cpf,idReserva);
         return new ResponseEntity<> ("RESERVA atualizada com sucesso!", HttpStatus.OK);
    }
}
