import zad1.CustomSerializationUtil;
import zad1.SensitiveData;
import zad2.DateSaver;

public class Main {
    public static void main(String[] args) {
//        Zadanie 1
        try {
            SensitiveData data = new SensitiveData("Jan Kowalski", "12345678901", "SuperSecretPassword");
            String filePath = "src/main/resources/sensitiveData.ser";

            CustomSerializationUtil.serialize(data, filePath);
            System.out.println("Dane zostały zapisane.");

            SensitiveData deserializedData = CustomSerializationUtil.deserialize(filePath);
            System.out.println("Odszyfrowane dane: " + deserializedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        Zadanie 2
        DateSaver dateSaver = new DateSaver();
        dateSaver.writeDate();
        dateSaver.readDate();

    }
}
