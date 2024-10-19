package zad2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class DateSaver {

    public static final String FILE_NAME = "src/main/resources/date";

    public void writeDate() {
        LocalDate currentDate = LocalDate.now();

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME))) {
            writer.write("Date: " + currentDate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readDate() {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}