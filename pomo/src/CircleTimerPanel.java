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

        int diameter = Math.min(getWidth(), getHeight()) - 20; // Leave some padding
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        g.setColor(new Color(65, 105, 225)); // Set color for the total time
        g.fillOval(x, y, diameter, diameter);

        int angle = (int) ((secondsLeft / 3600.0) * 360);
        g.setColor(new Color(135, 206, 235)); // Set color for the remaining time
        g.fillArc(x, y, diameter, diameter, 90 - angle, angle);
    }

}
