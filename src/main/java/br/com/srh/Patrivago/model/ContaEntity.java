package br.com.srh.Patrivago.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)

public class ContaEntity {
    private Integer idConta;
    private String nome;
    private String email;
    private String senha;
    private Integer fkIdTipoConta;

}
