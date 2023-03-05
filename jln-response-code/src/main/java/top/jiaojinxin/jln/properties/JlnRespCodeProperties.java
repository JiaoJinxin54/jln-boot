package top.jiaojinxin.jln.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应码相关配置
 *
 * @author JiaoJinxin
 */
@ConfigurationProperties(prefix = "jln.resp-code")
public class JlnRespCodeProperties implements Serializable {

    @Serial
    private static final long serialVersionUID = -5716874899492609994L;

    /**
     * 成功响应码code，默认：0000
     */
    private String success = "0000";

    /**
     * 失败响应码code，默认：0001
     */
    private String fail = "0001";

    /**
     * 系统异常响应码code，默认：0002
     */
    private String failSys = "0002";

    /**
     * jln框架异常响应码code，默认：0003
     */
    private String failJln = "0003";

    /**
     * 业务异常响应码code，默认：0004
     */
    private String failBiz = "0004";

    /**
     * 无法获取响应码描述时的默认值，默认：""
     */
    private String defaultMsg = "";

    public JlnRespCodeProperties() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail;
    }

    public String getFailSys() {
        return failSys;
    }

    public void setFailSys(String failSys) {
        this.failSys = failSys;
    }

    public String getFailJln() {
        return failJln;
    }

    public void setFailJln(String failJln) {
        this.failJln = failJln;
    }

    public String getFailBiz() {
        return failBiz;
    }

    public void setFailBiz(String failBiz) {
        this.failBiz = failBiz;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    public void setDefaultMsg(String defaultMsg) {
        this.defaultMsg = defaultMsg;
    }
}
