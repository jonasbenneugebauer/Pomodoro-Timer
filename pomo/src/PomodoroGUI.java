import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PomodoroGUI {

    private JFrame frame;
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;

    public PomodoroGUI() {
        frame = new JFrame();   
        label = new JLabel();
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        frame.setVisible(true);
        frame.setSize(1080, 720);
    }

    public static void main(String[] args) {
        new PomodoroGUI();
    }
    
    
    
}
