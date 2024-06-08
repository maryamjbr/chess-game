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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new CardLayout());
        JPanel startPanel = new JPanel();
        JPanel playPanel = new JPanel();

        startPanel.setSize(500, 550);
        startPanel.setBackground(Color.white);
        playPanel.setBackground(Color.white);

        JButton b1 = new JButton("Play New Game");
        JButton b2 = new JButton("Play Previous Game");
        b1.setPreferredSize(new Dimension(500, 100));
        b2.setPreferredSize(new Dimension(500, 100));
        b1.setBackground(new Color(102, 131, 136));
        b2.setBackground(new Color(154, 151, 151));

        // Adding components to startPanel
        ImageIcon icon = new ImageIcon("icons\\chess-symbol.jpg");
        JLabel pic = new JLabel(resizeImg(icon));
        startPanel.add(pic);
        startPanel.add(b1);
        startPanel.add(b2);

        SpringLayout layout = new SpringLayout();
        layout.putConstraint(SpringLayout.NORTH, pic, 0, SpringLayout.NORTH, startPanel);
        layout.putConstraint(SpringLayout.NORTH, b1, 5, SpringLayout.SOUTH, pic);
        layout.putConstraint(SpringLayout.NORTH, b2, 10, SpringLayout.SOUTH, b1);
        layout.putConstraint(SpringLayout.SOUTH, b2, 5, SpringLayout.SOUTH, startPanel);
        startPanel.setLayout(layout);

        // Action Listener for "Play New Game"
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b11 = new JButton("Play With Computer");
                JButton b12 = new JButton("Play With Friend");

                b11.setPreferredSize(new Dimension(500, 100));
                b12.setPreferredSize(new Dimension(500, 100));
                b11.setBackground(new Color(102, 131, 136));
                b12.setBackground(new Color(154, 151, 151));

                playPanel.removeAll();
                playPanel.add(pic);
                playPanel.add(b11);
                playPanel.add(b12);

                SpringLayout layout = new SpringLayout();
                layout.putConstraint(SpringLayout.NORTH, pic, 0, SpringLayout.NORTH, playPanel);
                layout.putConstraint(SpringLayout.NORTH, b11, 5, SpringLayout.SOUTH, pic);
                layout.putConstraint(SpringLayout.NORTH, b12, 10, SpringLayout.SOUTH, b11);
                layout.putConstraint(SpringLayout.SOUTH, b12, 5, SpringLayout.SOUTH, playPanel);
                playPanel.setLayout(layout);
//                playPanel.add(b11);
//                playPanel.add(b12);
                playPanel.revalidate();
                playPanel.repaint();

                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "playPanel");

                // Action Listener for "Play With Computer"
                b11.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Board chessBoard = new Board(data.getNewChessData(), false);
                        frame.getContentPane().removeAll();
                        frame.setSize(1100, 830);
                        frame.setResizable(true);
                        frame.add(chessBoard.getUI());
                        frame.revalidate();
                        frame.repaint();
                        al[0] = true;
                    }
                });

                // Action Listener for "Play With Friend"
                b12.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChessBoard chessBoard1 = new ChessBoard(data.getNewChessData());
                        frame.getContentPane().removeAll();
                        frame.setSize(1100, 830);
                        frame.setResizable(true);
                        frame.add(chessBoard1.getUI());
                        frame.revalidate();
                        frame.repaint();
                    }
                });
            }
        });

        // Action Listener for "Play Previous Game"
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (al[0]) {
                        Board chessBoard = new Board(data.getPreviousChessData(), false);
                        frame.getContentPane().removeAll();
                        frame.setSize(1100, 830);
                        frame.setResizable(true);
                        frame.add(chessBoard.getUI());
                    } else {
                        ChessBoard chessBoard = new ChessBoard(data.getPreviousChessData());
                        frame.getContentPane().removeAll();
                        frame.setSize(1100, 830);
                        frame.setResizable(true);
                        frame.add(chessBoard.getUI());
                    }
                    frame.revalidate();
                    frame.repaint();
                } catch (Exception exception) {
                    System.out.println("You don't have any game");
                }
            }
        });

        mainPanel.add(startPanel, "startPanel");
        mainPanel.add(playPanel, "playPanel");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static ImageIcon resizeImg(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}
