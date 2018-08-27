package per.learn.spring.boot.learning.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionHandlerInterceptor implements HandlerInterceptor {

    private final static Integer TOKEN_LENGTH = 32;

    /*
    执行拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = (String) request.getSession().getAttribute("token");
        if (token == null || "".equals(token) || token.length() != TOKEN_LENGTH) {
            return false;
        } else {
            return true;
        }
    }

    /*
    在调用cotroller方法结束后、页面渲染之前执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /*
    页面渲染结束之后调用，通常用来清除某些资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
