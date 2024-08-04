package br.com.srh.Patrivago.dao.Reserva;

import br.com.srh.Patrivago.dto.ContaClienteResponse;
import br.com.srh.Patrivago.dto.HotelResponse;
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

        //    String sqlIdCliente = ("SELECT id_conta_cliente from conta_cliente WHERE cpf = ?");
        //Long idCLiente = jdbcTemplate.queryForObject(sqlIdCliente, Long.class, cpf);

        String sqlCliente = ("SELECT * FROM conta_cliente INNER JOIN conta ON conta_cliente.id_conta = conta.id_conta WHERE cpf = ?");

        ContaClienteResponse clienteResponse = jdbcTemplate.queryForObject(sqlCliente,
                new BeanPropertyRowMapper<>(ContaClienteResponse.class),
                cpf);

//            String sqlIdHotel = ("SELECT id_hotel FROM hotel WHERE hotel_email = ?");
//            Long idHotel = jdbcTemplate.queryForObject(sqlIdHotel, Long.class, reserva.getHotelEmail());

        String sqlHotel = ("SELECT * FROM hotel WHERE hotel_email = ?");

        HotelResponse hotelResponse = jdbcTemplate.queryForObject(sqlHotel,
                new BeanPropertyRowMapper<>(HotelResponse.class),
                reserva.getHotelEmail());

        String sql = ("INSERT INTO reserva" +
                "(nome_hotel," +
                "hotel_email," +
                "nome_cliente," +
                "check_in," +
                "check_out," +
                "id_conta_cliente," +
                "id_hotel," +
                "id_reserva_status)" +
                " VALUES(?,?,?,?,?,?,?,?)");

        jdbcTemplate.update(sql,
                hotelResponse.getNome(),
                reserva.getHotelEmail(),
                clienteResponse.getNome(),
                reserva.getCheckIn(),
                reserva.getCheckOut(),
                clienteResponse.getIdContaCliente(),
                hotelResponse.getIdHotel(),
                1);


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
            reserva.setIdReservaStatus(rs.getInt("id_reserva_status"));
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
            reserva.setIdReservaStatus(rs.getInt("id_reserva_status"));
            return reserva;
        }), idHotel);
    }

    public ReservaEntity getReservaInfo(Long idCliente, Long idReserva) {
        String sql = ("SELECT * \n" +
                "FROM reserva\n" +
                "INNER JOIN conta_cliente \n" +
                "ON reserva.id_conta_cliente = conta_cliente.id_conta_cliente \n" +
                "WHERE id_reserva = ? \n" +
                "AND reserva.id_conta_cliente = ?\n");

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ReservaEntity.class), idReserva, idCliente);
    }

    public ReservaEntity getReservaInfo(Long idReserva) {
        String sql = ("SELECT * \n" +
                "FROM reserva\n" +
                "INNER JOIN conta_cliente \n" +
                "ON reserva.id_conta_cliente = conta_cliente.id_conta_cliente \n" +
                "WHERE id_reserva = ?\n");

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ReservaEntity.class), idReserva);
    }

    public ReservaEntity updateReserva(ReservaEntity reservaSalva, Long idReserva) {
        String sql = ("UPDATE reserva SET check_in = ?, check_out = ? WHERE id_reserva = ?");
        jdbcTemplate.update(sql, reservaSalva.getCheckIn(), reservaSalva.getCheckOut(), idReserva);
        return reservaSalva;
    }

    public ReservaEntity ativarCheckin(Long idReserva, ReservaEntity reservaSalva) {
        String sql = ("UPDATE reserva SET id_reserva_status = ? WHERE id_reserva = ?");
        jdbcTemplate.update(sql, reservaSalva.getIdReservaStatus(), idReserva);
        return reservaSalva;

    }

    public ReservaEntity ativarCheckOut(Long idReserva, ReservaEntity reservaSalva) {
        String sql = ("UPDATE reserva SET id_reserva_status = ? WHERE id_reserva = ?");
        jdbcTemplate.update(sql, reservaSalva.getIdReservaStatus(), idReserva);
        return reservaSalva;

    }

    public String getReservaCheckinDate(Long idReserva) {
        String sql = ("SELECT check_in FROM reserva WHERE id_reserva = ?");
        return jdbcTemplate.queryForObject(sql, String.class, idReserva);
    }

    public String getReservaCheckoutData(Long idReserva) {
        String sql = ("SELECT check_out FROM reserva WHERE id_reserva = ?");
        return jdbcTemplate.queryForObject(sql, String.class, idReserva);

    }

    public Integer checkinAtivadoVerify(Long idReserva) {
        String sql = ("SELECT id_reserva_status FROM reserva WHERE id_reserva = ?");
        return jdbcTemplate.queryForObject(sql, Integer.class, idReserva);
    }

    public Integer reservaExist(Long idReserva) {
        String sql = ("SELECT COUNT(*) FROM reserva WHERE id_reserva = ?");
        return jdbcTemplate.queryForObject(sql, Integer.class, idReserva);
    }
}
