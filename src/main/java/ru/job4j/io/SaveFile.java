package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.3. Синхронизация ресурсов
 * 1. Visibility. Общий ресурс вне критической секции [#1102 #268979]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.11.2022
 */
public class SaveFile implements SaveContent<File> {
    @Override
    public void save(String content, File output) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(output))) {
            out.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
