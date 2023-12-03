import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Board extends ChessBoard{
    int size=removedPieces_w.size();
    boolean White;
    boolean kill2=false;
    public Board(Piece[][] chess,boolean white) {
        super(chess);
        White=white;
    }


        AIPlayer player=new AIPlayer();

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String name = btn.getName();
        String[] index = name.split("-", 2);

        if(!White) {
        boolean is_whites_turn = true;
        if (turnCounter % 2 == 1) {
            is_whites_turn = false;}
            if (firstClick) {
                firstMove[0] = Integer.parseInt(index[0]);
                firstMove[1] = Integer.parseInt(index[1]);
                if (is_whites_turn && chess[firstMove[0]][firstMove[1]] != null) {
                    if (chess[firstMove[0]][firstMove[1]].getKind() > 0) {

                        firstClick = false;
                    }
                }
                if (!is_whites_turn) {

                    saveGame = player.nextMove(saveGame, White);
                    removedPieces_w=computerRemove(saveGame);

                    readData(saveGame);
                    turnCounter++;

                    mapData();
                    try {
                        saveMapData();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    panel.removeAll();
                    panel.revalidate();
                    panel.repaint();
                    initUI();

                }

            } else {
                secondMove[0] = Integer.parseInt(index[0]);
                secondMove[1] = Integer.parseInt(index[1]);

                if (chess[firstMove[0]][firstMove[1]] != null) {
                    if (chess[firstMove[0]][firstMove[1]].getKind() > 0) {
                        if (chess[firstMove[0]][firstMove[1]].getKind() == 1) {
                            King p = new King(true);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);
                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 2) {
                            Queen p = new Queen(true);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
                            if (turnCounter == 0) {
                                possibleMove = false;
                            }
                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 3) {
                            Rook p = new Rook(true);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
                            if (turnCounter == 0) {
                                possibleMove = false;
                            }
                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 4) {
                            Bishop p = new Bishop(true);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
                            if (turnCounter == 0) {
                                possibleMove = false;
                            }
                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 5) {
                            Knight p = new Knight(true);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);

                        } else {
                            Pawn p = new Pawn(true);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], true, saveGame);
//
                        }
                    }
                }
                if (chess[firstMove[0]][firstMove[1]] != null) {

                    if (chess[firstMove[0]][firstMove[1]].getKind() < 0) {
                        if (chess[firstMove[0]][firstMove[1]].getKind() == -1) {
                            King p = new King(false);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);

                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -2) {
                            Queen p = new Queen(false);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
                            if (turnCounter == 0) {
                                possibleMove = false;
                            }

                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -3) {
                            Rook p = new Rook(false);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
                            if (turnCounter == 0) {
                                possibleMove = false;
                            }

                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -4) {
                            Bishop p = new Bishop(false);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
                            if (turnCounter == 0) {
                                possibleMove = false;
                            }

                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -5) {
                            Knight p = new Knight(false);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);


                        } else {
                            Pawn p = new Pawn(false);
                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], false, saveGame);
//
                        }
                    }
                    if (chess[secondMove[0]][secondMove[1]] != null) {
                        if (chess[secondMove[0]][secondMove[1]].isWhite() == chess[firstMove[0]][firstMove[1]].isWhite()) {
                            possibleMove = false;
                        }
                    }
                }

                if (possibleMove) {
                    turnCounter++;

                    possibleMove = false;
                    if (chess[secondMove[0]][secondMove[1]] != null) {
                        if (chess[secondMove[0]][secondMove[1]].isWhite() != chess[firstMove[0]][firstMove[1]].isWhite()) {
                            kill = true;
                            Piece piece = chess[secondMove[0]][secondMove[1]];
                            removedPieces.add(piece.getKind());
                            if (chess[secondMove[0]][secondMove[1]].getKind() == -1) {
                                whiteWin = true;
                            }
                            if (chess[secondMove[0]][secondMove[1]].getKind() == 1) {
                                blackWin = true;

                            }
                        }

                    }
                    chess[secondMove[0]][secondMove[1]] = chess[firstMove[0]][firstMove[1]];
                    chess[firstMove[0]][firstMove[1]] = null;
                    mapData();
                    try {
                        saveMapData();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    panel.removeAll();
                    panel.revalidate();
                    panel.repaint();
                    initUI();
                    firstClick = true;
                    possibleMove = false;
                } else {
                    firstClick = true;
                }
            }
        }






//
//        else {
//            if (firstClick) {
//
//                boolean is_whites_turn = true;
//                if (turnCounter % 2 == 1) {
//                    is_whites_turn = false;
//                }
//                firstMove[0] = Integer.parseInt(index[0]);
//                firstMove[1] = Integer.parseInt(index[1]);
//                if (is_whites_turn) {
//                    saveGame = player.nextMove(saveGame, White);
//                    readData(saveGame);
//                    turnCounter++;
//                    panel.removeAll();
//                    panel.revalidate();
//                    panel.repaint();
//                    initUI();
//                }
//            }
//        }
//                if (is_whites_turn && chess[firstMove[0]][firstMove[1]] != null) {
//                    if (chess[firstMove[0]][firstMove[1]].getKind() > 0) {
//
//                        firstClick = false;
//                    }
//                }
//
//            } else {
//                secondMove[0] = Integer.parseInt(index[0]);
//                secondMove[1] = Integer.parseInt(index[1]);
//
//                if (chess[firstMove[0]][firstMove[1]] != null) {
//                    if (chess[firstMove[0]][firstMove[1]].getKind() > 0) {
//                        if (chess[firstMove[0]][firstMove[1]].getKind() == 1) {
//                            King p = new King(true);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 2) {
//                            Queen p = new Queen(true);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
//                            if (turnCounter == 0) {
//                                possibleMove = false;
//                            }
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 3) {
//                            Rook p = new Rook(true);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
//                            if (turnCounter == 0) {
//                                possibleMove = false;
//                            }
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 4) {
//                            Bishop p = new Bishop(true);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
//                            if (turnCounter == 0) {
//                                possibleMove = false;
//                            }
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == 5) {
//                            Knight p = new Knight(true);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);
//
//                        } else {
//                            Pawn p = new Pawn(true);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], true, saveGame);
////
//                        }
//                    }
//                }
//                if (chess[firstMove[0]][firstMove[1]] != null) {
//
//                    if (chess[firstMove[0]][firstMove[1]].getKind() < 0) {
//                        if (chess[firstMove[0]][firstMove[1]].getKind() == -1) {
//                            King p = new King(false);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);
//
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -2) {
//                            Queen p = new Queen(false);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
//                            if (turnCounter == 0) {
//                                possibleMove = false;
//                            }
//
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -3) {
//                            Rook p = new Rook(false);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
//                            if (turnCounter == 0) {
//                                possibleMove = false;
//                            }
//
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -4) {
//                            Bishop p = new Bishop(false);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], saveGame);
//                            if (turnCounter == 0) {
//                                possibleMove = false;
//                            }
//
//                        } else if (chess[firstMove[0]][firstMove[1]].getKind() == -5) {
//                            Knight p = new Knight(false);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1]);
//
//
//                        } else {
//                            Pawn p = new Pawn(false);
//                            possibleMove = p.canMove(firstMove[0], firstMove[1], secondMove[0], secondMove[1], false, saveGame);
////
//                        }
//                    }
//                    if (chess[secondMove[0]][secondMove[1]] != null) {
//                        if (chess[secondMove[0]][secondMove[1]].isWhite() == chess[firstMove[0]][firstMove[1]].isWhite()) {
//                            possibleMove = false;
//                        }
//                    }
//                }
//
//                if (possibleMove) {
//                    turnCounter++;
//
//                    possibleMove = false;
//                    if (chess[secondMove[0]][secondMove[1]] != null) {
//                        if (chess[secondMove[0]][secondMove[1]].isWhite() != chess[firstMove[0]][firstMove[1]].isWhite()) {
//                            kill = true;
//                            Piece piece = chess[secondMove[0]][secondMove[1]];
//                            removedPieces.add(piece.getKind());
//                            if (chess[secondMove[0]][secondMove[1]].getKind() == -1) {
//                                whiteWin = true;
//                            }
//                            if (chess[secondMove[0]][secondMove[1]].getKind() == 1) {
//                                blackWin = true;
//
//                            }
//                        }
//
//                    }
//                    chess[secondMove[0]][secondMove[1]] = chess[firstMove[0]][firstMove[1]];
//                    chess[firstMove[0]][firstMove[1]] = null;
//                    mapData();
//                    try {
//                        saveMapData();
//                    } catch (FileNotFoundException ex) {
//                        ex.printStackTrace();
//                    }
//                    panel.removeAll();
//                    panel.revalidate();
//                    panel.repaint();
//                    initUI();
//                    firstClick = true;
//                    possibleMove = false;
//                } else {
//                    firstClick = true;
//                }
//            }
//        }
    }



    public void readData(int[][] data) {


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
    public List<Integer> computerRemove (int[][] a) {
//        List<Integer> boardPiece = new ArrayList<>();
        List<Integer> removed = new ArrayList<>();

        int c2=0;
        int c3=0;
        int c4=0;
        int c5=0;
        int c6=0;


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (a[i][j] == 6) {
                    c2++;
                }
                if (a[i][j] == 6) {
                    c3++;
                }
                if (a[i][j] == 6) {
                    c4++;
                }
                if (a[i][j] == 6) {
                    c5++;
                }
                if (a[i][j] == 6) {
                    c6++;
                }
            }
        }
//        System.out.println(boardPiece);

                if (c6<8) {
                    removed.add(6);
                    if (c6<7) {
                        removed.add(6);
                    }
                    if (c6<6) {
                        removed.add(6);
                    }
                    if (c6<5) {
                        removed.add(6);
                    }
                    if (c6<4) {
                        removed.add(6);
                    }
                    if (c6<3) {
                        removed.add(6);
                    }
                    if (c6<2) {
                        removed.add(6);
                    }
                    if (c6<1) {
                        removed.add(6);
                    }
                }
            if (c5<2) {
                removed.add(c5);
                if (c5<1) {
                    removed.add(c5);
                }
            }
            if (c4<2) {
                removed.add(4);
                if (c4<1) {
                    removed.add(4);
                }
            }
            if (c3<2) {
                removed.add(3);
                if (c3<1) {
                    removed.add(3);
                }
            }
        if (c2<1) {
            removed.add(2);
        }

        System.out.println(removed);

        return removed;

        }
    public void initUI() {
        if (!whiteWin && !blackWin) {
            SpringLayout layout = new SpringLayout();
            panel.setLayout(layout);
            panel.setBackground(new Color(190, 190, 190, 255));
            panel.setPreferredSize(new Dimension(1100, 800));
            JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
            top.setPreferredSize(new Dimension(1100, 80));
            top.setBackground(Color.gray);
            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            bottom.setPreferredSize(new Dimension(1100, 80));
            bottom.setBackground(Color.gray);
            JPanel chessBoard = new JPanel(new GridLayout(8, 8));
            chessBoard.setPreferredSize(new Dimension(800, 640));
            if (kill) {
                ImageIcon icon = resizeLabel(labelImage(removedPieces.get(removedPieces.size() - 1)));
                JLabel pic = new JLabel(resizeImg(icon));

                    removedPieces_b.add(removedPieces.get(removedPieces.size() - 1));
                    label_b[removedPieces_b.size() - 1] = pic;

                kill = false;
            }
                for (int i = 0; i < size; i++) {
                    ImageIcon icon = resizeLabel(labelImage(removedPieces_w.get(i)));
                    JLabel pic = new JLabel(resizeImg(icon));
                    label_w[i] = pic;
                    bottom.add(label_w[i]);
                    System.out.println(removedPieces_w);

                }

            for (int i = 0; i < 15 && label_b[i] != null; i++) {
                top.add(label_b[i]);

            }
            layout.putConstraint(SpringLayout.NORTH, top, 0, SpringLayout.NORTH, panel);
            layout.putConstraint(SpringLayout.NORTH, chessBoard, 0, SpringLayout.SOUTH, top);
            layout.putConstraint(SpringLayout.WEST, chessBoard, 135, SpringLayout.WEST, panel);

            layout.putConstraint(SpringLayout.NORTH, bottom, 0, SpringLayout.SOUTH, chessBoard);
            layout.putConstraint(SpringLayout.SOUTH, bottom, 0, SpringLayout.SOUTH, panel);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    JButton button = new JButton();
                    button.setName(i + "-" + j);
                    button.addActionListener(this);
                    button.setPreferredSize(new Dimension(80, 75));
                    button.setOpaque(true);
                    button.setPreferredSize(new Dimension(64, 64));
                    button.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.CYAN, 0),
                            BorderFactory.createLineBorder(Color.BLACK, 0)));
                    if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                        button.setBackground(new Color(211, 211, 199));
                    } else {
                        button.setBackground(new Color(91, 117, 91));
                    }

                    Squares[i][j] = button;
                    chessBoard.add(Squares[i][j]);

                }
            }
            setIcon(Squares);
            panel.add(top);
            panel.add(chessBoard);
            panel.add(bottom);
            chessBoard.setLayout(new GridLayout(8, 8));
            chessBoard.setVisible(true);
            panel.setVisible(true);
        } else if (whiteWin) {
            JPanel p = new JPanel();
            panel.setSize(500, 550);
            panel.setBackground(Color.white);
            JTextField textField = new JTextField("                                                        white win the game");
            textField.setPreferredSize(new Dimension(500, 100));
            textField.setBackground(new Color(183, 210, 217));
            p.setSize(600, 800);
            p.setBackground(new Color(138, 153, 159));
            p.add(textField);
            panel.add(p, Component.CENTER_ALIGNMENT);
            panel.setVisible(true);
        } else {
            JPanel p = new JPanel();
            panel.setSize(500, 550);
            panel.setBackground(Color.white);
            JTextField textField = new JTextField("                               black win the game");
            textField.setPreferredSize(new Dimension(500, 100));
            textField.setBackground(new Color(181, 209, 213));
            p.setSize(600, 800);
            p.setBackground(new Color(138, 153, 159));
            p.add(textField);
            panel.add(p, Component.CENTER_ALIGNMENT);
            panel.setVisible(true);
        }
    }
}
