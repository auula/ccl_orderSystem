package me.ponyo.order.utils;

import cn.hutool.crypto.digest.DigestUtil;

import java.util.Random;
import java.util.UUID;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 加密工具类
 * <p>
 * Created with IDEA. Date：2019/11/22 4:40 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
public class EncryptionUtil {
    /**
     * 传入明文 返回密文
     *
     * @param str 需要加密的字符串
     * @return 密文
     */
    public static String encrypted(String str) {
        return str != null ? DigestUtil.md5Hex(str) : null;
    }

    /**
     * 传入明文和密码 判断是否一致
     *
     * @param clear  明文
     * @param cipher 密文
     * @return boolean类型
     */
    public static Boolean validation(String clear, String cipher) {

        if (encrypted(clear).equals(cipher)) {
            return true;
        }
        return false;
    }
    //生成uuid
    public static String randomStrUUID(){
        return UUID.randomUUID().toString();
    }
}
