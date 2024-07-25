package br.com.srh.Patrivago.dao.hotel;


import br.com.srh.Patrivago.dto.ContaRequest;
import br.com.srh.Patrivago.model.conta.ContaEmpresaEntity;
import br.com.srh.Patrivago.model.hotel.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class HotelRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public HotelEntity saveHotel(HotelEntity hotel, String cnpj) {
        try {
            String sqlIdEmpresa = "SELECT id_conta_empresa FROM conta_empresa WHERE cnpj = ?";
            Long idEmpresa = jdbcTemplate.queryForObject(sqlIdEmpresa, Long.class,cnpj);

            String sql = "INSERT INTO hotel (nome, qtde_quarto, qtde_quarto_disponivel, hotel_email,id_conta_empresa) VALUES (?, ?, ?, ?,?)";
            jdbcTemplate.update(sql, hotel.getNome(), hotel.getQtdeQuarto(), hotel.getQtdeQuartoDisponivel(),hotel.getHotelEmail() ,idEmpresa);

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

}
