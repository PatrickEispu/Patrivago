package br.com.srh.Patrivago.model.reserva;

import br.com.srh.Patrivago.model.conta.ContaEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReservaEntity implements Serializable {
    private Long idReserva;
    private String nomeHotel;
    private String nomeCliente;
    private String checkOut;
    private String checkIn;
    private ContaEntity conta;
    private String hotelEmail;
    private Integer idReservaStatus;
    private Integer idReservaOrder;
}
