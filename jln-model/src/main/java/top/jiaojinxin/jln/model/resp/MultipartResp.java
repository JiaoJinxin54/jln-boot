package top.jiaojinxin.jln.model.resp;

import top.jiaojinxin.jln.util.RespCodeUtil;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * 多值响应对象
 *
 * @param <T> 具体的响应体泛型
 * @author JiaoJinxin
 */
public class MultipartResp<T> extends Resp {

    @Serial
    private static final long serialVersionUID = -2746111135133306394L;

    /**
     * 响应数据值
     */
    private final Collection<T> data;

    /**
     * 构造方法
     *
     * @param data 响应数据值
     * @author JiaoJinxin
     */
    protected MultipartResp(Collection<T> data) {
        super(true, RespCodeUtil.success());
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 响应数据值（集合）
     * @param <T>  响应数据类型
     * @return top.jiaojinxin.jln.model.resp.MultipartResp
     * @author JiaoJinxin
     */
    public static <T> MultipartResp<T> ok(Collection<T> data) {
        return new MultipartResp<>(Optional.ofNullable(data).orElse(Collections.emptyList()));
    }

    /**
     * 成功
     *
     * @param data 响应数据值（数组）
     * @param <T>  响应数据类型
     * @return top.jiaojinxin.jln.model.resp.MultipartResp
     * @author JiaoJinxin
     */
    public static <T> MultipartResp<T> ok(T[] data) {
        if (data == null) {
            return new MultipartResp<>(Collections.emptyList());
        }
        return new MultipartResp<>(Arrays.stream(data).toList());
    }

    /**
     * 获取响应数据值
     *
     * @return java.util.Collection
     * @author JiaoJinxin
     */
    public Collection<T> getData() {
        return data;
    }
}
