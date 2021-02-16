package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_FILE = null;
    public static final boolean[] SECOND_FILE = null;
    public static final boolean[] SEVENTH_FILE = null;
    public static final boolean[] EIGHTH_FILE = null;

    private BoardUtils(){
        throw new RuntimeException("Cannot instantiate!");
    }
    public static boolean isValidSquareCoordinate(int coordinate) {
        return coordinate >=0 && coordinate < 64;
    }
}
