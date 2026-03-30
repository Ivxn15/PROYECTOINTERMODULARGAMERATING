package com.example.gamerating;

import java.util.ArrayList;
import java.util.List;

public class ResenaManager {

    private static final List<Resena> resenas = new ArrayList<>();

    public static List<Resena> getResenas() {
        return resenas;
    }

    public static void agregarResena(Resena resena) {
        resenas.add(resena);
    }
}
