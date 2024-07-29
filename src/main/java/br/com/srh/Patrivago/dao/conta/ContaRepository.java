package br.com.srh.Patrivago.dao.conta;

import br.com.srh.Patrivago.dto.ContaClienteResponse;
import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.ContaResponse;
import br.com.srh.Patrivago.model.conta.ContaClienteEntity;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public List<ContaResponse> getAllContaList() {
        String sql = "SELECT * FROM conta";
       // System.out.println("chegou aq");
      return jdbcTemplate.query(sql,(rs,rowNum)->
      {
          ContaResponse conta = new ContaResponse();
          conta.setIdConta(rs.getLong("id_conta"));
          conta.setNome(rs.getString ("nome"));
          conta.setEmail(rs.getString("email"));
          conta.setFkIdTipoConta(rs.getInt( "id_tipo_conta"));
          return conta;
      });
    }


    public List<ContaClienteResponse> getAllClienteList() {
        String sql = "SELECT *\n" +
                "FROM conta  \n" +
                "INNER JOIN conta_cliente   ON conta.id_conta = conta_cliente.id_conta_cliente ;\n";
        return jdbcTemplate.query(sql,(rs, rowNum) ->
        {
            ContaClienteResponse contaCliente = new ContaClienteResponse();
            contaCliente.setIdConta(rs.getLong("id_conta"));
            contaCliente.setIdContaCliente(rs.getLong("id_conta_cliente"));
            contaCliente.setNome(rs.getString ("nome"));
            contaCliente.setEmail(rs.getString("email"));
            contaCliente.setFkIdTipoConta(rs.getInt( "id_tipo_conta"));
            contaCliente.setCpf(rs.getString("cpf"));
            return contaCliente;
        });
    }

    public List<ContaClienteResponse> getClienteList(String cpf) {
        String sql = "SELECT *\n" +
                "FROM conta  \n" +
                "INNER JOIN conta_cliente ON conta.id_conta = conta_cliente.id_conta_cliente WHERE cpf =? ;\n";
        return jdbcTemplate.query(sql,(rs,rowNum) -> {
            ContaClienteResponse contaCliente = new ContaClienteResponse();
            contaCliente.setIdConta(rs.getLong("id_conta"));
            contaCliente.setIdContaCliente(rs.getLong("id_conta_cliente"));
            contaCliente.setNome(rs.getString ("nome"));
            contaCliente.setEmail(rs.getString("email"));
            contaCliente.setFkIdTipoConta(rs.getInt( "id_tipo_conta"));
            contaCliente.setCpf(rs.getString("cpf"));
            return contaCliente;
        },cpf);
    }


    public ContaClienteEntity getClienteInfo(String cpf)
    {
        String sql=("SELECT * " +
                "FROM conta " +
                "INNER JOIN conta_cliente " +
                "ON conta.id_conta = conta_cliente.id_conta_cliente WHERE cpf = ?");

        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(ContaClienteEntity.class),cpf);
    }

    public ContaClienteEntity updateCliente(ContaClienteEntity contaCliente,String cpf) {
        String sqlIdConta = ("SELECT id_conta FROM conta_cliente WHERE cpf =?");
        Long idConta = jdbcTemplate.queryForObject(sqlIdConta, Long.class,cpf);

        String sql=("UPDATE conta SET nome = ?, email = ?, senha = ? WHERE id_conta = ? ");
       jdbcTemplate.update(sql,contaCliente.getNome(),contaCliente.getEmail(),contaCliente.getSenha(),idConta);

       String sqlContaCliente=("UPDATE conta_cliente SET cpf = ? WHERE cpf = ?");
       jdbcTemplate.update(sqlContaCliente,contaCliente.getCpf(),cpf);

       return contaCliente;
    }
}
