package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.hotel.HotelRepository;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.dto.HotelResponse;
import br.com.srh.Patrivago.exception.*;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import br.com.srh.Patrivago.util.CnpjService;
import br.com.srh.Patrivago.util.EmailService;
import br.com.srh.Patrivago.util.NomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    EnderecoService enderecoService;
    @Autowired
    ContaService contaService;
    @Autowired
    CnpjService cnpjService;
    @Autowired
    EmailService emailService;


//TODO verificar erro de endereco

    public HotelResponse addHotel(HotelRequest hotelRequest, String cnpj) {
        hotelRequestNullCheck(hotelRequest);
        hotelRequestCheck(hotelRequest);
        enderecoService.cepFormatoCheck(hotelRequest.getEndereco().getCep());
        enderecoService.cepExist(hotelRequest.getEndereco().getCep());

        EnderecoEntity endereco = enderecoService.viaCepConverteEndereco
                (
                        hotelRequest.getEndereco().getCep(),
                        hotelRequest.getEndereco().getNumero());

        HotelEntity hotel = HotelEntity.builder()
                .nome(hotelRequest.getNome())
                .qtdeQuarto(hotelRequest.getQtdeQuarto())
                .qtdeQuartoDisponivel(hotelRequest.getQtdeQuarto())
                .endereco(hotelRequest.getEndereco())
                .hotelEmail(hotelRequest.getHotelEmail())
                .build();

        HotelEntity hotelSalvo = hotelRepository.saveHotel(hotel, cnpj);
        enderecoService.addEndereco(endereco, hotelRequest);

        return mapearHotel(hotelSalvo);
    }

    private void hotelRequestCheck(HotelRequest hotelRequest) {
        if (!emailService.isValidEmail(hotelRequest.getHotelEmail())) {
            throw new EmailException();
        }
        if (hotelEmailExist(hotelRequest.getHotelEmail())) {
            throw new EmailExistException();
        }


    }

    private void hotelRequestNullCheck(HotelRequest hotelRequest) {
        if (
                hotelRequest.getNome() == null ||
                        hotelRequest.getHotelEmail() == null ||
                        hotelRequest.getQtdeQuarto() == null ||
                        hotelRequest.getEndereco().getCep() == null ||
                        hotelRequest.getEndereco().getNumero() == null) {
            throw new NullPointerException();
        }
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
        if (!cnpjService.isValidCNPJ(cnpj)) {
            throw new CnpjException();
        }
        if (!contaService.empresaCnpjExist(cnpj)) {
            throw new CnpjDontExistException();
        }
        if (!hotelAndEmpresaCheck(cnpj, idHotel)) {
            throw new HotelMismatchExcpetion();
        }
        if (!hotelExist(idHotel)) {
            throw new HotelDontExistException();
        }


        hotelRequestUpdateCheck(hotelRequest, idHotel);

        HotelEntity hotelSalvo = hotelRepository.getHotelInfo(cnpj, idHotel);

        hotelSalvo.setNome(hotelRequest.getNome());
        hotelSalvo.setHotelEmail(hotelRequest.getHotelEmail());
        hotelSalvo.setQtdeQuarto(hotelRequest.getQtdeQuarto());
        hotelSalvo.setQtdeQuartoDisponivel(hotelSalvo.getQtdeQuarto());

        HotelEntity hotelAtualizado = hotelRepository.updateHotel(hotelSalvo, idHotel);
        return mapearHotel(hotelAtualizado);

    }

    private boolean hotelAndEmpresaCheck(String cnpj, Long idHotel) {
        Integer empresaCount = hotelRepository.hotelAndEmpresaCheck(cnpj, idHotel);
        if (empresaCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hotelExist(Long idHotel) {
        Integer hotelCount = hotelRepository.getHotel(idHotel);
        if (hotelCount > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void hotelRequestUpdateCheck(HotelRequest hotelRequest, Long idHotel) {
        if (!emailService.isValidEmail(hotelRequest.getHotelEmail())) {
            throw new EmailException();
        }
        if (hotelEmailExist(hotelRequest.getHotelEmail())) {
            throw new EmailExistException();
        }
        if (hotelHasReservationCheck(idHotel)) {
            throw new HotelCantUpdateExcpetion();
        }
    }

    private boolean hotelHasReservationCheck(Long idHotel) {
        Integer quartos = hotelRepository.getQtdeQuartos(idHotel);
        Integer quartoDisponivel = hotelRepository.getQtdeQuartoDisponivel(idHotel);
        if (quartoDisponivel < quartos) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hotelExist(String name) {
        return hotelRepository.getHotelName(name);
    }

    public boolean hotelIdExist(Long idHotel) {
        Integer hotelCount = hotelRepository.getHotelId(idHotel);
        if (hotelCount>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean hotelEmailExist(String hotelEmail) {
        return hotelRepository.hotelEmailExist(hotelEmail);
    }

    public boolean hotelEmailCheck(String hotelEmail, String nomeHotel) {
        Integer hotelEmailCheck = hotelRepository.hotelEmailCheck(hotelEmail, nomeHotel);

        if (hotelEmailCheck != null && hotelEmailCheck > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean quartoDisponivelCheck(String hotelEmail) {
        Integer qtdeQuartoDisponivel = hotelRepository.quartoDisponivelCheck(hotelEmail);
        if (qtdeQuartoDisponivel > 0) {
            return true;
        } else {
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
