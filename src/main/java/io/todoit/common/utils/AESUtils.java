package io.todoit.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * @author:zhangd
 * @date:2019/4/20 22:46
 *
 * 1.AES：高级数据加密标准，能够有效抵御已知的针对DES算法的所有攻击
 * 2.特点：密钥建立时间短、灵敏性好、内存需求低、安全性高
 *
 */
@Slf4j
public class AESUtils {
    public static final String KEY_ALGORITHM = "AES";
    /**
     * 注意key和加密用到的字符串是不一样的 加密还要指定填充的加密模式和填充模式 AES密钥可以是128或者256，加密模式包括ECB, CBC等
     * ECB模式是分组的模式，CBC是分块加密后，每块与前一块的加密结果异或后再加密 第一块加密的明文是与IV变量进行异或
     * cipher.init(Cipher.ENCRYPT_MODE , getSecretKeySpec(key),new IvParameterSpec(new byte[cipher.getBlockSize()]));
     */
    public static final String KEY_ALGORITHM_MODE = "AES/ECB/PKCS5Padding";



    /**
     * AES对称加密
     * @param data
     * @param key key需要16位
     * @return
     */
    public static String encrypt(String data , String key) {
        try {
            /*AES算法*/
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_MODE);
            /*加密模式*/
            cipher.init(Cipher.ENCRYPT_MODE , getSecretKeySpec(key));
            byte[] bs = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64Utils.encode(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * AES对称解密 key需要16位
     * @param data
     * @param key
     * @return
     */
    public static String decrypt(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM_MODE);
            /*加密模式*/
            cipher.init(Cipher.DECRYPT_MODE , getSecretKeySpec(key));
            byte[] originBytes = Base64Utils.decode(data.getBytes());
            byte[] result = cipher.doFinal(originBytes);
            return new String(result,StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 恢复密钥
     * @param key
     * @return
     */
    private static SecretKeySpec getSecretKeySpec(String key){
        return new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), KEY_ALGORITHM);
    }


    /**
     * 密钥生成
     * @return
     */
    public static byte[] generatorKey(){
        /*密钥生成器*/
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            log.error(" error failed to generatorKey ",e );
        }
        /*默认128，获得无政策权限后可为192或256*/
        keyGen.init(128);
        /*生成密钥*/
        SecretKey secretKey = keyGen.generateKey();
        /*密钥字节数组*/
        return secretKey.getEncoded();
    }


    public static void main(String[] args) throws Exception {
        String okey = "123456789abcdefg";
        //移动端随机key  AES加密数据
        String enr= encrypt("{'mobile':'18980840843','code':'8060','platform':'android','channelId':12454348}",okey);
        System.out.println(enr);
        System.out.println(decrypt(enr, okey));
    }
}
