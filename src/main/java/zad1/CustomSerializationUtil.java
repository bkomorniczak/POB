package zad1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class CustomSerializationUtil {

    private static final String SECRET_KEY = "1234567890123456";

    private static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    private static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public static void serialize(SensitiveData obj, String filePath) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj.getName());
            oos.writeObject(encrypt(obj.getPesel()));
            oos.writeObject(encrypt(obj.getPassword()));
        }
    }

    public static SensitiveData deserialize(String filePath) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            String name = (String) ois.readObject();
            String encryptedPesel = (String) ois.readObject();
            String encryptedPassword = (String) ois.readObject();

            String pesel = decrypt(encryptedPesel);
            String password = decrypt(encryptedPassword);

            return new SensitiveData(name, pesel, password);
        }
    }
}
