public abstract class Square {

    int squareCoordinate;

    Square(int squareCoordinate){

        this.squareCoordinate = squareCoordinate;
    }

    public abstract boolean isSquareOccupied();

    public abstract Piece getPiece();

    public  static final class EmptySquare extends Square{

        EmptySquare(int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isSquareOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }

    }

    public static final class OccupiedSquare extends Square{

        Piece pieceOnSquare;

        OccupiedSquare(int squareCoordinate, Piece pieceOnSquare){
            super(squareCoordinate);
            this.pieceOnSquare = pieceOnSquare;
        }

        @Override
        public boolean isSquareOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return  pieceOnSquare;
        }
    }
}

