package br.com.srh.Patrivago.model.hotel;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class QuartoEntity implements Serializable {

    private Integer idQuarto;
    private Integer quartoNum;
    private HotelEntity hotel;
}
