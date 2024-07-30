package br.com.srh.Patrivago.dao.Reserva;

import br.com.srh.Patrivago.dto.ReservaResponse;
import br.com.srh.Patrivago.model.reserva.ReservaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public ReservaEntity saveReserva(ReservaEntity reserva, String cpf) {
        try {

            String sqlIdCliente = ("SELECT id_conta_cliente from conta_cliente WHERE cpf = ?");
            Long idCLiente = jdbcTemplate.queryForObject(sqlIdCliente, Long.class, cpf);
            String sqlIdHotel = ("SELECT id_hotel FROM hotel WHERE nome = ? AND hotel_email = ?");
            Long idHotel = jdbcTemplate.queryForObject(sqlIdHotel, Long.class, reserva.getNomeHotel(), reserva.getHotelEmail());
            String sql = ("INSERT INTO reserva" +
                    "(nome_hotel," +
                    "hotel_email," +
                    "nome_cliente," +
                    "check_in," +
                    "check_out," +
                    "id_conta_cliente," +
                    "id_hotel)" +
                    " VALUES(?,?,?,?,?,?,?)");

            jdbcTemplate.update(sql,
                    reserva.getNomeHotel(),
                    reserva.getHotelEmail(),
                    reserva.getNomeCliente(),
                    reserva.getCheckIn(),
                    reserva.getCheckOut(),
                    idCLiente,
                    idHotel);


            System.out.println("Reserva feita com sucesso");

        } catch (Exception e) {
            System.out.println("Erro ao realizar a reserva " + e);
        }
        return reserva;
    }

    public List<ReservaResponse> getClienteReserva(Long idCliente) {

        String sql = ("SELECT * FROM reserva WHERE id_conta_Cliente = ?");
        return jdbcTemplate.query(sql, (rs, rowNum) ->
        {
            ReservaResponse reserva = new ReservaResponse();
            reserva.setIdReserva(rs.getLong("id_reserva"));
            reserva.setNomeHotel(rs.getString("nome_hotel"));
            reserva.setNomeCliente(rs.getString("nome_cliente"));
            reserva.setHotelEmail(rs.getString("hotel_email"));
            reserva.setCheckIn(rs.getString("check_in"));
            reserva.setCheckOut(rs.getString("check_out"));
            return reserva;
        }, idCliente);
    }


    public List<ReservaResponse> getHotelReserva(Long idHotel) {
        String sql = ("SELECT * from reserva WHERE id_hotel = ?");
        return jdbcTemplate.query(sql, ((rs, rowNum) ->
        {
            ReservaResponse reserva = new ReservaResponse();
            reserva.setIdReserva(rs.getLong("id_reserva"));
            reserva.setNomeHotel(rs.getString("nome_hotel"));
            reserva.setNomeCliente(rs.getString("nome_cliente"));
            reserva.setHotelEmail(rs.getString("hotel_email"));
            reserva.setCheckIn(rs.getString("check_in"));
            reserva.setCheckOut(rs.getString("check_out"));
            return reserva;
        }), idHotel);
    }

    public ReservaEntity getReservaInfo(Long idCliente, Long idReserva) {
        String sql=("SELECT * \n" +
                "FROM reserva\n" +
                "INNER JOIN conta_cliente \n" +
                "ON reserva.id_conta_cliente = conta_cliente.id_conta_cliente \n" +
                "WHERE id_reserva = ? \n" +
                "AND reserva.id_conta_cliente = ?\n");

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ReservaEntity.class),idReserva,idCliente);
    }

    public ReservaEntity updateReserva(ReservaEntity reservaSalva, Long idReserva) {
        String sql=("UPDATE reserva SET check_in = ?, check_out = ? WHERE id_reserva = ?");
         jdbcTemplate.update(sql,reservaSalva.getCheckIn(),reservaSalva.getCheckOut(),idReserva);
        return reservaSalva;
    }
}
