package ink.hansanshi.note.inerceptor;

import ink.hansanshi.note.util.ThreadLocalUtil;
import ink.hansanshi.note.util.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hansanshi
 * @date 2020/1/27
 */
@Component
@Slf4j
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String username = request.getHeader("username");
        ValidateUtil.notBlankString(username);
        ThreadLocalUtil.setUsername(username);
        // TODO log params
        //  log.info("request header: {} \n param: {}", request.get);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        ThreadLocalUtil.clearUsername();
    }
}
