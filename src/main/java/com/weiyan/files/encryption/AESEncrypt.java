package com.weiyan.files.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.bop.web.sjzx.util.encryption
 * @date 2019/5/21 18:27
 */
public class AESEncrypt {

    public static final String KEY_ALGORITHM = "AES";
    public static int Key_Size = 128;
    public static String CRYPT_KEY = "YUUAtestYUUAtest";
    public static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static byte[] encrypt(byte[] src, String key)
            throws Exception
    {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(Key_Size, new SecureRandom(key.getBytes("UTF-8")));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key1 = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(1, key1);
        byte[] result = cipher.doFinal(src);
        return result;
    }

    public static byte[] decrypt(byte[] src, String key)
            throws Exception
    {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(Key_Size, new SecureRandom(key.getBytes("UTF-8")));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key1 = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(2, key1);
        byte[] result = cipher.doFinal(src);
        return result;
    }

    public static final String decrypt(String data)
    {
        try
        {
            return new String(decrypt(EncryptUtil.toStringHex(data), CRYPT_KEY));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static final String encrypt(String data)
    {
        try
        {
            return EncryptUtil.toHexString(encrypt(data.getBytes(), CRYPT_KEY));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
            throws Exception
    {
        String ID = "sdfsfsdfssdsfs";
        CRYPT_KEY = "12345678";
        System.out.println(CRYPT_KEY.getBytes().length);
        String idEncrypt = encrypt(ID);
        System.out.println(idEncrypt);
        String idDecrypt = decrypt(idEncrypt);
        System.out.println(idDecrypt);
    }
}
