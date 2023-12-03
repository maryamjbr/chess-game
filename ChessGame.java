import javax.swing.*;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard c = new ChessBoard();

        JFrame frame = new JFrame();
        frame.setSize(812, 830);
        frame.setResizable(true);

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                frame.setVisible(true);
                frame.add(c.getUI());
//                for (int i=0;i<10;i++){
//
//                    frame.setVisible(true);
//
//                }
//                frame.repaint();
            }
        });
    }
}
