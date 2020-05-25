package com.yjx.androidword.Utils;

import java.security.MessageDigest;

/**
 * MD5编码相关的类
 *
 * @author wangjingtao
 */
public class MD5Utils {

    /**
     * 字符串MD5加密
     *
     * @param info 参数为需要加密的String
     * @return 返回加密后的String
     */
    public static String MD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));//设置编码格式
            byte[] encryption = md5.digest();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    builder.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    builder.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return builder.toString();
        } catch (Exception e) {
            return "MD5加密错误";
        }
    }


}
