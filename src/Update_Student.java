import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Update_Student extends JFrame implements ActionListener, MouseListener {

	
    JLabel Enroll,fname,syear,sdep,swinsum,sduesatus,semail,imagelabel;
    JTextField Enrollno,Fname,Syear,Sdep,Swinsum,Sduestatus,Semail;
    static Connection con;
    
   
	JButton insert,fetchrecord,updaterecord;


	public Update_Student() {
		ConnectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1084,567);
		getContentPane().setLayout(null);
		setTitle("Update Students Records");
		setVisible(true);
		
		Container cp = getContentPane();		
		cp.setBackground(Color.white);
		
		JLabel heading = new JLabel("Updating the Student Records");
		heading.setForeground(new Color(0, 0, 255));
		heading.setFont(new Font("Tahoma", Font.BOLD, 20));
		heading.setBounds(244,10,354,47);
		cp.add(heading);
		
		Enroll = new JLabel("Enrollment No: ");
		Enroll.setFont(new Font("Tahoma", Font.BOLD, 12));
		Enroll.setBounds(10,82,100,30);
		cp.add(Enroll);
		
		Enrollno = new JTextField();
		Enrollno.setEditable(true);
		Enrollno.setBounds(144,83,218,30);
		cp.add(Enrollno);

		fname = new JLabel("Full Name: ");
		fname.setFont(new Font("Tahoma", Font.BOLD, 12));
		fname.setBounds(10,128,100,30);
		cp.add(fname);
		
		Fname = new JTextField();
		Fname.addMouseListener(this);
		Fname.setEditable(false);
		Fname.setBounds(144,129,220,30);
		cp.add(Fname);
					
		syear = new JLabel("Student Year: ");
		syear.setFont(new Font("Tahoma", Font.BOLD, 12));
		syear.setBounds(10,232,180,30);
		cp.add(syear);
		
		Syear = new JTextField();
		Syear.addMouseListener(this);
		Syear.setEditable(false);
		Syear.setBounds(144,233,220,30);
		cp.add(Syear);
		
		sdep = new JLabel("Student Department: ");
		sdep.setFont(new Font("Tahoma", Font.BOLD, 12));
		sdep.setBounds(10,179,170,30);
		cp.add(sdep);
		
		Sdep = new JTextField();
		Sdep.addMouseListener(this);
		Sdep.setEditable(false);
		Sdep.setBounds(142,180,220,30);
		cp.add(Sdep);
		
		swinsum = new JLabel("Exam Time: ");
		swinsum.setFont(new Font("Tahoma", Font.BOLD, 12));
		swinsum.setBounds(10,289,129,30);
		cp.add(swinsum);
		
		Swinsum = new JTextField();
		Swinsum.setEditable(false);
		Swinsum.addMouseListener(this);
		Swinsum.setBounds(144,290,220,30);
		cp.add(		Swinsum);
		
		sduesatus = new JLabel("Due Status: ");
		sduesatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		sduesatus.setBounds(10,344,170,30);
		cp.add(sduesatus);
		
		Sduestatus = new JTextField();
		Sduestatus.addMouseListener(this);
		Sduestatus.setEditable(false);
		Sduestatus.setBounds(144,345,220,30);
		cp.add(Sduestatus);
		
		semail = new JLabel("Student Email: ");
		semail.setFont(new Font("Tahoma", Font.BOLD, 12));
		semail.setBounds(10,396,170,30);
		cp.add(semail);
		
		Semail = new JTextField();
		Semail.setEditable(true);
		Semail.setBounds(144,397,220,30);
		cp.add(Semail);
		
		
		updaterecord = new JButton("Update record");
		updaterecord.setForeground(Color.WHITE);
		updaterecord.setBackground(Color.BLUE);
		updaterecord.setFont(new Font("Tahoma", Font.BOLD, 16));
		updaterecord.setBounds(129,477,180,30);
		updaterecord.addActionListener(this);
		cp.add(updaterecord);
		
		fetchrecord = new JButton("Fetch record");
		fetchrecord.setFont(new Font("Tahoma", Font.BOLD, 16));
		fetchrecord.setForeground(Color.WHITE);
		fetchrecord.setBackground(Color.BLUE);
		fetchrecord.setBounds(394,76,170,40);
		fetchrecord.addActionListener(this);
		cp.add(fetchrecord);

		
		ImageIcon i1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Back.png"));
		Image i2 = i1.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		//add the label to display image on it
		imagelabel = new JLabel(i3);
		imagelabel.setBounds(1021,10,39,40);
		imagelabel.addMouseListener(this);
		cp.add(imagelabel);
		
		ImageIcon i4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Update Records.png"));
		Image i5 = i4.getImage().getScaledInstance(600, 500,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		
		//add the label to display image on it
		JLabel imageLabel = new JLabel(i6);
		imageLabel.setBounds(557,0,451,530);
		cp.add(imageLabel);

		
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
		new Update_Student();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.print(Enrollno.getText());
		if(e.getSource() == fetchrecord)
		{
			try {
				Statement smt;
				smt = con.createStatement();
				ResultSet rs = smt.executeQuery("Select * from student where Enrollno ="+Enrollno.getText());
				
				long enroll;
				String Sname = null,Department = null,WinandsSum = null,duestatus = null,mail = null;
				int year = 0;
				while(rs.next())
				{
					 Sname = rs.getString(2);
					 Department = rs.getString(3);
					 year = rs.getInt(4);
					 WinandsSum = rs.getString(5);
					 duestatus =  rs.getString(6);
					mail = rs.getString(8);
				}
				
				Fname.setText(Sname);
				Syear.setText(Integer.toString(year));
				Sdep.setText(Department);
				Swinsum.setText(WinandsSum);
				Sduestatus.setText(duestatus);
				Semail.setText(mail);
				
				JOptionPane.showMessageDialog(this, "Student Record Fetched Successfullyl!!!", "Information", JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception ue) {
				JOptionPane.showMessageDialog(this, "Student Record Fetch Failed!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				ue.printStackTrace();
			}
			
		}else
		{
			int update_status = 0;
			try {
				PreparedStatement smt_Update;
				smt_Update = con.prepareStatement("update student set mail = ? where Enrollno ="+Enrollno.getText());
				smt_Update.setString(1, Semail.getText());
				update_status = smt_Update.executeUpdate();
				
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Student Record Update Failed!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
			
			if(update_status >= 1)
			{
				Enrollno.setText("");
				Fname.setText("");
				Syear.setText("");
				Sdep.setText("");
				Swinsum.setText("");
				Sduestatus.setText("");
				Semail.setText("");
				
				update_status = 0;				
			}
			JOptionPane.showMessageDialog(this, "Student Record Updated Successfullyl!!", "Information", JOptionPane.INFORMATION_MESSAGE);

		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == imagelabel)
		{
			setVisible(false);
			Choose_Options frame = new Choose_Options();
			frame.setVisible(true);
		}else
		{
			JOptionPane.showMessageDialog(this, "Please Contact Institute Exam Section to Update Further Information!!!", "Information", JOptionPane.INFORMATION_MESSAGE);
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
