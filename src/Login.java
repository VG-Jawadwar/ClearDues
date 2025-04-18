import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JLabel heading, subheading;
	JTextField usernameField;
	JPasswordField passwordField;
	JLabel usernameLabel, passwordLabel;
	private JLabel lblInformaionByEmail;
	static Connection con;
	
	public Login() {
		
		try {
			
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		setTitle("ClearDues Login");
		getContentPane().setLayout(null);
		
		Container cp = getContentPane();
		
		cp.setBackground(Color.WHITE);
		
				ImageIcon i1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\LoginPage.jpg"));
				Image i2 = i1.getImage().getScaledInstance(600, 700,Image.SCALE_DEFAULT);
				ImageIcon i3 = new ImageIcon(i2);
				
				//add the label to display image on it
				JLabel imageLabel = new JLabel(i3);
				imageLabel.setBounds(0,0,549,613);
				cp.add(imageLabel);
				
		
		heading = new JLabel("Welcome to ClearDues: A simple Due Clearance App.");
		heading.setForeground(new Color(0, 0, 255));
		heading.setFont(new Font("Segoe Print", Font.ITALIC, 20));
		heading.setBounds(636, 24, 540, 50);
		cp.add(heading);
		
		subheading = new JLabel("Welcome Back :)");
		subheading.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 23));
		subheading.setBounds(809, 79, 220, 50);
		cp.add(subheading);
		
		usernameLabel = new JLabel("Enter Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameLabel.setBounds(750,177,150,50);
		cp.add(usernameLabel);
		
		usernameField = new JTextField(20);
		usernameField.setBounds(799,228,241,30);
		cp.add(usernameField);
	
		passwordLabel = new JLabel("Enter Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		passwordLabel.setBounds(750,271,150,50);
		cp.add(passwordLabel);
		
		passwordField = new JPasswordField(20);
		passwordField.setBounds(799,322,241,30);
		cp.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogin.setSize(150,50);
		btnLogin.setLocation(821,397);
		btnLogin.setVisible(true);
		btnLogin.addActionListener(this);
		cp.add(btnLogin);
		
		JLabel lblToKeepConnected = new JLabel("To Keep Connected With us Login with your personal");
		lblToKeepConnected.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 13));
		lblToKeepConnected.setBounds(727, 117, 343, 40);
		getContentPane().add(lblToKeepConnected);
		
		lblInformaionByEmail = new JLabel("informaion by email and password");
		lblInformaionByEmail.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 13));
		lblInformaionByEmail.setBounds(727, 142, 313, 40);
		getContentPane().add(lblInformaionByEmail);
		
		} catch (Exception e) {
		JOptionPane.showMessageDialog(this, "Application Loading Failed!!!","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	
	
	}
	
	public static void main(String[] args) {
		Login frame = new Login();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Usernm = usernameField.getText();
		String pass = passwordField.getText();
		
		if(Usernm.equals("admin@gmail.com") && pass.equals("Gramin@123"))
		{
			JOptionPane.showMessageDialog(this, "Login Successfull!!!", "Information",
					JOptionPane.INFORMATION_MESSAGE);
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
			Choose_Options frame = new Choose_Options();
			frame.setVisible(true);
			setVisible(false);
			
		}else
		{
			JOptionPane.showMessageDialog(this, "Invalid username or password", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
				
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
}
