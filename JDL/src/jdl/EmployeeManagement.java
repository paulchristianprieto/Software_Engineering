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

public class EmployeeManagement extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private JTextField emp_GenderTxt;
	private JTextField emp_AddressTxt;
	private JTextField emp_PositionTxt;
	private String clientSelectedName;
	private JTextField emp_FirstnameTxt;
	private boolean tables_validator = true;
	private JTextField emp_LastnameTxt;
	private JTextField emp_ContactTxt;
	private JTextField emp_EmailTxt;
	private JTable table_1;
	private JTextField emp_userIdTxt;
	private JTable table;
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
	public EmployeeManagement() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Employee Management");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(1422, 799));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		
		//Table
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(484, 132, 914, 284);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_1.setBounds(484, 468, 914, 297);
		getContentPane().add(scrollPane_1);
	
		table_1 = new JTable();
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setRowHeight(30);
		table_1.setBorder(null);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setBorder(null);
		
		JTableHeader header = table_1.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		scrollPane.setViewportView(table_1);

		JTableHeader header1 = table.getTableHeader();
		header1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header1.setBackground(new Color(155, 177, 166));
	    header1.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(22, 96, 441, 677);
		tables_inputPanel.setBackground(new Color (112, 128, 144));
		tables_inputPanel.setLayout(null);
		
		JButton tables_reloadBtn = new JButton("Reload");
		tables_reloadBtn.setBounds(1260, 82, 138, 38);
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					Statement stat=conn.createStatement();
					Statement stat1=conn.createStatement();
					
					ResultSet rs=stat.executeQuery("SELECT user_id AS 'User ID',"
							+ "user_username AS 'Username'" +
							", user_password AS 'Password' " + 
							", user_ifAdmin AS 'Is An Administrator' " + 
							" FROM jdl_accounts.users ORDER BY user_id DESC");
					
					ResultSet rs1=stat1.executeQuery("SELECT user_id AS 'User ID',"
							+ "emp_lastname AS 'Lastname'" +
							", emp_firstname AS 'Firstname' " + 
							", emp_position AS 'Position' " + 
							", emp_gender AS 'Gender' " + 
							", emp_birthdate AS 'Birthdate' " + 
							", emp_address AS 'Address' " + 
							", emp_contact AS 'Contact' " + 
							", emp_email AS 'Email' " + 
							" FROM jdl_accounts.employees ORDER BY user_id DESC");
			
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table_1);
					tca.adjustColumns();
					
					table.setModel(DbUtils.resultSetToTableModel(rs1));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca1 = new TableColumnAdjuster(table);
					tca1.adjustColumns();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		tables_reloadBtn.doClick();
		
		tables_reloadBtn.setBackground(new Color(155, 177, 166));
		tables_reloadBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		tables_reloadBtn.setBorder(null);
		tables_reloadBtn.setBorder(null);
		
		JPanel tables_titlePanel = new JPanel();
		tables_titlePanel.setBounds(0, 0, 1551, 37);
		tables_titlePanel.setBackground(new Color(126, 141, 151));
		tables_titlePanel.setLayout(null);
		
		//Images
		
		JLabel tables_minimize = new JLabel("");
		tables_minimize.setBounds(1381, 0, 35, 41);
		tables_titlePanel.add(tables_minimize);
		tables_minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		tables_minimize.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel tables_seeTablesLbl = new JLabel("Employee Management");
		tables_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tables_seeTablesLbl.setForeground(Color.WHITE);
		tables_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		tables_seeTablesLbl.setBounds(640, 0, 168, 41);
		tables_titlePanel.add(tables_seeTablesLbl);
		
		JLabel tables_back = new JLabel("");
		tables_back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				new OptionList().setVisible(true);
			}
		});
		tables_back.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_back.png")));
		tables_back.setHorizontalAlignment(SwingConstants.CENTER);
		tables_back.setForeground(Color.WHITE);
		tables_back.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		tables_back.setBounds(0, 0, 57, 37);
		tables_titlePanel.add(tables_back);
		
		//Input Section (Labels and Associated Textfields)
		
		JLabel tables_inputSectionLbl = new JLabel("Employee Details");
		tables_inputSectionLbl.setBounds(22, 54, 255, 37);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		
		JLabel emp_BirthdateLbl = new JLabel("Birthdate:");
		emp_BirthdateLbl.setForeground(new Color(255, 255, 255));
		emp_BirthdateLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_BirthdateLbl.setBounds(20, 393, 197, 29);
		tables_inputPanel.add(emp_BirthdateLbl);
		
		JComboBox emp_comboBox = new JComboBox();
		emp_comboBox.addItem("Select a registered username");
		emp_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "SELECT * FROM jdl_accounts.users WHERE user_username=?";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					
					statement.setString(1,(String)emp_comboBox.getSelectedItem().toString());
					ResultSet rs = statement.executeQuery();
					
					while (rs.next()) {
					emp_userIdTxt.setText(rs.getString("user_id"));				
				} 
				}catch (SQLException e1) {
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
		
		JLabel emp_assignLbl = new JLabel("Assign this employee information to:");
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
		emp_userIdTxt.setBounds(348, 42, 72, 29);
		tables_inputPanel.add(emp_userIdTxt);
		emp_userIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_userIdTxt.setColumns(10);
		emp_userIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JLabel lblEmployeeInformation = new JLabel("Employee Information:");
		lblEmployeeInformation.setForeground(Color.WHITE);
		lblEmployeeInformation.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblEmployeeInformation.setBounds(484, 418, 242, 49);
		getContentPane().add(lblEmployeeInformation);
		
		//Birthdate
		
		UtilDateModel birthdateModel = new UtilDateModel();
		Properties birthdate = new Properties();
		birthdate.put("text.today", "Date Today");
		birthdate.put("text.month", "Month");
		birthdate.put("text.year", "Year");
		birthdateModel.setDate(1980, 1, 1);

		JDatePanelImpl BirthdatePanel = new JDatePanelImpl(birthdateModel, birthdate);
		JDatePickerImpl birthdatePicker = new JDatePickerImpl(BirthdatePanel, new DateLabelFormatter());

		birthdatePicker.setLocation(20, 422);
		birthdatePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		birthdatePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		birthdatePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		birthdatePicker.setSize(400, 23);
		
		tables_inputPanel.add(birthdatePicker);
		
		JLabel emp_GenderLbl = new JLabel("Gender:");
		emp_GenderLbl.setForeground(new Color(255, 255, 255));
		emp_GenderLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_GenderLbl.setBounds(20, 336, 190, 29);
		tables_inputPanel.add(emp_GenderLbl);
		
		emp_GenderTxt = new JTextField();
		emp_GenderTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_GenderTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_GenderTxt.setColumns(10);
		emp_GenderTxt.setBounds(20, 367, 400, 23);
		tables_inputPanel.add(emp_GenderTxt);
		
		JLabel emp_AddressLbl = new JLabel("Address:");
		emp_AddressLbl.setForeground(Color.WHITE);
		emp_AddressLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_AddressLbl.setBounds(20, 447, 190, 29);
		tables_inputPanel.add(emp_AddressLbl);
		
		emp_AddressTxt = new JTextField();
		emp_AddressTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_AddressTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_AddressTxt.setColumns(10);
		emp_AddressTxt.setBounds(20, 476, 400, 23);
		tables_inputPanel.add(emp_AddressTxt);
		
		JLabel emp_PositionLbl = new JLabel("Position in the Company:");
		emp_PositionLbl.setForeground(Color.WHITE);
		emp_PositionLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_PositionLbl.setBounds(20, 282, 197, 29);
		tables_inputPanel.add(emp_PositionLbl);
		
		emp_PositionTxt = new JTextField();
		emp_PositionTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_PositionTxt.setColumns(10);
		emp_PositionTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_PositionTxt.setBounds(20, 312, 400, 23);
		tables_inputPanel.add(emp_PositionTxt);
		
		JLabel emp_LastnameLbl = new JLabel("Lastname:");
		emp_LastnameLbl.setForeground(Color.WHITE);
		emp_LastnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_LastnameLbl.setBounds(20, 108, 190, 41);
		tables_inputPanel.add(emp_LastnameLbl);
		
		emp_FirstnameTxt = new JTextField();
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
		emp_LastnameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_LastnameTxt.setColumns(10);
		emp_LastnameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_LastnameTxt.setBounds(20, 147, 400, 23);
		tables_inputPanel.add(emp_LastnameTxt);
		
		emp_ContactTxt = new JTextField();
		emp_ContactTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_ContactTxt.setColumns(10);
		emp_ContactTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_ContactTxt.setBounds(20, 530, 400, 23);
		tables_inputPanel.add(emp_ContactTxt);
		
		JLabel emp_ContactLbl = new JLabel("Contact No.:");
		emp_ContactLbl.setForeground(Color.WHITE);
		emp_ContactLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		emp_ContactLbl.setBounds(20, 501, 190, 29);
		tables_inputPanel.add(emp_ContactLbl);
		
		emp_EmailTxt = new JTextField();
		emp_EmailTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		emp_EmailTxt.setColumns(10);
		emp_EmailTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		emp_EmailTxt.setBounds(20, 584, 400, 23);
		tables_inputPanel.add(emp_EmailTxt);
		
		JLabel tables_clientEmailLbl = new JLabel("Email:");
		tables_clientEmailLbl.setForeground(Color.WHITE);
		tables_clientEmailLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientEmailLbl.setBounds(20, 556, 190, 29);
		tables_inputPanel.add(tables_clientEmailLbl);
		
		
		JLabel tables_primaryInformationLbl = new JLabel("-------------------------- Primary Information ---------------------------");
		tables_primaryInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_primaryInformationLbl.setForeground(Color.WHITE);
		tables_primaryInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_primaryInformationLbl.setBounds(20, 82, 400, 41);
		tables_inputPanel.add(tables_primaryInformationLbl);
		
		JLabel tables_secondaryInformationLbl = new JLabel("-------------------------- Secondary Information ------------------------");
		tables_secondaryInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_secondaryInformationLbl.setForeground(Color.WHITE);
		tables_secondaryInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_secondaryInformationLbl.setBounds(20, 237, 400, 41);
		tables_inputPanel.add(tables_secondaryInformationLbl);
		getContentPane().setLayout(null);
		
		JLabel tables_registeredClientsLbl = new JLabel("Registered User Accounts:");
		tables_registeredClientsLbl.setBounds(484, 85, 242, 49);
		tables_registeredClientsLbl.setForeground(Color.WHITE);
		tables_registeredClientsLbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		getContentPane().add(tables_registeredClientsLbl);
		
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
		getContentPane().add(tables_titlePanel);
		getContentPane().add(tables_inputSectionLbl);
		getContentPane().add(tables_reloadBtn);
		getContentPane().add(label);
		getContentPane().add(tables_inputPanel);
		
		JButton tables_registerBtn = new JButton("Register Employee Details");
		tables_registerBtn.setBounds(129, 625, 189, 41);
		tables_inputPanel.add(tables_registerBtn);
		
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
			 	UIManager.put("Button.background", Color.WHITE);
			 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				Connection conn2;
				
				try {
					String sql = "INSERT INTO jdl_accounts.employees (emp_lastname, emp_firstname, emp_position, emp_gender, emp_birthdate, emp_address, emp_contact, emp_email, user_id)"
							+ " values (?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE emp_lastname = ?, emp_firstname = ?, emp_position = ?, emp_gender = ?, emp_birthdate = ?, emp_address = ?, emp_contact = ?, emp_email = ?, user_id = ?";
					
					conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					PreparedStatement statement1 = conn2.prepareStatement(sql);
					
					if(emp_LastnameTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Employee's Lastname must not be empty.</font color = #ffffff></html>", "Detected an empty Employee's lastname", JOptionPane.ERROR_MESSAGE);
					}else if (emp_FirstnameTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Employee's Firstname must not be empty.</font color = #ffffff></html>", "Detected an empty Employee's firstname", JOptionPane.ERROR_MESSAGE);
					}else if(emp_PositionTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Employee's Position must not be empty.</font color = #ffffff></html>", "Detected an empty or undefinable company position ", JOptionPane.ERROR_MESSAGE);
					}else if(emp_GenderTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Employee's Gender must not be empty.</font color = #ffffff></html>", "Detected an empty or undefinable gender", JOptionPane.ERROR_MESSAGE);
					}else if(birthdatePicker.getJFormattedTextField().getText().toString().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Employee's Birthdate must not be empty.</font color = #ffffff></html>", "Detected an empty Employee's birthdate", JOptionPane.ERROR_MESSAGE);
					}else if(emp_ContactTxt.getText().equals("") || emp_EmailTxt.getText() .equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>There should at least one contact information available for an employee</font color = #ffffff></html>", "Detected an empty contact no. or email", JOptionPane.ERROR_MESSAGE);
					}else {
						statement1.setString(1, emp_LastnameTxt.getText());
						statement1.setString(2, emp_FirstnameTxt.getText());
						statement1.setString(3, emp_PositionTxt.getText());
						statement1.setString(4, emp_GenderTxt.getText());
						statement1.setDate(5, java.sql.Date.valueOf(birthdatePicker.getJFormattedTextField().getText().toString()));
						statement1.setString(6, emp_AddressTxt.getText());
						statement1.setString(7, emp_ContactTxt.getText());
						statement1.setString(8, emp_EmailTxt.getText());
						statement1.setString(9, emp_userIdTxt.getText());
						statement1.setString(10, emp_LastnameTxt.getText());
						statement1.setString(11, emp_FirstnameTxt.getText());
						statement1.setString(12, emp_PositionTxt.getText());
						statement1.setString(13, emp_GenderTxt.getText());
						statement1.setDate(14, java.sql.Date.valueOf(birthdatePicker.getJFormattedTextField().getText().toString()));
						statement1.setString(15, emp_AddressTxt.getText());
						statement1.setString(16, emp_ContactTxt.getText());
						statement1.setString(17, emp_EmailTxt.getText());
						statement1.setString(18, emp_userIdTxt.getText());
						
						statement1.executeUpdate();
						tables_inputPanel.revalidate();
					}
				}

				 catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	});
		
		tables_registerBtn.setBackground(new Color(255, 204, 51));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JButton emp_deleteBtn = new JButton("Delete");
		emp_deleteBtn.setIcon(new ImageIcon(EmployeeManagement.class.getResource("/jdl/Assets/button_delete.png")));
		emp_deleteBtn.setForeground(new Color(255, 255, 255));
		emp_deleteBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		emp_deleteBtn.setBorder(null);
		emp_deleteBtn.setBackground(new Color(255, 0, 51));
		emp_deleteBtn.setBounds(1112, 83, 138, 38);
		getContentPane().add(emp_deleteBtn);

	}
}

