package pieces;

public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white, white ? 4 : -4, "bishop");
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

        return false;

    }


}

