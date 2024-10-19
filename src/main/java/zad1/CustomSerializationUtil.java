package zad1;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CustomSerializationUtil {

    private CustomSerializationUtil(){}
    private static final String SECRET_KEY = "1234567890123456";

    private static String encrypt(String data)  {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        byte[] encryptedData;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedData = cipher.doFinal(data.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    private static String decrypt(String encryptedData)  {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        byte[] decryptedData;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);
             decryptedData = cipher.doFinal(decodedData);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return new String(decryptedData);
    }

    public static void serialize(SensitiveData obj, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj.getName());
            oos.writeObject(encrypt(obj.getPesel()));
            oos.writeObject(encrypt(obj.getPassword()));
        } catch (RuntimeException | IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public static SensitiveData deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            String name = (String) ois.readObject();
            String encryptedPesel = (String) ois.readObject();
            String encryptedPassword = (String) ois.readObject();

            String pesel = decrypt(encryptedPesel);
            String password = decrypt(encryptedPassword);

            return new SensitiveData(name, pesel, password);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

