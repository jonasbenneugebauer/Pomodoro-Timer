import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PomodoroGUI {

    private JFrame frame;
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;
    private JPanel panel;
    private Pomodoro pomodoro;

    public PomodoroGUI() {
        frame = new JFrame("Pomodoro Timer"); 
        label = new JLabel("00:00");
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        panel = new JPanel();
        pomodoro = new Pomodoro(0, 25, this); // Initialize with 25 minutes
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e -> startTimer()); 
            // Start the Pomodoro timer
        
        stopButton.addActionListener(e -> stopTimer()); 
            // Stop the Pomodoro timer
        

        panel.add(Box.createVerticalGlue()); // Add some space at the top
        panel.add(label);
        panel.add(Box.createVerticalStrut(20)); // Add some space between label and buttons
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10)); // Add some space between buttons
        panel.add(stopButton);
        panel.add(Box.createVerticalGlue()); // Add some space at the bottom
        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    } 

    public static void main(String[] args) {
        new PomodoroGUI();
    }

    public void startTimer() {
        System.out.println("Timer started");
        new Thread(() -> pomodoro.start()).start();
    }

    public void stopTimer() {
        System.out.println("Timer stopped");
    }
    public void updateLabel(String time) {
        label.setText(time);
    }

}
