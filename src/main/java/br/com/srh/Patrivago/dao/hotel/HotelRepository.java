package br.com.srh.Patrivago.dao.hotel;


import br.com.srh.Patrivago.dto.HotelResponse;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
            endereco.setIdHotel(rs.getLong("id_hotel"));

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

    public boolean getHotelName(String name) {
        String sql=("SELECT COUNT(*) FROM hotel WHERE nome = ?");
        Integer hotelExist = jdbcTemplate.queryForObject(sql, Integer.class,name);
        if (hotelExist!=null && hotelExist>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean hotelEmailExist(String hotelEmail) {
        String sql=("SELECT COUNT(*) FROM hotel WHERE hotel_email = ?");
        Integer hotelEmailExist = jdbcTemplate.queryForObject(sql, Integer.class,hotelEmail);
        if (hotelEmailExist!=null && hotelEmailExist>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Integer hotelEmailCheck(String hotelEmail, String nomeHotel) {
        String sql=("SELECT COUNT(*) FROM hotel WHERE hotel_email = ? AND nome = ?");
        return jdbcTemplate.queryForObject(sql, Integer.class,hotelEmail,nomeHotel);

    }

    public Integer quartoDisponivelCheck(String hotelEmail) {
        String sql=("SELECT qtde_quarto_disponivel FROM hotel WHERE hotel_email = ?");
        return jdbcTemplate.queryForObject(sql, Integer.class,hotelEmail);

    }

    public void blockRoom(String hotelEmail) {
        String sql=("UPDATE hotel SET qtde_quarto_disponivel = qtde_quarto_disponivel-1  WHERE hotel_email = ?");
        jdbcTemplate.update(sql,hotelEmail);


    }

    public void clearRoom(Long idReserva) {
        String sql=("UPDATE hotel SET qtde_quarto_disponivel = qtde_quarto_disponivel+1  WHERE id_reserva = ?");
        jdbcTemplate.update(sql,idReserva);
    }
}
