package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByResourceIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Viktor on 2017.05.30..
 */
@Component
public class ResourceReservationsController implements MVCController {

    @Autowired
    FindReservationByResourceIdService service;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        Long resourceIdFromRequest = Long.parseLong(request.getParameter("resourceId"));
        List<Reservation> reservationsFoundByService = service.find(resourceIdFromRequest);
        return new MVCModel("/resourceReservations.jsp", reservationsFoundByService);
    }
}
