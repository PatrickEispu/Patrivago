package br.com.srh.Patrivago.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ContaResponse {
    private Long idConta;
    private String nome;
    private String email;
    private Integer idTipoConta;
//    private String cnpj;
//    private String cpf;
//    private String senha;
//    private TipoContaEnum tipoConta;


//    @Override
//    public String toString() {
//        if (tipoConta == TipoContaEnum.EMPRESA) {
//            return "ContaResponse{" +
//                    "nome='" + nome + '\'' +
//                    ", email='" + email + '\'' +
//                    ", senha='" + senha + '\'' +
//                    ", tipoConta=" + tipoConta +
//                    ", cnpj='" + cnpj + '\'' +
//                    '}';
//        } else {
//            return "ContaResponse{" +
//                    "nome='" + nome + '\'' +
//                    ", email='" + email + '\'' +
//                    ", senha='" + senha + '\'' +
//                    ", tipoConta=" + tipoConta +
//                    ", cpf='" + cpf + '\'' +
//                    '}';
//        }
//    }

}

