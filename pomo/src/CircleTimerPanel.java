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

    // Heller Hintergrund (leer)
    g.setColor(new Color(200, 220, 255));
    g.fillOval(x, y, diameter, diameter);

    // Dunkler Bogen (abgelaufene Zeit - wächst!)
    int elapsedSeconds = totalSeconds - secondsLeft;  // ← NEU!
    int angle = (int) ((elapsedSeconds / (double) totalSeconds) * 360);
    g.setColor(new Color(65, 105, 225));
    g.fillArc(x, y, diameter, diameter, 90, -angle);
}
    public void setTotalSeconds(int total) {
        this.totalSeconds = total;
    
    }

}
