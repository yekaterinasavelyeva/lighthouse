package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.ReservationFactory;
import lv.javaguru.java2.services.reservation.validate.ReservationForResourceValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationFactoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static lv.javaguru.java2.domain.ReservationBuilder.createReservation;

/**
 * Created by user on 09.04.2017.
 */
@Component
public class ReservationFactoryImpl implements ReservationFactory {

    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private ReservationFactoryValidator reservationFactoryValidator;
    @Autowired
    private ReservationForResourceValidator reservationForResourceValidator;

    @Override
    public Reservation create(LocalDate dateFrom, LocalDate dateTo,
                              Long accountID, Long resourceID){
        reservationFactoryValidator.validate(dateFrom, dateTo, accountID, resourceID);
        reservationForResourceValidator.validateReservationClosedForResourceId(resourceID);
        Reservation reservation = buildWithParametrs(dateFrom, dateTo, accountID, resourceID);
        return reservationDAO.save(reservation);

    }

    private Reservation buildWithParametrs(LocalDate dateFrom, LocalDate dateTo, Long accountID, Long resourceID) {
        return createReservation()
                    .withDateFrom(dateFrom)
                    .withDateTo(dateTo)
                    .withAccountID(accountID)
                    .withResourceID(resourceID)
                    .withStatus(ReservationStatus.OPEN)
                    .build();
    }
}
