package lv.javaguru.java2.configs;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservation.CloseReservationService;
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
        assertThat(service, is(notNullValue()));
        assertThat(reservationDAO, is(notNullValue()));
    }
}