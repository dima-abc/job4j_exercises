package ru.job4j.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 3. Мидл
 * 3.1. Multithreading
 * 3.1.7. NIO и многопоточность
 * 1. Java NIO API. Каналы, буферы, селекторы [#504994]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.03.2023
 */
public class NioDemo {
    private static final String FILE = "data/nio.txt";
    private static final int CAPACITY = 1024;

    public static void main(String[] args) throws Exception {
        int count;
        try (SeekableByteChannel byteChannel =
                     Files.newByteChannel(Paths.get(FILE))) {
            ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);
            do {
                count = byteChannel.read(buffer);
                if (count != -1) {
                    buffer.rewind();
                    for (int i = 0; i < count; i++) {
                        System.out.print((char) buffer.get());
                    }
                }
            } while (count != -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
