package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindByUserAccountIdService;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */

@Component
public class FindByUserAccountIdServiceImpl implements FindByUserAccountIdService {

    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private UserAccountIdValidator userAccountIdValidator;
    @Autowired
    private SearchValidator searchValidator;

    @Override
    public List<Reservation> find(Long userAccountID){
        userAccountIdValidator.validate(userAccountID);
        searchValidator.validateReservationExistForUserAccountID(userAccountID);
        return reservationDAO.getByAccountID(userAccountID);
    }
}
