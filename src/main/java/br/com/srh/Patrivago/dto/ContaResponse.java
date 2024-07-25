package br.com.srh.Patrivago.dto;


import br.com.srh.Patrivago.enuns.TipoContaEnum;
import br.com.srh.Patrivago.model.conta.ContaClienteEntity;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ContaResponse {
    private String nome;
    private String email;
    private String senha;
    private TipoContaEnum tipoConta;
    private String cnpj;
    private String cpf;
    private Long idConta;
    private Integer fkIdTipoConta;

    @Override
    public String toString() {
        if(tipoConta==TipoContaEnum.EMPRESA)
        {
            return "ContaResponse{" +
                    "nome='" + nome + '\'' +
                    ", email='" + email + '\'' +
                    ", senha='" + senha + '\'' +
                    ", tipoConta=" + tipoConta +
                    ", cnpj='" + cnpj + '\'' +
                    '}';
        }
        else
        {
            return "ContaResponse{" +
                    "nome='" + nome + '\'' +
                    ", email='" + email + '\'' +
                    ", senha='" + senha + '\'' +
                    ", tipoConta=" + tipoConta +
                    ", cpf='" + cpf + '\'' +
                    '}';
        }
        }

    }

