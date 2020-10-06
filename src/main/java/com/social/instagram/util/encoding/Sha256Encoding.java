package com.social.instagram.util.encoding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static com.social.instagram.util.encoding.EncodingConstants.HEXA_TWO_OUTPUT;
import static com.social.instagram.util.encoding.EncodingConstants.SALT_LENGTH;
import static com.social.instagram.util.encoding.EncodingConstants.SHA_256_ALGORITHM;
import static com.social.instagram.util.encoding.EncodingConstants.SHA_256_CONSTANTS;
import static com.social.instagram.util.encoding.EncodingConstants.HEXA_DECIMAL;
import static com.social.instagram.util.encoding.EncodingConstants.DIGEST_BYTE_EXTRACT;

public class Sha256Encoding implements EncryptionEncoding {

    private static String getSalt() {
        Random random = new Random();
        byte[] salt = new byte[SALT_LENGTH];

        random.nextBytes(salt);

        StringBuilder saltBuilder = new StringBuilder();

        for (byte saltByte : salt) {
            saltBuilder.append(String.format(HEXA_TWO_OUTPUT, saltByte));
        }

        return saltBuilder.toString();
    }

    private static String getEncrypt(String password, String salt) {

        byte[] saltBytes = salt.getBytes();

        byte[] passwordByte = password.getBytes();
        byte[] sumPasswordAndSaltBytes = new byte[passwordByte.length + saltBytes.length];

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
            messageDigest.update(sumPasswordAndSaltBytes);

            byte[] digest = messageDigest.digest();

            StringBuilder encryptBuilder = new StringBuilder();

            for (byte digestByte : digest) {
                encryptBuilder.append(Integer.toString((digestByte & 0xFF) + SHA_256_CONSTANTS, HEXA_DECIMAL)
                        .substring(DIGEST_BYTE_EXTRACT));
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