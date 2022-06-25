import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Properties;

public class Students{
	
	private JPanel pn, pn1, pn2, pn3;
	private JFrame jf;
	private JButton JB_insert, JB_modify, JB_view, JB_delete;
	private JLabel JL_rno, JL_sname, JL_sem, JL_year, JL_phone;
	private JTextField JTF_rno, JTF_sname, JTF_sem, JTF_year, JTF_phone;
	
	
	private JMenuItem insert2, update2, view2, delete2;
	private List ProjectList;
	private Choice Projectid;
	
	public Students(JPanel pn, JFrame jf, JMenuItem insert2, JMenuItem update2, JMenuItem view2, JMenuItem delete2){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) {
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		this.jf = jf;
		this.insert2 = insert2;
		this.update2 = update2;
		this.view2 = view2;
		this.delete2 = delete2;
		
		JL_rno = new JLabel("Roll number: ");
		JTF_rno = new JTextField(10);
		JL_sname = new JLabel("Student name: ");
		JTF_sname = new JTextField(10);
		JL_sem = new JLabel("Semester: ");
		JTF_sem = new JTextField(10);
		JL_year = new JLabel("Year: ");
        JTF_year = new JTextField(10);
        JL_phone = new JLabel("Phone number: ");
        JTF_phone = new JTextField(10);

        this.pn=pn;
      }
	private void displaySQLErrors(SQLException e) {
		JOptionPane.showMessageDialog(pn1,"\nSQLException: " + e.getMessage() + "\n"+"SQLState:     " + e.getSQLState() + "\n"+"VendorError:  " + e.getErrorCode() + "\n");
	}
	
	public void loaddetails(){
		try{
			Projectid = new Choice();
			Projectid.removeAll();
			Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
			Statement st1 = con1.createStatement();
			ResultSet rs1 = st1.executeQuery("select * from student_details");
			while(rs1.next()) {
				Projectid.add(rs1.getString("rno"));
			}
		}
		catch(SQLException e) {
			displaySQLErrors(e);
		}
	}
	
	public void loadproject(){
		try{
			ProjectList = new List();
			ProjectList.removeAll();
			Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
			Statement st2 = con2.createStatement();
			ResultSet rs2 = st2.executeQuery("select * from student_details");
			while(rs2.next()) {
				ProjectList.add(rs2.getString("rno"));
			}
		}
		catch(SQLException e) {
			displaySQLErrors(e);
		}
	}
	public void buildGUI(){	
		insert2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent aevt){
				
				JB_insert = new JButton("Submit");
				JTF_rno.setText(null);
				JTF_sname.setText(null);
				JTF_sem.setText(null);
				JTF_year.setText(null);
				JTF_phone.setText(null);

				loadproject();
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10,10));
				pn1.add(JL_rno);
				pn1.add(JTF_rno);
				pn1.add(JL_sname);
				pn1.add(JTF_sname);
				pn1.add(JL_sem);
				pn1.add(JTF_sem);
				pn1.add(JL_year);
				pn1.add(JTF_year);
				pn1.add(JL_phone);
				pn1.add(JTF_phone);
				
				
				
				pn3 = new JPanel(new FlowLayout());
				pn3.add(JB_insert);
				pn1.setBounds(115, 80, 300, 250);
				pn3.setBounds(200, 350, 75, 35);
				 
				pn2 = new JPanel(new FlowLayout());
				ProjectList = new List(10);
				loadproject();
				pn2.add(ProjectList);
				pn2.setBounds(200, 350, 300, 180);  
				
				pn.add(pn1);
				pn.add(pn3);
				pn.add(pn2);
				
				pn.setLayout(new BorderLayout());
				jf.add(pn);
				jf.setSize(800, 800);
				jf.validate();
				
				JB_insert.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent aevt){
						try{
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","it20737055","teja2002"); 
							String query = "INSERT INTO Student_details (rno, sname, sem, year, phone_no) values(?, ?, ?, ?, ?)";
							PreparedStatement stp = con.prepareStatement(query);
							stp.setString(1,  JTF_rno.getText());
							stp.setString(2, JTF_sname.getText());
							stp.setString(3, JTF_sem.getText());
							stp.setString(4, JTF_year.getText());
							stp.setString(5, JTF_phone.getText());
							int  i = stp.executeUpdate();
							con.close();
							if(i > 0){
								JOptionPane.showMessageDialog(pn,"\nInserted successfully");
							}
						}
						catch(SQLException e) {
							displaySQLErrors(e);
						}
					}
				});	
			}
		});
		update2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent aevt){
				JB_modify = new JButton("Modify");
				
				JTF_rno.setText(null);
				JTF_sname.setText(null);
				JTF_sem.setText(null);
				JTF_year.setText(null);
				JTF_phone.setText(null);
				
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10, 10));
				
				pn1.add(JL_rno);
				pn1.add(JTF_rno);
				pn1.add(JL_sname);
				pn1.add(JTF_sname);
				pn1.add(JL_sem);
				pn1.add(JTF_sem);
				pn1.add(JL_year);
				pn1.add(JTF_year);
				pn1.add(JL_phone);
				pn1.add(JTF_phone);
				

				pn3 = new JPanel(new FlowLayout());
				pn3.add(JB_modify);
				pn1.setBounds(115, 80, 300, 250);
				pn3.setBounds(200, 350, 75, 35);
				 
				pn2  =new JPanel(new FlowLayout());
				ProjectList = new List(10);
				loadproject();
				pn2.add(ProjectList);
				pn2.setBounds(200, 350, 300, 180);  
				
				pn.add(pn1);
				pn.add(pn3);
				pn.add(pn2);
				
				pn.setLayout(new BorderLayout());
				jf.add(pn);
				jf.setSize(800, 800);
				jf.validate();
				
				ProjectList.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ievt){
						try {
							Connection con3 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
							Statement st3 = con3.createStatement();
							ResultSet rs3 = st3.executeQuery("select * from Student_details");
							while (rs3.next()) {
								if (rs3.getString("rno").equals(ProjectList.getSelectedItem()))
								break;
							}
							if (!rs3.isAfterLast()) {
								JTF_rno.setText(rs3.getString("rno"));
								JTF_sname.setText(rs3.getString("sname"));
								JTF_sem.setText(rs3.getString("sem"));
								JTF_year.setText(rs3.getString("year"));
								JTF_phone.setText(rs3.getString("phone_no"));
							}
						} 
						catch (SQLException selectException) {
							displaySQLErrors(selectException);
						}	
					}
				});	
				JB_modify.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent aevt){
						 try{
							int a = JOptionPane.showConfirmDialog(pn, "Are you sure want to update:");
							if(a == JOptionPane.YES_OPTION){  
								String query = "update student_details set sname = ?, year = ?, sem = ?, phone_no = ? where rno = ?"; 
								Connection con4 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
								PreparedStatement stp2 = con4.prepareStatement(query);
								stp2.setString(1, JTF_sname.getText());
								stp2.setString(2, JTF_year.getText());
								stp2.setString(3, JTF_sem.getText());
								stp2.setString(4, JTF_phone.getText());
								stp2.setString(5, JTF_rno.getText());
								int i = stp2.executeUpdate();
								if(i>0){
									JOptionPane.showMessageDialog(pn,"\nUpdated rows succesfully");
								}
								loadproject();
							}
						 }
						catch(SQLException e){
							displaySQLErrors(e);
						}
					}
				});
			}
		});
		delete2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent aevt){
				JB_delete = new JButton("Delete");

				JTF_rno.setText(null);
				JTF_sname.setText(null);
				JTF_sem.setText(null);
				JTF_year.setText(null);
				JTF_phone.setText(null);	
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10, 10));
				pn1.add(JL_rno);
				pn1.add(JTF_rno);
				pn1.add(JL_sname);
				pn1.add(JTF_sname);
				pn1.add(JL_sem);
				pn1.add(JTF_sem);
				pn1.add(JL_year);
				pn1.add(JTF_year);
				pn1.add(JL_phone);
				pn1.add(JTF_phone);
				
				
				pn3 = new JPanel(new FlowLayout());
				pn3.add(JB_delete);
				pn1.setBounds(115, 80, 300, 250);
				pn3.setBounds(200, 350, 75, 35);
				 
				pn2 = new JPanel(new FlowLayout());
				ProjectList = new List(10);
				loadproject();
				pn2.add(ProjectList);
				pn2.setBounds(200, 350, 300, 200);  
				
				pn.add(pn1);
				pn.add(pn3);
				pn.add(pn2);
				
				pn.setLayout(new BorderLayout());
				jf.add(pn);
				jf.setSize(800, 800);
				jf.validate();
				
				ProjectList.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ievt){
						try {
							Connection con5 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
							Statement st4 = con5.createStatement();
							ResultSet rs4 = st4.executeQuery("select * from student_details");
							while (rs4.next()) {
								if (rs4.getString("rno").equals(ProjectList.getSelectedItem()))
								 break;
							}
							if (!rs4.isAfterLast()) {
								JTF_rno.setText(rs4.getString("rno"));
								JTF_sname.setText(rs4.getString("sname"));
								JTF_sem.setText(rs4.getString("sem"));
								JTF_year.setText(rs4.getString("year"));
								JTF_phone.setText(rs4.getString("phone_no"));			
							}
						} 
						catch (SQLException selectException) {
							displaySQLErrors(selectException);
						}	
					}
				});	
				JB_delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent aevt){
						 try {
							int a = JOptionPane.showConfirmDialog(pn,"Are you sure want to Delete:");
							if(a == JOptionPane.YES_OPTION){  
								String query = "DELETE FROM Student_details WHERE rno = ?";
								Connection con6 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
								PreparedStatement stp3 = con6.prepareStatement(query);
								stp3.setString(1, JTF_rno.getText());
								int i = stp3.executeUpdate();
								if(i>0){
									JOptionPane.showMessageDialog(pn,"\nDeleted rows succesfully");
								}
								loadproject();
							}
						 }
						catch(SQLException e){
							displaySQLErrors(e);
						}
					}
				});
			}
		});
 		view2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent aevt){
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				JLabel view = new JLabel("Student details View");
				view.setForeground(Color.RED);
				JB_view = new JButton("View");
				Font myFont = new Font("Serif",Font.BOLD,25);
				view.setFont((myFont));
				
				pn1 = new JPanel();
				pn2 = new JPanel();
				pn1.add(view);
				pn2.add(JB_view);
				pn.add(pn1);
				pn.add(pn2);
				pn.setBounds(500, 800, 300, 300);
				pn.setLayout(new FlowLayout());
				
				jf.add(pn);
				jf.setSize(800, 800);
				jf.validate();
				
				JB_view.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent aevt){
						JFrame jfr = new JFrame("Student Details");
						JTable jt;
						DefaultTableModel model = new DefaultTableModel(); 
				        jt = new JTable(model); 
				        model.addColumn("Roll Number");
				        model.addColumn("Student Name");
				        model.addColumn("Semester");
				        model.addColumn("Year");
						model.addColumn("Phone number");
					    try {	
							Connection con7 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
							Statement st4 = con7.createStatement();
							ResultSet rs5 = st4.executeQuery("select * from student_details");						
							while(rs5.next()){
								model.addRow(new Object[]{rs5.getString("rno"), rs5.getString("sname"), rs5.getString("sem"), rs5.getString("year"), rs5.getString("phone_no")});
							}
						}
						catch(SQLException e){
							displaySQLErrors(e);
						}
						jt.setEnabled(false);
				        jt.setBounds(30, 40, 300, 300); 
				        JScrollPane jsp = new JScrollPane(jt); 
				        jfr.add(jsp); 
				        jfr.setSize(800, 400); 
				        jfr.setVisible(true); 
					}
				});	
			}
		});	
	}
}