package br.com.srh.Patrivago.model.hotel;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class EnderecoEntity {
    private Integer idEndereco;
    private String cep;
    private String rua;
    private String numero;
    private String uf;
    private Integer fkIdHotel;

}
