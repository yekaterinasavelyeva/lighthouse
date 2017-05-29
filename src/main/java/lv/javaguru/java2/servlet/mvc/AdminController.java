package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 29.05.2017.
 */
@Component
public class AdminController implements MVCController {
    @Autowired
    FindUserAccountService findService;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/adminPage.jsp");
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        request.getSession().setAttribute("accountId", accountId);
        //return null;
        return new MVCModel("/userAccountSearchResult.jsp", findService.getUserAccount(accountId));
    }
}

