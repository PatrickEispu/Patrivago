package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.Reserva.ReservaRepository;
import br.com.srh.Patrivago.dto.ReservaRequest;
import br.com.srh.Patrivago.dto.ReservaResponse;
import br.com.srh.Patrivago.model.reserva.ReservaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    HotelService hotelService;
    @Autowired
    ContaService contaService;


    public ReservaResponse addReserva(ReservaRequest reservaRequest,String cpf) {

        ReservaEntity reserva = ReservaEntity.builder()
                .nomeHotel(reservaRequest.getNomeHotel())
                .nomeCliente(reservaRequest.getNomeCliente())
                .hotelEmail(reservaRequest.getHotelEmail())
                .checkIn(reservaRequest.getCheckIn())
                .checkOut(reservaRequest.getCheckOut())
                .build();
        ReservaEntity reservaSalva = reservaRepository.saveReserva(reserva,cpf);
        return mapearReserva(reservaSalva);


    }

    private ReservaResponse mapearReserva(ReservaEntity reservaSalva) {
         return  ReservaResponse.builder()
                .nomeHotel(reservaSalva.getNomeHotel())
                .nomeCliente(reservaSalva.getNomeCliente())
                .hotelEmail(reservaSalva.getHotelEmail())
                .checkIn(reservaSalva.getCheckIn())
                .checkOut(reservaSalva.getCheckOut())
                .build();
    }

    public List<ReservaResponse> geClienteReserva(String cpf) {
       Long idCliente = contaService.getIdCliente(cpf);
        return reservaRepository.getClienteReserva(idCliente);
    }

    public List<ReservaResponse> getHotelReserva(Long idHotel) {
        return reservaRepository.getHotelReserva(idHotel);
    }

    public ReservaResponse updateReserva(ReservaRequest reservaRequest,String cpf, Long idReserva) {
        Long idCliente = contaService.getIdCliente(cpf);

        ReservaEntity reservaSalva = reservaRepository.getReservaInfo(idCliente,idReserva);
        reservaSalva.setCheckIn(reservaRequest.getCheckIn());
        reservaSalva.setCheckOut(reservaRequest.getCheckOut());

        ReservaEntity reservaAtualizada = reservaRepository.updateReserva(reservaSalva, idReserva);

        return mapearReserva(reservaAtualizada);
    }
}
