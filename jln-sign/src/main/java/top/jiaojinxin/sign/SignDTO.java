package top.jiaojinxin.sign;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import top.jiaojinxin.common.model.DTO;

import java.io.Serial;
import java.nio.charset.StandardCharsets;

/**
 * 签名参数
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
@ToString
public class SignDTO implements DTO {
    @Serial
    private static final long serialVersionUID = 171674764775623175L;

    /**
     * 客户端标识
     */
    private String clientCode;

    /**
     * 时间戳（精确到毫秒）
     */
    private String timestamp;

    /**
     * 唯一标识
     */
    private String uid;

    /**
     * 加密后的参数文本
     */
    private String content;

    /**
     * 加密算法盐值，用于配合加密算法对参数文本进行加密<br>
     * <b>注：需要使用私钥将盐值加密后再赋值至此属性，否可会导致参数泄露</b>
     */
    private String salt;

    /**
     * 加密算法，用于配合加密算法盐值对参数文本进行加密
     */
    private SymmetricAlgorithm algorithm;

    /**
     * 签名参数
     */
    public SignDTO() {
    }

    /**
     * 签名参数转换为字节数组
     *
     * @return byte[]
     */
    public byte[] getByteArray() {
        return this.toString().getBytes(StandardCharsets.UTF_8);
    }
}
