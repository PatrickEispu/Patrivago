package br.com.srh.Patrivago.model.conta;


import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class ContaClienteEntity extends ContaEntity {
    private Long idContaCliente;
    private String cpf;
    private Long idConta;

//    @Builder(toBuilder = true)
//    public ContaClienteEntity(Long idConta, Integer fkIdTipoConta, String nome, String email, String senha, TipoContaEnum tipoConta, Long idContaCliente, String cpf, Long fkIdConta) {
//        super(idConta, fkIdTipoConta, nome, email, senha, tipoConta);
//        this.idContaCliente = idContaCliente;
//        this.cpf = cpf;
//        this.fkIdConta = fkIdConta;
//    }
}
