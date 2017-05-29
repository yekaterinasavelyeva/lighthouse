package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.FindResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Viktor on 2017.05.30..
 */
@Component
public class ResourceSearchResultController implements MVCController {
    @Autowired
    FindResourceService service;

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return null;
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        Long resourceIdFromRequest = Long.parseLong(request.getParameter("resourceId"));
        Resource resourceFoundByService = service.findResource(resourceIdFromRequest);
        return new MVCModel("/resourceSearchResult.jsp", resourceFoundByService);
    }
}
