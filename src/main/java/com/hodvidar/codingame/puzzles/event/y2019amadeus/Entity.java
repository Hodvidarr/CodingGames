package com.hodvidar.codingame.puzzles.event.y2019amadeus;

import java.util.Scanner;

class Entity {
    private static final Coord DEAD_POS = new Coord(-1, -1);

    // Updated every turn
    final int id;
    final EntityType type;
    final Coord pos;
    final EntityType item;

    // Computed for my robots
    Action action;

    Entity(final Scanner in) {
        id = in.nextInt();
        type = EntityType.valueOf(in.nextInt());
        pos = new Coord(in);
        item = EntityType.valueOf(in.nextInt());
    }

    boolean isAlive() {
        return !DEAD_POS.equals(pos);
    }
}