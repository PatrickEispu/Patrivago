package br.com.srh.Patrivago.model.hotel;

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
    private Long idHotel;
    private HotelEntity hotel;

}
