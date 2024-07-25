package br.com.srh.Patrivago.model.conta;


import br.com.srh.Patrivago.enuns.TipoContaEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder(toBuilder = true)
@SuperBuilder
public class ContaEntity implements Serializable {
    private Long idConta;
    private Integer fkIdTipoConta;
    private String nome;
    private String email;
    private String senha;
    private TipoContaEnum tipoConta;






}
