package br.com.srh.Patrivago.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ContaClienteEntity {
    private Integer idContaCliente;
    private String cpf;
    private Integer fkIdConta;
}
