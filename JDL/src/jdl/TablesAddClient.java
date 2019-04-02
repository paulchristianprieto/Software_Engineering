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

public class TablesAddClient extends JFrame{
	private JTextField tables_clientBirthdateTxt;
	private JTextField tables_clientPositionTxt;
	private String clientSelectedName;
	private JTextField tables_clientFirstnameTxt;
	private boolean tables_validator = true;
	private JTextField tables_clientLastnameTxt;
	private JTextField tables_clientAliasTxt;
	private JTextField tables_clientContactTxt;
	private JTextField tables_clientEmailTxt;
	private JTable table_1;
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
	public TablesAddClient() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Add Client");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(1550, 900));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		
		//Table
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(493, 208, 1040, 682);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		table_1 = new JTable();
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setBounds(495, 198, 125, 68);
		table_1.setRowHeight(32);
		table_1.setBorder(null);

		JTableHeader header = table_1.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		scrollPane.setViewportView(table_1);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(25, 169, 450, 721);
		tables_inputPanel.setBackground(new Color (255, 255, 255, 60));
		tables_inputPanel.setLayout(null);
		
		JButton tables_reloadBtn = new JButton("Reload");
		tables_reloadBtn.setBounds(1389, 159, 138, 38);
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					Statement stat=conn.createStatement();
					Statement stat1=conn.createStatement();
					
					ResultSet rs=stat.executeQuery("SELECT client_id AS 'Client ID',"
							+ "client_lastname AS 'Lastname'" +
							", client_firstname AS 'Firstname'" + 
							", client_alias AS 'Alias' " + 
							", client_nationality AS 'Nationality' " + 
							", client_gender AS 'Gender' " + 
							", client_company AS 'Company' " + 
							", client_position AS 'Company Position' " + 
							", client_contact AS 'Contact No.' " + 
							", client_email AS 'Email' " + 
							" FROM jdl_accounts.clients ORDER BY client_id DESC");
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table_1);
					tca.adjustColumns();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		tables_reloadBtn.doClick();
	
		JButton tables_orderByBtn = new JButton("Order By");
		tables_orderByBtn.setBounds(1241, 159, 138, 38);
		tables_orderByBtn.setForeground(new Color(255, 255, 255));
		tables_orderByBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		tables_orderByBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_sort.png")));
		tables_orderByBtn.setBackground(new Color(0, 102, 102));
		tables_orderByBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		tables_reloadBtn.setBackground(new Color(0, 102, 102));
		tables_reloadBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		tables_reloadBtn.setBorder(null);
		tables_reloadBtn.setBorder(null);
		
		//Input Section (Labels and Associated Textfields)
		
		JLabel tables_inputSectionLbl = new JLabel("Input Section");
		tables_inputSectionLbl.setBounds(25, 125, 255, 44);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		JLabel tables_clientNationalityLbl = new JLabel("Nationality:");
		tables_clientNationalityLbl.setForeground(new Color(255, 255, 255));
		tables_clientNationalityLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientNationalityLbl.setBounds(20, 260, 204, 29);
		tables_inputPanel.add(tables_clientNationalityLbl);
		
		JLabel tables_clientBirthdateLbl = new JLabel("Birthdate:");
		tables_clientBirthdateLbl.setForeground(new Color(255, 255, 255));
		tables_clientBirthdateLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientBirthdateLbl.setBounds(20, 317, 197, 29);
		tables_inputPanel.add(tables_clientBirthdateLbl);
		
		JComboBox tables_nationalityBox = new JComboBox();
		tables_nationalityBox.setEditable(true);
		tables_nationalityBox.addItem("");
		Connection conn1;
		try {
			
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
			Statement stat=conn1.createStatement();
			ResultSet rs1=stat.executeQuery("SELECT DISTINCT client_nationality FROM jdl_accounts.clients");
			 while(rs1.next()){        
				 	String nationality = rs1.getString("client_nationality");
				 	tables_nationalityBox.addItem(nationality);
			    }
			 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		tables_nationalityBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_nationalityBox.setBounds(20, 290, 400, 25);
		tables_inputPanel.add(tables_nationalityBox);	
			
			
		getContentPane().add(scrollPane);
		
		//Birthdate
		
		UtilDateModel birthdateModel = new UtilDateModel();
		Properties birthdate = new Properties();
		birthdate.put("text.today", "Date Today");
		birthdate.put("text.month", "Month");
		birthdate.put("text.year", "Year");
		birthdateModel.setDate(1980, 1, 1);

		JDatePanelImpl BirthdatePanel = new JDatePanelImpl(birthdateModel, birthdate);
		JDatePickerImpl birthdatePicker = new JDatePickerImpl(BirthdatePanel, new DateLabelFormatter());

		birthdatePicker.setLocation(20, 350);
		birthdatePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		birthdatePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		birthdatePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		birthdatePicker.setSize(400, 23);
		
		tables_inputPanel.add(birthdatePicker);
		
		JLabel tables_clientGenderLbl = new JLabel("Gender:");
		tables_clientGenderLbl.setForeground(new Color(255, 255, 255));
		tables_clientGenderLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientGenderLbl.setBounds(20, 373, 190, 29);
		tables_inputPanel.add(tables_clientGenderLbl);
		
		JLabel tables_clientCompanyLbl = new JLabel("Company:");
		tables_clientCompanyLbl.setForeground(Color.WHITE);
		tables_clientCompanyLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientCompanyLbl.setBounds(20, 428, 190, 29);
		tables_inputPanel.add(tables_clientCompanyLbl);
		
		JLabel tables_clientPositionLbl = new JLabel("Position in the Company:");
		tables_clientPositionLbl.setForeground(Color.WHITE);
		tables_clientPositionLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientPositionLbl.setBounds(20, 486, 197, 29);
		tables_inputPanel.add(tables_clientPositionLbl);
		
		tables_clientPositionTxt = new JTextField();
		tables_clientPositionTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientPositionTxt.setColumns(10);
		tables_clientPositionTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientPositionTxt.setBounds(20, 516, 400, 23);
		tables_inputPanel.add(tables_clientPositionTxt);
		
		JLabel tables_clientLastnameLbl = new JLabel("Lastname:");
		tables_clientLastnameLbl.setForeground(Color.WHITE);
		tables_clientLastnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientLastnameLbl.setBounds(20, 37, 190, 41);
		tables_inputPanel.add(tables_clientLastnameLbl);
		
		tables_clientFirstnameTxt = new JTextField();
		tables_clientFirstnameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientFirstnameTxt.setColumns(10);
		tables_clientFirstnameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientFirstnameTxt.setBounds(20, 134, 400, 23);
		tables_inputPanel.add(tables_clientFirstnameTxt);
		
		JLabel tables_clientFirstnameLbl = new JLabel("Firstname:");
		tables_clientFirstnameLbl.setForeground(Color.WHITE);
		tables_clientFirstnameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientFirstnameLbl.setBounds(20, 97, 298, 41);
		tables_inputPanel.add(tables_clientFirstnameLbl);
		
		tables_clientLastnameTxt = new JTextField();
		tables_clientLastnameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientLastnameTxt.setColumns(10);
		tables_clientLastnameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientLastnameTxt.setBounds(20, 76, 400, 23);
		tables_inputPanel.add(tables_clientLastnameTxt);
		
		tables_clientAliasTxt = new JTextField();
		tables_clientAliasTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientAliasTxt.setColumns(10);
		tables_clientAliasTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientAliasTxt.setBounds(20, 189, 400, 23);
		tables_inputPanel.add(tables_clientAliasTxt);
		
		JLabel tables_clientAliasLbl = new JLabel("Alias:");
		tables_clientAliasLbl.setForeground(Color.WHITE);
		tables_clientAliasLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientAliasLbl.setBounds(20, 159, 204, 29);
		tables_inputPanel.add(tables_clientAliasLbl);
		
		tables_clientContactTxt = new JTextField();
		tables_clientContactTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientContactTxt.setColumns(10);
		tables_clientContactTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientContactTxt.setBounds(20, 571, 400, 23);
		tables_inputPanel.add(tables_clientContactTxt);
		
		JLabel tables_clientContactLbl = new JLabel("Contact No.:");
		tables_clientContactLbl.setForeground(Color.WHITE);
		tables_clientContactLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientContactLbl.setBounds(20, 541, 190, 29);
		tables_inputPanel.add(tables_clientContactLbl);
		
		tables_clientEmailTxt = new JTextField();
		tables_clientEmailTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientEmailTxt.setColumns(10);
		tables_clientEmailTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientEmailTxt.setBounds(20, 626, 400, 23);
		tables_inputPanel.add(tables_clientEmailTxt);
		
		JLabel tables_clientEmailLbl = new JLabel("Email:");
		tables_clientEmailLbl.setForeground(Color.WHITE);
		tables_clientEmailLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_clientEmailLbl.setBounds(20, 596, 190, 29);
		tables_inputPanel.add(tables_clientEmailLbl);
		
		
		JLabel tables_primaryInformationLbl = new JLabel("-------------------------- Primary Information ---------------------------");
		tables_primaryInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_primaryInformationLbl.setForeground(Color.WHITE);
		tables_primaryInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_primaryInformationLbl.setBounds(20, 11, 400, 41);
		tables_inputPanel.add(tables_primaryInformationLbl);
		
		JLabel tables_secondaryInformationLbl = new JLabel("-------------------------- Secondary Information ------------------------");
		tables_secondaryInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_secondaryInformationLbl.setForeground(Color.WHITE);
		tables_secondaryInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_secondaryInformationLbl.setBounds(20, 223, 400, 41);
		tables_inputPanel.add(tables_secondaryInformationLbl);
		
		JLabel tables_registeredClientsLbl = new JLabel("Registered Clients");
		tables_registeredClientsLbl.setForeground(Color.WHITE);
		tables_registeredClientsLbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		tables_registeredClientsLbl.setBounds(495, 160, 255, 37);
		getContentPane().add(tables_registeredClientsLbl);
		
		JButton tables_registerBtn = new JButton("Register Client");
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_registerBtn.setBounds(131, 675, 173, 35);
		tables_inputPanel.add(tables_registerBtn);
		
		JLabel tables_clientCreateTransactionLbl = new JLabel("Create New Transaction", SwingConstants.CENTER);
		tables_clientCreateTransactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Tables().setVisible(true);
				dispose();
			}
		});
		
		tables_clientCreateTransactionLbl.setBounds(330, 48, 227, 37);
		tables_clientCreateTransactionLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientCreateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_clientStatusTableLbl = new JLabel("Client Status Table", SwingConstants.CENTER);
		tables_clientStatusTableLbl.setBounds(929, 48, 243, 37);
		tables_clientStatusTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new TablesStatus().setVisible(true);
				dispose();
			}
		});
		tables_clientStatusTableLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientStatusTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_clientRemarksTableLbl = new JLabel("Client Remarks Table", SwingConstants.CENTER);
		tables_clientRemarksTableLbl.setBounds(1241, 48, 230, 37);
		tables_clientRemarksTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new TablesRemarks().setVisible(true);
				dispose();
			}
		});
		tables_clientRemarksTableLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientRemarksTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel label = new JLabel("");
		label.setBounds(1178, 48, 57, 37);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_line = new JLabel("");
		tables_line.setBounds(142, 87, 57, 27);
		tables_line.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/line.png")));
		tables_line.setHorizontalAlignment(SwingConstants.CENTER);
		tables_line.setForeground(Color.WHITE);
		tables_line.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_updateTransactionLbl = new JLabel("Update Transaction", SwingConstants.CENTER);
		tables_updateTransactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TablesUpdateTransactions().setVisible(true);
				dispose();
			}
		});
		tables_updateTransactionLbl.setBounds(626, 48, 249, 37);
		tables_updateTransactionLbl.setForeground(Color.LIGHT_GRAY);
		tables_updateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_addClientLbl = new JLabel("Add New Client", SwingConstants.CENTER);
		tables_addClientLbl.setBounds(25, 48, 295, 37);
		tables_addClientLbl.setForeground(Color.WHITE);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_allClientTransactionLbl = new JLabel("Registered Clients");
		tables_allClientTransactionLbl.setBounds(495, 158, 171, 37);
		tables_allClientTransactionLbl.setForeground(Color.WHITE);
		tables_allClientTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		// Add to Panels 
		
		getContentPane().setLayout(null);
		getContentPane().add(tables_inputSectionLbl);
		getContentPane().add(tables_orderByBtn);
		getContentPane().add(tables_reloadBtn);
		getContentPane().add(tables_addClientLbl);
		getContentPane().add(tables_clientCreateTransactionLbl);
		getContentPane().add(tables_updateTransactionLbl);
		getContentPane().add(tables_clientStatusTableLbl);
		getContentPane().add(label);
		getContentPane().add(tables_clientRemarksTableLbl);
		getContentPane().add(tables_line);
		getContentPane().add(tables_inputPanel);
		
		JComboBox tables_genderBox = new JComboBox();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
		tables_genderBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
>>>>>>> parent of 20d4436... Upload now working but still under coding session.
=======
		tables_genderBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
>>>>>>> parent of 20d4436... Upload now working but still under coding session.
=======
		tables_genderBox.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
>>>>>>> parent of 20d4436... Upload now working but still under coding session.
		tables_genderBox.setBounds(20, 402, 400, 24);
		tables_inputPanel.add(tables_genderBox);
		tables_genderBox.setEditable(false);
		tables_genderBox.addItem("Male");
		tables_genderBox.addItem("Female");
		
		JComboBox tables_companyBox = new JComboBox();
		tables_companyBox.setBounds(20, 457, 400, 24);
		tables_inputPanel.add(tables_companyBox);
		tables_companyBox.setEditable(true);
		tables_companyBox.addItem("");
		getContentPane().add(scrollPane);
		AutoCompletion.enable(tables_companyBox);
		AutoCompletion.enable(tables_nationalityBox);
		
		Connection conn2;
		try {
			
			conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
			Statement stat=conn2.createStatement();
			ResultSet rs1=stat.executeQuery("SELECT DISTINCT client_company FROM jdl_accounts.clients");
			 while(rs1.next()){        
				 	String company = rs1.getString("client_company");
				 	tables_companyBox.addItem(company);
			    }
			 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		tables_nationalityBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_nationalityBox.setBounds(20, 290, 400, 25);
		tables_inputPanel.add(tables_nationalityBox);
		
		JLabel tables_back = new JLabel("");
		tables_back.setBounds(0, 0, 57, 37);
		getContentPane().add(tables_back);
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
		
		JLabel tables_seeTablesLbl = new JLabel("See Tables");
		tables_seeTablesLbl.setBounds(667, 4, 168, 37);
		getContentPane().add(tables_seeTablesLbl);
		tables_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tables_seeTablesLbl.setForeground(Color.WHITE);
		tables_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		//Images
		
		JLabel tables_minimize = new JLabel("");
		tables_minimize.setBounds(1505, 0, 35, 41);
		getContentPane().add(tables_minimize);
		tables_minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		tables_minimize.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel background_tables = new JLabel("New label");
		background_tables.setIcon(new ImageIcon(TablesAddClient.class.getResource("/jdl/Assets/background_tables4.jpg")));
		background_tables.setBounds(0, 0, 1551, 900);
		getContentPane().add(background_tables);
		
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
			 	UIManager.put("Button.background", Color.WHITE);
			 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				Connection conn2;
				
				try {
					String sql = "INSERT INTO jdl_accounts.clients (client_lastname, client_firstname, client_nationality, client_birthdate, client_gender, client_company, client_position, client_alias, client_contact, client_email)"
							+ " values (?,?,?,?,?,?,?,?,?,?)";
					
					conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					PreparedStatement statement1 = conn2.prepareStatement(sql);
					
					if(tables_clientLastnameTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Client's Lastname must not be empty.</font color = #ffffff></html>", "Detected an empty client's lastname", JOptionPane.ERROR_MESSAGE);
					}else if (tables_clientFirstnameTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Client's Firstname must not be empty.</font color = #ffffff></html>", "Detected an empty client's firstname", JOptionPane.ERROR_MESSAGE);
					}else if((tables_nationalityBox.getSelectedItem().toString()).equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Client's Nationality must not be empty.</font color = #ffffff></html>", "Detected an empty or undefinable nationality ", JOptionPane.ERROR_MESSAGE);
					}else if(birthdatePicker.getJFormattedTextField().getText().toString().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Client's Birthdate must not be empty.</font color = #ffffff></html>", "Detected an empty client's birthdate", JOptionPane.ERROR_MESSAGE);
					}else if(tables_genderBox.getSelectedItem().toString().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Client's Gender must not be empty.</font color = #ffffff></html>", "Detected an empty or undefinable gender", JOptionPane.ERROR_MESSAGE);
					}else if(tables_clientContactTxt.getText().equals("") || tables_clientEmailTxt.getText() .equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>There should at least one contact information available for a client</font color = #ffffff></html>", "Detected an empty contact no or email", JOptionPane.ERROR_MESSAGE);
					}else {
						statement1.setString(1, tables_clientLastnameTxt.getText());
						statement1.setString(2, tables_clientFirstnameTxt.getText());
						statement1.setString(3, tables_nationalityBox.getSelectedItem().toString());
						statement1.setDate(4, java.sql.Date.valueOf(birthdatePicker.getJFormattedTextField().getText().toString()));
						statement1.setString(5, tables_genderBox.getSelectedItem().toString());
						statement1.setString(6, tables_companyBox.getSelectedItem().toString());
						statement1.setString(7, tables_clientPositionTxt.getText());
						statement1.setString(8, tables_clientAliasTxt.getText());
						statement1.setString(9, tables_clientContactTxt.getText());
						statement1.setString(10, tables_clientEmailTxt.getText());
						statement1.executeUpdate();
						tables_inputPanel.revalidate();
					}
				}

				 catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	});
	
	}
<<<<<<< HEAD
=======
	
	
<<<<<<< HEAD
>>>>>>> parent of 20d4436... Upload now working but still under coding session.
=======
	
<<<<<<< HEAD
>>>>>>> parent of 20d4436... Upload now working but still under coding session.
=======
	
>>>>>>> parent of 20d4436... Upload now working but still under coding session.
}

