package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.services.resource.GetAllResourcesService;
import lv.javaguru.java2.services.resource.GetAllResourcesServiceImpl;
import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import lv.javaguru.java2.services.useraccount.FindUserAccountServiceImpl;
import lv.javaguru.java2.services.useraccount.GetAllAccountsService;
import lv.javaguru.java2.services.useraccount.GetAllAccountsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 04.05.2017.
 */
@Component
public class GetAllAccountsController implements MVCController {

    @Autowired
    GetAllAccountsService service = new GetAllAccountsServiceImpl();
    FindUserAccountService findService = new FindUserAccountServiceImpl();

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/getAllAccounts.jsp", service.getAllAccountsList());
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        Long accountId = Long.parseLong(request.getParameter("userAccountId"));
        return new MVCModel("/userAccountSearchResult.jsp", findService.getUserAccount(accountId));
    }
}
