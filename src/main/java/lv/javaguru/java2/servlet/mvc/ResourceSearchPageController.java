package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Viktor on 2017.05.29..
 */
@Component
public class ResourceSearchPageController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/resourceSearchPage.jsp");
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        return null;
    }
}
