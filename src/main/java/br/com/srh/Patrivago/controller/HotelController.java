package br.com.srh.Patrivago.controller;

import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.dto.HotelResponse;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import br.com.srh.Patrivago.usecase.ContaService;
import br.com.srh.Patrivago.usecase.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/conta/hotel")

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    @Autowired
    ContaService contaService;


    @Operation(summary = "Criação de hotel para contas empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/{cnpj}/addHotel")
    public ResponseEntity<String> addHotel(@RequestBody HotelRequest hotelRequest,@PathVariable("cnpj")String cnpj) {
       HotelResponse hotelResponse= hotelService.addHotel(hotelRequest, cnpj);
        if (hotelResponse != null) {
            return new ResponseEntity<>("Hotel Criado com Sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Falha ao Criar Hotel!", HttpStatus.NOT_ACCEPTABLE);
        }
    }


    //Listagem de todos os hoteis

    @Operation(summary = "Listagem de todos os hoteis da plataforma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno da lista de hoteis!"),
            @ApiResponse(responseCode = "400", description = "Erro na listagem de hoteis"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/getAllHotel")
    public List<HotelResponse> getAllHotel()
    {
        return hotelService.getALlHotel();
    }




    //Listagem de hoteis por nome (coleta o nome de hotel por palavra chave)
    @Operation(summary = "Listagem de hoteis por busca aproximada do nome ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno da lista de hoteis!"),
            @ApiResponse(responseCode = "400", description = "Erro na listagem de hoteis"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/getHotel/{nome}")
    public List<HotelResponse> getHotel(@PathVariable ("nome")String nomeHotel)
    {
        return hotelService.getHotel(nomeHotel);
    }


    //Update de informações do hotel
    @Operation(summary = "Update de informações de um hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na atualização do hotel"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PutMapping("/{cnpj}/{idHotel}/updateHotel")
    public ResponseEntity<String> updateHotel(@RequestBody HotelRequest hotelRequest,
                                              @PathVariable("cnpj") String cnpj,
                                              @PathVariable("idHotel")Long idHotel)
    {
        HotelResponse hotelResponse = hotelService.updateHotel(hotelRequest,cnpj,idHotel);
        return new ResponseEntity<>("Hotel Atualizado com sucesso!",HttpStatus.OK);
    }
}
