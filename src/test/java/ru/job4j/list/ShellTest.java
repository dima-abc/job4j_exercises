package ru.job4j.list;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 2.1.4. List
 * 2. Симуляция терминала Linux
 * TEST
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 07.11.2022
 */
class ShellTest {
    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd("/user");
        shell.cd("../root");
        assertThat(shell.pwd())
                .isEqualTo("/root");
    }

    @Test
    public void whenAbsolutePath() {
        Shell shell = new Shell();
        shell.cd("/path/to/file");
        shell.cd("/new/path/to/my/file");
        assertThat(shell.pwd())
                .isEqualTo("/new/path/to/my/file");
    }

    @Test
    public void whenCdRoot() {
        Shell shell = new Shell();
        shell.cd("/");
        assertThat(shell.pwd())
                .isEqualTo("/");
    }

    @Test
    public void whenCdUserLocal() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("local");
        assertThat(shell.pwd())
                .isEqualTo("/user/local");
    }

    @Test
    public void whenCdUserBack() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("..");
        assertThat(shell.pwd())
                .isEqualTo("/");
    }
}