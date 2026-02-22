
import javax.swing.*;
import java.awt.*;

public class PomodoroGUI {  

    private JFrame frame;
    private JLabel label;
    private JLabel statusLabel;
    private JButton startButton;
    private JButton stopButton;
    private Pomodoro pomodoro;
    private Thread timerThread;
    private CircleTimerPanel circlePanel;

    public PomodoroGUI() {
        frame = new JFrame("Pomodoro Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 720);
        frame.getContentPane().setBackground(new Color(45, 52, 54));

        statusLabel = new JLabel("Ready", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        label = new JLabel("00:00", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 48));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);  // Weiße Schrift
        statusLabel.setForeground(new Color(178, 190, 195));  // Hellgrau

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        circlePanel = new CircleTimerPanel(25 * 60); // 25 Minuten in Sekunden
        circlePanel.setPreferredSize(new Dimension(200, 200));

        startButton.addActionListener(e -> {
            try {
                startTimer();
            } catch (Exception ex) {
                ex.printStackTrace();
                // show a dialog so the user sees the error
                JOptionPane.showMessageDialog(frame, "Fehler beim Starten des Timers:\n" + ex.toString(), "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        });
        stopButton.addActionListener(e -> stopTimer());
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.setBackground(new Color(46, 213, 115));  // Grün
        startButton.setForeground(Color.BLACK);  // Schwarze Schrift
        startButton.setFocusPainted(false);  // Kein Fokus-Rahmen
        startButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        
        stopButton.setBackground(new Color(255, 71, 87));  // Rot
        stopButton.setForeground(Color.BLACK);
        stopButton.setFocusPainted(false);
        stopButton.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(statusLabel);
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(stopButton);
        panel.add(Box.createVerticalGlue());
        panel.setBackground(new Color(45, 52, 54));
        panel.add(circlePanel);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
public void updateLabel(String time) {
    SwingUtilities.invokeLater(() -> label.setText(time));
}

public void startTimer() {
    if(timerThread != null && timerThread.isAlive()) {
        return; // Timer läuft bereits
    }
    pomodoro = new Pomodoro(25, 5, 4, this);
    circlePanel.setTotalSeconds(25 * 60); // Setze die Gesamtzeit für den Kreis
    timerThread = new Thread(() -> {
        try {
            pomodoro.start();
        } catch (Throwable t) {
            // Log any error that happens in the timer thread
            t.printStackTrace();
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, "Fehler im Timer-Thread:\n" + t.toString(), "Fehler", JOptionPane.ERROR_MESSAGE));
        }
    });
    timerThread.setDaemon(true);
    timerThread.start();
}

public void stopTimer() {
    if(timerThread != null && timerThread.isAlive()) {
        timerThread.interrupt();
        updateLabel("00:00");
    }
}

public void updateStatus(String status){
    SwingUtilities.invokeLater(() -> statusLabel.setText(status));
}

   
    public static void main(String[] args) {
        new PomodoroGUI();
        System.out.println("Pomodoro Timer gestartet");
    }

    public void updateCircle(int secondsLeft) {
        SwingUtilities.invokeLater(() -> circlePanel.updateTime(secondsLeft));
    }
    
    public void setCircleTotal(int total) {
        SwingUtilities.invokeLater(() -> circlePanel.setTotalSeconds(total));
    }
}
