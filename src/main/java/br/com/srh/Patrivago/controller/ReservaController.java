package br.com.srh.Patrivago.controller;


import br.com.srh.Patrivago.dto.ReservaRequest;
import br.com.srh.Patrivago.dto.ReservaResponse;
import br.com.srh.Patrivago.usecase.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/conta/reserva")
@RestController
public class ReservaController {
    @Autowired
    ReservaService reservaService;

    //Criacao de reserva
    @Operation(summary = "Criação de reserva de um cliente com um hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva feita com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na realização de reserva"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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


    //Listagem de todas a reservas de um cliente
    @Operation(summary = "Listagem de reservas feitas por um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno das reservas feito pelo cliente!"),
            @ApiResponse(responseCode = "400", description = "Erro ao trazer as reservas do cliente"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{cpf}/getClienteReserva")
    public List<ReservaResponse> getClienteReserva(@PathVariable ("cpf") String cpf)
    {
        return reservaService.geClienteReserva(cpf);
    }



    //Listagem de todas as reservas que um hotel possui
    @Operation(summary = "Listagem de reservas que um hotel possui")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno das reservas que o hotel possui!"),
            @ApiResponse(responseCode = "400", description = "Erro ao trazer as reservas do hotel"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{cnpj}/{idHotel}/getHotelReserva")
    public List<ReservaResponse> getHotelReserva(@PathVariable("idHotel")Long idHotel)
    {
        return reservaService.getHotelReserva(idHotel);
    }



    //Ativar checkin de cliente
    @Operation(summary = "Ativar o checkin da reserva de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Checkin feito com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao fazer o checkin"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{idReserva}/ativarCheckin")
    public ResponseEntity<String> ativarCheckin(@PathVariable("idReserva")Long idReserva)
    {
        reservaService.ativarCheckin(idReserva);
        return new ResponseEntity<>("Check-in feito com sucesso!",HttpStatus.OK);
    }



    //Ativar checkout de cliente
    @Operation(summary = "Ativar o checkout da reserva de um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Checkout feito com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro ao fazer o checkin"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{idReserva}/ativarCheckout")
    public ResponseEntity<String> ativarCheckout(@PathVariable("idReserva")Long idReserva)
    {
         reservaService.ativarCheckout(idReserva);
        return new ResponseEntity<>("Check-out feito com sucesso!",HttpStatus.OK);
    }




    //Update de reserva (check in e check out)
    @Operation(summary = "Atualizar as datas de uma reserva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva atualizada com sucesso!!"),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar a reserva"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{cpf}/{idReserva}/updateReserva")
    public ResponseEntity<String> updateReserva(@RequestBody ReservaRequest reservaRequest,
                                         @PathVariable("cpf")String cpf,
                                         @PathVariable("idReserva")Long idReserva)
    {
        reservaService.updateReserva(reservaRequest,cpf,idReserva);
         return new ResponseEntity<> ("RESERVA atualizada com sucesso!", HttpStatus.OK);
    }
}
