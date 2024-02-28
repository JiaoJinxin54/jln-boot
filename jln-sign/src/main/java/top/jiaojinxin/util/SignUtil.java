package top.jiaojinxin.util;

import lombok.Setter;
import org.springframework.util.StringUtils;
import top.jiaojinxin.common.exception.BizException;
import top.jiaojinxin.common.exception.SysException;
import top.jiaojinxin.sign.ClientPublicKeyHolder;
import top.jiaojinxin.sign.SignDTO;
import top.jiaojinxin.common.properties.SignProperties;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 * 签名工具类
 *
 * @author JiaoJinxin
 */
public class SignUtil {

    private static final Pattern TIMESTAMP_PATTERN = Pattern.compile("^\\d+$");

    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    private static final String RSA_ALGORITHM = "RSA";

    @Setter
    private static SignProperties signProperties;

    @Setter
    private static ClientPublicKeyHolder clientPublicKeyHolder;

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
        // 获取私钥
        PrivateKey privateKey = privateKey(SignUtil.signProperties.getClientPrivateKey());
        // 执行签名
        byte[] result = sign(signDTO.getByteArray(), privateKey);
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
        // 获取客户端公钥字符串
        String publicKeyStr = SignUtil.clientPublicKeyHolder.clientPublicKey(signDTO.getClientCode());
        // 获取公钥
        PublicKey publicKey = publicKey(publicKeyStr);
        // 获取签名值
        byte[] signValueByteArray = Base64.getDecoder().decode(signValue);
        // 执行验签
        return verify(signDTO.getByteArray(), signValueByteArray, publicKey);
    }

    /**
     * 执行签名
     *
     * @param data       签名参数
     * @param privateKey 私钥
     * @return byte[]
     */
    private static byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature signature = getSignature();
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (InvalidKeyException | SignatureException e) {
            throw new SysException(SignUtil.signProperties.getExceptionCode().getSignError(), e);
        }
    }

    /**
     * 执行验签
     *
     * @param data      签名参数
     * @param signValue 签名值
     * @param publicKey 公钥
     * @return boolean
     */
    private static boolean verify(byte[] data, byte[] signValue, PublicKey publicKey) {
        try {
            Signature signature = getSignature();
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(signValue);
        } catch (SignatureException | InvalidKeyException e) {
            throw new SysException(SignUtil.signProperties.getExceptionCode().getVerifyError(), e);
        }
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
                && StringUtils.hasText(signDTO.getAlgorithm())
                && StringUtils.hasText(signDTO.getSalt())) {
            return;
        }
        throw new BizException(SignUtil.signProperties.getExceptionCode().getIllegalRequest());
    }

    /**
     * 获取{@link Signature}
     *
     * @return java.security.Signature
     */
    private static Signature getSignature() {
        try {
            return Signature.getInstance(SIGNATURE_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new SysException(SignUtil.signProperties.getExceptionCode().getNoSuchAlgorithm(), e);
        }
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥
     * @return java.security.PrivateKey
     */
    private static PrivateKey privateKey(String privateKey) {
        KeySpec keySpec = new PKCS8EncodedKeySpec(decodeSecretKey(privateKey), RSA_ALGORITHM);
        try {
            return keyFactory().generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new SysException(SignUtil.signProperties.getExceptionCode().getGeneratePrivateKeyError(), e);
        }
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥
     * @return java.security.PublicKey
     */
    private static PublicKey publicKey(String publicKey) {
        KeySpec keySpec = new X509EncodedKeySpec(decodeSecretKey(publicKey), RSA_ALGORITHM);
        try {
            return keyFactory().generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new SysException(SignUtil.signProperties.getExceptionCode().getGeneratePublicKeyError(), e);
        }
    }

    /**
     * 获取{@link KeyFactory}
     *
     * @return java.security.KeyFactory
     */
    private static KeyFactory keyFactory() {
        try {
            return KeyFactory.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new SysException(SignUtil.signProperties.getExceptionCode().getNoSuchAlgorithm(), e);
        }
    }

    /**
     * 解码秘钥字符串
     *
     * @param secretKey 秘钥字符串
     * @return byte[]
     */
    private static byte[] decodeSecretKey(String secretKey) {
        return Base64.getDecoder().decode(secretKey);
    }
}
