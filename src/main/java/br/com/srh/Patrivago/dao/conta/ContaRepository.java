package br.com.srh.Patrivago.dao.conta;

import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.enuns.TipoContaEnum;
import br.com.srh.Patrivago.model.conta.ContaClienteEntity;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.conta.ContaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public ContaEmpresaEntity saveEmpresa(ContaEmpresaEntity contaEmpresa) {

        String sql = "INSERT INTO conta (nome, email, senha, id_tipo_conta) VALUES (?,?,?,?) RETURNING id_conta";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, contaEmpresa.getNome(), contaEmpresa.getEmail(), contaEmpresa.getSenha(), 2);
        sql = "INSERT INTO conta_empresa (cnpj,id_conta) VALUES (?,?)";
        jdbcTemplate.update(sql, contaEmpresa.getCnpj(), id);
        return contaEmpresa;
    }

    public ContaClienteEntity saveCliente(ContaClienteEntity contaCliente) {


        String sql = "INSERT INTO conta (nome, email, senha, id_tipo_conta) VALUES (?,?,?,?) RETURNING id_conta";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, contaCliente.getNome(), contaCliente.getEmail(), contaCliente.getSenha(), 1);
        sql = "INSERT INTO conta_cliente (cpf,id_conta) VALUES (?,?)";
        jdbcTemplate.update(sql, contaCliente.getCpf(), id);
        return contaCliente;


    }

//    public boolean loginCheck(ContaRequest contaRequest) {
//        try {
//            String sql = "SELECT COUNT(*) FROM conta WHERE email = ? AND senha = ?";
//            int count = jdbcTemplate.queryForObject(sql, Integer.class, contaRequest.getEmail(), contaRequest.getSenha());
//
//            return count > 0;
//        } catch (Exception e) {
//            System.out.println("Ocorreu um erro: " + e);
//            return false;
//        }
//    }

//    public Long getContaId(ContaRequest contaRequest) {
//        String sql = ("SELECT id_conta FROM conta WHERE email = ?");
//        return jdbcTemplate.queryForObject(sql, Long.class, contaRequest.getEmail());
//
//    }

//    public ContaClienteEntity getContaCliente(Long idConta) {
//        String sqlCpf = ("SELECT cpf FROM conta_cliente WHERE id_conta = ?");
//        String cpf = jdbcTemplate.queryForObject(sqlCpf, String.class, idConta);
//
//        String sql = ("SELECT nome,email,senha,id_tipo_conta FROM conta WHERE id_conta = ?");
//
//        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
//            return ContaClienteEntity.builder()
//                    .nome(resultSet.getString("nome"))
//                    .senha(resultSet.getString("senha"))
//                    .email(resultSet.getString("email"))
//                    .cpf(cpf)
//                    .build();
//
//        }, idConta);
//    }

//    public ContaEmpresaEntity getContaEmpresa(Long idConta) {
//        String sqlCnpj = ("SELECT cnpj FROM conta_empresa WHERE id_conta = ?");
//
//        String cnpj = jdbcTemplate.queryForObject(sqlCnpj, String.class, idConta);
//
//        String sql = ("SELECT nome,email,senha,id_tipo_conta FROM conta WHERE id_conta = ?");
//
//        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> {
//            return ContaEmpresaEntity.builder()
//                    .nome(resultSet.getString("nome"))
//                    .senha(resultSet.getString("senha"))
//                    .email(resultSet.getString("email"))
//                    .cnpj(cnpj)
//                    .build();
//
//        }, idConta);
//    }
//

//    public Integer getTipoConta(Long idConta) {
//        String sql = ("SELECT id_tipo_conta FROM conta WHERE id_conta = ?");
//        return jdbcTemplate.queryForObject(sql, Integer.class, idConta);
//    }
}
