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

    @Override
    public Reservation create(LocalDate dateFrom, LocalDate dateTo, Long accountID){
        reservationValidator.validate(dateFrom, dateTo, accountID);
        Reservation reservation = createReservation()
                .withDateFrom(dateFrom)
                .withDateTo(dateTo)
                .withAccountID(accountID)
                .withStatus(ReservationStatus.OPEN)
                .build();

            return reservationDAO.save(reservation);

    }
}
