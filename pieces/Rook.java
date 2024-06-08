package pieces;

public class Rook extends Piece {

    public Rook(boolean white) {
        super(white, white ? 3 : -3, "rook");
    }
    public boolean canMove(int x, int y,int des_x, int des_y, int[][] chess)
    {

        if (des_x==x){
            if(y<des_y){
            for (int i=1;y+i<des_y;i++){
                if (chess[x][y+i]!=0){
                    return false;
                }
            }}
            else if(y>des_y){
                for (int i=1;y-i>des_y;i++){
                    if (chess[x][y-i]!=0){
                        return false;
                    }
                }}
            return true;
        }
        else if (des_y==y){
            if(x<des_x){
                for (int i=1;x+i<des_x;i++){
                    if (chess[x+i][y]!=0){
                        return false;
                    }
                }}
            else if(x>des_x){
                for (int i=1;x-i>des_x;i++){
                    if (chess[x-i][y]!=0){
                        return false;
                    }
                }}
            return true;
        }


        return false;
    }
}






