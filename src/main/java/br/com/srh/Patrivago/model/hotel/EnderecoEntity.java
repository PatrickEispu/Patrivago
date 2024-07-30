package br.com.srh.Patrivago.model.hotel;

import br.com.srh.Patrivago.usecase.HotelService;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class EnderecoEntity implements Serializable {
    private Long idEndereco;
    private String cep;
    private String rua;
    private String numero;
    private String uf;
    private Long fkIdHotel;
    private HotelEntity hotel;

}
