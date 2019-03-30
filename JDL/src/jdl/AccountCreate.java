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

public class AccountCreate extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private String clientSelectedName;
	private boolean tables_validator = true;
	private JTextField emp_passwordTxt;
	private JTextField emp_userIdTxt;
	private JTextField emp_usernameTxt;
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
	public AccountCreate() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Employee Management");
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
		
		JLabel emp_usernameLbl = new JLabel("Enter Username:");
		emp_usernameLbl.setForeground(Color.WHITE);
		emp_usernameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_usernameLbl.setBounds(20, 0, 231, 41);
		tables_inputPanel.add(emp_usernameLbl);
		
		JLabel emp_userIdLbl = new JLabel("User ID:");
		emp_userIdLbl.setForeground(Color.WHITE);
		emp_userIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_userIdLbl.setBounds(281, 0, 63, 41);
		tables_inputPanel.add(emp_userIdLbl);
		
		emp_userIdTxt = new JTextField();
		emp_userIdTxt.setBounds(284, 42, 136, 29);
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
		
		JLabel emp_LastnameLbl = new JLabel("Password:");
		emp_LastnameLbl.setForeground(Color.WHITE);
		emp_LastnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_LastnameLbl.setBounds(20, 108, 190, 41);
		tables_inputPanel.add(emp_LastnameLbl);
		
		JLabel emp_FirstnameLbl = new JLabel("Create this account as an Administrator?");
		emp_FirstnameLbl.setForeground(Color.WHITE);
		emp_FirstnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_FirstnameLbl.setBounds(20, 179, 298, 41);
		tables_inputPanel.add(emp_FirstnameLbl);
		
		emp_passwordTxt = new JTextField();
		emp_passwordTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_passwordTxt.setColumns(10);
		emp_passwordTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_passwordTxt.setBounds(20, 147, 400, 29);
		tables_inputPanel.add(emp_passwordTxt);
		
		
		JLabel tables_primaryInformationLbl = new JLabel("-------------------------- Sensitive Credentials ---------------------------");
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		comboBox.setBounds(20, 219, 400, 29);
		tables_inputPanel.add(comboBox);
		comboBox.addItem("No");
		comboBox.addItem("Yes");
		
		JButton tables_registerBtn = new JButton("Create this User");
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "INSERT INTO jdl_accounts.users (user_username, user_id, user_password, user_ifAdmin) values (?,?,?,?)";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					
					statement.setString(1, emp_usernameTxt.getText());
					statement.setInt(2, Integer.parseInt(emp_userIdTxt.getText()));
					statement.setString(3, emp_passwordTxt.getText());
					
					if(comboBox.getSelectedItem().toString()=="Yes") {
						statement.setInt(4, 1);
					}
					else
						statement.setInt(4, 0);
					
	
					UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
				 	UIManager.put("Button.background", Color.WHITE);
				 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				 	
				 	if(emp_userIdTxt.getText().equals("")) {
				 		JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> No USER ID was specified. Kindly fill out the fields. </font color = #ffffff></html>", "Error in Creation", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 	else if(emp_usernameTxt.getText().equals("")) {
				 		JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> No USERNAME was specified. Kindly fill out the fields. </font color = #ffffff></html>", "Error in Creation", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 	else if(emp_passwordTxt.getText().equals("")) {
				 		JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> No PASSWORD was specified. Kindly fill out the fields. </font color = #ffffff></html>", "Error in Creation", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 	else if(emp_usernameTxt.getText().length() <= 1) {
				 		JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> Acceptable usernames must be at least 2 characters and above. Please enter a valid one.  </font color = #ffffff></html>", "Error in Creation", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 	else if(emp_passwordTxt.getText().length()<=7 ) {
				 		JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> Acceptable Passwords must be at least 7 characters and above. Please lengthen your password. </font color = #ffffff></html>", "Error in Creation", JOptionPane.INFORMATION_MESSAGE);
				 	}
				 	else {
				 		int message = JOptionPane.showConfirmDialog(null, "<html><font color = #ffffff> Create this user? </br></font color = #ffffff></html>", "Are you sure?", JOptionPane.YES_NO_OPTION);
				 			if (message == JOptionPane.YES_OPTION) {
				 				statement.execute();
			    		
				 				JOptionPane.showMessageDialog(null, "<html><font color = #ffffff> User Successfully Created. </font color = #ffffff></html>", "Created Successfully", JOptionPane.INFORMATION_MESSAGE);
				 				dispose();
				 				new AccountCreate().setVisible(true);
							}
			    	}
					
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		tables_registerBtn.setBounds(121, 267, 197, 36);
		tables_inputPanel.add(tables_registerBtn);

		
		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		emp_usernameTxt = new JTextField();
		emp_usernameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_usernameTxt.setColumns(10);
		emp_usernameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_usernameTxt.setBounds(20, 42, 254, 29);
		tables_inputPanel.add(emp_usernameTxt);
		
		JLabel emp_employeeDeleteLbl = new JLabel("Create an Account");
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
		emp_close.setIcon(new ImageIcon(AccountCreate.class.getResource("/jdl/Assets/button_back.png")));
		emp_close.setBounds(10, 2, 26, 37);
		getContentPane().add(emp_close);
		
		JLabel emp_warningLbl = new JLabel("<html><center> Note: You may want to create an account information later <br> regarding to this account.</center> </br></html>");
		emp_warningLbl.setForeground(new Color(255, 204, 51));
		emp_warningLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
		emp_warningLbl.setBounds(79, 48, 343, 39);
		getContentPane().add(emp_warningLbl);
		
		JLabel emp_background = new JLabel("New label");
		emp_background.setIcon(new ImageIcon(AccountCreate.class.getResource("/jdl/Assets/background_tables4.jpg")));
		emp_background.setBounds(0, 0, 488, 429);
		getContentPane().add(emp_background);

	}
}

