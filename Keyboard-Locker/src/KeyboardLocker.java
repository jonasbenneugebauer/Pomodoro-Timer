import javax.swing.JButton;
import javax.swing.JFrame;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;


public class KeyboardLocker implements NativeKeyListener {


    public JButton lockButton;
    public JFrame frame;
    private boolean isLocked = false;

    public KeyboardLocker() {
        frame = new JFrame("Keyboard Locker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        lockButton = new JButton("Lock Keyboard");
        lockButton.addActionListener(e -> {
          if(isLocked) {
              lockButton.setText("Lock Keyboard");
              isLocked = false;
          } else {
              lockButton.setText("Unlock Keyboard");
              isLocked = true;
          }
        });

        frame.getContentPane().add(lockButton);
        frame.setVisible(true);
    }
    @Override
public void nativeKeyPressed(NativeKeyEvent e) {
    // Hier kommt später die Logik zum Blockieren
}

@Override
public void nativeKeyReleased(NativeKeyEvent e) {
    // Kannst du leer lassen
}

@Override
public void nativeKeyTyped(NativeKeyEvent e) {
    // Kannst du leer lassen
}

    public static void main(String[] args) {
        new KeyboardLocker();
    }
}
    

