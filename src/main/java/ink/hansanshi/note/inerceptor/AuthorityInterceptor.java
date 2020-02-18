package ink.hansanshi.note.inerceptor;

import ink.hansanshi.note.exception.NoAuthorityException;
import ink.hansanshi.note.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hansanshi
 * @date 2019/10/6
 */
@Component
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        String username = request.getHeader("username");
        if( token == null || !userService.validate(username, token).isSuccess()){
            throw new NoAuthorityException();
        }
        return true;
    }
}
