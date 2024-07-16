package br.com.srh.Patrivago.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class QuartoEntity {

    private Integer idQuarto;
    private Integer quartoNum;
    private Integer fkIdHotel;
}
