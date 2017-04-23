package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidatorImpl;

import java.util.Optional;

/**
 * Created by user on 18.04.2017.
 */
public class FindByReservationIdServiceImpl implements FindByReservationIdService{
    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private ReservationIdValidator reservationValidator = new ReservationIdValidatorImpl();

    public FindByReservationIdServiceImpl (ReservationIdValidator validator, ReservationDAO dao){
        reservationDAO = dao;
        reservationValidator = validator;
    }
    public Reservation find(Long reservationId){
        reservationValidator.validate(reservationId);
        Optional<Reservation> userAccOpt = reservationDAO.getByID(reservationId);
        if (!userAccOpt.isPresent()) {
            throw new IllegalArgumentException("Reservation not found by id = " + reservationId);
        }
        return userAccOpt.get();

    }
}
