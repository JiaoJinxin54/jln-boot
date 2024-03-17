package top.jiaojinxin.sign;

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
     * @return byte[]
     */
    byte[] privateKey(String clientCode);

    /**
     * 获取客户端公钥字符串
     *
     * @param clientCode 客户端标识
     * @return byte[]
     */
    byte[] publicKey(String clientCode);
}
