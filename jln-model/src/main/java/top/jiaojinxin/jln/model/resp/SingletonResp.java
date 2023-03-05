package top.jiaojinxin.jln.model.resp;

import top.jiaojinxin.jln.util.RespCodeUtil;

import java.io.Serial;

/**
 * 单值响应对象
 *
 * @param <T> 具体的响应体泛型
 * @author JiaoJinxin
 */
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
     * @author JiaoJinxin
     */
    protected SingletonResp(T data) {
        super(true, RespCodeUtil.success());
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 响应数据值
     * @param <T>  响应数据类型
     * @return top.jiaojinxin.jln.model.resp.SingletonResp
     * @author JiaoJinxin
     */
    public static <T> SingletonResp<T> ok(T data) {
        return new SingletonResp<>(data);
    }

    /**
     * 获取{@link SingletonResp#data}
     *
     * @return T
     * @author JiaoJinxin
     */
    public T getData() {
        return data;
    }
}
