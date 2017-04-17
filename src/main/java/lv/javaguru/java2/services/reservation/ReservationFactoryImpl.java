package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.validate.ReservationValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationValidatorImpl;

import java.time.LocalDate;

import static lv.javaguru.java2.domain.ReservationBuilder.createReservation;

/**
 * Created by user on 09.04.2017.
 */
public class ReservationFactoryImpl implements ReservationFactory {

    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private ReservationValidator reservationValidator = new ReservationValidatorImpl();

    public ReservationFactoryImpl(ReservationValidator validator, ReservationDAO dao){
        reservationValidator = validator;
        reservationDAO = dao;
    }

    @Override
    public Reservation create(LocalDate dateFrom, LocalDate dateTo,
                              Long accountID, Long resourceID){
        reservationValidator.validate(dateFrom, dateTo, accountID, resourceID);
        Reservation reservation = createReservation()
                .withDateFrom(dateFrom)
                .withDateTo(dateTo)
                .withAccountID(accountID)
                .withResourceID(resourceID)
                .withStatus(ReservationStatus.OPEN)
                .build();

            return reservationDAO.save(reservation);

    }
}
