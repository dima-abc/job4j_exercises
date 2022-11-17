package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

/**
 * 2. Джуниор
 * 2.4. Garbage Collection
 * 2.4.4. Типы ссылок и коллекции на soft weak ссылках
 * 1. Реализация кеша на SoftReference [#1592 #256228]
 * Создать в папке cache/menu класс Emulator для работы с пользователем.
 * Предоставить пользователю возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 17.11.2022
 */
public class Emulator {
    private static final int CATALOG = 1;
    private static final int FILE = 2;
    private static final int GET_CACHE = 3;
    private static final String EXIT = "Программа остановлена.";
    private static final String SELECT = "Выберите меню:";
    private static final String SELECT_CATALOG = "Укажите каталог:";
    private static final String SELECT_FILE = "Укажите файл:";
    private String catalogLoad;
    private static final String MENU = String.join(System.lineSeparator(),
            "Введите 1 - указать кэшируемую директорию",
            "Введите 2 - загрузить содержимое файла в кэш",
            "Введите 3 - получить содержимое файла из кэша",
            "Введите любое другое число для выхода");


    public void start(AbstractCache<String, String> abstractCache, Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.print(SELECT);
            int userSelect = Integer.parseInt(scanner.nextLine());
            System.out.println(userSelect);
            String file;
            if (CATALOG == userSelect) {
                System.out.print(SELECT_CATALOG);
                catalogLoad = scanner.nextLine();
            } else if (FILE == userSelect) {
                System.out.print(SELECT_FILE);
                file = scanner.nextLine();
                String temp = abstractCache.get(catalogLoad + file);
                System.out.println(temp);
            } else if (GET_CACHE == userSelect) {
                System.out.print(SELECT_FILE);
                file = scanner.nextLine();
                String temp = abstractCache.get(catalogLoad + file);
                System.out.println(temp);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static void main(String[] args) {
        AbstractCache<String, String> abstractCache = new DirFileCache();
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        emulator.start(abstractCache, scanner);
    }
}
