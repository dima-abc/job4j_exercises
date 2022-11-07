package ru.job4j.list;

import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * 2.1.4. List
 * 2. Симуляция терминала Linux
 * В этом задании нужно написать программу, которая будет симулировать поведение команды cd.
 * В качестве основной структуры нужно использовать очередь.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
public class Shell {
    private final LinkedList<String> shell = new LinkedList<>();
    private final StringJoiner line = new StringJoiner("");
    private static final String SLASH = "/";
    private static final String DOT_DOT = "..";


    public void cd(String path) {
        if (DOT_DOT.equals(path)) {
            shell.clear();
            return;
        }
        if (SLASH.equals(path)) {
            shell.clear();
            return;
        }
        if (!path.startsWith(SLASH) && !path.startsWith(DOT_DOT)) {
            path = SLASH + path;
            shell.add(path);
            return;
        }
        if (path.startsWith(DOT_DOT)) {
            shell.clear();
            path = path.substring(DOT_DOT.length());
            shell.add(path);
            return;
        }
        if (path.startsWith(SLASH)) {
            shell.clear();
            shell.add(path);
        }
    }

    public String pwd() {
        if (shell.size() == 0) {
            return SLASH;
        }

        for (String str : shell) {
            line.add(str);
        }

        return line.toString();
    }
}
