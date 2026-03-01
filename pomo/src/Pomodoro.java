public class Pomodoro {
   private int totalSeconds;
   private int minutes;
   private PomodoroGUI gui;


    public Pomodoro (int seconds, int minutes){
        this.totalSeconds = seconds + minutes * 60;
        this.minutes = totalSeconds / 60;
        this.gui = gui;
    }
public void start(){

    while(totalSeconds >0){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalSeconds--;
        int mins = totalSeconds / 60;
        int secs = totalSeconds % 60;
        System.out.println(String.format("%02d:%02d", mins, secs));

    }
    System.out.println("Pomodoro session ended.");
}

public static void main(String[] args) {
    Pomodoro pomodoro = new Pomodoro(20, 1);
    pomodoro.start();
}

}