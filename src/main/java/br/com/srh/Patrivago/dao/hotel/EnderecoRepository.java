package br.com.srh.Patrivago.dao.hotel;

import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnderecoRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public EnderecoEntity saveEndereco(EnderecoEntity endereco, HotelRequest hotelRequest) {
        String sqlIdHotel= ("SELECT id_hotel FROM hotel where nome = ? AND hotel_email = ?");
       Long idHotel = jdbcTemplate.queryForObject(sqlIdHotel,Long.class,hotelRequest.getNome(),hotelRequest.getHotelEmail());
        String sql =("INSERT INTO endereco (cep,rua,numero,uf,id_hotel) VALUES (?,?,?,?,?)");
        jdbcTemplate.update(sql,endereco.getCep(),endereco.getRua(),endereco.getNumero(),endereco.getUf(),idHotel);
        return endereco;

    }
}
