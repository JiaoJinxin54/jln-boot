package top.jiaojinxin.sign.properties;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 签名异常码
 *
 * @author JiaoJinxin
 */
@Getter
@Setter
public class SignExceptionCode implements Serializable {
    @Serial
    private static final long serialVersionUID = -8559132147981865855L;

    /**
     * 未查询到算法异常，默认：respCode.noSuchAlgorithm
     */
    private String noSuchAlgorithm = "respCode.noSuchAlgorithm";

    /**
     * 构建私钥异常，默认：respCode.generatePrivateKeyError
     */
    private String generatePrivateKeyError = "respCode.generatePrivateKeyError";

    /**
     * 构建公钥异常，默认：respCode.generatePublicKeyError
     */
    private String generatePublicKeyError = "respCode.generatePublicKeyError";

    /**
     * 签名异常，默认：respCode.signError
     */
    private String signError = "respCode.signError";

    /**
     * 验证异常，默认：respCode.verifyError
     */
    private String verifyError = "respCode.verifyError";

    /**
     * 非法请求，默认：respCode.illegalRequest
     */
    private String illegalRequest = "respCode.illegalRequest";

    /**
     * 签名异常码
     */
    public SignExceptionCode() {
    }
}
