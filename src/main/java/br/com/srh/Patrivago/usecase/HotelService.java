package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.conta.ContaEmpresaRepository;
import br.com.srh.Patrivago.dao.hotel.HotelRepository;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.EnderecoResponse;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.dto.HotelResponse;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    EnderecoService enderecoService;






    public HotelResponse addHotel(HotelRequest hotelRequest, String cnpj) {


        HotelEntity hotel = HotelEntity.builder()
                .nome(hotelRequest.getNome())
                .qtdeQuarto(hotelRequest.getQtdeQuarto())
                .qtdeQuartoDisponivel(hotelRequest.getQtdeQuartoDisponivel())
                .endereco(hotelRequest.getEndereco())
                .hotelEmail(hotelRequest.getHotelEmail())
                .build();

        HotelEntity hotelSalvo = hotelRepository.saveHotel(hotel, cnpj);
        enderecoService.addEndereco(hotelRequest);

        return mapearHotel(hotelSalvo);
    }

    private static HotelResponse mapearHotel(HotelEntity hotelSalvo) {

        return HotelResponse.builder()
                .nome(hotelSalvo.getNome())
                .qtdeQuarto(hotelSalvo.getQtdeQuarto())
                .qtdeQuartoDisponivel(hotelSalvo.getQtdeQuartoDisponivel())
                .endereco(hotelSalvo.getEndereco())
                .build();
    }


}
