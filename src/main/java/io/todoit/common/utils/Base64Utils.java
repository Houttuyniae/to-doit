package io.todoit.common.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author:zhangd
 * @date:2019/4/20 22:08
 */
public class Base64Utils {


    /**
     * 对string数据进行编码
     * @param data
     * @return
     */
    public static String encode(String data){
        return encode(data.getBytes(StandardCharsets.UTF_8 ));
    }


    /**
     * 对byte数据进行编码
     * @param data
     * @return
     */
    public static String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * 对byte数据解码
     * @param data
     * @return
     */
    public static byte[] decode(byte[] data){
        return Base64.getDecoder().decode(data);
    }

    public static String decode(String data){
        byte[] decode = decode(data.getBytes());
        return new String(decode, StandardCharsets.UTF_8);
    }

}
