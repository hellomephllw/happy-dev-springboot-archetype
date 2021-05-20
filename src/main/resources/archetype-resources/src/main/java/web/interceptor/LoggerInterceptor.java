package ${package}.web.interceptor;

import com.happy.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 日志拦截器
 * @author: liliwen
 * @date: 2019-07-30
 */
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    /**请求进入时间*/
    private final static String REQUEST_ENTER_TIME = "__request_enter_time__";

    /**过滤静态资源正则*/
    private final static String REGEX_STATIC_RESOURCE = "^(/communal/ping|/swagger-ui.html|/webjars/|/error|/swagger-resources|/csrf)+";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!RegexUtil.find(REGEX_STATIC_RESOURCE, request.getRequestURI())) {
            request.setAttribute(REQUEST_ENTER_TIME, System.currentTimeMillis());
            log.info(">>>>>>>>>新的请求进入");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        if (!RegexUtil.find(REGEX_STATIC_RESOURCE, request.getRequestURI())) {
            Long enterTime = (Long) request.getAttribute(REQUEST_ENTER_TIME);
            log.info("处理耗时: {}ms", System.currentTimeMillis() - enterTime);
            log.info(">>>>>>>>>请求结束");
        }
    }

}
