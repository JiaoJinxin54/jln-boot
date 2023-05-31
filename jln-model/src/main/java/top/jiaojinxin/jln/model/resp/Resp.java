package top.jiaojinxin.jln.model.resp;

import top.jiaojinxin.jln.model.DTO;
import top.jiaojinxin.jln.respcode.RespCode;
import top.jiaojinxin.jln.util.RespCodeUtil;

import java.io.Serial;

/**
 * 顶级数据响应对象
 *
 * @author JiaoJinxin
 */
public class Resp implements DTO {

    @Serial
    private static final long serialVersionUID = -6256774481869845018L;

    /**
     * 是否成功
     */
    private final boolean ok;

    /**
     * 结果码
     */
    private final String code;

    /**
     * 提示信息
     */
    private final String msg;

    /**
     * 构造方法
     *
     * @param ok       是否成功
     * @param respCode 响应码
     * @author JiaoJinxin
     */
    protected Resp(boolean ok, RespCode respCode) {
        this.ok = ok;
        this.code = respCode.code();
        this.msg = respCode.msg();
    }

    /**
     * 成功
     *
     * @return top.jiaojinxin.jln.model.resp.Resp
     * @author JiaoJinxin
     */
    public static Resp ok() {
        return new Resp(true, RespCodeUtil.success());
    }

    /**
     * 失败（使用默认的响应码）
     *
     * @return top.jiaojinxin.jln.model.resp.Resp
     * @author JiaoJinxin
     */
    public static Resp bad() {
        return new Resp(false, RespCodeUtil.fail());
    }

    /**
     * 失败（使用指定的响应码）
     *
     * @param code 响应码code
     * @param args 响应码描述参数
     * @return top.jiaojinxin.jln.model.resp.Resp
     * @author JiaoJinxin
     */
    public static Resp bad(String code, String... args) {
        return new Resp(false, RespCodeUtil.getRespCode(code, args));
    }

    /**
     * 失败（使用指定的响应码）
     *
     * @param respCode 响应码
     * @return top.jiaojinxin.jln.model.resp.Resp
     * @author JiaoJinxin
     */
    public static Resp bad(RespCode respCode) {
        return new Resp(false, respCode);
    }

    /**
     * 获取{@link Resp#ok}
     *
     * @return boolean
     * @author JiaoJinxin
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * 获取{@link Resp#code}
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取{@link Resp#msg}
     *
     * @return java.lang.String
     * @author JiaoJinxin
     */
    public String getMsg() {
        return msg;
    }
}
