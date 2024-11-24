package domashKa;

import java.io.*;
import java.util.Scanner;

public class TextFileApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rootDirectory = System.getProperty("C:/");

        System.out.println("Список текстовых файлов в корневом каталоге:");
        File[] files = new File(rootDirectory).listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("Нет текстовых файлов в корневом каталоге.");
            return;
        }

        for (File file : files) {
            System.out.println("- " + file.getName());
        }

        System.out.println("Введите имя файла для работы:");
        String fileName = scanner.nextLine();
        File selectedFile = new File(rootDirectory, fileName);

        if (!selectedFile.exists() || !selectedFile.isFile()) {
            System.out.println("Указанный файл не найден.");
            return;
        }

        System.out.println("Содержимое файла:");
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }

        System.out.println("Введите строки для записи в файл (введите 'exit' для завершения):");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile, true))) {
            while (true) {
                String userInput = scanner.nextLine();
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }
                writer.write(userInput);
                writer.newLine();
            }
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
