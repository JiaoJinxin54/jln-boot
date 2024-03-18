package top.jiaojinxin.sign;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * 客户端秘钥持有者
 *
 * @author JiaoJinxin
 */
public interface ClientSecretKeyHolder {

    /**
     * 获取客户端私钥字符串
     *
     * @param clientCode 客户端标识
     * @return java.lang.String
     */
    String privateKey(String clientCode);

    /**
     * 获取客户端公钥字符串
     *
     * @param clientCode 客户端标识
     * @return java.lang.String
     */
    String publicKey(String clientCode);

    /**
     * 获取{@link RSA}
     *
     * @param clientCode 客户端标识
     * @return cn.hutool.crypto.asymmetric.RSA
     */
    default RSA rsa(String clientCode) {
        return SecureUtil.rsa(privateKey(clientCode), publicKey(clientCode));
    }
}
