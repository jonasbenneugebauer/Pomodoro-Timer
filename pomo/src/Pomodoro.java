public class Pomodoro {
   private int totalSeconds;


    public Pomodoro (int seconds, int minutes){
        this.totalSeconds = seconds + minutes * 60;
    }

public void start(){

    while(totalSeconds >0){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        totalSeconds--;

    }
}

    
}
