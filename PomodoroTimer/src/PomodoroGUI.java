package PomodoroTimer.src;

import javax.swing.*;
import java.awt.*;

public class PomodoroGUI {  

    private JFrame frame;
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;
    private Pomodoro pomodoro;
    private Thread timerThread;

    public PomodoroGUI() {
        frame = new JFrame("Pomodoro Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        label = new JLabel("00:00", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 48));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e -> startTimer());
        stopButton.addActionListener(e -> stopTimer());
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(stopButton);
        panel.add(Box.createVerticalGlue());

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
public void updateLabel(String time) {
    SwingUtilities.invokeLater(() -> label.setText(time));
}

public void startTimer() {
    if (timerThread != null && timerThread.isAlive()) {
        return; // Timer läuft schon
    }
    pomodoro = new Pomodoro(25, 5, 2, this); // Neues Pomodoro-Objekt!
    timerThread = new Thread(() -> pomodoro.start());
    timerThread.start();
}

public void stopTimer() {
    if(timerThread != null && timerThread.isAlive()) {
        timerThread.interrupt();
        updateLabel("00:00");
    }
}

   
    public static void main(String[] args) {
        new PomodoroGUI();
    }
    
}
