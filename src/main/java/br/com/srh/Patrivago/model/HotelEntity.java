package br.com.srh.Patrivago.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class HotelEntity {
    private Integer idHotel;
    private String nome;
    private Integer qtdeQuarto;
    private Integer qtdeQuartoDisponivel;
    private Integer fkIdQuarto;
    private Integer fkIdConta;

}
