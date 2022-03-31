package ${package}.web.interceptor;

import ${package}.constant.BanUrlConst;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!RegexUtil.find(BanUrlConst.REGEX_SWAGGER_RESOURCE, request.getRequestURI())) {
            request.setAttribute(REQUEST_ENTER_TIME, System.currentTimeMillis());
            log.info(">>>>>>>>>新的请求进入");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        if (!RegexUtil.find(BanUrlConst.REGEX_SWAGGER_RESOURCE, request.getRequestURI())) {
            Long enterTime = (Long) request.getAttribute(REQUEST_ENTER_TIME);
            log.info("处理耗时: {}ms", System.currentTimeMillis() - enterTime);
            log.info(">>>>>>>>>请求结束");
        }
    }

}
