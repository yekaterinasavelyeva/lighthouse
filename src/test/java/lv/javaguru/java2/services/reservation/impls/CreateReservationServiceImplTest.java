package lv.javaguru.java2.services.reservation.impls;


import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.CreateReservationService;
import lv.javaguru.java2.services.validators.ReservationPropertyValidator;
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
    ReservationPropertyValidator reservationPropertyValidator;
    @InjectMocks
    CreateReservationService createReservationService = new CreateReservationServiceImpl();

    @Test
    public void checkMethodsOrderForReservationFactory() {
        createReservationService.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        InOrder inOrder = Mockito.inOrder(reservationPropertyValidator, reservationDAO);
        inOrder.verify(reservationPropertyValidator).validate(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        inOrder.verify(reservationDAO).save(any(Reservation.class));
    }
}