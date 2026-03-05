import java.awt.Toolkit;

public class Pomodoro {
   private int totalSeconds;
   private int workDuration;
   private int breakDuration;
   private PomodoroGUI gui;
   private int cycles;


    public Pomodoro (int workMinutes, int breakMinutes, int cycles, PomodoroGUI gui){
        this.workDuration = workMinutes * 60;
        this.breakDuration = breakMinutes * 60;
        this.gui = gui;
        this.cycles = cycles;
    }

public void start(){

  for(int i = 0; i < cycles; i++){
    gui.setTotalSeconds(workDuration);
    countdown(workDuration, "work");
       Toolkit.getDefaultToolkit().beep();
    gui.setTotalSeconds(breakDuration);
    countdown(breakDuration, "break");
       Toolkit.getDefaultToolkit().beep();
}
System.out.println("All cycles completed!");
System.out.println("Pomodoro session ended.");
}


public void countdown(int seconds, String phase) {
    totalSeconds = seconds;
    while(totalSeconds > 0){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        totalSeconds--;
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        String timeText = String.format( "%02d:%02d", mins, secs);
    

        if(phase.equals("break")) {
            gui.updateLabel("Break: " + timeText);
        } else {
            gui.updateLabel("Work: " + timeText);
        }
        gui.updateCircle(totalSeconds);
    }
}

}
