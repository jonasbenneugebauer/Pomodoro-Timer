
import javax.swing.*;
import java.awt.*;

public class PomodoroGUI {  

    private JFrame frame;
    private JButton startButton;
    private JButton stopButton;
    private Pomodoro pomodoro;
    private Thread timerThread;
    private CircleTimerPanel circlePanel;

    public PomodoroGUI() {
    frame = new JFrame("Pomodoro Timer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // we'll use pack() later so don't set a fixed size
    frame.getContentPane().setBackground(new Color(45, 52, 54));

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    circlePanel = new CircleTimerPanel(25 * 60); // 25 Minuten in Sekunden
    // make the circle slightly smaller but still prominent
    circlePanel.setPreferredSize(new Dimension(500, 500));

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

        // Layout: circle centered, buttons below
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(circlePanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(new Color(45, 52, 54));
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);

        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
// time is shown inside the circle; updateLabel removed

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
        // reset circle to zero
        SwingUtilities.invokeLater(() -> circlePanel.updateTime(0));
    }
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
