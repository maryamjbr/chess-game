package pieces;

public class Piece {


    private boolean white = false;
    private int kind = 0;
    private String img = null;


    public Piece( boolean white, int kind, String img)
    {

        this.white = white;
        this.kind = kind;
        this.img = img;


    }

    public Piece(boolean white) {


    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public boolean isWhite() {
        return white;
    }

    public int getKind() {
        return kind;
    }

    public String getImg() {
        return img;
    }



    public boolean canMove(int x,int y,int des_x, int des_y)
    {
        return true;
    }
}
