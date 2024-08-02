package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.hotel.HotelRepository;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.dto.HotelResponse;
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
                .qtdeQuartoDisponivel(hotelRequest.getQtdeQuarto())
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


    public List<HotelResponse> getALlHotel() {
      return hotelRepository.getAllHotel();
    }

    public List<HotelResponse> getHotel(String nomeHotel) {
       return hotelRepository.getHotel(nomeHotel);
    }

    public HotelResponse updateHotel(HotelRequest hotelRequest, String cnpj, Long idHotel) {
       HotelEntity hotelSalvo = hotelRepository.getHotelInfo(cnpj,idHotel);

       hotelSalvo.setNome(hotelRequest.getNome());
       hotelSalvo.setHotelEmail(hotelRequest.getHotelEmail());
       hotelSalvo.setQtdeQuarto(hotelRequest.getQtdeQuarto());
       hotelSalvo.setQtdeQuartoDisponivel(hotelSalvo.getQtdeQuarto());

       HotelEntity hotelAtualizado = hotelRepository.updateHotel(hotelSalvo,idHotel);
       return mapearHotel(hotelAtualizado);

    }

    public boolean hotelExist(String name) {
        return hotelRepository.getHotelName(name);
    }

    public boolean hotelEmailExist(String hotelEmail) {
        return hotelRepository.hotelEmailExist(hotelEmail);
    }

    public boolean hotelEmailCheck(String hotelEmail, String nomeHotel) {
        Integer hotelEmailCheck= hotelRepository.hotelEmailCheck(hotelEmail,nomeHotel);
        
        if (hotelEmailCheck!=null && hotelEmailCheck>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean quartoDisponivelCheck(String hotelEmail) {
       Integer qtdeQuartoDisponivel=hotelRepository.quartoDisponivelCheck(hotelEmail);
        if (qtdeQuartoDisponivel>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void blockRoom(String hotelEmail) {
        hotelRepository.blockRoom(hotelEmail);
    }

    public void clearRoom(Long idReserva) {
        hotelRepository.clearRoom(idReserva);
    }
}
