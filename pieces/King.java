package pieces;

public class King extends Piece {
    public King(boolean white) {
        super(white, white ? 1 : -1, "king");
    }
    public boolean canMove(int x,int y,int des_x, int des_y)
    {

        if((des_x-x>1||des_x-x<-1||des_y-y>1||des_y-y<-1)){
            return false;
        }



        return true;
    }
}
