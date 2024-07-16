package br.com.srh.Patrivago.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ReservaEntity {
    private Integer idReserva;
    private String nomeHotel;
    private Integer quartoNum;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer fkIdConta;
}
