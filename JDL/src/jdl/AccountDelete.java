package jdl;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Properties;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AccountDelete extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private String clientSelectedName;
	private JTextField emp_FirstnameTxt;
	private boolean tables_validator = true;
	private JTextField emp_LastnameTxt;
	private JTextField emp_userIdTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tables window = new Tables();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccountDelete() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Account Delete");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(488, 429));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(22, 96, 441, 314);
		tables_inputPanel.setBackground(new Color (255,255,255,60));
		tables_inputPanel.setLayout(null);
		
		JComboBox emp_comboBox = new JComboBox();
		emp_comboBox.addItem("Select username");
		emp_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "SELECT * FROM jdl_accounts.users WHERE user_username=?";
					String sql1 = "SELECT * FROM jdl_accounts.employees WHERE user_id=?";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					PreparedStatement statement1 = (PreparedStatement) conn.prepareStatement(sql1);
					
					statement.setString(1,(String)emp_comboBox.getSelectedItem().toString());
					ResultSet rs = statement.executeQuery();
					
					while (rs.next()) {
					emp_userIdTxt.setText(rs.getString("user_id"));	
					}
					
					statement1.setInt(1, Integer.parseInt(emp_userIdTxt.getText()));
					ResultSet rs1 = statement1.executeQuery();
					
					while (rs1.next()) {
						emp_LastnameTxt.setText(rs1.getString("emp_lastname"));
						emp_FirstnameTxt.setText(rs1.getString("emp_firstname"));
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Connection conn1;
		try {
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
			Statement stat=conn1.createStatement();
			ResultSet rs1=stat.executeQuery("SELECT * FROM jdl_accounts.users");
			
			 while(rs1.next()){        
				 	String user_username = rs1.getString("user_username");
			
			       	emp_comboBox.addItem(user_username);
			       	clientSelectedName = emp_comboBox.getSelectedItem().toString();
			       	
			    }
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		emp_comboBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		emp_comboBox.setBounds(20, 42, 318, 29);
		
		AutoCompletion.enable(emp_comboBox);
		tables_inputPanel.add(emp_comboBox);
		
		JLabel emp_assignLbl = new JLabel("Select an Account to Delete:");
		emp_assignLbl.setForeground(Color.WHITE);
		emp_assignLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_assignLbl.setBounds(20, 0, 231, 41);
		tables_inputPanel.add(emp_assignLbl);
		
		JLabel emp_userIdLbl = new JLabel("User ID:");
		emp_userIdLbl.setForeground(Color.WHITE);
		emp_userIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_userIdLbl.setBounds(348, 0, 63, 41);
		tables_inputPanel.add(emp_userIdLbl);
		
		emp_userIdTxt = new JTextField();
		emp_userIdTxt.setEditable(false);
		emp_userIdTxt.setBounds(348, 42, 72, 29);
		tables_inputPanel.add(emp_userIdTxt);
		emp_userIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_userIdTxt.setColumns(10);
		emp_userIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		//Birthdate
		
		UtilDateModel birthdateModel = new UtilDateModel();
		Properties birthdate = new Properties();
		birthdate.put("text.today", "Date Today");
		birthdate.put("text.month", "Month");
		birthdate.put("text.year", "Year");
		birthdateModel.setDate(1980, 1, 1);

		JDatePanelImpl BirthdatePanel = new JDatePanelImpl(birthdateModel, birthdate);
		
		JLabel emp_LastnameLbl = new JLabel("Lastname:");
		emp_LastnameLbl.setForeground(Color.WHITE);
		emp_LastnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_LastnameLbl.setBounds(20, 108, 190, 41);
		tables_inputPanel.add(emp_LastnameLbl);
		
		emp_FirstnameTxt = new JTextField();
		emp_FirstnameTxt.setEditable(false);
		emp_FirstnameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_FirstnameTxt.setColumns(10);
		emp_FirstnameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_FirstnameTxt.setBounds(20, 203, 400, 23);
		tables_inputPanel.add(emp_FirstnameTxt);
		
		JLabel emp_FirstnameLbl = new JLabel("Firstname:");
		emp_FirstnameLbl.setForeground(Color.WHITE);
		emp_FirstnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_FirstnameLbl.setBounds(20, 168, 298, 41);
		tables_inputPanel.add(emp_FirstnameLbl);
		
		emp_LastnameTxt = new JTextField();
		emp_LastnameTxt.setEditable(false);
		emp_LastnameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_LastnameTxt.setColumns(10);
		emp_LastnameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_LastnameTxt.setBounds(20, 147, 400, 23);
		tables_inputPanel.add(emp_LastnameTxt);
		
		
		JLabel tables_primaryInformationLbl = new JLabel("----------------------------- Account Owner ------------------------------");
		tables_primaryInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_primaryInformationLbl.setForeground(Color.WHITE);
		tables_primaryInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_primaryInformationLbl.setBounds(20, 82, 400, 41);
		tables_inputPanel.add(tables_primaryInformationLbl);
		getContentPane().setLayout(null);
		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		JLabel label = new JLabel("");
		label.setBounds(1178, 48, 57, 37);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_allClientTransactionLbl = new JLabel("Registered Clients");
		tables_allClientTransactionLbl.setBounds(495, 158, 171, 37);
		tables_allClientTransactionLbl.setForeground(Color.WHITE);
		tables_allClientTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(label);
		getContentPane().add(tables_inputPanel);
		
		JButton tables_registerBtn = new JButton("Delete this User");
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "DELETE FROM jdl_accounts.employees WHERE user_id=?";
					String sql1 = "DELETE FROM jdl_accounts.users WHERE user_id=?";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					PreparedStatement statement1 = (PreparedStatement) conn.prepareStatement(sql1);
					
					if(emp_userIdTxt.getText().equals("")) {
						statement.setString(1, null);
						statement1.setString(1, null);
					}
					else {
						statement.setInt(1, Integer.parseInt(emp_userIdTxt.getText()));
						statement1.setInt(1, Integer.parseInt(emp_userIdTxt.getText()));
					}
					
					UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
				 	UIManager.put("Button.background", Color.WHITE);
				 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				 	
				 	if(emp_userIdTxt.getText().equals("")) {
				 		JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> No user is specified. Please select one. </font color = #ffffff></html>", "Error in Deletion", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 	else {
				 		int message = JOptionPane.showConfirmDialog(null, "<html><font color = #ffffff> Are you sure you want to delete this user?<br>This will prevent"
				 			+ " the user from accessing this system.</br></font color = #ffffff></html>", "Are you sure?", JOptionPane.YES_NO_OPTION);
				 			if (message == JOptionPane.YES_OPTION) {
				 				statement.executeUpdate();
				 				statement1.executeUpdate();
			    		
				 				JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> User Successfully deleted. </font color = #ffffff></html>", "Deleted Successfully", JOptionPane.INFORMATION_MESSAGE);
				 				dispose();
				 				new AccountDelete().setVisible(true);
			    	}
					
				} 
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		tables_registerBtn.setBounds(121, 259, 197, 36);
		tables_inputPanel.add(tables_registerBtn);

		
		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel emp_employeeDeleteLbl = new JLabel("Delete an Account");
		emp_employeeDeleteLbl.setBounds(158, 2, 168, 41);
		getContentPane().add(emp_employeeDeleteLbl);
		emp_employeeDeleteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		emp_employeeDeleteLbl.setForeground(Color.WHITE);
		emp_employeeDeleteLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel emp_close = new JLabel("");
		emp_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AccountManagement().setVisible(true);
				dispose();
			}
		});
		emp_close.setIcon(new ImageIcon(AccountDelete.class.getResource("/jdl/Assets/button_back.png")));
		emp_close.setBounds(10, 0, 26, 37);
		getContentPane().add(emp_close);
		
		JLabel emp_warningLbl = new JLabel("Note: This will also delete the account information associated in this account.");
		emp_warningLbl.setForeground(new Color(255, 255, 255));
		emp_warningLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		emp_warningLbl.setBounds(23, 64, 441, 21);
		getContentPane().add(emp_warningLbl);
		
		JLabel emp_background = new JLabel("New label");
		emp_background.setIcon(new ImageIcon(AccountDelete.class.getResource("/jdl/Assets/background_tables4.jpg")));
		emp_background.setBounds(0, 0, 488, 429);
		getContentPane().add(emp_background);
		

	}
}

