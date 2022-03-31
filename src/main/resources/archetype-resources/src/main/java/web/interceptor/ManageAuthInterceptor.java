package ${package}.web.interceptor;

import ${package}.constant.auth.AuthConst;
import ${package}.constant.CacheTimeConst;
import ${package}.exception.TokenAlreadyExpiredException;
import ${package}.exception.TokenMissingException;
import ${package}.service.auth.AuthTokenService;
import com.happy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 管理系统权限
 * @author: liliwen
 * @date: 2019-07-30
 */
public class ManageAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthTokenService authTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getParameter(AuthConst.AUTH_TOKEN_KEY);
        if (StringUtil.isEmpty(token)) {
            throw new TokenMissingException();
        }

        if (authTokenService.exist(token)) {
            //更新token过期时间
            authTokenService.update(token, CacheTimeConst.MANAGE_ADMIN_TOKEN_EXPIRE);

            return true;
        }

        throw new TokenAlreadyExpiredException();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
    }

}
