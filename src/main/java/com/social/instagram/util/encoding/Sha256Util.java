package com.social.instagram.util.encoding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Sha256Util implements EncryptionUtil {

    private static final int SALT_LENGTH = 10;
    private static final int SHA_256 = 256;
    private static final int HEXA_DECIMAL = 16;
    private static final int DIGEST_BYTE_EXTRACT = 1;

    private static String getSalt() {
        Random random = new Random();
        byte[] salt = new byte[SALT_LENGTH];

        random.nextBytes(salt);

        StringBuilder saltBuilder = new StringBuilder();

        for (byte saltByte : salt) {
            saltBuilder.append(String.format("%02x", saltByte));
        }

        return saltBuilder.toString();
    }

    private static String getEncrypt(String password, String salt) {

        byte[] saltBytes = salt.getBytes();

        byte[] passwordByte = password.getBytes();
        byte[] sumPasswordAndSaltBytes = new byte[passwordByte.length + saltBytes.length];

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(sumPasswordAndSaltBytes);

            byte[] digest = messageDigest.digest();

            StringBuilder encryptBuilder = new StringBuilder();

            for (byte digestByte : digest) {
                encryptBuilder.append(Integer.toString((digestByte & 0xFF) + SHA_256, HEXA_DECIMAL).substring(DIGEST_BYTE_EXTRACT));
            }

         return encryptBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("현재 사용할 수 없는 알고리즘 입니다.");
        }

    }

    @Override
    public String changeEncoding(String plainText) {
        return getEncrypt(plainText, getSalt());
    }

}