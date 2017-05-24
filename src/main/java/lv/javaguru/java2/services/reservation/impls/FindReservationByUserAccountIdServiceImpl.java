package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByUserAccountIdService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */

@Component
class FindReservationByUserAccountIdServiceImpl implements FindReservationByUserAccountIdService {

    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;
    @Autowired
    private UserAccountIdValidator userAccountIdValidator;
    @Autowired
    private DataExistValidator dataExistValidator;

    @Override
    public List<Reservation> find(Long userAccountID){
        userAccountIdValidator.validate(userAccountID); // TODO: 28/04/17 further improvement - to inputValidator + dataExistValidator
        dataExistValidator.validateReservationExistForUserAccountId(userAccountID);
        return reservationDAO.getByAccountID(userAccountID);
    }
}
