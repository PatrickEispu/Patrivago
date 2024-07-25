package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.Reserva.ReservaRepository;
import br.com.srh.Patrivago.dto.ReservaRequest;
import br.com.srh.Patrivago.dto.ReservaResponse;
import br.com.srh.Patrivago.model.reserva.ReservaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    HotelService hotelService;

    public ReservaResponse addReserva(ReservaRequest reservaRequest,String cpf) {

        ReservaEntity reserva = ReservaEntity.builder()
                .nomeHotel(reservaRequest.getNomeHotel())
                .nomeCliente(reservaRequest.getNomeCliente())
                .hotelEmail(reservaRequest.getHotelEmail())
                .checkIn(reservaRequest.getCheckIn())
                .checkOut(reservaRequest.getCheckOut())
                .build();
        ReservaEntity reservaSalva = reservaRepository.saveReserva(reserva,cpf);
//TODO verificar porque os valores estão nulos na repository
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
}
