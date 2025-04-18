import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.mysql.cj.protocol.Resultset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;  
import com.itextpdf.text.Paragraph;  
import com.itextpdf.text.pdf.PdfWriter;


public class Clear_Dues extends JFrame implements ActionListener,MouseListener,WindowListener {
	
	JLabel deprtmnt,Year,Totalpaid, TotalUnpaid,Remark;
	Choice ch_deprtmnt, ch_Year, ch_Remark;
	JTable tabel;
	JPanel panel;
	JButton fetch,sendmsg,sendreceipt,Logout,Export,Fetchinfo;
	JTextField paid,unpaid,EnRollno;
	static Connection con;
	JLabel imageLabel,imageLabel_Next;
	Object data[][];
	Object colHead[] = {"Enrollment No","Name","Department","Year","Winter/Summer","Due Status"};
	static int count_stud;
	static private String userName = "vgjawadwar5@gmail.com";
	static private String password = "ddit xrae mdxv huny"; // App-specific password recommended
	static int receipt_Number = 1000000;
	
    

	static public void exportToExcel(JTable table) 
	{
		  JFileChooser jFileChooser = new JFileChooser();
          jFileChooser.showSaveDialog(new Clear_Dues());
          File saveFile = jFileChooser.getSelectedFile();
          
          if(saveFile != null){
              saveFile = new File(saveFile.toString()+".csv");  
		  try (FileWriter writer = new FileWriter(saveFile)) {
	            // Write the header row
	            for (int i = 0; i < table.getColumnCount(); i++) {
	                writer.write(table.getColumnName(i));
	                if (i < table.getColumnCount() - 1) {
	                    writer.write(",");
	                }
	            }
	            writer.write("\n");

	            // Write the data rows
	            for (int i = 0; i < table.getRowCount(); i++) {
	                for (int j = 0; j < table.getColumnCount(); j++) {
	                    writer.write(table.getValueAt(i, j).toString());
	                    if (j < table.getColumnCount() - 1) {
	                        writer.write(",");
	                    }
	                }
	                writer.write("\n");
	            
	            }
		  
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
          }
	}
	
	static public void createReceipt(String recipientEmail, String subject, String body,String Stud_Name,long Enroll,String Dep,int Year,String winsum) throws FileNotFoundException, DocumentException
	{
		int i = 0;
		Document doc = new Document();  
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\Receipt"+ LocalDate.now() +".pdf"));
		System.out.println("PDF created.");  

		doc.open();  
		 receipt_Number++;
  
		doc.add(new Paragraph("Gramin Technical and Management Campus\r\n"
				+ "Vishnupuri, Nanded - 431606\r\n"
				+ "02462 - 872732\r\n"
				+ "\nReceipt for Dues Payment\r\n"
				+ "\r\n"
				+ "Receipt Number: " + receipt_Number +"\r\n"
				+ "Date: "+ LocalDate.now()+"\r\n"
				+ "\r\n"
				+ "Student Details:\r\n"
				+ "Name: "+ Stud_Name+"\r\n"
				+ "Enrollment Number: "+Enroll+"\r\n"
				+ "Department: "+Dep+"\r\n"
				+ "Year: "+Year+"\r\n"
				+ "\r\n"
				+ "Payment Details:\r\n"
				+ "Payment Mode: Cash\r\n"
				+ "Date of Payment: " + LocalDate.now()+"\r\n"
				+ "For Semester: " + Year + ".\r\n"
				+ "Dues Cleared: Yes\r\n"
				+ "\r\n"
				+ "Description:\r\n"
				+ "This is to confirm that the payment of your Dues has been received for the clearance of dues for the "+ winsum +". The student is now clear of any outstanding dues for the mentioned period.\r\n"
				+ "\r\n"
				+ "If you have any questions or need further assistance, please contact the finance office.\r\n"
				+ "\r\n"
				+ "Authorized By:\r\n"
				+ "Vaibhav Jawadwar\r\n"
				+ "Exam Section\r\n"
				+ "\r\n"
				+ "Note: Please keep this receipt for your records."));   
		  
		doc.close();  

		writer.close();  
		
		// Setting up mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Creating session with authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        try {
            // Creating the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
            
            String filename = "D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\Receipt"+ LocalDate.now() +".pdf";//change accordingly  
            DataSource source = new FileDataSource(filename);  
            messageBodyPart2.setDataHandler(new DataHandler(source));  
            messageBodyPart2.setFileName("No Dues Receipt.pdf");  
             
             
            //5) create Multipart object and add MimeBodyPart objects to this object      
            Multipart multipart = new MimeMultipart(); 
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messageBodyPart2);  
          
            //6) set the multiplart object to the message object  
            message.setContent(multipart ); 
            

            // Sending the email
            Transport.send(message);
            System.out.print("Receipt send Successfully!!!!");
          

        } catch (MessagingException e) {
        	JOptionPane.showMessageDialog(new Clear_Dues(), "Receipt Sent Failed!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        
       
		
		
		
	}
	
    static public void sendMail(String recipientEmail, String subject, String body) {

        // Setting up mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Creating session with authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        try {
            // Creating the email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
   
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body); // Email body

            // Sending the email
            Transport.send(message);
            System.out.print("Mail send Successfully!!!!");

       
            System.out.print("Mail send Successfully!!!!");


        } catch (MessagingException e) {
           
        	JOptionPane.showMessageDialog(new Clear_Dues(), "Reminder Mail Send Failed!!!", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
       
    }


	public Clear_Dues() {

		ConnectDB();
		addWindowListener(this);
		System.out.print("Student Count: "+count_stud+"\n");
		setTitle("Dues Status");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1100, 750);
		getContentPane().setLayout(null);
		Container cp = getContentPane();
		cp.setBackground(Color.white);
		
		
		Totalpaid = new JLabel("Paid students Count: ");
		Totalpaid.setBounds(23,622,150,30);
		cp.add(Totalpaid);
		
		paid = new JTextField();
		paid.setEditable(false);
		paid.setBounds(175,623,150,30);
		cp.add(paid);
		
		TotalUnpaid = new JLabel("Not Paid students Count: ");
		TotalUnpaid.setBounds(23,675,150,30);
		cp.add(TotalUnpaid);
		
		unpaid = new JTextField();
		unpaid.setEditable(false);
		unpaid.setBounds(175,675,150,30);
		cp.add(unpaid);
		
		sendmsg = new JButton("Send Reminder");
		sendmsg.setForeground(Color.WHITE);
		sendmsg.setBackground(Color.BLUE);
		sendmsg.setFont(new Font("Tahoma", Font.BOLD, 14));
		sendmsg.setBounds(10,20,153,40);
		sendmsg.addActionListener(this);
		cp.add(sendmsg);
		
		sendreceipt = new JButton("Send Receipt");
		sendreceipt.setForeground(Color.WHITE);
		sendreceipt.setBackground(Color.BLUE);
		sendreceipt.setFont(new Font("Tahoma", Font.BOLD, 14));
		sendreceipt.addActionListener(this);
		sendreceipt.setBounds(180,20,133,40);
		cp.add(	sendreceipt);
		
		Export = new JButton("Export to Sheets");
		Export.setForeground(Color.WHITE);
		Export.setBackground(Color.RED);
		Export.setFont(new Font("Tahoma", Font.BOLD, 14));
		Export.addActionListener(this);
		Export.setBounds(900,390,160,40);
		cp.add(	Export);
	
		
		ImageIcon i1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\Back.png"));
		Image i2 = i1.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		
		//add the label to display image on it
		imageLabel = new JLabel(i3);
		imageLabel.setBounds(1040,10,39,40);
		imageLabel.addMouseListener(this);
		cp.add(imageLabel);
		
		
		ImageIcon i4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("D:\\AJP Programs\\No_Dues_App AJP Project Using Swing\\No_Dues_App\\src\\icons\\nextPage.png"));
		Image i5 = i4.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		
		//add the label to display image on it
	    imageLabel_Next = new JLabel(i6);
		imageLabel_Next.setBounds(1020,640,79,67);
		imageLabel_Next.addMouseListener(this);
		cp.add(imageLabel_Next);
				
		
		Statement smt;

		try {
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery("Select * from student");
			
			int rowcount = 0;
			
			
			long[] enroll = new long[count_stud];
			String[] Sname = new String[count_stud];
			String[] Department = new String[count_stud] ;
			int [] Syear= new int[count_stud];
			String[] WinandsSum= new String[count_stud] ;
			String[] duestatus= new String[count_stud];
//			String[] Remark= new String[count_stud];
			
			int in = 0;
			
			
			data = new Object[count_stud][11];
			while(rs.next())
			{
		
				data[in][0]= rs.getLong(1);
				data[in][1]= rs.getString(2);
				data[in][2]= rs.getString(3);
				data[in][3]= rs.getInt(4);
				data[in][4]= rs.getString(5);
				data[in][5]= rs.getString(6);
				
				System.out.println(in);
				System.out.println(data[in][0]);

				in++;
				
				
			}
			
			
			
			
			tabel  = new JTable(data,colHead);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	
		
		
		tabel.setBackground(Color.white);
	
				
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        tabel.setFont(new Font("Tahoma",Font.PLAIN,14));
        tabel.setRowHeight(20);
        JTableHeader header = tabel.getTableHeader();
        
        header.setFont(new Font("Tahoma",Font.BOLD,15));
        JScrollPane jsp = new JScrollPane(tabel,v,h);

		panel = new JPanel();
		panel.setBounds(10, 80, 1070, 300);
		panel.setLayout(new BorderLayout());
		panel.add(jsp,BorderLayout.CENTER);
		
		cp.add(panel);
		
		JLabel Heading = new JLabel("**************OR*************");
		Heading.setFont(new Font("Tahoma", Font.BOLD, 15));
		Heading.setBounds(300,380,450,40);
		cp.add(Heading);
		
		 Heading = new JLabel("Dues Information");
		 Heading.setForeground(new Color(0, 0, 255));
		 Heading.setFont(new Font("Tahoma", Font.BOLD, 20));
		 Heading.setBounds(450,10,250,40);
		cp.add(Heading);
		
		Heading = new JLabel("Search By Enrollment Number: ");
		Heading.setFont(new Font("Tahoma", Font.BOLD, 14));
		Heading.setBounds(220,420,250,40);
		cp.add(Heading);
	
		 EnRollno = new JTextField();
		EnRollno.setBounds(160,470,170,30);
		cp.add(EnRollno);
		
		Fetchinfo = new JButton("Fetch Details");
		Fetchinfo.setForeground(Color.WHITE);
		Fetchinfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		Fetchinfo.setBackground(Color.BLUE);
		Fetchinfo.addActionListener(this);
		Fetchinfo.setBounds(350,470,160,30);
		cp.add(Fetchinfo);
		
	
	}
	
public static void main(String[] args) {
	ConnectDB();
	Statement smt;
	int count = 0;
	try {
		smt = con.createStatement();
		ResultSet rs = smt.executeQuery("Select * from student");
		
		while(rs.next())
		{
			count_stud++;
			count++;
		}
		
		System.out.println(count_stud);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	Clear_Dues frame = new Clear_Dues();
	frame.setVisible(true);
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

@Override
public void actionPerformed(ActionEvent e) {
	
	
	if(e.getSource() == Fetchinfo)
	{
		
		Statement smt;
		try {
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery("Select * from student where Enrollno ="+EnRollno.getText());
	
		while(rs.next())
		{
			
				JOptionPane.showMessageDialog(this, "Student Details are:\n Enrollment Number: "+ rs.getLong(1) + "\n Name: " + rs.getString(2) + "\n Department: "+ rs.getString(3)+ "\n Year: "+ rs.getInt(4)+"\n Winter/Summer: "+ rs.getString(5)+"\n Due Status: "+ rs.getString(6) , "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		}catch (Exception fe) {
			fe.printStackTrace();
		}
			
		
	}
		
	int paidcount = 0,unpaidcount = 0;
	
	System.out.println(tabel.getRowCount());

	
	for(int i = 0;i<tabel.getRowCount();i++)
	{
		if(tabel.getValueAt(i, 5).equals("Paid"))
		{
			paidcount++;
			
		}else
		{
			unpaidcount++;
		}
	}
	
	paid.setText(Integer.toString(paidcount));
	unpaid.setText(Integer.toString(unpaidcount));
	
	if(e.getSource() == sendmsg)
	{
		int loop = 0 ;
		Statement smt;
		long Enroll_mail[] = new long[unpaidcount];
		String name_mail[] = new String[unpaidcount];
		String winandsum_mail[] = new String[unpaidcount];
		String Dep_Mail[] = new String[unpaidcount];
		int Syear_Mail[] = new int[unpaidcount];
		String Mail_Mail[] = new String[unpaidcount];
		
		try {
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery("Select * from student where duestatus = 'UnPaid'");
			
			while(rs.next())
			{
				
				 Enroll_mail[loop] = rs.getLong(1);
				 name_mail[loop] = rs.getString(2);
				 Dep_Mail[loop] = rs.getString(3);
				 Syear_Mail[loop] = rs.getInt(4);
				 winandsum_mail[loop] = rs.getString(5);
				 Mail_Mail[loop] = rs.getString(8);
				 
				 loop++;
			}
			
			for(int i = 0 ; i<Mail_Mail.length;i++)
			{
				
		sendMail(Mail_Mail[i], "Reminder: Pending Dues Clearance - GT&MC", "Dear "+ name_mail[i] + " ,\r\n"
				+ "\r\n"
				+ "I hope this email finds you well. We would like to remind you that according to our records, your dues for the "+ winandsum_mail[i] + " remain unpaid. Kindly review the details below:\r\n"
				+ "\r\n"
				+ "Enrollment Number: " + Enroll_mail[i] + "\r\n"
				+ "Department: "+ Dep_Mail[i]+"\r\n"
				+ "Year: " + Syear_Mail[i] +"\r\n"
				+ "Due For: " + winandsum_mail[i] +"\r\n"
				+ "\r\n"
				+ "It is essential to clear your dues to avoid any inconvenience, including restrictions on accessing certain university services or delays in receiving your results.\r\n"
				+ "\r\n"
				+ "You can make the payment by visiting the finance office for further assistance. Please ensure the payment is completed at your earliest convenience.\r\n"
				+ "\r\n"
				+ "If you have already made the payment or believe this notice has been sent in error, kindly ignore this message or contact us immediately to confirm.\r\n"
				+ "\r\n"
				+ "Thank you for your attention to this matter.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "Vaibhav Jawadwar\r\n"
				+ "Exam Section\r\n"
				+ "Gramin Technical and Management Campus, Vishnupuri, Nanded - 431606\r\n"
				+ "8288394884");
			}
		JOptionPane.showMessageDialog(this, "Nortification Send Successfully to "+ Mail_Mail.length + " Unpaid Students!!", "Information", JOptionPane.INFORMATION_MESSAGE);
		
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
if(e.getSource() == Export)
{
	exportToExcel(tabel);
}
	
	if(e.getSource() == sendreceipt)
	{
		try {
			
			int loop = 0 ;
			Statement smt;
			long Enroll_mail[] = new long[paidcount];
			String name_mail[] = new String[paidcount];
			String winandsum_mail[] = new String[paidcount];
			String Dep_Mail[] = new String[paidcount];
			int Syear_Mail[] = new int[paidcount];
			String Mail_Mail[] = new String[paidcount];
			
		
				smt = con.createStatement();
				ResultSet rs = smt.executeQuery("Select * from student where duestatus = 'Paid' AND Remark = 'Receipt not Send'");
				
				
				while(rs.next())
				{
					 Enroll_mail[loop] = rs.getLong(1);
					 name_mail[loop] = rs.getString(2);
					 Dep_Mail[loop] = rs.getString(3);
					 Syear_Mail[loop] = rs.getInt(4);
					 winandsum_mail[loop] = rs.getString(5);
					 Mail_Mail[loop] = rs.getString(8);
					 
					 loop++;
				}
				
				for(int i = 0 ; i<Mail_Mail.length;i++)
				{
					if(Mail_Mail[i]!= null)
					{
						
					createReceipt(Mail_Mail[i], "Confirmation of Dues Clearance - GT&MC", "Dear "+ name_mail[i] + " ,\r\n"
							+ "\r\n"
							+ "I hope this email finds you well. We would like to Pleased to inform you that according to our records, your dues for the "+ winandsum_mail[i] + " have been successfully cleared. Kindly review the details below:\r\n"
							+ "\r\n"
							+ "Enrollment Number: " + Enroll_mail[i] + "\r\n"
							+ "Department: "+ Dep_Mail[i]+"\r\n"
							+ "Year: " + Syear_Mail[i] +"\r\n"
							+ "Due For: " + winandsum_mail[i] +"\r\n"
							+ "\r\n"
							+ "Please retain this email as confirmation of your dues clearance. If you require any further documentation or have any questions, feel free to contact our office"+
							 "Thank you for your attention to this matter.\r\n"
							+ "\r\n"
							+ "Best regards,\r\n"
							+ "Vaibhav Jawadwar\r\n"
							+ "Exam Section\r\n"
							+ "Gramin Technical and Management Campus, Vishnupuri, Nanded - 431606\r\n"
							+ "8288394884",name_mail[i],Enroll_mail[i],Dep_Mail[i],Syear_Mail[i],winandsum_mail[i]);
					}
						}
			
					JOptionPane.showMessageDialog(this, "Receipt Send Successfully to Due Paid Students!!!", "Information", JOptionPane.INFORMATION_MESSAGE);
					
					try {
						PreparedStatement smt_Update;
						smt_Update = con.prepareStatement("update student set Remark = ? where Remark = 'Receipt not Send' AND duestatus = 'Paid'");
						smt_Update.setString(1, "Receipt Send");
						smt_Update.executeUpdate();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(this, "Student Record Update Failed!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
					}
					
					
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

}

@Override
public void mouseClicked(MouseEvent e) {
	if(e.getSource() == imageLabel)
	{
	setVisible(false);
	Choose_Options  frame = new Choose_Options();
	frame.setVisible(true);
	}else if(e.getSource() == imageLabel_Next)
	{
		
		Pay_Dues frame = new Pay_Dues();
		setVisible(false);
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

@Override
public void windowOpened(WindowEvent e) {
int paidcount = 0,unpaidcount = 0;
	
	System.out.println(tabel.getRowCount());
	

	
	for(int i = 0;i<tabel.getRowCount();i++)
	{
		if(tabel.getValueAt(i, 5).equals("Paid"))
		{
			paidcount++;
			
		}else
		{
			unpaidcount++;
		}
	}
	
	paid.setText(Integer.toString(paidcount));
	unpaid.setText(Integer.toString(unpaidcount));
	
	
	
	
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
int paidcount = 0,unpaidcount = 0;
	
	System.out.println(tabel.getRowCount());

	
	for(int i = 0;i<tabel.getRowCount();i++)
	{
		if(tabel.getValueAt(i, 5).equals("Paid"))
		{
			paidcount++;
			
		}else
		{
			unpaidcount++;
		}
	}
	
	paid.setText(Integer.toString(paidcount));
	unpaid.setText(Integer.toString(unpaidcount));
	
	
	
	
}

@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
}