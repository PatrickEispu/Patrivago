package br.com.srh.Patrivago.dto;


import br.com.srh.Patrivago.model.conta.ContaEntity;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import br.com.srh.Patrivago.model.hotel.QuartoEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class HotelResponse {
    private Integer idHotel;
    private String nome;
    private Integer qtdeQuarto;
    private Integer qtdeQuartoDisponivel;
 //   private QuartoEntity quarto;
   // private ContaEntity conta;
    private EnderecoEntity endereco;
    private String hotelEmail;
}