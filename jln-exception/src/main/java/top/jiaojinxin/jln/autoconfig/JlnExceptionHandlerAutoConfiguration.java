package top.jiaojinxin.jln.autoconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.jiaojinxin.jln.exception.BaseException;
import top.jiaojinxin.jln.model.resp.Resp;
import top.jiaojinxin.jln.respcode.RespCode;
import top.jiaojinxin.jln.util.LogUtil;
import top.jiaojinxin.jln.util.RespCodeUtil;

/**
 * 异常自动装配
 *
 * @author JiaoJinxin
 */
@RestControllerAdvice
public class JlnExceptionHandlerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JlnExceptionHandlerAutoConfiguration.class);

    /**
     * 处理{@link BaseException}
     *
     * @param exc {@link BaseException}
     * @return top.jiaojinxin.jln.model.resp.Resp
     * @author JiaoJinxin
     */
    @ExceptionHandler(BaseException.class)
    public Resp handle(BaseException exc) {
        RespCode respCode = RespCodeUtil.getRespCode(exc.getCode(), exc.getArgs());
        LogUtil.error(log, exc, respCode.msg());
        return Resp.bad(respCode);
    }

    /**
     * 处理{@link Throwable}
     *
     * @param throwable {@link Throwable}
     * @return top.jiaojinxin.jln.model.resp.Resp
     * @author JiaoJinxin
     */
    @ExceptionHandler(Throwable.class)
    public Resp handle(Throwable throwable) {
        LogUtil.error(log, throwable, throwable.getMessage());
        return Resp.bad();
    }
}
