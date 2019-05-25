package com.example.test;

/**
 *
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 * @Description: 这里用一句话描述这个类的作用
 * @see: PabSignatureUtils 此处填写需要参考的类
 * @version 2017年2月4日 上午10:58:11
 * @author zhongxuan.fan
 */
public class PabSignatureUtils {

    public static final String CHAR_ENCODING = "UTF-8";

    public static String base64encode(String msg) throws UnsupportedEncodingException {
        byte[] bytes = msg.getBytes();
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(bytes), "UTF-8");
    }

    public static String base64encode(byte[] msg) throws UnsupportedEncodingException {
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(msg), "UTF-8");
    }

    public static byte[] base64decode(String msg) throws UnsupportedEncodingException {
        return org.apache.commons.codec.binary.Base64.decodeBase64(msg);
    }

    /**
     * @Description RSA加密
     * @param data
     * @param prop
     * @return
     * @throws Exception
     * @see 需要参考的类或方法
     */
    public static byte[] encryptByPrivateKey(String data, Properties prop) throws Exception {
        PrivateKey privateKey = RSAUtils.getPvkformPfx(PabSignatureUtils.class.getResource("/private-rsa.pfx").getPath(), "es2018");
        Cipher cipher = Cipher.getInstance(KeyFactory.getInstance("RSA").getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data.getBytes());
    }

    /**
     * @Description MD5加密
     * @param plainText 原串
     * @return 摘要串
     * @throws Exception
     * @see 需要参考的类或方法
     */
    public static String getMD5Encoding(String plainText) {
        byte[] input = plainText.getBytes();// 声明16进制字母
        char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] hex = new char[32];;
        try {
            // 获得一个MD5摘要算法的对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input);
			/*
			 * MD5算法的结果是128位一个整数，在这里javaAPI已经把结果转换成字节数组了
			 */
            byte[] digest = md.digest();// 获得MD5的摘要结果
            byte b = 0;
            for (int i = 0; i < 16; i++) {
                b = digest[i];
                hex[2 * i] = hexChar[b >>> 4 & 0xf];// 取每一个字节的低四位换成16进制字母
                hex[2 * i + 1] = hexChar[b & 0xf];// 取每一个字节的高四位换成16进制字母
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        return new String(hex).toLowerCase();
    }

    /**
     * @Description DES加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     * @see 需要参考的类或方法
     */
    public static byte[] desEncode(String data, byte[] key) throws Exception {
        // 实例化DES密钥材料
        DESedeKeySpec dks = new DESedeKeySpec(key);
        // 实例化秘密密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        // 生成秘密密钥
        Key k = keyFactory.generateSecret(dks);
        /**
         * 实例化
         * 使用PKCS7Padding填充方式，按如下代码实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
         */
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data.getBytes());
    }

    /**
     * @Description DES解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     * @see 需要参考的类或方法
     */
    public static byte[] desDecode(byte[] data, byte[] key) throws Exception {
        // 实例化DES密钥材料
        DESedeKeySpec dks = new DESedeKeySpec(key);
        // 实例化秘密密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        // 生成秘密密钥
        Key k = keyFactory.generateSecret(dks);
        /**
         * 实例化
         * 使用PKCS7Padding填充方式，按如下代码实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
         */
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        // 初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }

    public static String encode(String merchantId,String xml,byte[] key) throws Exception {
        PublicKey publickey = RSAUtils.getPubKeyFromCRT(RSAUtils.class.getResource("/public-rsa.cer").getPath());
        PrivateKey privateKey = RSAUtils.getPvkformPfx(PabSignatureUtils.class.getResource("/private-rsa.pfx").getPath(),"es2018");
        String first = base64encode((merchantId));
        String second = base64encode(RSAUtils.encrypt(key, publickey));
        String third = base64encode(desEncode(xml, key));
        String fourth = RSAUtils.encryptByPrivateKey(getMD5Encoding(xml), privateKey);
        String reqXml = first + "|" + second + "|" + third + "|" + fourth;
        return reqXml;
    }

    public static String decode(String data,byte[] key) throws Exception {
        String[] list = data.split("\\|");
        if (list.length != 3) throw new RuntimeException("格式不正确,data:" + data);

        if ("1".equals(list[0])) {
            String returnXml = new String(desDecode(base64decode(list[1]), key), "UTF-8");
            String md5 = new String(base64decode(list[2]), "UTF-8");
            String sign = getMD5Encoding(returnXml).toUpperCase();
            if(!md5.equals(sign)) throw new RuntimeException("签名验证不通过");
            return returnXml;
        } else if ("0".equals(list[0])) {
            return "错误代码: " + list[1] + "\r\n错误描述 : " + new String(base64decode(list[2]), "UTF-8");
        } else {
            throw new RuntimeException("格式不正确,data:" + data);
        }
    }

    public static String decode(String data) throws Exception {
        try {

            PublicKey publickey = RSAUtils.getPubKeyFromCRT(RSAUtils.class.getResource("/public-rsa.cer").getPath());
            PrivateKey privateKey = RSAUtils.getPvkformPfx(PabSignatureUtils.class.getResource("/private-rsa.pfx").getPath(),"es2018");
            String[] strs = data.split("\\|");
            if(strs.length != 4) throw new RuntimeException("参数格式错误");
            String first = new String(Base64.decodeBase64(strs[0]));
            byte[] second = Base64.decodeBase64(strs[1]);
            byte[] third = Base64.decodeBase64(strs[2]);
            byte[] fourth = Base64.decodeBase64(strs[3]);
            byte[] desKey = null;
            try {
                desKey = RSAUtils.decrypt(second, privateKey);
            } catch(Exception e) {
                throw new RuntimeException("解密异常", e);
            }

            if(desKey.length != 24) new RuntimeException("3des密钥长度异常");

            String plaintext = new String(desDecode(third, desKey));
            String md5 = getMD5Encoding(plaintext);
            String sign = new String(RSAUtils.decryptByPublicKey(fourth, publickey));
            if(!md5.equals(sign)) new RuntimeException("验签失败");
            return plaintext;
        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("参数格式错误");
        }
    }



}

