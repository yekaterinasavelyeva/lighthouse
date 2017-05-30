package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.services.reservation.FindReservationByUserAccountIdService;
import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 30.05.2017.
 */
@Component
public class UserReservationsSearchResultController implements MVCController{

    @Autowired
    FindReservationByUserAccountIdService reservationFindService;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        request.getAttribute("accountId");
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        request.getSession().setAttribute("accountId", accountId);
        //return null;
        return new MVCModel("/userReservationsSearchResult.jsp", reservationFindService.find(accountId));
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        request.getAttribute("accountId");
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        request.getSession().setAttribute("accountId", accountId);
        //return null;
        return new MVCModel("/userReservationsSearchResult.jsp", reservationFindService.find(accountId));
    }
}
