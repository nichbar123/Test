import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MatrixArt extends JPanel implements ActionListener {
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 2560;
    private static final int FONT_SIZE = 16;
    private static final int COLUMNS = WIDTH / FONT_SIZE;
    private Timer timer;
    private Random random;
    private int[] columnPositions;
    private int[] columnSpeeds;
    private long[] lastCharacterChange;

    public MatrixArt() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
        timer = new Timer(30, this);
        random = new Random();
        columnPositions = new int[COLUMNS];
        columnSpeeds = new int[COLUMNS];
        lastCharacterChange = new long[COLUMNS];
        for (int i = 0; i < COLUMNS; i++) {
            columnPositions[i] = random.nextInt(HEIGHT);
            columnSpeeds[i] = random.nextInt(10) + 1;
            lastCharacterChange[i] = System.currentTimeMillis();
        }
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < COLUMNS; i++) {
            int x = i * FONT_SIZE;
            int y = columnPositions[i];

            char character = (char) (random.nextInt(94) + 33);
            if (System.currentTimeMillis() - lastCharacterChange[i] > 100 * columnSpeeds[i]) {
                g.setColor(new Color(0, 255, 0, 180));
                g.drawString(String.valueOf(character), x, y);
                lastCharacterChange[i] = System.currentTimeMillis();
            } else {
                g.setColor(Color.GREEN);
                g.drawString(String.valueOf(character), x, y);
            }

            columnPositions[i] = y >= HEIGHT ? 0 : y + columnSpeeds[i];
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Matrix Rain Enhanced");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MatrixArt());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

