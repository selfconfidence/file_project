package com.weiyan.files.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.bop.web.sjzx.util.encryption
 * @date 2019/5/21 18:26
 */
public class AES {

    public static String getKey()
            throws NoSuchAlgorithmException
    {
        KeyGenerator keygen = KeyGenerator.getInstance("DES");
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        return EncryptUtil.toHexString(keygen.generateKey().getEncoded());
    }

    public static String Encrypt(String sSrc, String sKey)
            throws Exception
    {
        if ((sKey == null) || (sKey.length() < 16))
        {
            System.out.print("Key��������16��������������");
            return null;
        }
        byte[] raw = sKey.subSequence(0, 16).toString().getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return EncryptUtil.BASE64EncoderToString(encrypted);
    }

    public static String Decrypt(String sSrc, String sKey)
            throws Exception
    {
        if ((sKey == null) || (sKey.length() < 16))
        {
            System.out.print("Key��������16��������������");
            return null;
        }
        byte[] raw = sKey.subSequence(0, 16).toString().getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, skeySpec);

        byte[] encrypted1 = EncryptUtil.BASE64DecoderToBytes(sSrc);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    public static String Srit_Decrypt(String identifier, String sKey, String content)
            throws Exception
    {
        if ((sKey == null) || (sKey.length() < 16))
        {
            System.out.print("Key��������16��������������");
            return null;
        }
        if (identifier == null)
        {
            System.out.print("identifier��������");
            return null;
        }
        String key = Decrypt(identifier, sKey);
        if ((key == null) || (key.length() < 16))
        {
            System.out.print("identifier������������������������16��");
            return null;
        }
        byte[] raw = key.subSequence(0, 16).toString().getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, skeySpec);

        byte[] encrypted1 = EncryptUtil.BASE64DecoderToBytes(content);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    public static String Srit_Encrypt(String identifier, String sKey, String content)
            throws Exception
    {
        if ((sKey == null) || (sKey.length() < 16))
        {
            System.out.print("Key��������16��������������");
            return null;
        }
        if (identifier == null)
        {
            System.out.print("identifier��������");
            return null;
        }
        String key = Decrypt(identifier, sKey);
        if ((key == null) || (key.length() < 16))
        {
            System.out.print("identifier������������������������16��");
            return null;
        }
        byte[] raw = key.subSequence(0, 16).toString().getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, skeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));

        return EncryptUtil.BASE64EncoderToString(encrypted);
    }

    public static void main(String[] args)
            throws Exception
    {
        String sKey = "CB23F2B0B6DF4057";
        String identifier = "iIMrwgXH+5csSLXTYbdbpXxKlrTRVi4Oilo5bNULywIdZJffj0zt10zuEakqZCeE";
        String content = "www.gowhere.sosdf��������%%ss***{11}";
        System.out.println(content);

        String enString = Srit_Encrypt(identifier, sKey, content);
        System.out.println("����������������" + enString);

        String DeString = Srit_Decrypt(identifier, sKey, enString);
        System.out.println("����������������" + DeString);
    }
}
