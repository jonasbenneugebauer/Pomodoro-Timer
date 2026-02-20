public class PomodoroLogger {

    public void logPomodoro(String date, int count, int minutes) {
        
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pomodoro_log.csv", true)))) {
            out.println(date + "," + count + "," + minutes);
        } catch (IOException e) {
            System.out.println("Error writing to log: " + e.getMessage());
        }
            
    }