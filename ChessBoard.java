import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChessBoard implements ActionListener {
    public JButton[][] Squares = new JButton[8][8];
    public JPanel panel = new JPanel();
    public JLabel[] label_w = new JLabel[15];
    public JLabel[] label_b = new JLabel[15];
    public int turnCounter = 0;
    boolean possibleMove = false;
    boolean whiteWin = false;
    boolean blackWin = false;
    boolean firstClick = true;
    boolean kill = false;
    int[] firstMove = new int[2];
    int[] secondMove = new int[2];
    int[][] saveGame = new int[8][8];
    public Piece[][] chess;
    static List<Integer> removedPieces = new ArrayList<>();
    static List<Integer> removedPieces_b = new ArrayList<>();
    static List<Integer> removedPieces_w = new ArrayList<>();

    public ChessBoard(Piece[][] chess) {
        this.chess = chess;
        initUI();
    }

    public ChessBoard() {

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
                if (removedPieces.get(removedPieces.size() - 1) > 0) {
                    removedPieces_w.add(removedPieces.get(removedPieces.size() - 1));
                    label_w[removedPieces_w.size() - 1] = pic;
                } else {
                    removedPieces_b.add(removedPieces.get(removedPieces.size() - 1));
                    label_b[removedPieces_b.size() - 1] = pic;
                }
                kill = false;
            }
            for (int i = 0; i < 15 && label_w[i] != null; i++) {
                bottom.add(label_w[i]);

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

    public JPanel getUI() {
        return panel;
    }

    public void setIcon(JButton[][] Squares) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chess[i][j] != null) {
                    Piece piece = chess[i][j];
                    String BW = piece.isWhite() ? "w_" : "b_";
                    ImageIcon imageIcon = new ImageIcon("icons/" + BW + piece.getImg() + ".png");
                    Squares[i][j].setIcon(resizeImg(imageIcon));
                }
            }
        }
    }

    public static ImageIcon resizeImg(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(65, 65, image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public static ImageIcon resizeLabel(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(40, 40, image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public static ImageIcon labelImage(int removedPieces) {

        if (removedPieces == 1) {
            ImageIcon imageIcon = new ImageIcon("icons\\w_king.png");
            return resizeLabel(imageIcon);


        } else if (removedPieces == 2) {
            ImageIcon imageIcon = new ImageIcon("icons\\w_queen.png");
            return resizeLabel(imageIcon);

        } else if (removedPieces == 3) {
            ImageIcon imageIcon = new ImageIcon("icons\\w_rook.png");
            return resizeLabel(imageIcon);


        } else if (removedPieces == 4) {
            ImageIcon imageIcon = new ImageIcon("icons\\w_bishop.png");
            return resizeLabel(imageIcon);

        } else if (removedPieces == 5) {
            ImageIcon imageIcon = new ImageIcon("icons\\w_knight.png");
            return resizeLabel(imageIcon);


        } else if (removedPieces == 6) {
            ImageIcon imageIcon = new ImageIcon("icons\\w_pawn.png");
            return resizeLabel(imageIcon);

        } else if (removedPieces == -1) {
            ImageIcon imageIcon = new ImageIcon("icons\\b_king.png");
            return resizeLabel(imageIcon);


        } else if (removedPieces == -2) {
            ImageIcon imageIcon = new ImageIcon("icons\\b_queen.png");
            return resizeLabel(imageIcon);


        } else if (removedPieces == -3) {
            ImageIcon imageIcon = new ImageIcon("icons\\b_rook.png");
            return resizeLabel(imageIcon);

        } else if (removedPieces == -4) {
            ImageIcon imageIcon = new ImageIcon("icons\\b_bishop.png");
            return resizeLabel(imageIcon);


        } else if (removedPieces == -5) {
            ImageIcon imageIcon = new ImageIcon("icons\\b_knight.png");
            return resizeLabel(imageIcon);


        } else if (removedPieces == -6) {
            ImageIcon imageIcon = new ImageIcon("icons\\b_pawn.png");
            return resizeLabel(imageIcon);


        }
        ImageIcon imageIcon = new ImageIcon();
        return resizeLabel(imageIcon);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String name = btn.getName();
        String[] index = name.split("-", 2);
//        System.out.println(index[0] + '-' + index[1]);
        boolean is_whites_turn = true;
        if (turnCounter % 2 == 1) {
            is_whites_turn = false;
        }
        if (firstClick) {
            firstMove[0] = Integer.parseInt(index[0]);
            firstMove[1] = Integer.parseInt(index[1]);
            if (is_whites_turn && chess[firstMove[0]][firstMove[1]] != null) {
                if (chess[firstMove[0]][firstMove[1]].getKind() > 0) {

                    firstClick = false;
                }
            }
            if (!is_whites_turn && chess[firstMove[0]][firstMove[1]] != null) {

                if (chess[firstMove[0]][firstMove[1]].getKind() < 0) {

                    firstClick = false;
                }
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


    public void mapData() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chess[i][j] != null) {
                    Piece piece = chess[i][j];
                    saveGame[i][j] = piece.getKind();
                } else {
                    saveGame[i][j] = 0;
                }
            }
        }
    }

    public void saveMapData() throws FileNotFoundException {

        PrintStream out = new PrintStream("saveGame.txt");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String value = saveGame[i][j] <= 0 ? saveGame[i][j] + "" : "+" + saveGame[i][j];
                out.print(value);
                out.print(" ");
            }
            out.print("\n");
        }

    }



}
