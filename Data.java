import pieces.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {
    private Piece[][] chess = new Piece[8][8];

    private void initChess() {
        chess[0][0] = new Rook(false);
        chess[0][1] = new Knight(false);
        chess[0][2] = new Bishop(false);
        chess[0][3] = new Queen(false);
        chess[0][4] = new King(false);
        chess[0][5] = new Bishop(false);
        chess[0][6] = new Knight(false);
        chess[0][7] = new Rook(false);
        chess[1][0] = new Pawn(false);
        chess[1][1] = new Pawn(false);
        chess[1][2] = new Pawn(false);
        chess[1][3] = new Pawn(false);
        chess[1][4] = new Pawn(false);
        chess[1][5] = new Pawn(false);
        chess[1][6] = new Pawn(false);
        chess[1][7] = new Pawn(false);
        chess[6][0] = new Pawn(true);
        chess[6][1] = new Pawn(true);
        chess[6][2] = new Pawn(true);
        chess[6][3] = new Pawn(true);
        chess[6][4] = new Pawn(true);
        chess[6][5] = new Pawn(true);
        chess[6][6] = new Pawn(true);
        chess[6][7] = new Pawn(true);
        chess[7][0] = new Rook(true);
        chess[7][1] = new Knight(true);
        chess[7][2] = new Bishop(true);
        chess[7][3] = new Queen(true);
        chess[7][4] = new King(true);
        chess[7][5] = new Bishop(true);
        chess[7][6] = new Knight(true);
        chess[7][7] = new Rook(true);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chess[i][j] = chess[i][j];
            }
        }
    }

    public Piece[][] getNewChessData() {
        initChess();
        return chess;
    }

    public Piece[][] getPreviousChessData() {
        this.readMapData();
        return chess;
    }

    public void readMapData() {
        int[][] data = new int[8][8];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("saveGame.txt"));
            String line;
            for (int i = 0; i < 8; i++) {
                line = reader.readLine();
                String[] numbers = line.split(" ");
                for (int j = 0; j < 8; j++) {
                    data[i][j] = Integer.parseInt(numbers[j]);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int num = data[i][j];
                Piece piece = null;
                switch (num) {
                    case 1:
                        piece = new King(true);
                        break;
                    case -1:
                        piece = new King(false);
                        break;
                    case 2:
                        piece = new Queen(true);
                        break;
                    case -2:
                        piece = new Queen(false);
                        break;
                    case 3:
                        piece = new Rook(true);
                        break;
                    case -3:
                        piece = new Rook(false);
                        break;
                    case 4:
                        piece = new Bishop(true);
                        break;
                    case -4:
                        piece = new Bishop(false);
                        break;
                    case 5:
                        piece = new Knight(true);
                        break;
                    case -5:
                        piece = new Knight(false);
                        break;
                    case 6:
                        piece = new Pawn(true);
                        break;
                    case -6:
                        piece = new Pawn(false);
                        break;
                }
                chess[i][j] = piece;
            }
        }
    }
}
