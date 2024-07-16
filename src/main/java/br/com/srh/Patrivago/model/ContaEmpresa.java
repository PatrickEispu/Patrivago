package br.com.srh.Patrivago.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ContaEmpresa {
    private Integer idContaEmpresa;
    private String cnpj;
    private Integer fkIdConta;
}
