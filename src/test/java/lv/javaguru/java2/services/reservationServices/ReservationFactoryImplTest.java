package lv.javaguru.java2.services.reservationServices;


import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservationServices.validate.ReservationValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 09.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationFactoryImplTest {

    private ReservationFactory factory;
    private ReservationDAO reservationDAO;
    private ReservationValidator validator;

    private static final Long RESERVATIONID = 1234l;
    private static final LocalDate DATEFROM = LocalDate.now();
    private static final LocalDate DATETO = LocalDate.now().plusDays(14);
    private static final Long ACCOUNTID = 1234l;
    private static final Long RESOURCEID = 1234l;
    private static final ReservationStatus STATUS = ReservationStatus.OPEN;

    @Before
    public void init() {
        validator = mock(ReservationValidator.class);
        reservationDAO = mock(ReservationDAO.class);
        factory = new ReservationFactoryImpl(validator, reservationDAO);
    }

    @Test
    public void shouldBePossibilityToProvideReservationDetails() {
        factory = mock(ReservationFactory.class);
        factory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        verify(factory).create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
    }

    @Test
    public void checkReservationCreationMethodsOrder() {
        factory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        InOrder inOrder = Mockito.inOrder(validator, reservationDAO);
        inOrder.verify(validator).validate(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        inOrder.verify(reservationDAO).getByResourceID(RESERVATIONID);
        inOrder.verify(reservationDAO).save(any(Reservation.class));
    }


    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenThereIsOpenedReservationForThisResource(){
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservationA = Mockito.mock(Reservation.class);
        Reservation reservationB = Mockito.mock(Reservation.class);
        reservations.add(reservationA);
        reservations.add(reservationB);
        when(reservationDAO.getByResourceID(RESOURCEID))
                .thenReturn(reservations);
        when(reservationA.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        when(reservationB.getStatus())
                .thenReturn(ReservationStatus.OPEN);
        factory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
    }

    @Test
    public void shouldCreateNewReservationIfAllOthersClosedForThisResource(){
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservationA = Mockito.mock(Reservation.class);
        Reservation reservationB = Mockito.mock(Reservation.class);
        reservations.add(reservationA);
        reservations.add(reservationB);
        when(reservationDAO.getByResourceID(RESOURCEID))
                .thenReturn(reservations);
        when(reservationA.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        when(reservationB.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        factory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        verify(reservationDAO).save(any(Reservation.class));
    }
}