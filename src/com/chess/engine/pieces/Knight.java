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

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(final int piecePosition, final Colour pieceColour) {
        super(piecePosition, pieceColour);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if(BoardUtils.isValidSquareCoordinate(candidateDestinationCoordinate)){
                if(isAFileExclusion(this.piecePosition, currentCandidateOffset) ||
                        (isBFileExclusion(this.piecePosition, currentCandidateOffset)) ||
                        (isGFileExclusion(this.piecePosition, currentCandidateOffset)) ||
                        (isHFileExclusion(this.piecePosition, currentCandidateOffset))){
                    continue;
                }

                final Square candidateDestinationSquare = board.getSquare(candidateDestinationCoordinate);

                if(!candidateDestinationSquare.isSquareOccupied()){
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestinationSquare.getPiece();
                    final Colour pieceColour = pieceAtDestination.getPieceColour();

                    if(this.pieceColour != pieceColour){
                        legalMoves.add(new Move());
                    }
                }
            }

        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_FILE[currentPosition] && (candidateOffset == -17
                || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isBFileExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_FILE[currentPosition] && (candidateOffset == -10 && candidateOffset == -6);
    }

    private static boolean isGFileExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SEVENTH_FILE[currentPosition] && (candidateOffset == 10 || candidateOffset == 6);
    }

    private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_FILE[currentPosition] && (candidateOffset == 17
                || candidateOffset == 10 || candidateOffset == -6 || candidateOffset == -15);
    }

}
