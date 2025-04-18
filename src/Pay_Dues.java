import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Pay_Dues extends JFrame implements ActionListener{

	JLabel Heading,Sections,First,Dep,EnRoll,Winsum;
	private JLabel Sections_1;
	JTextField F_Name,Departmnt,EnRollno,Winsumyear;
	JButton Fetchinfo;
	JButton btnPrevious;
	Connection con,con2;
	JButton btnSubmitDetails;
	
	JLabel Account,Library,Scholarship, Workshop_LabIncharge,Hostel,Membership,insurance,Others,HOD; 
	Choice AccountDue,Library_Due,Scholarship_Due, Workshop_LabIncharge_Due,Hostel_Due,Membership_Due,insurance_Due,Others_Due,HOD_Due;
	private JLabel lblNewLabel;
	
	public Pay_Dues() {
		ConnectDB();
		setTitle("Pay Dues");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1253, 647);
		getContentPane().setLayout(null);
		
		Container cp = getContentPane();
		cp.setBackground(Color.white);
		
		
		Heading  = new JLabel("Student Information");
		Heading.setForeground(new Color(0, 0, 255));
		Heading.setFont(new Font("Tahoma", Font.BOLD, 25));
		Heading.setBounds(257,10,351,40);
		cp.add(Heading);
		
		Sections = new JLabel("Personal Information");
		Sections.setForeground(new Color(0, 0, 0));
		Sections.setFont(new Font("Tahoma", Font.BOLD, 25));
		Sections.setBounds(243, 125, 322, 40);
		cp.add(Sections);
		
		EnRoll = new JLabel("Enter Enrollment No: ");
		EnRoll.setBounds(10,84,150,30);
		cp.add(EnRoll);
		
		EnRollno = new JTextField();
		EnRollno.setBounds(154,85,150,30);
		cp.add(EnRollno);
		
		
		ImageIcon i4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Payment.png"));
		Image i5 = i4.getImage().getScaledInstance(600, 500,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		
		//add the label to display image on it
		JLabel imageLabel = new JLabel(i6);
		imageLabel.setBounds(764,10,447,577);
		cp.add(imageLabel);
		
		
		Fetchinfo = new JButton("Fetch Details");
		Fetchinfo.setForeground(Color.WHITE);
		Fetchinfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		Fetchinfo.setBackground(Color.BLUE);
		Fetchinfo.addActionListener(this);
		Fetchinfo.setBounds(322,85,160,30);
		cp.add(Fetchinfo);
		 
		First = new JLabel("First Name: ");
		First.setFont(new Font("Tahoma", Font.BOLD, 12));
		First.setBounds(10,175,100,30);
		cp.add(First);
		
		F_Name = new JTextField();
		F_Name.setFont(new Font("Tahoma", Font.PLAIN, 12));
		F_Name.setBounds(10,214,221,30);
		F_Name.setEditable(false);
		cp.add(F_Name);
		
		Dep = new JLabel("Department Name: ");
		Dep.setFont(new Font("Tahoma", Font.BOLD, 12));
		Dep.setBounds(332,175,170,30);
		cp.add(Dep);
		
		Departmnt = new JTextField();
		Departmnt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Departmnt.setEditable(false);
		Departmnt.setBounds(332,214,150,30);
		cp.add(Departmnt);
		
		Winsum = new JLabel("Exam Time:(Winter/Summer) ");
		Winsum.setFont(new Font("Tahoma", Font.BOLD, 12));
		Winsum.setBounds(581,174,200,30);
		cp.add(Winsum);
		
		Winsumyear = new JTextField();
		Winsumyear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Winsumyear.setEditable(false);
		Winsumyear.setBounds(591,214,150,30);
		cp.add(Winsumyear);
		
		Sections_1 = new JLabel("Department");
		Sections_1.setForeground(new Color(0, 0, 0));
		Sections_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		Sections_1.setBounds(263,277,218,30);
		cp.add(Sections_1);
		
		Account = new JLabel("Account Fees Dues: ");
		Account.setFont(new Font("Tahoma", Font.BOLD, 12));
		Account.setBounds(10,317,150,30);
		cp.add(Account);
		
		AccountDue = new Choice();
		AccountDue.add("Paid");
		AccountDue.add("UnPaid");
		AccountDue.setBounds(10,353,128,18);
		cp.add(AccountDue);
		
		Library = new JLabel("Library Dues: ");
		Library.setFont(new Font("Tahoma", Font.BOLD, 12));
		Library.setBounds(214,317,150,30);
		cp.add(Library);
		
		Library_Due = new Choice();
		Library_Due.add("Paid");
		Library_Due.add("UnPaid");
		Library_Due.setBounds(214,353,128,18);
		cp.add(Library_Due);
		
		Scholarship = new JLabel("Scholarship Dues: ");
		Scholarship.setFont(new Font("Tahoma", Font.BOLD, 12));
		Scholarship.setBounds(426,317,150,30);
		cp.add(Scholarship);
		
		Scholarship_Due = new Choice();
		Scholarship_Due.add("Paid");
		Scholarship_Due.add("UnPaid");
		Scholarship_Due.setBounds(426,353,128,18);
		cp.add(Scholarship_Due);
		
		Workshop_LabIncharge = new JLabel("Workshop/Lab Incharge Dues: ");
		Workshop_LabIncharge.setFont(new Font("Tahoma", Font.BOLD, 12));
		Workshop_LabIncharge.setBounds(10,377,194,30);
		cp.add(Workshop_LabIncharge);
		
		Workshop_LabIncharge_Due = new Choice();
		Workshop_LabIncharge_Due.add("Paid");
		Workshop_LabIncharge_Due.add("UnPaid");
		Workshop_LabIncharge_Due.setBounds(10,413,128,18);
		cp.add(Workshop_LabIncharge_Due);
		
		Hostel = new JLabel("Hostel Dues: ");
		Hostel.setFont(new Font("Tahoma", Font.BOLD, 12));
		Hostel.setBounds(214,377,116,30);
		cp.add(Hostel);
		
		Hostel_Due= new Choice();
		Hostel_Due.add("Paid");
		Hostel_Due.add("UnPaid");
		Hostel_Due.setBounds(214,413,128,18);
		cp.add(Hostel_Due);
		
		Membership = new JLabel("Membership Dues: ");
		Membership.setFont(new Font("Tahoma", Font.BOLD, 12));
		Membership.setBounds(426,377,250,30);
		cp.add(Membership);
		
		Membership_Due= new Choice();
		Membership_Due.add("Paid");
		Membership_Due.add("UnPaid");
		Membership_Due.setBounds(426,413,128,18);
		cp.add(Membership_Due);
		
		insurance = new JLabel("insurance Dues: ");
		insurance.setFont(new Font("Tahoma", Font.BOLD, 12));
		insurance.setBounds(10,437,120,30);
		cp.add(insurance);
		
		insurance_Due= new Choice();
		insurance_Due.add("Paid");
		insurance_Due.add("UnPaid");
		insurance_Due.setBounds(10,473,128,18);
		cp.add(insurance_Due);
		
		Others = new JLabel("Other Social Contribution Dues: ");
		Others.setFont(new Font("Tahoma", Font.BOLD, 12));
		Others.setBounds(216,437,200,30);
		cp.add(Others);
		
		Others_Due= new Choice();
		Others_Due.add("Paid");
		Others_Due.add("UnPaid");
		Others_Due.setBounds(214,473,128,18);
		cp.add(Others_Due);
		
		HOD = new JLabel("HOD for Attendance/TW/Seminar Dues: ");
		HOD.setFont(new Font("Tahoma", Font.BOLD, 12));
		HOD.setBounds(426,437,250,30);
		cp.add(HOD);
		
		HOD_Due= new Choice();
		HOD_Due.add("Paid");
		HOD_Due.add("UnPaid");
		HOD_Due.setBounds(426,473,128,18);
		cp.add(HOD_Due);
		
		btnSubmitDetails = new JButton("Submit Details");
		btnSubmitDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmitDetails.setForeground(Color.WHITE);
		btnSubmitDetails.setBackground(new Color(51, 0, 255));
		btnSubmitDetails.setBounds(386, 559, 160, 30);
		btnSubmitDetails.addActionListener(this);
		getContentPane().add(btnSubmitDetails);
		
		 btnPrevious = new JButton("Previous ");
		btnPrevious.setForeground(Color.WHITE);
		btnPrevious.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrevious.setBackground(new Color(51, 0, 255));
		btnPrevious.setBounds(154, 559, 160, 30);
		btnPrevious.addActionListener(this);
		getContentPane().add(btnPrevious);
		
		
		
	}
	
	
	void ConnectDB()
	{
		String url = "jdbc:mysql://localhost:3306/";
		
		String dbname = "students";
		String username = "root";
		String password = "Vaibhav@05";
		
		try {
			 con = DriverManager.getConnection(url+dbname, username, password);
			 con2 = DriverManager.getConnection(url+dbname, username, password);
			System.out.print("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Pay_Dues frame = new Pay_Dues();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == btnSubmitDetails)
		{
			PreparedStatement smt_Update;
			try {
				
				smt_Update = con.prepareStatement("update student_Dues set AccountDue = ? ,Library_Due = ?,Scholarship_Due= ?,Workshop_LabIncharge_Due= ?,Hostel_Due= ?,Membership_Due= ?,insurance_Due= ?,Others_Due= ?,HOD_Due= ? where Enrollno ="+EnRollno.getText());
				smt_Update.setString( 1, AccountDue.getSelectedItem());
				smt_Update.setString( 2, Library_Due.getSelectedItem());
				smt_Update.setString( 3, Scholarship_Due.getSelectedItem());
				smt_Update.setString( 4, Workshop_LabIncharge_Due.getSelectedItem());
				smt_Update.setString( 5,Hostel_Due.getSelectedItem());
				smt_Update.setString( 6,Membership_Due.getSelectedItem());
				smt_Update.setString( 7, insurance_Due.getSelectedItem());
				smt_Update.setString( 8, Others_Due.getSelectedItem());
				smt_Update.setString( 9, HOD_Due.getSelectedItem());
			    smt_Update.executeUpdate();
				System.out.println("Updated");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Student Due Submission Failed", "ERROR", JOptionPane.ERROR_MESSAGE);
						
			}
			
			
			if(AccountDue.getSelectedItem() == "Paid" && Library_Due.getSelectedItem() == "Paid" && Scholarship_Due.getSelectedItem() == "Paid" && Workshop_LabIncharge_Due.getSelectedItem() == "Paid" && Hostel_Due.getSelectedItem() == "Paid" && Membership_Due.getSelectedItem() == "Paid" && insurance_Due.getSelectedItem() == "Paid" && Others_Due.getSelectedItem() == "Paid" && HOD_Due.getSelectedItem() == "Paid")
			{
			try {
				smt_Update = con.prepareStatement("update student set duestatus = ?,Remark = ? where Enrollno ="+EnRollno.getText());
				smt_Update.setString(1, "Paid");
				smt_Update.setString(2, "Receipt not Send");
				smt_Update.executeUpdate();
				 		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Student Due Submission Failed", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
			
			System.out.println("Updated the student tabel set to Paid");
			
			JOptionPane.showMessageDialog(this, "Student Due Submitted Sucessfully!!!", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
			
		
			SucessPage frame = new SucessPage();
			setVisible(false);
			frame.setVisible(true);
		}else
		{
			try {
				smt_Update = con.prepareStatement("update student set duestatus = ? where Enrollno ="+EnRollno.getText());
				smt_Update.setString(1, "UnPaid");
				smt_Update.executeUpdate();
				System.out.println("Updated the student tabel set to UnPaid");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
			
			
		}else if(e.getSource() == btnPrevious)
		{
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
			setVisible(false);
		}
		
		
		if(e.getSource() == Fetchinfo)
		{
			
		
		Statement smt;

		try {
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery("Select * from student where Enrollno ="+EnRollno.getText());
			
			int rowcount = 0;
			long enroll = 0;
			String Sname = null;
			String Department = null ;
			int  Syear= 0 ;
			String WinandsSum= null ;
			String duestatus= null ;
			String Remark= null ;
			int in = 0;

			while(rs.next())
			{
				 enroll = rs.getLong(1);
				 Sname = rs.getString(2);
				 Department = rs.getString(3);
				 Syear = rs.getInt(4);
				 WinandsSum = rs.getString(5);
				 duestatus =  rs.getString(6);
				 Remark = rs.getString(7);
				 
				 in++;
				 rowcount++;
				 System.out.println(in);
				
			}
			
			 F_Name.setText(Sname);
			 Departmnt.setText(Department);
			 Winsumyear.setText(WinandsSum);
				JOptionPane.showMessageDialog(this, "Student Record Fetched Sucessfully!!!", "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
				
			 
			
		} catch (SQLException ae) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Student Record Fetched Failed!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
			
			ae.printStackTrace();
		}
		}
		
		
		
	}
}
