package headfirst.design.adapter;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class AdapterInSpring {
    public static void main(String[] args) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(); // doDispatch 확인
        HandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
    }
}
