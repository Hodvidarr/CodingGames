package com.hodvidar.codingame.puzzles.event.y2019amadeus;

import java.util.Scanner;

import static java.lang.Math.abs;

public class Coord {
    final int x;
    final int y;

    Coord(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    Coord(final Scanner in) {
        this(in.nextInt(), in.nextInt());
    }

    Coord add(final Coord other) {
        return new Coord(x + other.x, y + other.y);
    }

    // Manhattan distance (for 4 directions maps)
    // see: https://en.wikipedia.org/wiki/Taxicab_geometry
    int distance(final Coord other) {
        return abs(x - other.x) + abs(y - other.y);
    }

    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + x;
        result = PRIME * result + y;
        return result;
    }

    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Coord other = (Coord) obj;
        return (x == other.x) && (y == other.y);
    }

    public String toString() {
        return x + " " + y;
    }
}