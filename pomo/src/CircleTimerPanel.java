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

    int diameter = Math.min(getWidth(), getHeight()) - 20;
    int x = (getWidth() - diameter) / 2;
    int y = (getHeight() - diameter) / 2;

    // 1. Hellblauer Hintergrund (voller Kreis - abgelaufene Zeit)
    g.setColor(new Color(200, 220, 255));  // Ganz hell
    g.fillOval(x, y, diameter, diameter);

    // 2. Dunkelblauer Bogen (verbleibende Zeit)
    int angle = (int) ((secondsLeft / (double) totalSeconds) * 360);
    g.setColor(new Color(65, 105, 225));   // Dunkel
    g.fillArc(x, y, diameter, diameter, 90, -angle);
}
    public void setTotalSeconds(int total) {
        this.totalSeconds = total;
    
    }

}
