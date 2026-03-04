import java.awt.Component;
import java.awt.Dimension;
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
    private Thread timerThread;
    private CircleTimerPanel circlePanel;

    public PomodoroGUI() {
        frame = new JFrame("Pomodoro Timer"); 
        label = new JLabel("25:00");
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        panel = new JPanel();
        pomodoro = new Pomodoro(0, 25, 4, this); // Initialize with 25 minutes and 4 cycles

        circlePanel = new CircleTimerPanel(25 * 60);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e -> startTimer()); 
            // Start the Pomodoro timer
        
        stopButton.addActionListener(e -> stopTimer()); 
            // Stop the Pomodoro timer
        

        panel.add(Box.createVerticalGlue()); // Add some space at the top
        panel.add(circlePanel);
        panel.add(Box.createVerticalStrut(20)); // Add some space between label and buttons
        panel.add(label);
        panel.add(Box.createVerticalStrut(20)); // Add some space between buttons
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(stopButton);
        panel.add(Box.createVerticalGlue()); // Add some space at the bottom
        frame.add(panel);
        circlePanel.setPreferredSize(new Dimension(400, 400));
        frame.setVisible(true);
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    } 

    public static void main(String[] args) {
        new PomodoroGUI();
    }

    public void startTimer() {
        if(timerThread != null && timerThread.isAlive()) {
            
            System.out.println("Timer is already running.");
            return;
        }
        System.out.println("Timer started");
        timerThread = new Thread(() -> pomodoro.start());
        timerThread.start();
    }

    public void stopTimer() {
        System.out.println("Timer stopped");
        if(timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
        }
    }
    public void updateLabel(String time) {
        label.setText(time);
    }
    public void updateCircle(int seconds) {
        circlePanel.updateTime(seconds);
    }

}
