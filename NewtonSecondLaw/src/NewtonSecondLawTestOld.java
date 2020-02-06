import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class NewtonSecondLawTestOld extends JFrame {

	private JPanel contentPane;
	private JTextField txtMass;
	private JTextField txtAcceleration;
	private JTextField txtForce;
	private AnimationPanel animationPanel;
	private JButton btnStart;	

	
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewtonSecondLawTestOld frame = new NewtonSecondLawTestOld();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public NewtonSecondLawTestOld() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headingPanel = new JPanel();
		headingPanel.setBackground(Color.GRAY);
		headingPanel.setBorder(null);
		headingPanel.setBounds(0, 0, 545, 39);
		contentPane.add(headingPanel);
		
		JLabel lblTesting = new JLabel("Newton's Second Law Experiment");
		lblTesting.setFont(new Font("Tahoma", Font.BOLD, 16));
		headingPanel.add(lblTesting);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(null);
		buttonPanel.setBounds(0, 45, 545, 157);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JLabel lblAccel = new JLabel("Acceleration (m/s/s):");
		lblAccel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAccel.setBounds(10, 40, 127, 33);
		buttonPanel.add(lblAccel);
		
		JLabel lblMass = new JLabel("Mass of Car (lbs):");
		lblMass.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMass.setBounds(10, 11, 127, 24);
		buttonPanel.add(lblMass);
		
		JLabel lblForce = new JLabel("Force (newtons):");
		lblForce.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblForce.setBounds(254, 11, 109, 19);
		buttonPanel.add(lblForce);
		
		txtMass = new JTextField();
		txtMass.setText("1000");
		txtMass.setBounds(137, 11, 86, 20);
		buttonPanel.add(txtMass);
		txtMass.setColumns(10);
		
		txtAcceleration = new JTextField();
		txtAcceleration.setText("50");
		txtAcceleration.setBounds(138, 45, 86, 20);
		buttonPanel.add(txtAcceleration);
		txtAcceleration.setColumns(10);
		
		txtForce = new JTextField();
		txtForce.setEditable(false);
		txtForce.setBounds(359, 11, 86, 20);
		buttonPanel.add(txtForce);
		txtForce.setColumns(10);
		
		JLabel lblFormula = new JLabel("( F=M*A)");
		lblFormula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFormula.setBounds(451, 14, 77, 14);
		buttonPanel.add(lblFormula);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(231, 100, 89, 23);
		buttonPanel.add(btnStart);
		
		btnStart.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    startButtonPressed();
			  } 
		} );
		
		animationPanel = new AnimationPanel();
		animationPanel.setBorder(null);
		animationPanel.setBounds(0, 202, 545, 50);
		animationPanel.setBackground(Color.WHITE);
		contentPane.add(animationPanel);
		
		JLabel lblM = new JLabel("0 m");
		lblM.setBounds(5, 140, 46, 14);
		buttonPanel.add(lblM);
		
		JLabel lblM_1 = new JLabel("1000 m");
		lblM_1.setBounds(495, 140, 46, 14);
		buttonPanel.add(lblM_1);
		
	}
	
	private void startButtonPressed (){
		System.out.println("Start");
		txtForce.setText("");		
		animationPanel.startAnimation();
	}
	
	class AnimationPanel extends JPanel {
		int mass;
		int accel;
		int delay=10;
		private BufferedImage car;       
	    private int direction = 10;
	    private int xPos = 0;
	    private Timer timer;

		
        public AnimationPanel() {
            try {
            	car = ImageIO.read(new File("E:\\Downloads\\car.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        private void startAnimation() {
        	try {	
        		xPos = 0;
        		mass = Integer.valueOf(txtMass.getText());
           		accel = Integer.valueOf(txtAcceleration.getText());
           		System.out.println("Mass, Force");
          		
        		if(timer == null) {
        			 timer = new Timer(delay, new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {                        	
                       	
                       		int force  = mass * accel;
                       		
                        	 txtForce.setText("" + force);
                             xPos += direction;
                             if(xPos >= 474) {
                                 timer.stop();
                                 System.out.println("timer stopped.");
                                 return;
                              }
                             
                             if (xPos + car.getWidth() > getWidth()) {
                                 xPos = getWidth() - car.getWidth();
                                 
                                
                                  
                             } else if (xPos < 0) {
                                 xPos = 0;
                             }
                             
                     		 //System.out.println("timer running - xPos: " + xPos + ", mass: " + mass + ", accel: " + accel + ", force = " + force);
                     		System.out.println(mass + ", " + force);
                     		
                             repaint();
                             mass += 100;
                             txtMass.setText(mass +"");
                         }
                         
                     });  
        			 
        			 
        		}        
               
                timer.start();              
                
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
       

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int y = getHeight() - car.getHeight();
            g.drawImage(car, xPos, y, this);

        }

    }

}
