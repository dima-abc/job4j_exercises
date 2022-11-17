package ru.job4j.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 2. Джуниор
 * 2.4. Garbage Collection
 * 2.4.4. Типы ссылок и коллекции на soft weak ссылках
 * 1. Реализация кеша на SoftReference [#1592 #256228]
 * пт.2
 * Создать программу, эмулирующее поведение данного кеша.
 * Программа должна считывать текстовые файлы из системы
 * и выдавать текст при запросе имени файла.
 * Если в кеше файла нет.
 * Кеш должен загрузить себе данные.
 * По умолчанию в кеше нет ни одного файла.
 * Текстовые файлы должны лежать в одной директории.
 * Пример. Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 * Частная реализация кэша.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.11.2022
 */
public class DirFileCache extends AbstractCache<String, String> {
    private static final Logger LOG = LoggerFactory.getLogger(DirFileCache.class.getSimpleName());


    @Override
    protected String load(String key) {
        String result = "";
        try {
            Path path = Path.of(key);
            LOG.info(path.toString());
            result = Files.readString(path);
        } catch (IOException e) {
            LOG.error("Error load files {}, {}", key, e.getMessage());
        }
        return result;
    }
}
