package br.com.srh.Patrivago.controller;

import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.dto.HotelResponse;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import br.com.srh.Patrivago.usecase.ContaService;
import br.com.srh.Patrivago.usecase.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/conta/{cnpj}")

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    @Autowired
    ContaService contaService;


    @PostMapping("/addHotel")
    public ResponseEntity<String> addHotel(@RequestBody HotelRequest hotelRequest,@PathVariable("cnpj")String cnpj) {
       HotelResponse hotelResponse= hotelService.addHotel(hotelRequest, cnpj);
        if (hotelResponse != null) {
            return new ResponseEntity<>("Hotel Criado com Sucesso!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Falha ao Criar Hotel!", HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
