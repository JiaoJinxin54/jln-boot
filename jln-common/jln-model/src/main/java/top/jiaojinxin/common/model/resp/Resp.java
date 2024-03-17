package top.jiaojinxin.common.model.resp;

import lombok.Getter;
import lombok.NonNull;
import top.jiaojinxin.common.i18n.I18nCode;
import top.jiaojinxin.common.model.DTO;

import java.io.Serial;

import static top.jiaojinxin.util.I18nManager.getRespCode;
import static top.jiaojinxin.util.PropertiesManager.getFail;
import static top.jiaojinxin.util.PropertiesManager.getSuccess;

/**
 * 顶级数据响应对象
 *
 * @author JiaoJinxin
 */
@Getter
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
     * @param i18nCode 国际化码
     */
    protected Resp(boolean ok, @NonNull I18nCode i18nCode) {
        this.ok = ok;
        this.code = i18nCode.code();
        this.msg = i18nCode.msg();
    }

    /**
     * 成功
     *
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp ok() {
        return new Resp(true, getRespCode(getSuccess()));
    }

    /**
     * 失败（使用指定的国际化码）
     *
     * @param i18nCode 国际化码
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp bad(@NonNull I18nCode i18nCode) {
        return new Resp(false, i18nCode);
    }

    /**
     * 失败（使用指定的国际化码）
     *
     * @param code 国际化码code
     * @param args 国际化码描述参数
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp bad(@NonNull String code, String... args) {
        return bad(getRespCode(code, args));
    }

    /**
     * 失败（使用默认的国际化码）
     *
     * @return top.jiaojinxin.common.model.resp.Resp
     */
    public static Resp bad() {
        return bad(getFail());
    }
}
