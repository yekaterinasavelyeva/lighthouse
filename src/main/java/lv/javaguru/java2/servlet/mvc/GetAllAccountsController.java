package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import lv.javaguru.java2.services.useraccount.GetAllAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 04.05.2017.
 */
@Component
public class GetAllAccountsController implements MVCController {

    @Autowired
    GetAllAccountsService service;
    FindUserAccountService findService;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/getAllAccounts.jsp", service.getAllAccountsList());
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        System.out.println("Account ID is:" + accountId);
        return new MVCModel("/userAccountSearchResult.jsp", "accountId");
    }
}
