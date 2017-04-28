package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.CreateReservationService;
import lv.javaguru.java2.services.reservation.validate.ReservationFactoryValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static lv.javaguru.java2.domain.ReservationBuilder.createReservation;

/**
 * Created by user on 09.04.2017.
 */
@Component
public class CreateReservationServiceImpl implements CreateReservationService {

    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private ReservationFactoryValidator reservationFactoryValidator;
    @Autowired
    private ReservationRuleValidator reservationRuleValidator;

    @Override
    public Reservation create(LocalDate dateFrom, LocalDate dateTo,
                              Long accountID, Long resourceID){
        reservationFactoryValidator.validate(dateFrom, dateTo, accountID, resourceID);
        reservationRuleValidator.validateReservationForResourceMustBeClosed(resourceID);
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
