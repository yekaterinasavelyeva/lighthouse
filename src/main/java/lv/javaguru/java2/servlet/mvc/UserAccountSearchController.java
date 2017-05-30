package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import lv.javaguru.java2.servlet.mvc.MVCController;
import lv.javaguru.java2.servlet.mvc.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 26.05.2017.
 */
@Component
public class UserAccountSearchController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/userAccountSearch.jsp");
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        return null;
    }
}
