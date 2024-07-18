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
    private Integer idReserva;
    private String nomeHotel;
    private Integer quartoNum;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private ContaEntity conta;

}
