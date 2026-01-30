import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class RubberBandEffect extends JPanel {

    private static final double SPRING_CONSTANT = 0.05;
    private static final double DAMPING_CONSTANT = 0.9;
    private static final double MASS = 1;

    private Point2D anchor = new Point2D.Double(250, 250);
    private Point2D position = new Point2D.Double(250, 250);
    private Point2D velocity = new Point2D.Double(0, 0);

    public RubberBandEffect() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                anchor.setLocation(e.getPoint());
            }
        };
        addMouseMotionListener(mouseAdapter);
        new Timer(20, e -> update()).start();
    }

    private void update() {
        double forceX = -SPRING_CONSTANT * (position.getX() - anchor.getX());
        double forceY = -SPRING_CONSTANT * (position.getY() - anchor.getY());

        double accelerationX = forceX / MASS;
        double accelerationY = forceY / MASS;

        velocity.setLocation(velocity.getX() * DAMPING_CONSTANT + accelerationX, velocity.getY() * DAMPING_CONSTANT + accelerationY);

        position.setLocation(position.getX() + velocity.getX(), position.getY() + velocity.getY());

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.draw(new Line2D.Double(anchor, position));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Elastic Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new RubberBandEffect());
        frame.setVisible(true);
    }
}


