package lv.javaguru.java2.servlet.mvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HelloWorldController implements MVCController {

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/helloWorld.jsp");
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        return null;
    }

}
