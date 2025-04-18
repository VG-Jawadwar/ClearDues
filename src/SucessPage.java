import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SucessPage extends JFrame implements WindowListener {


	public static void main(String[] args) {
		new  SucessPage();
	} 


	public SucessPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1012, 603);
		getContentPane().setLayout(null);
		setTitle("Success");
		setVisible(true);
		
		Container cp = getContentPane();
		cp.setBackground(Color.WHITE);
		
		JLabel Heading = new JLabel("ClearDues");
		 Heading.setForeground(new Color(0, 0, 255));
		 Heading.setFont(new Font("Tahoma", Font.BOLD, 30));
		 Heading.setBounds(417,20,250,40);
		cp.add(Heading);
		
		ImageIcon i4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Sucess3.gif"));
		Image i5 = i4.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		
		//add the label to display image on it
		JLabel imageLabel = new JLabel(i6);
		imageLabel.setBounds(321,70,400,400);
		cp.add(imageLabel);
		
		JLabel lblNewLabel = new JLabel("Student Due Submitted Sucessfully");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(347, 480, 374, 51);
		getContentPane().add(lblNewLabel);
		
		Timer timer = new Timer(5000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  // Close the first frame
               Pay_Dues frame = new Pay_Dues(); // Open the second frame
               frame.setVisible(true);
            }
        });
		
        timer.setRepeats(false);  // Only execute once
        timer.start();
	}
	


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
