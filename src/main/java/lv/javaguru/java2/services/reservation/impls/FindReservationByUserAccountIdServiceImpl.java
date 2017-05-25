package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByUserAccountIdService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
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
    private DataInputValidator inputValidator;
    @Autowired
    private DataExistValidator existValidator;

    @Override
    public List<Reservation> find(Long userAccountID){
        inputValidator.validateUserAccountIdInput(userAccountID);
        existValidator.validateUserAccountIdExists(userAccountID);
        existValidator.validateReservationExistForUserAccountId(userAccountID);
        return reservationDAO.getByAccountID(userAccountID);
    }
}
