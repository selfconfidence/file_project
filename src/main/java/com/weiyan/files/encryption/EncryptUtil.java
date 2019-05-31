package com.weiyan.files.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.bop.web.sjzx.util.encryption
 * @date 2019/5/21 18:29
 */
public class EncryptUtil {
    public static String MD5(String val)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes("utf-8"));
            return toHexString(md5.digest());
        }
        catch (Exception e) {}
        return null;
    }

    public static String SHA(String val)
    {
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("SHA");
            md5.update(val.getBytes("utf-8"));
            return toHexString(md5.digest());
        }
        catch (Exception e) {}
        return null;
    }

    public static String toHexString(byte[] buf)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++)
        {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] toStringHex(String hexStr)
    {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++)
        {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = ((byte)(high * 16 + low));
        }
        return result;
    }

    public static String BASE64Encoder(String val)
    {
        try
        {
            return new BASE64Encoder().encode(val.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {}
        return null;
    }

    public static byte[] BASE64EncoderToBytes(byte[] bytes)
    {
        try
        {
            return new BASE64Encoder().encode(bytes).getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e) {}
        return null;
    }

    public static String BASE64EncoderToString(byte[] bytes)
    {
        return new BASE64Encoder().encode(bytes);
    }

    public static String BASE64Decoder(String val)
    {
        try
        {
            return new String(new BASE64Decoder().decodeBuffer(val), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
        catch (IOException e) {}
        return null;
    }

    public static byte[] BASE64DecoderToBytes(String val)
    {
        try
        {
            return new BASE64Decoder().decodeBuffer(val);
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
        catch (IOException e) {}
        return null;
    }

    public static byte[] BASE64DecoderToBytes(byte[] bytes)
    {
        try
        {
            return new BASE64Decoder().decodeBuffer(new String(bytes, "UTF-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            return null;
        }
        catch (IOException e) {}
        return null;
    }
}
