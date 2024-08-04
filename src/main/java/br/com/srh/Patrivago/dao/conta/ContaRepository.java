package br.com.srh.Patrivago.dao.conta;

import br.com.srh.Patrivago.dto.ContaClienteResponse;
import br.com.srh.Patrivago.dto.ContaEmpresaResponse;
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
        return jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            ContaResponse conta = new ContaResponse();
            conta.setIdConta(rs.getLong("id_conta"));
            conta.setNome(rs.getString("nome"));
            conta.setEmail(rs.getString("email"));
            conta.setIdTipoConta(rs.getInt("id_tipo_conta"));
            return conta;
        });
    }


    public List<ContaClienteResponse> getAllClienteList() {
        String sql = "SELECT *\n" +
                "FROM conta  \n" +
                "INNER JOIN conta_cliente   ON conta.id_conta = conta_cliente.id_conta ;\n";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            ContaClienteResponse contaCliente = new ContaClienteResponse();
            contaCliente.setIdConta(rs.getLong("id_conta"));
            contaCliente.setIdContaCliente(rs.getLong("id_conta_cliente"));
            contaCliente.setNome(rs.getString("nome"));
            contaCliente.setEmail(rs.getString("email"));
            contaCliente.setIdTipoConta(rs.getInt("id_tipo_conta"));
            contaCliente.setCpf(rs.getString("cpf"));
            return contaCliente;
        });
    }

    public List<ContaClienteResponse> getClienteList(String cpf) {
        String sql = "SELECT *\n" +
                "FROM conta  \n" +
                "INNER JOIN conta_cliente ON conta.id_conta = conta_cliente.id_conta WHERE cpf =? ;\n";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ContaClienteResponse contaCliente = new ContaClienteResponse();
            contaCliente.setIdConta(rs.getLong("id_conta"));
            contaCliente.setIdContaCliente(rs.getLong("id_conta_cliente"));
            contaCliente.setNome(rs.getString("nome"));
            contaCliente.setEmail(rs.getString("email"));
            contaCliente.setIdTipoConta(rs.getInt("id_tipo_conta"));
            contaCliente.setCpf(rs.getString("cpf"));
            return contaCliente;
        }, cpf);
    }


    public ContaClienteEntity getClienteInfo(String cpf) {
        String sql = ("SELECT * " +
                "FROM conta " +
                "INNER JOIN conta_cliente " +
                "ON conta.id_conta = conta_cliente.id_conta WHERE cpf = ?");

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ContaClienteEntity.class), cpf);
    }

    public ContaClienteEntity updateCliente(ContaClienteEntity contaCliente, String cpf) {
        String sqlIdConta = ("SELECT id_conta FROM conta_cliente WHERE cpf =?");
        Long idConta = jdbcTemplate.queryForObject(sqlIdConta, Long.class, cpf);

        String sql = ("UPDATE conta SET nome = ?, email = ?, senha = ? WHERE id_conta = ? ");
        jdbcTemplate.update(sql, contaCliente.getNome(), contaCliente.getEmail(), contaCliente.getSenha(), idConta);

        String sqlContaCliente = ("UPDATE conta_cliente SET cpf = ? WHERE cpf = ?");
        jdbcTemplate.update(sqlContaCliente, contaCliente.getCpf(), cpf);

        return contaCliente;
    }

    public List<ContaEmpresaResponse> getAllEmpresaList() {
        String sql = ("SELECT * from conta INNER JOIN conta_empresa ON conta.id_conta = conta_empresa.id_conta");
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ContaEmpresaResponse contaEmpresa = new ContaEmpresaResponse();
            contaEmpresa.setIdConta(rs.getLong("id_conta"));
            contaEmpresa.setIdContaEmpresa(rs.getLong("id_conta_empresa"));
            contaEmpresa.setNome(rs.getString("nome"));
            contaEmpresa.setEmail(rs.getString("email"));
            contaEmpresa.setCnpj(rs.getString("cnpj"));
            contaEmpresa.setIdTipoConta(rs.getInt("id_tipo_conta"));
            return contaEmpresa;
        });
    }

    public List<ContaEmpresaResponse> getEmpresaList(String cnpj) {
        String sql = ("SELECT * FROM conta INNER JOIN conta_empresa ON conta.id_conta = conta_empresa.id_conta WHERE cnpj = ?");
        return jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            ContaEmpresaResponse contaEmpresa = new ContaEmpresaResponse();
            contaEmpresa.setIdConta(rs.getLong("id_conta"));
            contaEmpresa.setIdContaEmpresa(rs.getLong("id_conta_empresa"));
            contaEmpresa.setNome(rs.getString("nome"));
            contaEmpresa.setEmail(rs.getString("email"));
            contaEmpresa.setCnpj(rs.getString("cnpj"));
            contaEmpresa.setIdTipoConta(rs.getInt("id_tipo_conta"));
            return contaEmpresa;
        }, cnpj);
    }

    public ContaEmpresaEntity getEmpresaInfo(String cnpj) {
        String sql=("SELECT * " +
                "FROM conta " +
                "INNER JOIN conta_empresa " +
                "ON conta.id_conta = conta_empresa.id_conta " +
                "WHERE cnpj = ?");
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(ContaEmpresaEntity.class),cnpj);
    }

    public ContaEmpresaEntity updateEmpresa(ContaEmpresaEntity contaSalva, String cnpj) {
        String sqlIdConta = ("SELECT id_conta FROM conta_empresa WHERE cnpj = ? ");
        Long idConta = jdbcTemplate.queryForObject(sqlIdConta,Long.class,cnpj);

        String sql=("UPDATE conta SET nome = ?, email = ?, senha = ? WHERE id_conta = ? ");
        jdbcTemplate.update(sql,contaSalva.getNome(),contaSalva.getEmail(),contaSalva.getEmail(),idConta);

        String sqlContaEmpresa=("UPDATE conta_empresa SET cnpj = ? WHERE cnpj = ?");
        jdbcTemplate.update(sqlContaEmpresa,contaSalva.getCnpj(),cnpj);
        return contaSalva;
    }

    public Long getIdCliente(String cpf) {
        String sqlIdCliente = ("SELECT id_conta_cliente FROM conta_cliente WHERE cpf = ?");
        return jdbcTemplate.queryForObject(sqlIdCliente, Long.class,cpf);
    }

    public boolean clienteCpfExist(String cpf) {
        String sql = ("SELECT COUNT(*) FROM conta_cliente WHERE cpf = ?");
        Integer cpfExist = jdbcTemplate.queryForObject(sql, Integer.class,cpf);
        if (cpfExist!=null && cpfExist>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean empresaCnpjExist(String cnpj) {
        String sql = ("SELECT COUNT(*) FROM conta_empresa WHERE cnpj = ?");
        Integer cnpjExist = jdbcTemplate.queryForObject(sql, Integer.class,cnpj);
        if (cnpjExist!=null && cnpjExist>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean emailExist(String email) {
        String sql = ("SELECT COUNT(*) FROM conta WHERE email = ?");
        Integer emailExist = jdbcTemplate.queryForObject(sql, Integer.class,email);
        if (emailExist!=null && emailExist>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public String getClienteEmail(String cpf) {
        String sql=("SELECT email FROM conta INNER JOIN conta_cliente ON conta.id_conta = conta_cliente.id_conta WHERE cpf = ?");
        return jdbcTemplate.queryForObject(sql, String.class,cpf);

    }

    public String getEmpresaEmail(String cnpj) {
        String sql=("SELECT email FROM conta INNER JOIN conta_empresa ON conta.id_conta = conta_empresa.id_conta WHERE cnpj = ?");
        return jdbcTemplate.queryForObject(sql, String.class,cnpj);

    }
}
