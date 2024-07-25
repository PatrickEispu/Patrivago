package br.com.srh.Patrivago.dto;

import br.com.srh.Patrivago.enuns.TipoContaEnum;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContaRequest {
    private String email;
    private String senha;
    private TipoContaEnum tipoConta;
    private String cnpj;
    private String cpf;
    private String nome;
}
