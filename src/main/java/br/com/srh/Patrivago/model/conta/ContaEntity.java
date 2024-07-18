package br.com.srh.Patrivago.model.conta;


import br.com.srh.Patrivago.enuns.TipoContaEnum;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class ContaEntity implements Serializable {
    private Long idConta;
    private String nome;
    private String email;
    private String senha;
    private TipoContaEnum tipoConta;
    private ContaClienteEntity contaCliente;
    private ContaEmpresaEntity contaEmpresa;

}
