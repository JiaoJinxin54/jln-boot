package top.jiaojinxin.core.model.resp;

import lombok.Getter;
import lombok.NonNull;
import top.jiaojinxin.core.i18n.I18nCode;
import top.jiaojinxin.core.model.DTO;
import top.jiaojinxin.core.util.CoreUtil;

import java.io.Serial;

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
     * @author JiaoJinxin
     */
    protected Resp(boolean ok, @NonNull I18nCode i18nCode) {
        this.ok = ok;
        this.code = i18nCode.code();
        this.msg = i18nCode.msg();
    }

    /**
     * 成功
     *
     * @return top.jiaojinxin.core.model.resp.model.Resp
     * @author JiaoJinxin
     */
    public static Resp ok() {
        return new Resp(true, CoreUtil.getSuccessRespCode());
    }

    /**
     * 失败（使用默认的国际化码）
     *
     * @return top.jiaojinxin.core.model.resp.model.Resp
     * @author JiaoJinxin
     */
    public static Resp bad() {
        return new Resp(false, CoreUtil.getFailRespCode());
    }

    /**
     * 失败（使用指定的国际化码）
     *
     * @param i18nCode 国际化码
     * @return top.jiaojinxin.core.model.resp.model.Resp
     * @author JiaoJinxin
     */
    public static Resp bad(@NonNull I18nCode i18nCode) {
        return new Resp(false, i18nCode);
    }
}
