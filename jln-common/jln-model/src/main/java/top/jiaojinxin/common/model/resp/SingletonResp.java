package top.jiaojinxin.common.model.resp;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import top.jiaojinxin.util.PropertiesManager;

import java.io.Serial;

/**
 * 单值响应对象
 *
 * @param <T> 具体的响应体泛型
 * @author JiaoJinxin
 */
@Getter
@Setter
public class SingletonResp<T> extends Resp {
    @Serial
    private static final long serialVersionUID = 4575956737833444028L;

    /**
     * 响应数据值
     */
    private T data;

    /**
     * 构造方法
     */
    public SingletonResp() {
    }

    /**
     * 构造方法
     *
     * @param data 响应数据值
     */
    public SingletonResp(@NonNull T data) {
        super(true, PropertiesManager.getSuccess());
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
