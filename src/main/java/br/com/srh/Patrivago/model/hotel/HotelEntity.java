package br.com.srh.Patrivago.model.hotel;


import br.com.srh.Patrivago.model.conta.ContaEntity;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
public class HotelEntity implements Serializable {
    private Long idHotel;
    private String nome;
    private Integer qtdeQuarto;
    private Integer qtdeQuartoDisponivel;
    private EnderecoEntity endereco;
    private String hotelEmail;
//    private QuartoEntity quarto;
//    private ContaEntity conta;


}
