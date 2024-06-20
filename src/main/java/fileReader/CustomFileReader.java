package fileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CustomFileReader {
    public ArrayList<String> readFileContents(String fileName) {
        //String path = "./scr/main/java/resources/" + fileName;
        String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчетом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

}
