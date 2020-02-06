import java.awt.EventQueue;
import javax.swing.JFrame;

public class NewtonSecondLawTest extends JFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewtonSecondLawTest frame = new NewtonSecondLawTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

