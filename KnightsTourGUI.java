
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KnightsTourGUI {

    public static void displaySolution(int[][] solution) {
        int size = solution.length;

        JFrame frame = new JFrame("Knight's Tour Solution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(size, size));

        // Her hücre için boyut tanımla
        int cellSize = 100; // Her hücre için 100x100 piksel
        int frameSize = size * cellSize;

        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                JLabel label = new JLabel(solution[i][j] == 0 ? "" : String.valueOf(solution[i][j]), SwingConstants.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                frame.add(label);
            }
        }

        frame.pack();
        frame.setSize(frameSize, frameSize); // Dinamik boyut
        frame.setVisible(true);

        // PNG olarak kaydet
        saveAsPNG(frame, "knights_tour_solution.png");
    }

    private static void saveAsPNG(JFrame frame, String filePath) {
        try {
            // Ensure all components are laid out and painted
            frame.doLayout();
            frame.validate();

            // Create a buffered image with the frame's full dimensions
            BufferedImage image = new BufferedImage(
                    frame.getWidth(),
                    frame.getHeight(),
                    BufferedImage.TYPE_INT_ARGB
            );

            // Create graphics context directly from the frame's content pane
            Graphics2D g2d = image.createGraphics();
            frame.getContentPane().printAll(g2d);
            g2d.dispose();

            // Save the image
            File file = new File(filePath);
            ImageIO.write(image, "png", file);
            System.out.println("Solution image was saved to " + filePath);

        } catch (IOException e) {
            System.err.println("The image couldn't be saved: " + e.getMessage());
        }
    }
}
