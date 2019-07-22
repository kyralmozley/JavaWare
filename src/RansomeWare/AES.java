package RansomeWare;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class AES {
    static String key = "aesEncryptionKey";
    static String initVector = "encryptionIntVec";

    public static  String encrypt(String value) throws Exception {
         IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
         SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
         cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

         byte[] encrypted = cipher.doFinal(value.getBytes());
         return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encrypted) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(original);

    }

    public static void main(String[] args) throws Exception {
        String originalString = "myPa$$w0rd";
        System.out.println("Original String to encrypt - " + originalString);
        String encryptedString = encrypt(originalString);
        System.out.println("Encrypted String - " + encryptedString);
        String decryptedString = decrypt(encryptedString);
        System.out.println("After decryption - " + decryptedString);
    }
}
