import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGame {
    public static void main(String[] args) {
        final boolean[] al = {false};
        Data data = new Data();
        JFrame frame = new JFrame();
        frame.setBackground(Color.white);
        frame.setSize(500, 550);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setSize(500, 550);
        panel.setBackground(Color.white);

        JButton b1 = new JButton();
        JButton b2 = new JButton();
        b1.setText("Play New Game");
        b2.setText("Play Previous Game");
        b1.setPreferredSize(new Dimension(500, 100));
        b2.setPreferredSize(new Dimension(500, 100));
        b1.setBackground(new Color(102, 131, 136));
        b2.setBackground(new Color(154, 151, 151));
        b1.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                b1.setText("Play With Computer");
                b1.setPreferredSize(new Dimension(500, 210));
                JButton b12 = new JButton();
                b12.setText("Play With Friend");
                b12.setPreferredSize(new Dimension(500, 230));
                b12.setBackground(new Color(154, 151, 151));
                panel.remove(b2);
                panel.add(b12);
                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Board chessBoard = new Board(data.getNewChessData(), false);
                                frame.getContentPane().removeAll();
                                frame.repaint();
                                frame.setSize(1100, 830);
                                frame.setResizable(true);
                                frame.setVisible(true);
                                frame.add(chessBoard.getUI());
                                al[0] =true;

//                        b1.setText("black");
//                        b2.setText("white");
//                        b1.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                Board chessBoard = new Board(data.getNewChessData(), false);
//                                frame.getContentPane().removeAll();
//                                frame.repaint();
//                                frame.setSize(1100, 830);
//                                frame.setResizable(true);
//                                frame.setVisible(true);
//                                frame.add(chessBoard.getUI());
//                            }
//                        });
//                        b2.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                Board chessBoard = new Board(data.getNewChessData(), true);
//                                frame.getContentPane().removeAll();
//                                frame.repaint();
//                                frame.setSize(1100, 830);
//                                frame.setResizable(true);
//                                frame.setVisible(true);
//                                frame.add(chessBoard.getUI());
//                            }
//                        });

                    }
                });
                b12.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChessBoard chessBoard1 = new ChessBoard(data.getNewChessData());
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.setSize(1100, 830);
                        frame.setResizable(true);
                        frame.setVisible(true);

                        frame.add(chessBoard1.getUI());
                    }
                });
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(al[0]){
                    Board chessBoard = new Board(data.getPreviousChessData(),false);
                    frame.getContentPane().removeAll();
                    frame.repaint();
                    frame.setSize(1100, 830);
                    frame.setResizable(true);
                    frame.setVisible(true);
                    frame.add(chessBoard.getUI());}
                    else {
                        ChessBoard chessBoard = new ChessBoard(data.getPreviousChessData());
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.setSize(1100, 830);
                        frame.setResizable(true);
                        frame.setVisible(true);
                        frame.add(chessBoard.getUI());
                    }

                } catch (Exception exception) {
                    System.out.println("you don't have any game");
                }
            }
        });
        ImageIcon icon = new ImageIcon("icons\\chess-symbol.jpg");
        JLabel pic = new JLabel(resizeImg(icon));
        panel.add(pic);
        panel.add(b1);
        panel.add(b2);
        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.NORTH, pic, 0, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, b1, 5, SpringLayout.SOUTH, pic);
        layout.putConstraint(SpringLayout.NORTH, b2, 10, SpringLayout.SOUTH, b1);
        layout.putConstraint(SpringLayout.SOUTH, b2, 5, SpringLayout.SOUTH, panel);
        panel.setLayout(layout);
        frame.add(panel);
        frame.setVisible(true);
        panel.setVisible(true);
    }

    public static ImageIcon resizeImg(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(500, 300, image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
