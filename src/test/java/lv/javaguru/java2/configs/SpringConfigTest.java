package lv.javaguru.java2.configs;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.services.reservation.*;
import lv.javaguru.java2.services.resource.*;
import lv.javaguru.java2.services.useraccount.*;
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
        DeleteResourceService deleteResourceService = context.getBean(DeleteResourceService.class);
        ResourceDAO resourceDAO = context.getBean(ResourceDAO.class);
        EditResourceService editResourceService = context.getBean(EditResourceService.class);
        FindResourceService findResourceService = context.getBean(FindResourceService.class);
        GetAllResourcesService getAllResourcesService = context.getBean(GetAllResourcesService.class);
        CreateResourceService createResourceService = context.getBean(CreateResourceService.class);
        assertThat(deleteResourceService, is(notNullValue()));
        assertThat(resourceDAO, is(notNullValue()));
        assertThat(editResourceService, is(notNullValue()));
        assertThat(findResourceService, is(notNullValue()));
        assertThat(getAllResourcesService, is(notNullValue()));
        assertThat(createResourceService, is(notNullValue()));
    }

    @Test
    public void testUserAccount(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DeleteUserAccountService deleteUserAccountService = context.getBean(DeleteUserAccountService.class);
        UserAccountDAO userAccountDAO = context.getBean(UserAccountDAO.class);
        EditUserAccountService editUserAccountService = context.getBean(EditUserAccountService.class);
        FindUserAccountService findUserAccountService = context.getBean(FindUserAccountService.class);
        GetAllAccountsService getAllAccountsService = context.getBean(GetAllAccountsService.class);
        CreateUserAccountService createUserAccountService = context.getBean(CreateUserAccountService.class);
        assertThat(deleteUserAccountService, is(notNullValue()));
        assertThat(userAccountDAO, is(notNullValue()));
        assertThat(editUserAccountService, is(notNullValue()));
        assertThat(findUserAccountService, is(notNullValue()));
        assertThat(getAllAccountsService, is(notNullValue()));
        assertThat(createUserAccountService, is(notNullValue()));
    }
}