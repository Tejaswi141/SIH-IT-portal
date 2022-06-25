import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Properties;

public class Project_details{
	
	private JPanel pn, pn1, pn2, pn3;
	private JFrame jf;
	private JButton JB_insert, JB_modify, JB_view, JB_delete;
	private JLabel JL_pname, JL_expla, JL_duration, JL_year;
	private JTextField JTF_pname, JTF_expla, JTF_duration, JTF_year;
	
	private JMenuItem insert2, update2, view2, delete2;
	private List ProjectList;
	private Choice Projectid;
	
	public Project_details(JPanel pn, JFrame jf, JMenuItem insert2, JMenuItem update2, JMenuItem view2, JMenuItem delete2){
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
		
		JL_pname = new JLabel("Project Name:");
		JTF_pname = new JTextField(10);
		JL_expla = new JLabel("Explanation:");
		JTF_expla = new JTextField(10);
		JL_duration = new JLabel("Duration:");
        JTF_duration = new JTextField(10);
        JL_year = new JLabel("Year:");
        JTF_year = new JTextField(10);

        this.pn = pn;
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
			ResultSet rs1 = st1.executeQuery("select * from project_details");
			while(rs1.next()) {
				Projectid.add(rs1.getString("pname"));
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
			ResultSet rs2 = st2.executeQuery("select * from project_details");
			while(rs2.next()) {
				ProjectList.add(rs2.getString("pname"));
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
				JTF_pname.setText(null);
				JTF_expla.setText(null);
				JTF_duration.setText(null);
				JTF_year.setText(null);

				loadproject();
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10,10));
				pn1.add(JL_pname);
				pn1.add(JTF_pname);
				pn1.add(JL_expla);
				pn1.add(JTF_expla);
				pn1.add(JL_duration);
				pn1.add(JTF_duration);
				pn1.add(JL_year);
				pn1.add(JTF_year);
				
				
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
							String query = "INSERT INTO project_details (pname, explanation, duration, year) values(?, ?, ?, ?)";
							PreparedStatement stp1 = con.prepareStatement(query);
							stp1.setString(1, JTF_pname.getText());
							stp1.setString(2, JTF_expla.getText());
							stp1.setString(3, JTF_duration.getText());
							stp1.setString(4, JTF_year.getText());
							int  i = stp1.executeUpdate();
							con.close();
							if(i > 0){
								JOptionPane.showMessageDialog(pn,"\nInserted successfully");
							}
							loadproject();
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
				JB_modify=new JButton("Modify");
				
				JTF_pname.setText(null);
				JTF_expla.setText(null);
				JTF_duration.setText(null);
				JTF_year.setText(null);
				
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1=new JPanel();
				pn1.setLayout(new GridLayout(10, 10));
				
				pn1.add(JL_pname);
				pn1.add(JTF_pname);
				pn1.add(JL_expla);
				pn1.add(JTF_expla);
				pn1.add(JL_duration);
				pn1.add(JTF_duration);
				pn1.add(JL_year);
				pn1.add(JTF_year);

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
							ResultSet rs3 = st3.executeQuery("select * from project_details");
							while (rs3.next()) {
								if (rs3.getString("pname").equals(ProjectList.getSelectedItem()))
								break;
							}
							if (!rs3.isAfterLast()) {
								JTF_pname.setText(rs3.getString("pname"));
								JTF_expla.setText(rs3.getString("explanation"));
								JTF_duration.setText(rs3.getString("duration"));
								JTF_year.setText(rs3.getString("year"));
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
								String query = "update project_details set explanation = ?, year = ?, duration = ? where pname = ?"; 
								Connection con4 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
								PreparedStatement stp2 = con4.prepareStatement(query);
								stp2.setString(1, JTF_expla.getText());
								stp2.setString(2, JTF_year.getText());
								stp2.setString(3, JTF_duration.getText());
								stp2.setString(4, JTF_pname.getText());
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

				JTF_pname.setText(null);
				JTF_expla.setText(null);
				JTF_duration.setText(null);
				JTF_year.setText(null);
				
				pn.removeAll();
				jf.invalidate();
				jf.validate();
				jf.repaint();
				
				pn1 = new JPanel();
				pn1.setLayout(new GridLayout(10, 10));
				pn1.add(JL_pname);
				pn1.add(JTF_pname);
				pn1.add(JL_expla);
				pn1.add(JTF_expla);
				pn1.add(JL_duration);
				pn1.add(JTF_duration);
				pn1.add(JL_year);
				pn1.add(JTF_year);
				
				
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
							ResultSet rs4 = st4.executeQuery("select * from project_details");
							while (rs4.next()) {
								if (rs4.getString("pname").equals(ProjectList.getSelectedItem()))
								 break;
							}
							if (!rs4.isAfterLast()) {
								JTF_pname.setText(rs4.getString("pname"));
								JTF_expla.setText(rs4.getString("explanation"));
								JTF_duration.setText(rs4.getString("duration"));
								JTF_year.setText(rs4.getString("year"));						
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
								String query = "DELETE FROM project_details WHERE pname = ?";
								Connection con6 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
								PreparedStatement stp3 = con6.prepareStatement(query);
								stp3.setString(1, JTF_pname.getText());								
								int i = stp3.executeUpdate();
								if(i > 0){
									JOptionPane.showMessageDialog(pn,"\nDeleted  rows succesfully");
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
				
				JLabel view = new JLabel("Project details View");
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
						JFrame jfr = new JFrame("Project Details");
						JTable jt;
						DefaultTableModel model = new DefaultTableModel(); 
				        jt = new JTable(model); 
				        model.addColumn("pname");
				        model.addColumn("explanation");
				        model.addColumn("duration");
				        model.addColumn("year");
					    try {		
							Connection con7 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "it20737055", "teja2002"); 
							Statement st4 = con7.createStatement();
							ResultSet rs5 = st4.executeQuery("select * from project_details");
							while(rs5.next()){
								model.addRow(new Object[]{rs5.getString("pname"), rs5.getString("explanation"), rs5.getString("duration"), rs5.getString("year")});
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