public class Pomodoro {
   private int totalSeconds;
   private PomodoroGUI gui;
   private int cycles;


    public Pomodoro (int seconds, int minutes, int cycles, PomodoroGUI gui){
        this.totalSeconds = seconds + minutes * 60;
        this.gui = gui;
        this.cycles = cycles;
    }
public void start(){

  for(int i = 0; i < cycles; i++){
    
    // WORK PHASE (25 Min)
    totalSeconds = 25 * 60;  // ← Reset auf Work-Zeit
    while(totalSeconds > 0){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        totalSeconds--;
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        gui.updateLabel(String.format("%02d:%02d", mins, secs));
        gui.updateCircle(totalSeconds);
    }
    
    // BREAK PHASE (5 Min)
    totalSeconds = 5 * 60;  // ← Reset auf Break-Zeit!
    while(totalSeconds > 0){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        totalSeconds--;
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        gui.updateLabel(String.format("Break: %02d:%02d", mins, secs));
        gui.updateCircle(totalSeconds);
    }
}
System.out.println("All cycles completed!");
    System.out.println("Pomodoro session ended.");
}

}
