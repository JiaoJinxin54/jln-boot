package top.jiaojinxin.util;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import lombok.Setter;
import org.springframework.util.StringUtils;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.sign.ClientSecretKeyHolder;
import top.jiaojinxin.sign.SignDTO;

import java.util.Base64;
import java.util.regex.Pattern;

/**
 * 签名工具类
 *
 * @author JiaoJinxin
 */
public class SignUtil {

    private static final Pattern TIMESTAMP_PATTERN = Pattern.compile("^\\d+$");

    public static final SignAlgorithm SIGNATURE_ALGORITHM = SignAlgorithm.SHA512withRSA;

    @Setter
    private static ClientSecretKeyHolder clientSecretKeyHolder;

    private SignUtil() {
    }

    /**
     * 签名
     *
     * @param signDTO 签名参数
     * @return java.lang.String
     */
    public static String sign(SignDTO signDTO) {
        // 验证签名参数
        validated(signDTO);
        // 获取客户端私钥
        byte[] privateKey = SignUtil.clientSecretKeyHolder.privateKey(signDTO.getClientCode());
        // 执行签名
        byte[] result = getSign(privateKey, null).sign(signDTO.toString());
        // 编码为字符串
        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * 验签
     *
     * @param signDTO   签名参数
     * @param signValue 签名参数
     * @return boolean
     */
    public static boolean verify(SignDTO signDTO, String signValue) {
        // 验证签名参数
        validated(signDTO);
        // 获取客户端公钥
        byte[] publicKey = SignUtil.clientSecretKeyHolder.publicKey(signDTO.getClientCode());
        // 获取签名值
        byte[] signValueByteArray = Base64.getDecoder().decode(signValue);
        // 执行验签
        return getSign(null, publicKey).verify(signDTO.getByteArray(), signValueByteArray);
    }

    /**
     * 签名参数验证
     *
     * @param signDTO 签名参数
     */
    private static void validated(SignDTO signDTO) {
        if (signDTO != null
                && StringUtils.hasText(signDTO.getClientCode())
                && StringUtils.hasText(signDTO.getTimestamp())
                && TIMESTAMP_PATTERN.matcher(signDTO.getTimestamp()).find()
                && StringUtils.hasText(signDTO.getUid())
                && StringUtils.hasText(signDTO.getContent())
                && StringUtils.hasText(signDTO.getSalt())
                && ObjUtil.isNotNull(signDTO.getAlgorithm())) {
            return;
        }
        throw new BizException(PropertiesManager.getIllegalRequest());
    }

    /**
     * 获取Sign对象
     *
     * @param privateKey 私钥
     * @param publicKey  公钥
     * @return cn.hutool.crypto.asymmetric.Sign
     */
    private static Sign getSign(byte[] privateKey, byte[] publicKey) {
        return cn.hutool.crypto.SignUtil.sign(SIGNATURE_ALGORITHM, privateKey, publicKey);
    }
}
