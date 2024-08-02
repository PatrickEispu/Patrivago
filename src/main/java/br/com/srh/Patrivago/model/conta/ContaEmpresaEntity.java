package br.com.srh.Patrivago.model.conta;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ContaEmpresaEntity extends ContaEntity {
    private Long idContaEmpresa;
    private Long idConta;
    private String cnpj;

//    @Builder(toBuilder = true)
//    public ContaEmpresaEntity(Long idConta, Integer fkIdTipoConta, String nome, String email, String senha, TipoContaEnum tipoConta, Long idContaEmpresa, Long fkIdConta, String cnpj) {
//        super(idConta, fkIdTipoConta, nome, email, senha, tipoConta);
//        this.idContaEmpresa = idContaEmpresa;
//        this.fkIdConta = fkIdConta;
//        this.cnpj = cnpj;
//    }
}
