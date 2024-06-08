package pieces;

public class Queen extends Piece {

    public Queen(boolean white) {
        super(white, white ? 2 : -2, "queen");
    }

    public boolean canMove(int x, int y, int des_x, int des_y, int[][] chess) {

        if ((des_x - x == des_y - y )&&des_x>x) {
            for (int n=1; x + n>= 0 && x + n < 8 && y + n >= 0 && y + n < 8&&x+n<des_x&&y+n<des_y;n++) {

                if (chess[x + n][y + n] != 0)
                    return false;

            }
            return true;
        }
        else if ((des_x - x == des_y - y )&&des_x<x) {
            for (int n=1;x - n >= 0 && x - n < 8  && y - n >= 0 && y - n < 8 &&x-n>des_x&&y-n>des_y ;n++) {

                if (chess[x - n][y - n] != 0)
                    return false;

            }
            return true;
        }
        else   if(( des_x - x == -(des_y - y))&&des_x>x) {
            for (int n1=1;x + n1 >= 0 && x + n1 < 8 && y - n1 >= 0 && y - n1 < 8 && x+n1<des_x &&y-n1>des_y;n1++) {

                if (chess[x + n1][y - n1] != 0)
                    return false;
            }
            return true;
        }
        else if( (des_x - x == -(des_y - y))&&des_x<x) {
            for (int n1=1;x - n1 >= 0 && x - n1 < 8  && y + n1 >= 0 && y + n1 < 8&&x-n1>des_x&&y+n1<des_y;n1++) {
                if (chess[x - n1][y + n1] != 0)
                    return false;

            }
            return true;
        }
        if (des_x == x) {
            if (y < des_y) {
                for (int i = 1; y + i < des_y; i++) {
                    if (chess[x][y + i] != 0) {
                        return false;
                    }
                }
            } else if (y > des_y) {
                for (int i = 1; y - i > des_y; i++) {
                    if (chess[x][y - i] != 0) {
                        return false;
                    }
                }
            }
            return true;
        } else if (des_y == y) {
            if (x < des_x) {
                for (int i = 1; x + i < des_x; i++) {
                    if (chess[x + i][y] != 0) {
                        return false;
                    }
                }
            } else if (x > des_x) {
                for (int i = 1; x - i > des_x; i++) {
                    if (chess[x - i][y] != 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}