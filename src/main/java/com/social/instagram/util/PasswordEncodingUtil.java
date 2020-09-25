package com.social.instagram.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordEncodingUtil {

    private static String getSalt() {
        Random random = new Random();
        byte[] salt = new byte[10];

        random.nextBytes(salt);

        StringBuilder sb = new StringBuilder();

        for (byte b : salt) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    private static String getEncrypt(String pwd, String salt) {

        byte[] saltBytes = salt.getBytes();

        byte[] temp = pwd.getBytes();
        byte[] bytes = new byte[temp.length + saltBytes.length];

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] b = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte value : b) {
                sb.append(Integer.toString((value & 0xFF) + 256, 16).substring(1));
            }

         return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("현재 사용할 수 없는 알고리즘 입니다.");
        }

    }

    public static String changePasswordEncoding(String password) {
        return getEncrypt(password, getSalt());
    }

}