package top.jiaojinxin.common.model.resp;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import top.jiaojinxin.common.model.DTO;
import top.jiaojinxin.util.I18nManager;
import top.jiaojinxin.util.PropertiesManager;

import java.io.Serial;

/**
 * 顶级数据响应对象
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class Resp implements DTO {
    @Serial
    private static final long serialVersionUID = -6256774481869845018L;

    /**
     * 是否成功
     */
    private boolean ok;

    /**
     * 结果码
     */
    private String code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 构造方法
     */
    public Resp() {
    }

    /**
     * 构造方法
     *
     * @param ok   是否成功
     * @param code 结果码
     */
    public Resp(boolean ok, String code) {
        this(ok, code, I18nManager.getMsg(code));
    }

    /**
     * 构造方法
     *
     * @param ok   是否成功
     * @param code 结果码
     * @param msg  提示信息
     */
    public Resp(boolean ok, String code, String msg) {
        this.ok = ok;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 成功
     *
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp ok() {
        return new Resp(true, PropertiesManager.getSuccess());
    }

    /**
     * 失败（使用指定的国际化码）
     *
     * @param code 国际化码code
     * @param msg  国际化码描述
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp bad(@NonNull String code, @NonNull String msg) {
        return new Resp(false, code, msg);
    }

    /**
     * 失败（使用指定的国际化码）
     *
     * @param code 国际化码code
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp bad(@NonNull String code) {
        return new Resp(false, code);
    }

    /**
     * 失败（使用默认的国际化码）
     *
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp bad() {
        return new Resp(false, PropertiesManager.getFail());
    }
}
