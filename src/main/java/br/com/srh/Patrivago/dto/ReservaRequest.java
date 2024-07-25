package br.com.srh.Patrivago.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReservaRequest {
    private Integer idReserva;
    private String nomeHotel;
    private String nomeCliente;
    private String checkIn;
    private String checkOut;
    private String hotelEmail;
    // private Integer quartoNum;
}
