package lv.javaguru.java2.configs;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservation.*;
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
        FindByUserAccountIdService findByUserAccountIdService = context.getBean(FindByUserAccountIdService.class);
        FindByReservationIdService findByReservationIdService = context.getBean(FindByReservationIdService.class);
        FindByResourceIdService findByResourceIdService = context.getBean(FindByResourceIdService.class);
        CreateReservationService createReservationService = context.getBean(CreateReservationService.class);
        assertThat(service, is(notNullValue()));
        assertThat(reservationDAO, is(notNullValue()));
        assertThat(findByUserAccountIdService, is(notNullValue()));
        assertThat(findByReservationIdService, is(notNullValue()));
        assertThat(findByResourceIdService, is(notNullValue()));
        assertThat(createReservationService, is(notNullValue()));
    }
}