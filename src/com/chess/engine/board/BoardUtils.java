package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_FILE = initFile(0);
    public static final boolean[] SECOND_FILE = initFile(1);
    public static final boolean[] SEVENTH_FILE = initFile(6);
    public static final boolean[] EIGHTH_FILE = initFile(7);

    public static final int NUM_SQUARES = 64;
    public final static int NUM_SQUARES_PER_ROW = 8;

    private BoardUtils(){
        throw new RuntimeException("Cannot instantiate!");
    }

    private static boolean[] initFile(int fileNumber) {
        final boolean[] file = new boolean[NUM_SQUARES];
        do{
            file[fileNumber] = true;
            fileNumber += NUM_SQUARES_PER_ROW;
        } while (fileNumber < NUM_SQUARES);
        return file;
    }

    public static boolean isValidSquareCoordinate(final int coordinate) {
        return coordinate >=0 && coordinate < NUM_SQUARES;
    }
}
