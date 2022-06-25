import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PortalUI extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private JMenuBar mnu;
	
	private JMenu mnuProject;
	private JMenu mnuStudents;
	private JMenu mnuFaculty;
	private JMenu mnuEvent;

	private JMenuItem insert1, update1, delete1, view1;
	private JMenuItem insert2, update2, delete2, view2;
	private JMenuItem insert3, update3, delete3, view3;
	private JMenuItem insert4, update4, delete4, view4;
	
	private JLabel labelName;

	
	private static JPanel p0, p1;
	
	
	public void initialize() {
		mnu = new JMenuBar();
		mnuProject = new JMenu("Project details");
		mnuStudents = new JMenu("Students");
		mnuFaculty = new JMenu("Faculty");
		mnuEvent = new JMenu("Hackathon Event");
		Color lightBlue= new Color(51, 204, 255);
		
		labelName=new JLabel("SIH IT PORTAL");
		labelName.setFont(new Font("Serif", Font.PLAIN, 40));
		labelName.setForeground(Color.RED);
		p1=new JPanel();
		p0=new JPanel();
		insert1 = new JMenuItem("Insert");
		update1 = new JMenuItem("Update");
		delete1 = new JMenuItem("Delete");
		view1 = new JMenuItem("View");
		
		insert2 = new JMenuItem("Insert");
		update2 = new JMenuItem("Update");
		delete2 = new JMenuItem("Delete");
		view2 = new JMenuItem("View");
		
		insert3 = new JMenuItem("Insert");
		update3 = new JMenuItem("Update");
		delete3 = new JMenuItem("Delete");
		view3 = new JMenuItem("View");
		
		insert4 = new JMenuItem("Insert");
		update4 = new JMenuItem("Update");
		delete4 = new JMenuItem("Delete");
		view4 = new JMenuItem("View");
	}
	
	void addComponentsToFrame() {
		 mnuProject.add(insert1);
		 mnuProject.add(delete1);
		 mnuProject.add(update1);
		 mnuProject.add(view1);
		 
		 mnuStudents.add(insert2);
		 mnuStudents.add(delete2);
		 mnuStudents.add(update2);
		 mnuStudents.add(view2);
		 
		 mnuFaculty.add(insert3);
		 mnuFaculty.add(delete3);
		 mnuFaculty.add(update3);
		 mnuFaculty.add(view3);
		 
		 mnuEvent.add(insert4);
		 mnuEvent.add(delete4);
		 mnuEvent.add(update4);
		 mnuEvent.add(view4);
		 
		 mnu.add(mnuProject);
		 mnu.add(mnuStudents);
		 mnu.add(mnuFaculty);
		 mnu.add(mnuEvent);
		 
		 setJMenuBar(mnu); 
		 p1.add(labelName);
		 p1.setAlignmentY(CENTER_ALIGNMENT); 
		 p1.setBounds(500, 500, 800, 100);	
		 p0.add(p1);
		 p0.setBackground(Color.lightGray);
		 add(p0);
	}
void closeWindow(){
		try {
			int a = JOptionPane.showConfirmDialog(this,"Are you sure want to Quit SIH IT Portal?");
			if(a == JOptionPane.YES_OPTION){  
				JOptionPane.showMessageDialog(this, "Thank you!\nExiting SIH IT Portal", "Quit", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			else if (a == JOptionPane.NO_OPTION) {
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
			else if (a == JOptionPane.CANCEL_OPTION) {
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		}
			catch(Exception e) {
				System.out.println(e);
				}
	}
	void register() {
		Project_details pjd=new Project_details(p0, PortalUI.this, insert1, update1, view1, delete1); 
		pjd.buildGUI();
        Students st = new Students(p0, PortalUI.this, insert2, update2, view2, delete2);
		st.buildGUI();
		Faculty fc = new Faculty(p0, PortalUI.this, insert3, update3, view3, delete3); 
		fc.buildGUI();
		Hackathon_event hce=new Hackathon_event(p0, PortalUI.this, insert4, update4, view4, delete4); 
		hce.buildGUI(); 
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we) 
			{ 
				closeWindow();
			} 
		}); 
		
} 
public PortalUI() {
		initialize();
		addComponentsToFrame();
		register();
		pack();
		setTitle("SIH IT Portal");
		setSize(800, 800);
		setVisible(true);
	}
}

