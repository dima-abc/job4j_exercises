package ru.job4j.io;

import java.io.*;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.3. Синхронизация ресурсов
 * 1. Visibility. Общий ресурс вне критической секции [#1102 #268979]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.11.2022
 */
public class LoadFile implements LoadContent<File> {
    @Override
    public byte[] load(File type) {
        byte[] rsl = new byte[0];
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(type))) {
            rsl = in.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
