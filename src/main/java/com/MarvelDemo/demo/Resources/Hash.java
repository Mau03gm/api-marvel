package com.MarvelDemo.demo.Resources;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    // Función que genera el hash MD5 para la API de Marvel
    public static String generateMarvelHash() throws NoSuchAlgorithmException {

        String privateKey = System.getProperty("secretKey");
        String publicKey = System.getProperty("publicKey");

        String ts = "1";

        String toHash = ts + privateKey + publicKey;

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(toHash.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
}
}
