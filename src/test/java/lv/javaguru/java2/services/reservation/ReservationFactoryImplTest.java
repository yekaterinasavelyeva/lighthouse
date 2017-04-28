package lv.javaguru.java2.services.reservation;


import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.impls.ReservationFactoryImpl;
import lv.javaguru.java2.services.reservation.validate.ReservationForResourceValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationFactoryValidator;
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
public class ReservationFactoryImplTest {

    private static final LocalDate DATEFROM = LocalDate.now();
    private static final LocalDate DATETO = LocalDate.now().plusDays(14);
    private static final Long ACCOUNTID = 1234l;
    private static final Long RESOURCEID = 1234l;

    @Mock
    ReservationDAO reservationDAO;
    @Mock
    ReservationFactoryValidator reservationFactoryValidator;
    @Mock
    ReservationForResourceValidator reservationForResourceValidator;
    @InjectMocks
    ReservationFactory reservationFactory = new ReservationFactoryImpl();

    @Test
    public void checkMethodsOrderForReservationFactory() {
        reservationFactory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        InOrder inOrder = Mockito.inOrder(reservationFactoryValidator, reservationDAO, reservationForResourceValidator);
        inOrder.verify(reservationFactoryValidator).validate(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        inOrder.verify(reservationForResourceValidator).validateReservationClosedForResourceId(RESOURCEID);
        inOrder.verify(reservationDAO).save(any(Reservation.class));
    }
}