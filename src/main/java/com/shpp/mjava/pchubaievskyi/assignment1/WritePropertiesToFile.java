package com.shpp.mjava.pchubaievskyi.assignment1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WritePropertiesToFile {
    public static void main(String[] args) {
        try {
            // Створюємо об'єкт Properties
            Properties properties = new Properties();

            // Додаємо дані у об'єкт Properties
            properties.setProperty("database.url", "jdbc:mysql://localhost:3306/mydb");
            properties.setProperty("database.username", "myuser");
            properties.setProperty("database.password", "mypassword");

            // Вказуємо шлях до файлу .properties
            String filePath = "src/main/resources/app2.properties";

            // Відкриваємо потік для запису у файл
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            // Записуємо дані у файл
            properties.store(fileOutputStream, "Database Configuration");

            // Закриваємо потік
            fileOutputStream.close();

            System.out.println("Data written to " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
