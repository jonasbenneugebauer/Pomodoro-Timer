import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PomodoroGUI {

    private JFrame frame;
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;
    private JPanel panel;
    private Pomodoro pomodoro;
    private Thread timerThread;
    private CircleTimerPanel circlePanel;
    private JTextField workBlock;
    private JTextField breakBlock;
    private JButton applyButton;
    

    public PomodoroGUI() {
        frame = new JFrame("Pomodoro Timer"); 
        label = new JLabel("25:00");
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        panel = new JPanel();
        pomodoro = new Pomodoro(1, 1, 4, this); // Initialize with 25 minutes and 4 cycles

        workBlock = new JTextField("25");
        breakBlock = new JTextField("5");
        applyButton = new JButton("Apply");

        circlePanel = new CircleTimerPanel(25 * 60);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        workBlock.setAlignmentX(Component.CENTER_ALIGNMENT);
        breakBlock.setAlignmentX(Component.CENTER_ALIGNMENT);
        workBlock.setMaximumSize(new Dimension(100, 30));
        breakBlock.setMaximumSize(new Dimension(100, 30));
        applyButton.setMaximumSize(new Dimension(100, 30));
        applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e -> startTimer()); 
            // Start the Pomodoro timer
        
        stopButton.addActionListener(e -> stopTimer()); 
            // Stop the Pomodoro timer

        applyButton.addActionListener(e -> {
            try {
                int workMinutes = Integer.parseInt(workBlock.getText());
                int breakMinutes = Integer.parseInt(breakBlock.getText());
                pomodoro = new Pomodoro(workMinutes, breakMinutes, 4, this);
                circlePanel.setTotalSeconds(workMinutes * 60);
                label.setText(String.format("%02d:00", workMinutes));
            } catch (NumberFormatException ex) {
                System.out.println("Please enter valid numbers for work and break durations.");
            }
        });
        

        panel.add(Box.createVerticalGlue()); // Add some space at the top
        panel.add(circlePanel);
        panel.add(Box.createVerticalStrut(20)); // Add some space between label and buttons
        panel.add(label);
        panel.add(Box.createVerticalStrut(20)); // Add some space between buttons
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(stopButton);
        panel.add(Box.createVerticalGlue()); // Add some space at the bottom
        

        JLabel workLabel = new JLabel("Work Duration (minutes):");
        workLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(workLabel);
        panel.add(workBlock);

        panel.add(Box.createVerticalStrut(5));

        JLabel breakLabel = new JLabel("Break Duration (minutes):");
        breakLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(breakLabel);
        panel.add(breakBlock);
        panel.add(Box.createVerticalStrut(10));
        panel.add(applyButton);

        panel.add(Box.createVerticalGlue());

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

    public void setTotalSeconds(int total) {
        circlePanel.setTotalSeconds(total);
    }

}