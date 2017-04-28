package lv.javaguru.java2.services.reservation;


import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.impls.CreateReservationServiceImpl;
import lv.javaguru.java2.services.reservation.validate.CreateReservationServiceValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 09.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateReservationServiceImplTest {

    private static final LocalDate DATEFROM = LocalDate.now();
    private static final LocalDate DATETO = LocalDate.now().plusDays(14);
    private static final Long ACCOUNTID = 1234l;
    private static final Long RESOURCEID = 1234l;

    @Mock
    ReservationDAO reservationDAO;
    @Mock
    CreateReservationServiceValidator createReservationServiceValidator;
    @Mock
    ReservationRuleValidator reservationRuleValidator;
    @InjectMocks
    CreateReservationService createReservationService = new CreateReservationServiceImpl();

    @Test
    public void checkMethodsOrderForReservationFactory() {
        createReservationService.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        InOrder inOrder = Mockito.inOrder(createReservationServiceValidator, reservationDAO, reservationRuleValidator);
        inOrder.verify(createReservationServiceValidator).validate(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        inOrder.verify(reservationRuleValidator).validateResourceIdForNewReservation(RESOURCEID);
        inOrder.verify(reservationDAO).save(any(Reservation.class));
    }
}