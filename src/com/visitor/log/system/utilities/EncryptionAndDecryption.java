package com.visitor.log.system.utilities;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionAndDecryption {

    public static void main(String[] args) throws Exception {
        String originalText = "hello";
        String secretKey = "ThisIsASecretKey";

        String encryptedText = encrypt(originalText);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }
    public static final String secretKey = "ThisIsASecretKey";
    public static String encrypt(String password) throws Exception {
        SecretKey key = generateSecretKey(secretKey);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        SecretKey key = generateSecretKey(secretKey);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKey generateSecretKey(String secretKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 128 bits key size
        return new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
    }
}

