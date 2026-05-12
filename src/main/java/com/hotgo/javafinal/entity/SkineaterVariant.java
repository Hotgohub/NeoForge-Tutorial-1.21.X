package com.hotgo.javafinal.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum SkineaterVariant {
    GREEN(0),
    BLUE(1),
    RED(2),
    ORANGE(3);


    private static final SkineaterVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SkineaterVariant::getId)).toArray(SkineaterVariant[]::new);
    private final int id;

    SkineaterVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SkineaterVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
