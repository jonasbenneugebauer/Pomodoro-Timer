public class Pomodoro {
   private int totalSeconds;
   private PomodoroGUI gui;


    public Pomodoro (int seconds, int minutes, PomodoroGUI gui){
        this.totalSeconds = seconds + minutes * 60;
        this.gui = gui;
    }
public void start(){

    while(totalSeconds >0){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Timer interrupted.");
            return;
        }
        totalSeconds--;
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        gui.updateLabel(String.format("%02d:%02d", mins, secs));
        gui.updateCircle(totalSeconds);
    }
    System.out.println("Pomodoro session ended.");
}


}