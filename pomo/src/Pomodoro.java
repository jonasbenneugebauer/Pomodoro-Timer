import java.awt.Toolkit;

public class Pomodoro {
    private int totalSeconds;
    private int workDuration;
    private int breakDuration;
    private PomodoroGUI gui;
    private int cycles;

    // Steuerflags
    private volatile boolean skipPhase = false;
    private volatile boolean cancelSession = false;

    public Pomodoro(int workMinutes, int breakMinutes, int cycles, PomodoroGUI gui) {
        this.workDuration = workMinutes * 60;
        this.breakDuration = breakMinutes * 60;
        this.gui = gui;
        this.cycles = cycles;
    }

    public void start() {
        // Stelle sicher, dass Flags zurückgesetzt sind
        cancelSession = false;
        skipPhase = false;

        for (int i = 0; i < cycles; i++) {
            if (cancelSession)
                return;

            int currentWork = this.workDuration; // lese Laufzeit beim Beginn der Phase
            gui.setTotalSeconds(currentWork);
            countdown(currentWork, "work");

            if (cancelSession)
                return;

            Toolkit.getDefaultToolkit().beep();

            if (cancelSession)
                return;

            int currentBreak = this.breakDuration; // lese Laufzeit beim Beginn der Phase
            gui.setTotalSeconds(currentBreak);
            countdown(currentBreak, "break");

            if (cancelSession)
                return;

            Toolkit.getDefaultToolkit().beep();
        }
        System.out.println("All cycles completed!");
        System.out.println("Pomodoro session ended.");
        gui.enableApplyButton(); // Re-enable the apply button after all cycles are completed
    }

    public void countdown(int seconds, String phase) {
        totalSeconds = seconds;
        // Schleife auch abbrechbar via skipPhase / cancelSession
        while (totalSeconds > 0 && !skipPhase && !cancelSession) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                cancelSession = true;
                break;
            }
            totalSeconds--;
            int mins = totalSeconds / 60;
            int secs = totalSeconds % 60;
            String timeText = String.format("%02d:%02d", mins, secs);

            if (phase.equals("break")) {
                gui.updateLabel("Break: " + timeText);
            } else {
                gui.updateLabel("Work: " + timeText);
            }
            gui.updateCircle(totalSeconds);
        }
        // Nach Verlassen einer Phase zurücksetzen, damit nächste Phase normal läuft
        skipPhase = false;
    }

    // Aufruf von GUI: überspringe aktuelle Phase und fahre mit nächster fort
    public void stopCurrentPhase() {
        skipPhase = true;
    }

    // Komplettes Beenden der Session (falls benötigt)
    public void cancelSession() {
        cancelSession = true;
        skipPhase = true;
    }

    // Apply: neue Minuten-Werte setzen (gelten ab der nächsten Phase)
    public void setDurations(int workMinutes, int breakMinutes) {
        this.workDuration = workMinutes * 60;
        this.breakDuration = breakMinutes * 60;
    }

}
