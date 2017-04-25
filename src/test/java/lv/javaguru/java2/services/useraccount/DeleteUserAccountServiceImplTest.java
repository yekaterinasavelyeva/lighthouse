package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by user on 07.04.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserAccountServiceImplTest {

    private DeleteUserAccountService service;
    private UserAccountDAO userAccountDAO;
    private ReservationDAO reservationDAO;
    private UserAccountIdValidator deleteValidator;
    private UserAccount account = new UserAccount();

    private static final Long userAccountId = 1234l;

    @Before
    public void init() {
        deleteValidator = mock(UserAccountIdValidator.class);
        userAccountDAO = mock(UserAccountDAO.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new DeleteUserAccountServiceImpl(deleteValidator, userAccountDAO, reservationDAO);
        account.setUserAccountId(userAccountId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest(){
        service.deleteUserAccount(1001l);
    }

    @Test
    public void shouldBePossibilityToProvideUserAccountID() {
        service = mock(DeleteUserAccountService.class);
        service.deleteUserAccount(userAccountId);
        verify(service).deleteUserAccount(userAccountId);
    }

    @Test
    public void checkAccountDeletionMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(deleteValidator, userAccountDAO, reservationDAO);
        when(userAccountDAO.getById(userAccountId)).thenReturn(Optional.of(account));
        service.deleteUserAccount(userAccountId);
        inOrder.verify(deleteValidator).validate(userAccountId);
        inOrder.verify(reservationDAO).getByAccountID(userAccountId);
        inOrder.verify(userAccountDAO).delete(userAccountId);
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfThereAreOpenedReservationsForThisAccount(){
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservationA = Mockito.mock(Reservation.class);
        Reservation reservationB = Mockito.mock(Reservation.class);
        reservations.add(reservationA);
        reservations.add(reservationB);
        when(userAccountDAO.getById(userAccountId))
                .thenReturn(Optional.of(account));
        when(reservationDAO.getByAccountID(userAccountId))
                .thenReturn(reservations);
        when(reservationA.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        when(reservationB.getStatus())
                .thenReturn(ReservationStatus.OPEN);
        service.deleteUserAccount(userAccountId);
    }

    @Test
    public void shouldDeleteAccountWhenAllReservationsForItAreClosed(){
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservationA = Mockito.mock(Reservation.class);
        Reservation reservationB = Mockito.mock(Reservation.class);
        reservations.add(reservationA);
        reservations.add(reservationB);
        when(userAccountDAO.getById(userAccountId))
                .thenReturn(Optional.of(account));
        when(reservationDAO.getByAccountID(userAccountId))
                .thenReturn(reservations);
        when(reservationA.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        when(reservationB.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        service.deleteUserAccount(userAccountId);
    }

}