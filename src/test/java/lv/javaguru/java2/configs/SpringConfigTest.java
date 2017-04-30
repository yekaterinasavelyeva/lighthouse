package lv.javaguru.java2.configs;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.services.reservation.*;
import lv.javaguru.java2.services.resource.DeleteResourceService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by mobileqa on 26/04/17.
 */
public class SpringConfigTest {

    @Test
    public void testStarting() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CloseReservationService service = context.getBean(CloseReservationService.class);
        ReservationDAO reservationDAO = context.getBean(ReservationDAO.class);
        FindReservationByUserAccountIdService findReservationByUserAccountIdService = context.getBean(FindReservationByUserAccountIdService.class);
        FindReservationByIdService findReservationByIdService = context.getBean(FindReservationByIdService.class);
        FindReservationByResourceIdService findReservationByResourceIdService = context.getBean(FindReservationByResourceIdService.class);
        CreateReservationService createReservationService = context.getBean(CreateReservationService.class);
        assertThat(service, is(notNullValue()));
        assertThat(reservationDAO, is(notNullValue()));
        assertThat(findReservationByUserAccountIdService, is(notNullValue()));
        assertThat(findReservationByIdService, is(notNullValue()));
        assertThat(findReservationByResourceIdService, is(notNullValue()));
        assertThat(createReservationService, is(notNullValue()));
    }

    @Test
    public void testResource(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DeleteResourceService service = context.getBean(DeleteResourceService.class);
        ResourceDAO resourceDAO = context.getBean(ResourceDAO.class);
        assertThat(service, is(notNullValue()));
        assertThat(resourceDAO, is(notNullValue()));
    }
}