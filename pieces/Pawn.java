package pieces;

import java.lang.reflect.Array;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white, white ? 6 : -6, "pawn");

    }


    public boolean canMove(int x, int y, int destination_x, int destination_y, boolean white, int[][] chess) {


        if (white) {
            if (x == 6) {
                if ((( destination_x - x == -1) && destination_y == y)&&chess[destination_x][destination_y] == 0) {
                    return true;
                }else if (((destination_x - x == -2 ) && destination_y == y)&&chess[destination_x][destination_y] == 0&&chess[destination_x+1][destination_y] == 0) {
                    return true;
                }
                else if ((destination_x - x == -1) && ((destination_y == y&&chess[destination_x][destination_y] == 0) || (chess[destination_x][destination_y] != 0 && (destination_y - y == 1 || destination_y - y == -1)))) {
                    return true;

                }
            } else if ((destination_x - x == -1) && ((destination_y == y&&chess[destination_x][destination_y] == 0) || (chess[destination_x][destination_y] != 0 && (destination_y - y == 1 || destination_y - y == -1)))) {
                return true;

            }

        } else {
            if (x == 1) {
                if ((( destination_x - x == 1) && destination_y == y)&&chess[destination_x][destination_y] == 0) {
                    return true;
                } if (((destination_x - x == 2 ) && destination_y == y)&&chess[destination_x][destination_y] == 0&&chess[destination_x-1][destination_y] == 0) {
                    return true;
                }
                else if ((destination_x - x == 1) && ((destination_y == y&&chess[destination_x][destination_y] == 0) || (chess[destination_x][destination_y] != 0 && (destination_y - y == 1 || destination_y - y == -1)))) {
                    return true;

                }
            } else if ((destination_x - x == 1) && ((destination_y == y&&chess[destination_x][destination_y] == 0) || (chess[destination_x][destination_y] != 0 && (destination_y - y == 1 || destination_y - y == -1)))) {
                return true;

            }
        }

        return false;
    }
}
