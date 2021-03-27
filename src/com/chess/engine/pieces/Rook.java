package com.chess.engine.pieces;

import com.chess.engine.Colour;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Square;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_CANDIDATE = {-8, -1, 1, 8};

    Rook(int piecePosition, Colour pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_CANDIDATE){

            int candidateDestinationCoordinate = this.piecePosition;

            while(BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)){
                if(isAFileExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isHFileExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
                    break;
                }

                candidateDestinationCoordinate += candidateCoordinateOffset;

                if(BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)){

                    final Square candidateDestinationSquare = board.getSquare(candidateDestinationCoordinate);

                    if(!candidateDestinationSquare.isSquareOccupied()){
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
                        final Colour pieceColour = pieceAtDestination.getPieceColour();

                        if(this.pieceColour != pieceColour){
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }

                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_FILE[currentPosition] && (candidateOffset == -1);
    }

    private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_FILE[currentPosition] && (candidateOffset == 1);
    }
}
