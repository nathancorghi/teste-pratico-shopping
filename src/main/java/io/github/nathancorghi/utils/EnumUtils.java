package io.github.nathancorghi.utils;

import java.util.Random;

public class EnumUtils <T extends Enum<T>> {

    private static final Random random = new Random();
    private final T[] valores;

    public EnumUtils(Class<T> e) {

        valores = e.getEnumConstants();
    }

    public T retornaEnumAleatorio() {

        return valores[random.nextInt(valores.length)];
    }
}
