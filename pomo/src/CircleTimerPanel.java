package src;
import javax.swing.*;
import java.awt.*;

public class CircleTimerPanel extends JPanel {
    private int secondsLeft;
    private int totalSeconds;

    public CircleTimerPanel(int totalSeconds) {
        this.totalSeconds = totalSeconds;
        this.secondsLeft = totalSeconds;
    }

    public void updateTime(int secondsLeft) {
        this.secondsLeft = secondsLeft;
        repaint();
    }

   @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int diameter = Math.min(getWidth(), getHeight()) - 20;
    int x = (getWidth() - diameter) / 2;
    int y = (getHeight() - diameter) / 2;

    // 1. Hellrosa Hintergrund (gesamte Stunde)
    g.setColor(new Color(255, 200, 200));
    g.fillOval(x, y, diameter, diameter);

    // 2. Dunkelrot (verbleibende Pomodoro-Zeit)
    g.setColor(new Color(255, 100, 100));
    int remainingAngle = (int) (((double) secondsLeft / 3600.0) * 360);
    g.fillArc(x, y, diameter, diameter, 90, -remainingAngle);  // GEGEN Uhrzeigersinn

    // 4. Zeittext
    g.setColor(Color.BLACK);
    g.setFont(new Font("Arial", Font.BOLD, 24));
    String timeText = String.format("%02d:%02d", secondsLeft / 60, secondsLeft % 60);
    FontMetrics fm = g.getFontMetrics();
    int textX = getWidth() / 2 - fm.stringWidth(timeText) / 2;
    int textY = getHeight() / 2 + fm.getAscent() / 2;
    g.drawString(timeText, textX, textY);
}

    public void setTotalSeconds(int total) {
        this.totalSeconds = total;
    }
    
}
