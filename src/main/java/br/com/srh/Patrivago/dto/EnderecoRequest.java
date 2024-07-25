package br.com.srh.Patrivago.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EnderecoRequest {
    private String cep;
    private String rua;
    private String numero;
    private String uf;

}
