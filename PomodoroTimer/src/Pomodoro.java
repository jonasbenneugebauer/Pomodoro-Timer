package PomodoroTimer.src;

    public class Pomodoro {
    private int workDuration;
    private int breakDuration;
    private int cycles;

    public Pomodoro(int workDuration, int breakDuration, int cycles) {
        this.workDuration = workDuration;
        this.breakDuration = breakDuration;
        this.cycles = cycles;
    }

    public void start() {
        for (int i = 0; i < cycles; i++) {
            System.out.println("Cycle " + (i + 1) + ": Work for " + workDuration + " minutes.");
            // Simulate work duration
            try {
                Thread.sleep(workDuration * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Cycle " + (i + 1) + ": Take a break for " + breakDuration + " minutes.");
            // Simulate break duration
            try {
                Thread.sleep(breakDuration * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } 

    public static void main(String[] args) {
        Pomodoro pomodoro = new Pomodoro(25, 5, 4);
        pomodoro.start();
    }

    
}