package top.jiaojinxin.core.model.resp;

import lombok.Getter;
import lombok.NonNull;
import top.jiaojinxin.core.util.CoreUtil;

/**
 * 单值响应对象
 *
 * @param <T> 具体的响应体泛型
 * @author JiaoJinxin
 */
@Getter
public class SingletonResp<T> extends Resp {
    private static final long serialVersionUID = 4575956737833444028L;

    /**
     * 响应数据值
     */
    private final T data;

    /**
     * 构造方法
     *
     * @param data 响应数据值
     * @author JiaoJinxin
     */
    protected SingletonResp(@NonNull T data) {
        super(true, CoreUtil.getSuccessRespCode());
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 响应数据值
     * @param <T>  响应数据类型
     * @return top.jiaojinxin.core.model.resp.model.SingletonResp
     * @author JiaoJinxin
     */
    public static <T> SingletonResp<T> ok(@NonNull T data) {
        return new SingletonResp<>(data);
    }
}
