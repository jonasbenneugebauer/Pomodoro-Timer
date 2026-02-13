package PomodoroTimer.src;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PomodoroGUI {

private JFrame frame;
private JLabel label;
private JButton startButton;
private JButton stopButton;
private boolean isRunning;

public PomodoroGUI() {
    frame = new JFrame("Pomodoro Timer"); 
    label = new JLabel("00:00");
    startButton = new JButton("Start");
    stopButton = new JButton("Stop");
    isRunning = false;

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    frame.setLayout(null);
    label.setBounds(100, 50, 100, 30);
    startButton.setBounds(50, 100, 80, 30);
    stopButton.setBounds(150, 100, 80, 30);
    frame.setVisible(true);
}

public void startTimer() {
    while(isRunning) {
        thread.sleep(1000);
        thread.
        
    }

   
    public static void main(String[] args) {
        
        new PomodoroGUI();
    }
    
}
