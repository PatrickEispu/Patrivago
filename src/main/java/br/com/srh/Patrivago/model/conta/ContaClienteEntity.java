package br.com.srh.Patrivago.model.conta;


import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class ContaClienteEntity implements Serializable {
    private Integer idContaCliente;
    private String cpf;
    private ContaEntity conta;
}
