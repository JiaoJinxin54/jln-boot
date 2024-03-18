package top.jiaojinxin.util;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import lombok.Setter;
import top.jiaojinxin.sign.ClientSecretKeyHolder;
import top.jiaojinxin.sign.SignDTO;

import java.security.KeyPair;
import java.util.Base64;

/**
 * 签名工具类
 *
 * @author JiaoJinxin
 */
public class SignUtil {

    /**
     * 默认的签名密钥
     */
    private static final SignAlgorithm DEFAULT_SIGN_ALGORITHM = SignAlgorithm.SHA512withRSA;

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
        // 获取客户端私钥
        String privateKeyStr = SignUtil.clientSecretKeyHolder.privateKey(signDTO.getClientCode());
        // 执行签名
        byte[] result = getSign(privateKeyStr, null).sign(signDTO.toString());
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
        // 获取客户端公钥
        String publicKeyStr = SignUtil.clientSecretKeyHolder.publicKey(signDTO.getClientCode());
        // 获取签名值
        byte[] signValueByteArray = Base64.getDecoder().decode(signValue);
        // 执行验签
        return getSign(null, publicKeyStr).verify(signDTO.getByteArray(), signValueByteArray);
    }

    /**
     * 加密
     *
     * @param clientCode 客户端编码
     * @param data       待加密数据
     * @param keyType    加密密钥类型
     * @return java.lang.String
     */
    public static String encrypt(String clientCode, String data, KeyType keyType) {
        return SignUtil.clientSecretKeyHolder.rsa(clientCode).encryptBase64(data, keyType);
    }

    /**
     * 解密
     *
     * @param clientCode 客户端编码
     * @param data       待解密数据
     * @param keyType    解密密钥类型
     * @return java.lang.String
     */
    public static String decrypt(String clientCode, String data, KeyType keyType) {
        return SignUtil.clientSecretKeyHolder.rsa(clientCode).decryptStr(data, keyType);
    }

    /**
     * 构建密钥对
     *
     * @return java.security.KeyPair
     */
    public static KeyPair generateKeyPair() {
        return KeyUtil.generateKeyPair(DEFAULT_SIGN_ALGORITHM.getValue());
    }

    /**
     * 获取Sign对象
     *
     * @param privateKey 私钥
     * @param publicKey  公钥
     * @return cn.hutool.crypto.asymmetric.Sign
     */
    private static Sign getSign(String privateKey, String publicKey) {
        return cn.hutool.crypto.SignUtil.sign(DEFAULT_SIGN_ALGORITHM, privateKey, publicKey);
    }
}
