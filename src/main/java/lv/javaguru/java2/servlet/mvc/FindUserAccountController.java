package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 11.05.2017.
 */
@Component
public class FindUserAccountController implements MVCController {
    @Autowired
    FindUserAccountService findService;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        String accountId = request.getParameter("accountId");
        System.out.println(accountId);
        Long accountID = Long.parseLong(accountId);
        return new MVCModel("/userAccountSearchResult.jsp", findService.getUserAccount(accountID));
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        request.getSession().setAttribute("accountId", accountId);
        //return null;
        return new MVCModel("/userAccountSearchResult.jsp", findService.getUserAccount(accountId));
    }
}
