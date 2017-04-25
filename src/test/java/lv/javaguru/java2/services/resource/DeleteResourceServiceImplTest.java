package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteResourceServiceImplTest {

    private DeleteResourceService service;
    private ResourceDAO resourceDAO;
    private ReservationDAO reservationDAO;
    private ResourceIdValidator deleteValidator;
    private Resource resource = new Resource();

    private static final Long resourceId = 1234l;

    @Before
    public void init() {
        deleteValidator = mock(ResourceIdValidator.class);
        resourceDAO = mock(ResourceDAO.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new DeleteResourceServiceImpl(deleteValidator, resourceDAO, reservationDAO);
        resource.setResourceID(resourceId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfResourceNotFoundTest(){
        service.deleteResource(1001l);
    }

    @Test
    public void shouldBePossibilityToProvideResourceID() {
        service = mock(DeleteResourceService.class);
        service.deleteResource(resourceId);
        verify(service).deleteResource(resourceId);
    }

    @Test
    public void checkResourceDeletionMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(deleteValidator, resourceDAO, reservationDAO);
        when(resourceDAO.getByID(resourceId)).thenReturn(Optional.of(resource));
        service.deleteResource(resourceId);
        inOrder.verify(deleteValidator).validate(resourceId);
        inOrder.verify(reservationDAO).getByResourceID(resourceId);
        inOrder.verify(resourceDAO).delete(resourceId);
    }

    @Test
    public void deleteResourceFromDatabaseTest(){
        when(resourceDAO.getByID(resourceId)).thenReturn(Optional.of(resource));
        when(resourceDAO.getAll()).thenReturn(Arrays.asList(new Resource(), new Resource()));
        service.deleteResource(resource.getResourceID());
        List<Resource> resources = resourceDAO.getAll();
        assertFalse(resources.contains(resource));
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfThereAreOpenedReservationsForThisResource(){
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservationA = Mockito.mock(Reservation.class);
        Reservation reservationB = Mockito.mock(Reservation.class);
        reservations.add(reservationA);
        reservations.add(reservationB);
        when(resourceDAO.getByID(resourceId))
                .thenReturn(Optional.of(resource));
        when(reservationDAO.getByResourceID(resourceId))
                .thenReturn(reservations);
        when(reservationA.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        when(reservationB.getStatus())
                .thenReturn(ReservationStatus.OPEN);
        service.deleteResource(resourceId);
    }

    @Test
    public void shouldDeleteResourceWhenAllReservationsForItAreClosed(){
        List<Reservation> reservations = new ArrayList<>();
        Reservation reservationA = Mockito.mock(Reservation.class);
        Reservation reservationB = Mockito.mock(Reservation.class);
        reservations.add(reservationA);
        reservations.add(reservationB);
        when(resourceDAO.getByID(resourceId))
                .thenReturn(Optional.of(resource));
        when(reservationDAO.getByResourceID(resourceId))
                .thenReturn(reservations);
        when(reservationA.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        when(reservationB.getStatus())
                .thenReturn(ReservationStatus.CLOSED);
        service.deleteResource(resourceId);
        verify(resourceDAO).delete(resourceId);
    }


}