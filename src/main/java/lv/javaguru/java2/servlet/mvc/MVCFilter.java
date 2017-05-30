package lv.javaguru.java2.servlet.mvc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MVCFilter implements Filter {

    private static Logger logger = Logger.getLogger(MVCFilter.class.getName());

    private ApplicationContext springContext;
    private Map<String, MVCController> controllerMapping;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            springContext =
                    new AnnotationConfigApplicationContext(SpringAppConfig.class);
        } catch (BeansException e) {
            logger.log(Level.INFO, "Spring context failed to start", e);
        }
        try {
            putControllersIntoMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MVCController getBean(Class clazz){
        return (MVCController) springContext.getBean(clazz);
    }

    public void putControllersIntoMap() throws Exception {
        controllerMapping = new HashMap();
        controllerMapping.put("/hello", getBean(HelloWorldController.class));
        controllerMapping.put("/accounts", getBean(GetAllAccountsController.class));
        controllerMapping.put("/userAccountSearch", getBean(UserAccountSearchController.class));
        controllerMapping.put("/userAccountSearchResult", getBean(FindUserAccountController.class));
        controllerMapping.put("/userReservationsSearchResult", getBean(UserReservationsSearchResultController.class));
        controllerMapping.put("/adminPage", getBean(AdminController.class));
        controllerMapping.put("/resourceSearch", getBean(ResourceSearchController.class));
        controllerMapping.put("/resourceSearchResult", getBean(ResourceSearchResultController.class));
        controllerMapping.put("/resourceReservations", getBean(ResourceReservationsController.class));

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String contextURI = req.getServletPath();
        if (controllerMapping.keySet().contains(contextURI)){
            MVCController controller = controllerMapping.get(contextURI);

            MVCModel model = null;

            String method = req.getMethod();
            if ("GET".equalsIgnoreCase(method)) {
                model = controller.processGet(req);
            }
            if ("POST".equalsIgnoreCase(method)) {
                model = controller.processPost(req);
            }

            req.setAttribute("model", model.getData());
            ServletContext context = req.getServletContext();
            RequestDispatcher requestDispatcher =
                    context.getRequestDispatcher(model.getView());
            requestDispatcher.forward(req, resp);
        }
        else {
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() { }

}
