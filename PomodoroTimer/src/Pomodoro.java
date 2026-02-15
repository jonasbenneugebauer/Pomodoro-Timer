package PomodoroTimer.src;

    public class Pomodoro {
    private int workDuration;
    private int breakDuration;
    private int cycles;
    private PomodoroGUI gui;

    public Pomodoro(int workDuration, int breakDuration, int cycles, PomodoroGUI gui) {
        this.workDuration = workDuration;
        this.breakDuration = breakDuration;
        this.cycles = cycles;
        this.gui = gui;
    }

    public void start() {

        for(int i = 0; i < cycles; i++) {
          int totalSeconds = workDuration * 60;
   while(totalSeconds > 0) {
        try {
            Thread.sleep(1000);
            totalSeconds--;
            int minutes = totalSeconds / 60;
            int seconds = totalSeconds % 60;
            gui.updateLabel(String.format("%02d:%02d", minutes, seconds));
        } catch (InterruptedException e) {
           System.out.println("Timer interrupted");
           return;
        }
    }
    int totalBreakSeconds = breakDuration * 60; 
    while(totalBreakSeconds > 0) {
        try {
            Thread.sleep(1000);
            totalBreakSeconds--;
            int minutes = totalBreakSeconds / 60;
            int seconds = totalBreakSeconds % 60;
            gui.updateLabel(String.format("%02d:%02d", minutes, seconds));
        } catch (InterruptedException e) {
           System.out.println("Timer interrupted");
           return;
        }
       
    }
}
    }

    
}