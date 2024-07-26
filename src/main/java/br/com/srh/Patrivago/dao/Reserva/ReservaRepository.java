package br.com.srh.Patrivago.dao.Reserva;

import br.com.srh.Patrivago.model.reserva.ReservaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

        }
        catch (Exception e)
        {
            System.out.println("Erro ao realizar a reserva "+e);
        }
        return reserva;
    }
}
