import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,img,error;
	JTextField t1;
	JPasswordField t2;
	String s = "Student Login";
	JButton b1,b2;
	boolean isStudent;
	//DBConnection db;
	
	public LoginUI(boolean f)
	{
		isStudent = f;
		if(!isStudent)
			s = "Faculty Login";
		
		setSize(500,580);
		setLayout(null);
		setVisible(true);
		setTitle("Login Page");
		setResizable(false);
		setLocation(500,200);
		
		createComponents();
		addComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void createComponents()
	{
		l1 = new JLabel(s);
		l1.setFont(new Font("Serif",Font.PLAIN,40));
		l2 = new JLabel("User ID:  ");
		l2.setFont(new Font("Serif",Font.PLAIN,30));
		l3 = new JLabel("Password:  ");
		l3.setFont(new Font("Serif",Font.PLAIN,30));
		error = new JLabel("Invalid UserID/Password");
		error.setFont(new Font("Serif",Font.PLAIN,25));
		error.setForeground(Color.RED);
		error.setVisible(false);
		
		b1 = new JButton("Submit");
		b1.setFont(new Font("Arial",Font.PLAIN,30));
		b1.addActionListener(this);
		b2 = new JButton("Go Back");
		b2.setFont(new Font("Arial",Font.PLAIN,30));
		b2.addActionListener(this);
		
		t1 = new JTextField();
		t1.setFont(new Font("Serif",Font.PLAIN,26));
		t2 = new JPasswordField();
		t2.setFont(new Font("Serif",Font.PLAIN,26));
		t2.setEchoChar('*');

		//db = new DBConnection();
		
		l1.setBounds(110,20,300,50);
		l2.setBounds(20,150,150,30);
		l3.setBounds(20,250,150,30);
		t1.setBounds(200,150,200,40);
		t2.setBounds(200,250,200,40);
		error.setBounds(90,300,300,50);
		
		b1.setBounds(150,370,150,40);
		b2.setBounds(150,440,150,40);
	}
	
	void addComponents()
	{
		add(l1);
		add(l2);
		add(l3);
		add(t1);
		add(t2);
		add(b1);
		add(b2);
		add(error);
		repaint();
	}
	
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource() == b2)
		{
			dispose();
			//new HomePageUI();
		}
		
		else
		{	
			try
			{
					//if(db.verifyLogin(t1.getText(),t2.getText(),isStudent))
					//{
					//	dispose();
					//	db.st.close();
						//db.conn.close();
						//new MainUI(isStudent,t1.getText());
					//}
				
					/*else
					{
						//System.out.println("User not found");
						error.setVisible(true);
					}*/
			}
			catch (Exception ae)
			{
				System.out.println(ae);
			}
		}
		
	}
	
	/*public static void main(String a[])
	{
		new LoginUI(true);
	}*/
}