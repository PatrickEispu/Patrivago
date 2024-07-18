package br.com.srh.Patrivago.model.conta;


import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContaEmpresaEntity implements Serializable {
    private Integer idContaEmpresa;
    private String cnpj;
    private ContaEntity conta;
}
