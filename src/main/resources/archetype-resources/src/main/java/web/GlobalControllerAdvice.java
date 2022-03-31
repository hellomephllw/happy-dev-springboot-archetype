package ${package}.web;

import ${package}.constant.BanUrlConst;
import ${package}.constant.StatusCodeEnum;
import com.happy.base.ResultVo;
import com.happy.exception.BusinessException;
import com.happy.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: 全局异常处理器
 * @author: liliwen
 * @date: 2019-01-05
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVo defaultExceptionHandler(Exception e) {
        log.error(">>>>>>>>>出现异常");

        if (e instanceof BusinessException) {
            log.error(e.getMessage(), e);
            return new ResultVo<>(StatusCodeEnum.ERROR_CODE.getErrorCode(), e.getMessage(), null);
        } else if (e instanceof MissingServletRequestParameterException) {
            log.error(e.getMessage(), e);
            return new ResultVo<>(StatusCodeEnum.ERROR_CODE.getErrorCode(), "缺少参数", null);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error(e.getMessage(), e);
            return new ResultVo<>(StatusCodeEnum.ERROR_CODE.getErrorCode(), "参数类型错误", null);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error(e.getMessage(), e);
            return new ResultVo<>(StatusCodeEnum.ERROR_CODE.getErrorCode(), "http请求的method错误", null);
        }
        for (StatusCodeEnum statusCodeEnum : StatusCodeEnum.values()) {
            if (statusCodeEnum.getExceptionClass() != null && e.getClass() == statusCodeEnum.getExceptionClass()) {
                log.error(statusCodeEnum.getErrorDesc());
                return new ResultVo<>(statusCodeEnum.getErrorCode(), statusCodeEnum.getErrorDesc(), null);
            }
        }

        LoggerUtil.printStackTrace(log, e);

        return new ResultVo<>(0, "服务器繁忙", null);
    }

    @ModelAttribute
    public String requestBodyHandler(HttpServletRequest request, @RequestBody(required = false) String requestBody) {
        if (RegexUtil.find(BanUrlConst.REGEX_SWAGGER_RESOURCE, request.getRequestURI())) return requestBody;

        log.info("请求方式: {}", request.getMethod());
        log.info("url: {}", request.getRequestURI());
        log.info("完整请求路径: {}", request.getRequestURL());
        log.info("ContentType: {}", request.getContentType());

        if (isJsonType(request.getContentType())) {
            log.info("请求json参数: {}", requestBody);
        } else if (isFileType(request.getContentType())) {
            log.info("请求file参数: {}", requestBody);
        } else {
            printParams(request.getParameterMap());
        }

        return requestBody;
    }

    /**
     * json类型
     * @param contentType Content-Type
     * @return 是json类型
     */
    private boolean isJsonType(String contentType) {
        return !StringUtil.isEmpty(contentType) && contentType.contains(NetUtil.CONTENT_TYPE_JSON);
    }

    /**
     * 文件类型
     * @param contentType Content-Type
     * @return 是file类型
     */
    private boolean isFileType(String contentType) {
        return !StringUtil.isEmpty(contentType) && contentType.contains(NetUtil.CONTENT_TYPE_FILE);
    }

    /**
     * 打印表单参数
     * @param params 参数
     */
    private void printParams(Map<String, String[]> params) {
        if (CollectionUtil.isEmpty(params)) {
            log.info("请求参数个数: {}", params.size());
        } else {
            StringBuilder paramsStringBuilder = new StringBuilder();
            int count = 0;
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                if (count++ > 0) paramsStringBuilder.append(", ");
                paramsStringBuilder.append(entry.getKey());
                paramsStringBuilder.append("=");
                paramsStringBuilder.append(entry.getValue()[0]);
            }
            log.info("请求form参数: {}", paramsStringBuilder);
        }
    }

}
