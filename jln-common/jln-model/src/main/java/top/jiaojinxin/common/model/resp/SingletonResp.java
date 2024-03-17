package top.jiaojinxin.common.model.resp;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serial;

import static top.jiaojinxin.util.I18nManager.getRespCode;
import static top.jiaojinxin.util.PropertiesManager.getSuccess;

/**
 * 单值响应对象
 *
 * @param <T> 具体的响应体泛型
 * @author JiaoJinxin
 */
@Getter
public class SingletonResp<T> extends Resp {
    @Serial
    private static final long serialVersionUID = 4575956737833444028L;

    /**
     * 响应数据值
     */
    private final T data;

    /**
     * 构造方法
     *
     * @param data 响应数据值
     */
    protected SingletonResp(@NonNull T data) {
        super(true, getRespCode(getSuccess()));
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 响应数据值
     * @param <T>  响应数据类型
     * @return top.jiaojinxin.common.model.resp.SingletonResp
     */
    public static <T> SingletonResp<T> ok(@NonNull T data) {
        return new SingletonResp<>(data);
    }
}
