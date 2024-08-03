package br.com.srh.Patrivago.dao.hotel;

import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.dto.EnderecoResponse;
import br.com.srh.Patrivago.dto.HotelRequest;
import br.com.srh.Patrivago.dto.HotelResponse;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.EnderecoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class EnderecoRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public EnderecoEntity saveEndereco(EnderecoEntity endereco, HotelRequest hotelRequest) {
        String sqlIdHotel= ("SELECT id_hotel FROM hotel where nome = ? AND hotel_email = ?");
       Long idHotel = jdbcTemplate.queryForObject(sqlIdHotel,Long.class,hotelRequest.getNome(),hotelRequest.getHotelEmail());

        String sql =("INSERT INTO endereco (cep,rua,numero,cidade,uf,id_hotel) VALUES (?,?,?,?,?,?)");
        jdbcTemplate.update(sql,endereco.getCep(),endereco.getRua(),endereco.getNumero(),endereco.getCidade(),endereco.getUf(),idHotel);
        return endereco;

    }


//    public List<EnderecoEntity> getHotelEndereco(String nomeHotel, List<Long> idHotel) {
//        String sql = ("SELECT * from endereco WHERE nomeHotel = ? AND id_hotel = ?");
//        List<EnderecoEntity> enderecoList = new ArrayList<>();
//        for (Long id : idHotel)
//        {
//            enderecoList.addAll(jdbcTemplate.query(sql,((rs, rowNum) ->
//            {
//                EnderecoEntity endereco = new EnderecoEntity();
//                endereco.setCep(rs.getString("cep"));
//                endereco.setRua(rs.getString("rua"));
//                endereco.setNumero(rs.getString("numero"));
//                endereco.setUf(rs.getString("uf"));
//                endereco.setFkIdHotel(rs.getLong("id_hotel"));
//                return endereco;
//            }),nomeHotel,id));
//        }
//        return enderecoList;
//    }
}
