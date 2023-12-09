package top.jiaojinxin.core.model.resp;

import lombok.Getter;
import lombok.NonNull;
import top.jiaojinxin.core.util.CoreUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 多值响应对象
 *
 * @param <T> 具体的响应体泛型
 * @author JiaoJinxin
 */
@Getter
public class MultipartResp<T> extends Resp {
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
    protected MultipartResp(@NonNull Collection<T> data) {
        super(true, CoreUtil.getSuccessRespCode());
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data 响应数据值（集合）
     * @param <T>  响应数据类型
     * @return top.jiaojinxin.core.model.resp.model.MultipartResp
     * @author JiaoJinxin
     */
    public static <T> MultipartResp<T> ok(@NonNull Collection<T> data) {
        return new MultipartResp<>(data);
    }

    /**
     * 成功
     *
     * @param data 响应数据值（数组）
     * @param <T>  响应数据类型
     * @return top.jiaojinxin.core.model.resp.model.MultipartResp
     * @author JiaoJinxin
     */
    public static <T> MultipartResp<T> ok(@NonNull T[] data) {
        return new MultipartResp<>(Arrays.stream(data).collect(Collectors.toList()));
    }
}
