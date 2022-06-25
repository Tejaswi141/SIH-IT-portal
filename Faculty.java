import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Properties;

public class Faculty{
	
	private JPanel pn, pn1, pn2, pn3;
	private JFrame jf;
	private JButton JB_insert, JB_modify, JB_view, JB_delete;
	private JLabel JL_fid, JL_fname, JL_pname, JL_year, JL_phone;
	private JTextField JTF_fid, JTF_fname, JTF_pname, JTF_year, JTF_phone;
	
	private JMenuItem insert2, update2, view2, delete2;
	private List ProjectList;
	private Choice Projectid;
	
	public Faculty(JPanel pn, JFrame jf, JMenuItem insert2, JMenuItem update2, JMenuItem view2, JMenuItem delete2){
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
		
		JL_fid = new JLabel("Faculty ID: ");
		JTF_fid = new JTextField(10);
		JL_fname = new JLabel("Faculty name: ");
		JTF_fname = new JTextField(10);
		JL_pname = new JLabel("Project name: ");
		JTF_pname = new JTextField(10);
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
			ResultSet rs1 = st1.executeQuery("select * from faculty");
			while(rs1.next()) {
				Projectid.add(rs1.getString("fid"));
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
			ResultSet rs2 = st2.executeQuery("select * from faculty");
			while(rs2.next()) {
				ProjectList.add(rs2.getString("fid"));
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
				JTF_fid.setText(null);
				JTF_fname.setText(null);
				JTF_pname.setText(null);
				JTF_year.setText(null);
				JTF_phone.setText(null);

				loadproject();
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10, 10));
				pn1.add(JL_fid);
				pn1.add(JTF_fid);
				pn1.add(JL_fname);
				pn1.add(JTF_fname);
				pn1.add(JL_pname);
				pn1.add(JTF_pname);
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
							String query = "INSERT INTO faculty (fid, fname, pname, year, phone_no) values(?, ?, ?, ?, ?)";
							PreparedStatement stp = con.prepareStatement(query);
							stp.setString(1,  JTF_fid.getText());
							stp.setString(2, JTF_fname.getText());
							stp.setString(3, JTF_pname.getText());
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
				
				JTF_fid.setText(null);
				JTF_fname.setText(null);
				JTF_pname.setText(null);
				JTF_year.setText(null);
				JTF_phone.setText(null);
				
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10, 10));
				
				pn1.add(JL_fid);
				pn1.add(JTF_fid);
				pn1.add(JL_fname);
				pn1.add(JTF_fname);
				pn1.add(JL_pname);
				pn1.add(JTF_pname);
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
							ResultSet rs3 = st3.executeQuery("select * from faculty");
							while (rs3.next()) {
								if (rs3.getString("fid").equals(ProjectList.getSelectedItem()))
								break;
							}
							if (!rs3.isAfterLast()) {
								JTF_fid.setText(rs3.getString("fid"));
								JTF_fname.setText(rs3.getString("fname"));
								JTF_pname.setText(rs3.getString("pname"));
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
								String query = "update faculty set fname = ?, pname = ?, year = ?, phone_no = ? where fid = ?";
								Connection con4 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
								PreparedStatement stp2 = con4.prepareStatement(query);
								stp2.setString(1, JTF_fname.getText());
								stp2.setString(2, JTF_pname.getText());
								stp2.setString(3, JTF_year.getText());
								stp2.setString(4, JTF_phone.getText());
								stp2.setString(5, JTF_fid.getText());
								int i = stp2.executeUpdate();{
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

				JTF_fid.setText(null);
				JTF_fname.setText(null);
				JTF_pname.setText(null);
				JTF_year.setText(null);
				JTF_phone.setText(null);
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10, 10));
				pn1.add(JL_fid);
				pn1.add(JTF_fid);
				pn1.add(JL_fname);
				pn1.add(JTF_fname);
				pn1.add(JL_pname);
				pn1.add(JTF_pname);
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
							ResultSet rs4 = st4.executeQuery("select * from faculty");
							while (rs4.next()) {
								if (rs4.getString("fid").equals(ProjectList.getSelectedItem()))
								 break;
							}
							if (!rs4.isAfterLast()) {
								JTF_fid.setText(rs4.getString("fid"));
								JTF_fname.setText(rs4.getString("fname"));
								JTF_pname.setText(rs4.getString("pname"));
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
							int a = JOptionPane.showConfirmDialog(pn, "Are you sure want to Delete:");
							if(a == JOptionPane.YES_OPTION){  
								String query = "DELETE FROM faculty WHERE fid = ? ";
								Connection con6 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
								PreparedStatement stp3 = con6.prepareStatement(query);
								stp3.setString(1, JTF_fid.getText());
								int i = stp3.executeUpdate(query);
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
				
				JLabel view = new JLabel("Faculty details View");
				view.setForeground(Color.RED);
				JB_view = new JButton("View");
				Font myFont = new Font("Serif", Font.BOLD,25);
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
				        model.addColumn("Faculty ID");
				        model.addColumn("Faculty Name");
				        model.addColumn("Project name");
				        model.addColumn("Year");
						model.addColumn("Phone number");
					    try {		
							Connection con7 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
							Statement st4 = con7.createStatement();
							ResultSet rs5 = st4.executeQuery("select * from faculty");		
							while(rs5.next()){
								model.addRow(new Object[]{rs5.getString("fid"), rs5.getString("fname"), rs5.getString("pname"), rs5.getString("year"), rs5.getString("phone_no")});
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