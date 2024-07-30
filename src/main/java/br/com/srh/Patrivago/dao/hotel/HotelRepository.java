package br.com.srh.Patrivago.dao.hotel;


import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.EnderecoResponse;
import br.com.srh.Patrivago.dto.HotelResponse;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class HotelRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public HotelEntity saveHotel(HotelEntity hotel, String cnpj) {
        try {
            String sqlIdEmpresa = "SELECT id_conta_empresa FROM conta_empresa WHERE cnpj = ?";
            Long idEmpresa = jdbcTemplate.queryForObject(sqlIdEmpresa, Long.class, cnpj);

            String sql = "INSERT INTO hotel (nome, qtde_quarto, qtde_quarto_disponivel, hotel_email,id_conta_empresa) VALUES (?, ?, ?, ?,?)";
            jdbcTemplate.update(sql, hotel.getNome(), hotel.getQtdeQuarto(), hotel.getQtdeQuartoDisponivel(), hotel.getHotelEmail(), idEmpresa);

            System.out.println("Hotel inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir hotel: " + e);
        }
        return hotel;
    }


    public void showAll() {

    }

    public void showHotelByCnpj(String cnpj) {

    }

    public List<HotelResponse> getAllHotel() {
        String sql = ("SELECT * FROM hotel");
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HotelResponse hotel = new HotelResponse();
            hotel.setIdHotel(rs.getLong("id_hotel"));
            hotel.setNome(rs.getString("nome"));
            hotel.setHotelEmail(rs.getString("hotel_email"));
            hotel.setQtdeQuarto(rs.getInt("qtde_quarto"));
            hotel.setQtdeQuartoDisponivel(rs.getInt("qtde_quarto_disponivel"));
            hotel.setFkIdContaEmpresa(rs.getLong("id_conta_empresa"));
            return hotel;
        });
    }

    public List<HotelResponse> getHotel(String nomeHotel) {
        String sql = ("SELECT * FROM hotel INNER JOIN endereco ON hotel.id_hotel = endereco.id_hotel WHERE nome LIKE ? ");
        List<HotelResponse>hotelResponseList = new ArrayList<>();


         jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            HotelResponse hotel = new HotelResponse();
            hotel.setIdHotel(rs.getLong("id_hotel"));
            hotel.setNome(rs.getString("nome"));
            hotel.setHotelEmail(rs.getString("hotel_email"));
            hotel.setQtdeQuarto(rs.getInt("qtde_quarto"));
            hotel.setQtdeQuartoDisponivel(rs.getInt("qtde_quarto_disponivel"));
            hotel.setFkIdContaEmpresa(rs.getLong("id_conta_empresa"));

            EnderecoEntity endereco = new EnderecoEntity();
            endereco.setIdEndereco(rs.getLong("id_endereco"));
            endereco.setCep(rs.getString("cep"));
            endereco.setRua(rs.getString("rua"));
            endereco.setNumero(rs.getString("numero"));
            endereco.setUf(rs.getString("uf"));
            endereco.setFkIdHotel(rs.getLong("id_hotel"));

            hotel.setEndereco(endereco);
            hotelResponseList.add(hotel);
            return hotel;

        }, "%"+nomeHotel+"%");
        return hotelResponseList;
    }


    public HotelEntity updateHotel(HotelEntity hotelAtualizado,Long idHotel) {
        String sql=("UPDATE hotel " +
                "SET nome = ?, qtde_quarto = ?, qtde_quarto_disponivel = ?, hotel_email = ? " +
                "WHERE id_hotel = ?");

         jdbcTemplate.update(sql,hotelAtualizado.getNome(),
                hotelAtualizado.getQtdeQuarto(),
                hotelAtualizado.getQtdeQuartoDisponivel(),
                hotelAtualizado.getHotelEmail(),
                idHotel);

         return hotelAtualizado;

    }

    public HotelEntity getHotelInfo(String cnpj, Long idHotel) {
        String sqlIdConta = ("SELECT id_conta_empresa FROM conta_empresa WHERE cnpj = ?");
        Long idConta= jdbcTemplate.queryForObject(sqlIdConta, Long.class,cnpj);

        String sql=("SELECT * FROM hotel WHERE id_hotel = ? and id_conta_empresa = ?");
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(HotelEntity.class),idHotel,idConta);
    }
}
