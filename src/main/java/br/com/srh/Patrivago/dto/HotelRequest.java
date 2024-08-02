package br.com.srh.Patrivago.dto;


import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class HotelRequest {
    private Integer idHotel;
    private String nome;
    private Integer qtdeQuarto;
    private Integer qtdeQuartoDisponivel;
//    private QuartoEntity quarto;
    private String hotelEmail;
  //  private ContaEntity conta;
    private EnderecoEntity endereco;

}
