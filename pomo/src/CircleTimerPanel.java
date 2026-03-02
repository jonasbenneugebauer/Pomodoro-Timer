import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CircleTimerPanel extends JPanel {

    private int secondsLeft;
    private int totalSeconds;

    public CircleTimerPanel(int seconds) {
        this.totalSeconds = seconds;
        this.secondsLeft = seconds;
    }

     public void updateTime(int seconds) {
        this.secondsLeft = seconds;
        repaint(); // Trigger a repaint to update the circle
    }

     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 200, 200)); // Set color for the remaining time
        g.fillOval(100, 100, 200, 200);

        int angle = (int) ((secondsLeft / 3600.0) * 360);
        g.setColor(new Color(255, 100, 100)); // Set color for the elapsed time
        g.fillArc(100, 100, 200, 200, 90 - angle, angle);
    }

}
