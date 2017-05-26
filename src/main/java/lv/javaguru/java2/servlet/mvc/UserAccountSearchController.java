package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 26.05.2017.
 */
@Component
public class UserAccountSearchController implements MVCController {

    @Autowired
    FindUserAccountService findService;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/userAccountSearch.jsp");
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        //return null;
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        request.getSession().setAttribute("accountId", accountId);
        //return null;
        return new MVCModel("/userAccountSearchResult.jsp", findService.getUserAccount(accountId));
    }
}
