package top.jiaojinxin.log.audit.handler;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import top.jiaojinxin.log.audit.annotation.Log;
import top.jiaojinxin.log.audit.model.LogDetails;

/**
 * 简单日志注解处理程序
 *
 * @author JiaoJinxin
 */
@Slf4j
public class SimpleLogAnnotationHandler implements LogAnnotationHandler<SimpleLogAnnotationHandler.DefLogDetails> {

    @Override
    public DefLogDetails init() {
        return new DefLogDetails();
    }

    @Override
    public void preHandle(@NonNull DefLogDetails logDetails, Log log, Object[] args) {
        logDetails.setOperation(log.operation());
    }

    @Override
    public void postHandle(@NonNull DefLogDetails logDetails, Object result, Throwable throwable) {
    }

    @Override
    public void publish(@NonNull DefLogDetails logDetails) {
        log.info("{}", logDetails);
    }

    /**
     * 默认日志详情
     *
     * @author JiaoJinxin
     */
    @Getter
    @Setter
    @ToString
    public static class DefLogDetails implements LogDetails {
        private static final long serialVersionUID = 5490160100089945203L;

        /**
         * 操作
         */
        private String operation;
    }
}
