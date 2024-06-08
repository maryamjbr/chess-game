package pieces;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white, white ? 5 : -5, "knight");
    }
    public boolean canMove(int x,int y,int des_x, int des_y)
    {

        // WRITE CODE HERE
        if(des_x-x==2||des_x-x==-2){
            if (des_y-y==-1||des_y-y==1){
                return true;
            }
            return false;
        } else if((des_x-x==1)||des_x-x==-1){
            if (des_y-y==-2||des_y-y==2){
                return true;
            }
        }


        return false;

    }
}