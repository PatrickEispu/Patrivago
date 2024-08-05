package br.com.srh.Patrivago.usecase;

import br.com.srh.Patrivago.dao.Reserva.ReservaRepository;
import br.com.srh.Patrivago.dto.ReservaRequest;
import br.com.srh.Patrivago.dto.ReservaResponse;
import br.com.srh.Patrivago.exception.*;
import br.com.srh.Patrivago.model.reserva.ReservaEntity;
import br.com.srh.Patrivago.util.CpfService;
import br.com.srh.Patrivago.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    HotelService hotelService;
    @Autowired
    ContaService contaService;
    @Autowired
    CpfService cpfService;
    @Autowired
    EmailService emailService;


    public ReservaResponse addReserva(ReservaRequest reservaRequest, String cpf) {
        reservaCheckRequest(reservaRequest, cpf);

        ReservaEntity reserva = ReservaEntity.builder()
                .hotelEmail(reservaRequest.getHotelEmail())
                .checkIn(reservaRequest.getCheckIn())
                .checkOut(reservaRequest.getCheckOut())
                .build();
        ReservaEntity reservaSalva = reservaRepository.saveReserva(reserva, cpf);

        hotelService.blockRoom(reservaRequest.getHotelEmail());

        return mapearReserva(reservaSalva);


    }

    private void reservaCheckRequest(ReservaRequest reservaRequest, String cpf) {
        if (!cpfService.isValidCPF(cpf)) {
            throw new CpfException();
        }
        if (!contaService.clienteCpfExist(cpf)) {
            throw new CpfDontExistException();
        }
//        if (!hotelService.hotelExist(reservaRequest.getNomeHotel())) {
//            throw new HotelDontExistException();
//        }
        if (!emailService.isValidEmail(reservaRequest.getHotelEmail())) {
            throw new EmailException();
        }
        if (!hotelService.hotelEmailExist(reservaRequest.getHotelEmail())) {
            throw new EmailDontExist();
        }
//        if (!hotelService.hotelEmailCheck(reservaRequest.getHotelEmail(), reservaRequest.getNomeHotel())) {
//            throw new HotelWrongEmailExcpetion();
//        }
        if (!dateCorrectFormat(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new IncorretDateFormatException();
        }
        if (checkDataIsBeforeToday(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new DataInvalidaException();
        }
        if (!checkDataIsValid(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new ReservaInvalidaException();
        }
        if (reservaDayLimit(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new ReservaDayLimitException();
        }
        if (reservaMonthLimit(reservaRequest.getCheckIn())) {
            throw new ReservaMonthLimitException();
        }
        if (!hotelService.quartoDisponivelCheck(reservaRequest.getHotelEmail())) {
            throw new HotelOcupadoException();
        }
    }

    private boolean reservaMonthLimit(String checkInStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate checkin = LocalDate.parse(checkInStr, dtf);
        LocalDate limit = LocalDate.now().plusMonths(8);
        return checkin.isAfter(limit);
    }

    private boolean dateCorrectFormat(String checkInStr, String checkOutStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate checkin = LocalDate.parse(checkInStr, dtf);
            LocalDate checkout = LocalDate.parse(checkOutStr, dtf);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }


    }

    private boolean reservaDayLimit(String checkInStr, String checkOutStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate checkin = LocalDate.parse(checkInStr, dtf);
        LocalDate checkout = LocalDate.parse(checkOutStr, dtf);
        Long daysCount = ChronoUnit.DAYS.between(checkin, checkout);
        System.out.println(daysCount);
        return daysCount > 20;
    }

    private boolean checkDataIsBeforeToday(String checkinStr, String checkoutStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate checkinDate = LocalDate.parse(checkinStr, dtf);
        LocalDate checkoutDate = LocalDate.parse(checkoutStr, dtf);
        return checkinDate.isBefore(LocalDate.now()) || checkoutDate.isBefore(LocalDate.now());

    }


    private ReservaResponse mapearReserva(ReservaEntity reservaSalva) {
        return ReservaResponse.builder()
                .nomeHotel(reservaSalva.getNomeHotel())
                .nomeCliente(reservaSalva.getNomeCliente())
                .hotelEmail(reservaSalva.getHotelEmail())
                .checkIn(reservaSalva.getCheckIn())
                .checkOut(reservaSalva.getCheckOut())
                .build();
    }

    public List<ReservaResponse> geClienteReserva(String cpf) {
        if (!cpfService.isValidCPF(cpf)) {
            throw new CpfException();
        }
        if (!contaService.clienteCpfExist(cpf)) {
            throw new CpfDontExistException();
        }

        Long idCliente = contaService.getIdCliente(cpf);
        return reservaRepository.getClienteReserva(idCliente);
    }

    public List<ReservaResponse> getHotelReserva(Long idHotel) {
        if (!hotelService.hotelIdExist(idHotel)) {
            throw new HotelDontExistException();
        }
        return reservaRepository.getHotelReserva(idHotel);
    }

    public ReservaResponse updateReserva(ReservaRequest reservaRequest, String cpf, Long idReserva) {
        reservaUpdateCheckRequest(reservaRequest, cpf, idReserva);

        Long idCliente = contaService.getIdCliente(cpf);

        ReservaEntity reservaSalva = reservaRepository.getReservaInfo(idCliente, idReserva);
        reservaSalva.setCheckIn(reservaRequest.getCheckIn());
        reservaSalva.setCheckOut(reservaRequest.getCheckOut());

        ReservaEntity reservaAtualizada = reservaRepository.updateReserva(reservaSalva, idReserva);

        return mapearReserva(reservaAtualizada);

    }

    private void reservaUpdateCheckRequest(ReservaRequest reservaRequest, String cpf, Long idReserva) {
        if (!cpfService.isValidCPF(cpf)) {
            throw new CpfException();
        }
        if (!contaService.clienteCpfExist(cpf)) {
            throw new CpfDontExistException();
        }
        if (!reservaExist(idReserva)) {
            throw new ReservaDontExistException();
        }
        if (reservaStatusAlreadyCheck(idReserva)) {
            throw new CheckinAtivadoException();
        }
        if (!dateCorrectFormat(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new IncorretDateFormatException();
        }
        if (checkDataIsBeforeToday(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new DataInvalidaException();
        }
        if (!checkDataIsValid(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new ReservaInvalidaException();
        }
        if (reservaDayLimit(reservaRequest.getCheckIn(), reservaRequest.getCheckOut())) {
            throw new ReservaDayLimitException();
        }
        if (reservaMonthLimit(reservaRequest.getCheckIn())) {
            throw new ReservaMonthLimitException();
        }

    }


    public static boolean checkDataIsValid(String dataCheckIn, String dataCheckOut) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate checkIn = LocalDate.parse(dataCheckIn, dtf);
        LocalDate checkOut = LocalDate.parse(dataCheckOut, dtf);

        return !checkOut.isBefore(checkIn);
    }

    public ReservaResponse ativarCheckin(Long idReserva) {
        if (!reservaExist(idReserva)) {
            throw new ReservaDontExistException();
        }
        if (reservaStatusAlreadyCheck(idReserva)) {
            throw new CheckinAtivadoException();
        }
        if (!checkin(idReserva)) {
            throw new CheckinException();
        }
        if (reservaFinalizada(idReserva)) {
            throw new ReservaFinalizadaException();
        }
        ReservaEntity reservaSalva = reservaRepository.getReservaInfo(idReserva);
        reservaSalva.setIdReservaStatus(2);

        ReservaEntity reservaAtualizada = reservaRepository.ativarCheckin(idReserva, reservaSalva);
        return mapearReserva(reservaAtualizada);
    }

    private boolean reservaFinalizada(Long idReserva) {
        Integer reservaOrder = reservaRepository.reservaFinalizada(idReserva);
        if (reservaOrder == 2) {
            return true;
        } else {
            return false;
        }
    }

    public ReservaResponse ativarCheckout(Long idReserva) {
        if (!reservaExist(idReserva)) {
            throw new ReservaDontExistException();
        }
        if (!reservaStatusAlreadyCheck(idReserva)) {
            throw new CheckoutAtivadoException();
        }
        if (!checkout(idReserva)) {
            throw new CheckoutException();
        }
        ReservaEntity reservaSalva = reservaRepository.getReservaInfo(idReserva);
        reservaSalva.setIdReservaStatus(1);
        reservaSalva.setIdReservaOrder(2);
        hotelService.clearRoom(idReserva);

        ReservaEntity reservaAtualizada = reservaRepository.ativarCheckOut(idReserva, reservaSalva);
        return mapearReserva(reservaAtualizada);
    }

    private boolean reservaExist(Long idReserva) {
        Integer idReservaCount = reservaRepository.reservaExist(idReserva);
        if (idReservaCount > 0) {
            return true;
        }
        return false;
    }

    private boolean checkin(Long idReserva) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String checkinDateStr = reservaRepository.getReservaCheckinDate(idReserva);
        String checkoutDateStr = reservaRepository.getReservaCheckoutData(idReserva);
        LocalDate checkinDate = LocalDate.parse(checkinDateStr, dtf);
        LocalDate checkoutDate = LocalDate.parse(checkoutDateStr, dtf);
        LocalDate checkinAttemptDate = LocalDate.now();

        return !checkinAttemptDate.isBefore(checkinDate) && !checkinAttemptDate.isAfter(checkoutDate);
    }

    private boolean checkout(Long idReserva) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String checkinDateStr = reservaRepository.getReservaCheckinDate(idReserva);
        String checkoutDateStr = reservaRepository.getReservaCheckoutData(idReserva);


        LocalDate checkinDate = LocalDate.parse(checkinDateStr, dateFormat);
        LocalDate checkoutDate = LocalDate.parse(checkoutDateStr, dateFormat);
        LocalDate checkoutAttemptDate = LocalDate.now();


        // Verifica se a data de checkout está entre a data de check-in e a data final de checkout
        return !checkoutAttemptDate.isBefore(checkinDate) && !checkoutAttemptDate.isAfter(checkoutDate);


    }

    private boolean reservaStatusAlreadyCheck(Long idReserva) {
        Integer reservaStatus = reservaRepository.checkinAtivadoVerify(idReserva);
        if (reservaStatus == 1) {
            return false;
        } else {
            return true;
        }

    }
}
