package com.sad.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
//import sun.misc.BASE64Encoder;

public class Security {
    private final static String SALT="JMS$32Mk@29sUHMBiw2398E1d66NmXqDWMCL@!SKT(*%^CWSxfw29skt2";
    private final static String KEY="SDEV425HasBeenAFunClass";
    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String password1 = "mypassword";
        String password2 = "adminpasstest";
        String password3 = "customerpasstest";
        String cc1 = "1111111111111111";
        String cc2 = "2222222222222222";
        String cc3 = "3333333333333333";
        //String dbpass = "sdev425";
        String dbpass = "sdevadmin";
        System.out.println(password1+" : " + md5Hash(password1));
        System.out.println(password2+" : " + md5Hash(password2));
        System.out.println(password3+" : " + md5Hash(password3));
        System.out.println(cc1+" : " + encrypt(cc1));
        System.out.println(cc2+" : " + encrypt(cc2));
        System.out.println(cc3+" : " + encrypt(cc3));
        System.out.println(dbpass+" : "+ encrypt(dbpass));
    }

    //Takes a string, and converts it to md5 hashed string.
    public static String md5Hash(String message) {
        String md5 = "";
        if(null == message)
            return null;

        message = message+SALT;//adding a salt to the string before it gets hashed.
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");//Create MessageDigest object for MD5
            digest.update(message.getBytes(), 0, message.length());//Update input string in message digest
            md5 = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        return md5;
    }

    public static String encrypt(String message) {
        try {
            setKey(KEY);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init( Cipher.ENCRYPT_MODE, secretKey );
            return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String decrypt(String message) {
        try {
            setKey(KEY);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey );
            return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String mask(String message, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length() - len; i++) {
            sb.append("*");
        }
        if (len > 0) {
            sb.append(message.substring(message.length()-4));
        }
        return sb.toString();
    }

}
