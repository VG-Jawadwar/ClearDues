import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class Choose_Options extends JFrame implements ActionListener,MouseListener {

	JButton btn_Dues,btn_Update;
	JLabel imageLabel;
	private JLabel lblNewLabel;
	private JLabel lblUpdateRecords;
	static Connection con;
	
	public Choose_Options() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 687);
		setTitle("What you Want to Do?");
		getContentPane().setLayout(null);
		setVisible(true);
		Container cp = getContentPane();
		cp.setBackground(Color.white);
		
		btn_Dues = new JButton("");
		btn_Dues.setBackground(Color.WHITE);
		btn_Dues.setIcon(new ImageIcon("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Dues pay.png"));
		btn_Dues.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_Dues.setBounds(193, 454, 137, 133);
		btn_Dues.addActionListener(this);
		cp.add(btn_Dues);
		
		btn_Update = new JButton("");
		btn_Update.setBackground(Color.WHITE);
		btn_Update.setIcon(new ImageIcon("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Update.png"));
		btn_Update.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_Update.setBounds(417, 454, 137, 133);
		btn_Update.addActionListener(this);
		cp.add(btn_Update);
		
		JLabel heading = new JLabel("Welcome to ClearDues");
		heading.setForeground(new Color(0, 0, 255));
		heading.setFont(new Font("Tahoma", Font.BOLD, 25));
		heading.setBounds(230, 382, 374, 46);
		cp.add(heading);
		
		
		ImageIcon i1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\logout.png"));
		Image i2 = i1.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		//add the label to display image on it
		imageLabel = new JLabel(i3);
		imageLabel.setBounds(715,10,39,40);
		imageLabel.addMouseListener(this);
		cp.add(imageLabel);
		
		ImageIcon i4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Welcome page.png"));
		Image i5 = i4.getImage().getScaledInstance(600, 500,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		
		//add the label to display image on it
		JLabel imageLabel = new JLabel(i6);
		imageLabel.setBounds(10,0,744,372);
		cp.add(imageLabel);
		
		lblNewLabel = new JLabel("Clear Dues");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(220, 597, 110, 30);
		getContentPane().add(lblNewLabel);
		
		lblUpdateRecords = new JLabel("Update Records");
		lblUpdateRecords.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUpdateRecords.setBounds(417, 597, 152, 30);
		getContentPane().add(lblUpdateRecords);
	}
	
	static void ConnectDB()
	{
		String url = "jdbc:mysql://localhost:3306/";
		
		String dbname = "students";
		String username = "root";
		String password = "Vaibhav@05";
		
		try {
			con = DriverManager.getConnection(url+dbname, username, password);
			System.out.print("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new Choose_Options();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_Dues)
		{
			setVisible(false);
			ConnectDB();
			Statement smt; 
			try {
				smt = con.createStatement();
				ResultSet rs = smt.executeQuery("Select * from student");
				int count = 0;
				while(rs.next())
				{
					count++;
				}
				
				Clear_Dues.count_stud = count;
				
				System.out.println("Count Gatherd"+count);
			} catch (Exception ae) {
				// TODO Auto-generated catch block
				ae.printStackTrace();
			}
			Clear_Dues frame = new Clear_Dues();
			frame.setVisible(true);
		}else
		{
			setVisible(false);
			Update_Student frame = new Update_Student();
			frame.setVisible(true);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == imageLabel)
		{
		setVisible(false);
		Login frame = new Login();
		frame.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
