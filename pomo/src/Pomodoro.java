
import java.awt.Toolkit;
import java.time.LocalDate;

public class Pomodoro {
    private int workDuration;
    private int breakDuration;
    private int cycles;
    private PomodoroGUI gui;
    private PomodoroLogger logger;

    public Pomodoro(int workDuration, int breakDuration, int cycles, PomodoroGUI gui) {
        this.workDuration = workDuration;
        this.breakDuration = breakDuration;
        this.cycles = cycles;
        this.gui = gui;
        this.logger = new PomodoroLogger();
    }

    public void start() {

        for(int i = 0; i < cycles; i++) {
          int totalSeconds = workDuration * 60;
          Toolkit.getDefaultToolkit().beep();
          gui.updateStatus("Working Time!");
   while(totalSeconds > 0) {
        try {
            Thread.sleep(1000);
            totalSeconds--;
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            gui.updateLabel(String.format("%02d:%02d", minutes, seconds));
            gui.updateCircle(totalSeconds);
        } catch (InterruptedException e) {
           System.out.println("Timer interrupted");
           return;
        }
       
    }
    int totalBreakSeconds = breakDuration * 60;
    Toolkit.getDefaultToolkit().beep();
    gui.updateStatus("Break Time!"); 
    gui.setCircleTotal(breakDuration * 60);
    while(totalBreakSeconds > 0) {
        try {
            Thread.sleep(1000);
            totalBreakSeconds--;
            int minutes = totalBreakSeconds / 60;
            int seconds = totalBreakSeconds % 60;
            gui.updateLabel(String.format("%02d:%02d", minutes, seconds));
            gui.updateCircle(totalBreakSeconds);
        } catch (InterruptedException e) {
           System.out.println("Timer interrupted");
           return;
        }
       
    }
         logger.logPomodoro(LocalDate.now().toString(), (i+1), workDuration);
}
}

}

