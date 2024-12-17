import java.awt.*;
import javax.swing.*;

public class KnightsTourGUI {

    public static void displaySolution(int[][] solution) {
        int size = solution.length;

        JFrame frame = new JFrame("Knight's Tour Solution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(size, size));

        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                JLabel label = new JLabel(solution[i][j] == 0 ? "" : String.valueOf(solution[i][j]), SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                frame.add(label);
            }
        }

        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
