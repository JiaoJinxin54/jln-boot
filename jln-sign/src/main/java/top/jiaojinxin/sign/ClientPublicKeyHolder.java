package top.jiaojinxin.sign;

/**
 * 客户端公钥持有者（<b>验证签名时需实现该接口</b>）
 *
 * @author JiaoJinxin
 */
public interface ClientPublicKeyHolder {

    /**
     * 获取客户端公钥
     *
     * @param clientCode 客户端标识
     * @return java.lang.String
     * @author JiaoJinxin
     */
    String clientPublicKey(String clientCode);
}
