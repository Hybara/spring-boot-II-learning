package per.learn.spring.boot.learning.controller;

import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ErrorController extends AbstractErrorController {

    private HttpStatus status;
    private Map<String, Object> attribute;

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }
    /*
    spring boot中，cotroller抛出的异常都某人交给/error来处理，应用可以继承AbstractErrorController统一处理系统的各种异常
    */
    @Override
    @RequestMapping("/error")
    public String getErrorPath() {
        JSONObject result = new JSONObject();
        result.element("status", status);
        result.element("attribute", attribute);
        return result.toString();
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        attribute = super.getErrorAttributes(request, includeStackTrace);
        return attribute;
    }

    @Override
    protected HttpStatus getStatus(HttpServletRequest request) {
        status = super.getStatus(request);
        return status;
    }
}
