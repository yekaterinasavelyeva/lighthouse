package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.*;
import lv.javaguru.java2.services.reservation.validate.impls.ReservationEndDateValidatorImpl;
import lv.javaguru.java2.services.reservation.validate.impls.ReservationIdValidatorImpl;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Created by user on 18.04.2017.
 */
public class ProlongReservationServiceImpl implements ProlongReservationService {

    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private ReservationEndDateValidator reservationValidator = new ReservationEndDateValidatorImpl();
    private ReservationIdValidator reservationIdValidator = new ReservationIdValidatorImpl();

    public ProlongReservationServiceImpl (ReservationIdValidator idValidator, ReservationEndDateValidator validator, ReservationDAO dao){
        reservationIdValidator = idValidator;
        reservationDAO = dao;
        reservationValidator = validator;
    }

    @Override
    public void prolong(LocalDate dateTo, Long reservationId){
        reservationIdValidator.validate(reservationId);
        Optional<Reservation> userAccOpt = reservationDAO.getByID(reservationId);
        if (!userAccOpt.isPresent()) {
            throw new IllegalArgumentException("Reservation account not found by id = " + reservationId);
        }

        Reservation reservation = userAccOpt.get();
        reservationValidator.validate(dateTo);
        reservation.setDateTo(dateTo);
        reservationDAO.update(reservation);
    }
}
