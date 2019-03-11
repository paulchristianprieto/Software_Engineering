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
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SpringLayout;

public class TablesStatusPermits extends JFrame{
	
	private JTextField tables_instructionsTxt;
	private JTextField tables_aepCancellationTxt;
	private String clientSelectedName;
	private JTextField tables_clientFirstNameTxt;
	private JTextField tables_clientIdTxt;
	private boolean tables_validator = true;
	private JTable table_1;
	private JTextField tables_acrReleaseTxt;
	private JTextField tables_aepDowngradingTxt;
	private JTextField tables_aepExitClearanceTxt;
	private JTextField tables_updatedVisaExtendTxt;
	private JTextField tables_documentationTxt;
	private JTextField tables_addReqTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tables window = new Tables();
					window.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TablesStatusPermits() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Status");
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
		scrollPane.setBounds(485, 410, 1040, 485);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(1036, 147);
		scrollPane_1.setLocation(493, 208);
		scrollPane.setBounds(493, 405, 1040, 485);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JTable table = new JTable();
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(32);
		table.setBorder(null);
		
		table_1 = new JTable();
		table_1.setRowHeight(32);
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setBorder(null);
		table_1.setBounds(492, 217, 1040, 138);
	
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JTableHeader header_1 = table_1.getTableHeader();
		header_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header_1.setBackground(new Color(155, 177, 166));
	    header_1.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section (Declaration of Panel) and Client_id Textfield
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(25, 174, 450, 716);
		tables_inputPanel.setBackground(new Color (112, 128, 144));
		tables_inputPanel.setLayout(null);
		
		
		tables_clientIdTxt = new JTextField();
		tables_clientIdTxt.setText("1");
		tables_clientIdTxt.setEditable(false);
		tables_clientIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientIdTxt.setColumns(10);
		tables_clientIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientIdTxt.setBounds(20, 188, 167, 23);
		tables_inputPanel.add(tables_clientIdTxt);
		
		//Buttons
		
		JComboBox tables_comboBox = new JComboBox();
		tables_comboBox.addItem("Select a registered client last name");
		
		
		JComboBox tables_comboBox1 = new JComboBox();
		
		Connection conn1;
		try {
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
			Statement stat=conn1.createStatement();
			ResultSet rs1=stat.executeQuery("SELECT * FROM jdl_accounts.clients");
			
			 while(rs1.next()){        
				 	String client_lastname = rs1.getString("client_lastname");
				 	rs1.getString("client_firstname");
			
			       	tables_comboBox.addItem(client_lastname);
			       	
			    }
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		tables_comboBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_comboBox.setBounds(20, 61, 400, 29);
		

		tables_comboBox1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_comboBox1.setBounds(197, 188, 223, 23);
		tables_inputPanel.add(tables_comboBox1);
		
		
		AutoCompletion.enable(tables_comboBox);
		tables_inputPanel.add(tables_comboBox);
		
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
					
					ResultSet rs=stat.executeQuery("SELECT client_id AS 'Client ID' "
							+ ", trans_transId AS 'Transaction ID' " + 
							", statusV_documentation AS 'Documentation'" +
							", statusV_dateFiled AS 'Date Filed' "+ 
							", statusV_immigrant AS 'Immigrant' " + 
							", statusV_earlyHearing AS 'Early Hearing Date' " + 
							", statusV_hearingDate AS 'Hearing Date' " + 
							", statusV_agenda AS 'Agenda' " + 
							", statusV_visaReleased AS 'Visa Released' " + 
							", statusV_waiverEcc AS 'Waiver/ECC Payment/ Others' " + 
							", statusV_acrIcard AS 'ACR I-card' " + 
							", statusV_docComplete AS 'Documentation Complete' " + 
							" FROM jdl_accounts.status_visa ORDER BY client_id");
	
					ResultSet rs1 = stat1.executeQuery("SELECT client_id AS 'Client ID' "
							+ ", trans_transId AS 'Transaction ID' " + 
							", statusV_documentation AS 'Documentation' " + 
							", statusV_dateFiled AS 'Date Filed'" + 
							", statusV_immigrant AS 'Immigrant'" + 
							", statusV_earlyHearing AS 'Early Hearing Date'" + 
							", statusV_hearingDate AS 'Hearing Date'" + 
							", statusV_agenda AS 'Agenda'" + 
							", statusV_visaReleased AS 'Visa Released'" + 
							", statusV_waiverEcc AS 'Waiver/ECC Payment/ Others'" + 
							", statusV_acrIcard AS 'ACR I-card'" + 
							", statusV_docComplete AS 'Documentation Complete'" + 
							" FROM jdl_accounts.status_visa WHERE client_id = "+Integer.parseInt(tables_clientIdTxt.getText())+" ORDER BY trans_transId DESC");
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table);
					tca.adjustColumns();
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs1));
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
					tca1.adjustColumns();

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
		tables_orderByBtn.setBackground(new Color(155, 177, 166));
		tables_orderByBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
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
		tables_minimize.setBounds(1516, 0, 35, 41);
		tables_titlePanel.add(tables_minimize);
		tables_minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		tables_minimize.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel tables_seeTablesLbl = new JLabel("See Tables");
		tables_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tables_seeTablesLbl.setForeground(Color.WHITE);
		tables_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		tables_seeTablesLbl.setBounds(739, 0, 168, 37);
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
		
		JLabel tables_inputSectionLbl = new JLabel("Input Section:");
		tables_inputSectionLbl.setBounds(27, 125, 132, 37);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		JLabel tables_dateReceivedLbl = new JLabel("Date Received:");
		tables_dateReceivedLbl.setForeground(new Color(255, 255, 255));
		tables_dateReceivedLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_dateReceivedLbl.setBounds(20, 235, 131, 54);
		tables_inputPanel.add(tables_dateReceivedLbl);
		
		tables_instructionsTxt = new JTextField();
		tables_instructionsTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_instructionsTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_instructionsTxt.setBounds(227, 279, 197, 23);
		tables_inputPanel.add(tables_instructionsTxt);
		tables_instructionsTxt.setColumns(10);
		
		JLabel tables_instructionsLbl = new JLabel("Instructions:");
		tables_instructionsLbl.setForeground(new Color(255, 255, 255));
		tables_instructionsLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_instructionsLbl.setBounds(227, 248, 197, 29);
		tables_inputPanel.add(tables_instructionsLbl);
		
		JLabel tables_aepCancellationLbl = new JLabel("AEP Cancellation:");
		tables_aepCancellationLbl.setForeground(new Color(255, 255, 255));
		tables_aepCancellationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepCancellationLbl.setBounds(20, 303, 190, 37);
		tables_inputPanel.add(tables_aepCancellationLbl);
		
		tables_aepCancellationTxt = new JTextField();
		tables_aepCancellationTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_aepCancellationTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_aepCancellationTxt.setColumns(10);
		tables_aepCancellationTxt.setBounds(20, 338, 197, 23);
		tables_inputPanel.add(tables_aepCancellationTxt);
		
		JLabel tables_aepDowngradingLbl = new JLabel("AEP Downgrading:");
		tables_aepDowngradingLbl.setForeground(new Color(255, 255, 255));
		tables_aepDowngradingLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepDowngradingLbl.setBounds(227, 307, 190, 29);
		tables_inputPanel.add(tables_aepDowngradingLbl);
		
		//Date Input
		
		//Date Received
		UtilDateModel dateReceivedModel = new UtilDateModel();
		Properties dateReceived = new Properties();
		dateReceived.put("text.today", "Date Today");
		dateReceived.put("text.month", "Month");
		dateReceived.put("text.year", "Year");
		
		JDatePanelImpl dateReceivedPanel = new JDatePanelImpl(dateReceivedModel, dateReceived);

		JDatePickerImpl dateReceivedPicker = new JDatePickerImpl(dateReceivedPanel, new DateLabelFormatter());

		dateReceivedPicker.setLocation(20, 279);
		dateReceivedPicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		dateReceivedPicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		dateReceivedPicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		dateReceivedPicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		dateReceivedPicker.setSize(197, 23);

		tables_inputPanel.add(dateReceivedPicker);
		
		//Date Filed
		UtilDateModel dateFiledModel = new UtilDateModel();
		Properties dateFiled = new Properties();
		dateFiled.put("text.today", "Date Today");
		dateFiled.put("text.month", "Month");
		dateFiled.put("text.year", "Year");
				
		JDatePanelImpl dateFiledPanel = new JDatePanelImpl(dateFiledModel, dateFiled);

		JDatePickerImpl dateFiledPicker = new JDatePickerImpl(dateFiledPanel, new DateLabelFormatter());

		dateFiledPicker.setLocation(20, 507);
		dateFiledPicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		dateFiledPicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		dateFiledPicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		dateFiledPicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		dateFiledPicker.setSize(197, 23);

		tables_inputPanel.add(dateFiledPicker);
		
		//Date Released
		UtilDateModel aepDateReleasedModel = new UtilDateModel();
		Properties aepDateReleased = new Properties();
		aepDateReleased.put("text.today", "Date Today");
		aepDateReleased.put("text.month", "Month");
		aepDateReleased.put("text.year", "Year");
		
		JDatePanelImpl aepReleasedDatePanel = new JDatePanelImpl(aepDateReleasedModel, aepDateReleased);

		JDatePickerImpl aepDateReleasePicker = new JDatePickerImpl(aepReleasedDatePanel, new DateLabelFormatter());

		aepDateReleasePicker.setLocation(227, 507);
		aepDateReleasePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		aepDateReleasePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		aepDateReleasePicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		aepDateReleasePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		aepDateReleasePicker.setSize(200, 23);

		tables_inputPanel.add(aepDateReleasePicker);
		
		//Permit Date Filed
		UtilDateModel permitDateFiledModel = new UtilDateModel();
		Properties permitDateFiledReleased = new Properties();
		permitDateFiledReleased.put("text.today", "Date Today");
		permitDateFiledReleased.put("text.month", "Month");
		permitDateFiledReleased.put("text.year", "Year");
		
		JDatePanelImpl permitDateFiledPanel = new JDatePanelImpl(permitDateFiledModel, permitDateFiledReleased);

		JDatePickerImpl permitDateFiledPicker = new JDatePickerImpl(permitDateFiledPanel, new DateLabelFormatter());

		permitDateFiledPicker.setLocation(20, 560);
		permitDateFiledPicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		permitDateFiledPicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		permitDateFiledPicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		permitDateFiledPicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		permitDateFiledPicker.setSize(200, 23);

		tables_inputPanel.add(permitDateFiledPicker);
		
		//Permit Date Released
		UtilDateModel permitDateReleasedModel = new UtilDateModel();
		Properties permitDateReleasedReleased = new Properties();
		permitDateReleasedReleased.put("text.today", "Date Today");
		permitDateReleasedReleased.put("text.month", "Month");
		permitDateReleasedReleased.put("text.year", "Year");
		
		JDatePanelImpl permitDateReleasedPanel = new JDatePanelImpl(permitDateReleasedModel, permitDateReleasedReleased);

		JDatePickerImpl permitDateReleasedPicker = new JDatePickerImpl(permitDateReleasedPanel, new DateLabelFormatter());

		permitDateReleasedPicker.setLocation(227, 560);
		permitDateReleasedPicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		permitDateReleasedPicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		permitDateReleasedPicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		permitDateReleasedPicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		permitDateReleasedPicker.setSize(200, 23);

		tables_inputPanel.add(permitDateReleasedPicker);
		
		//Input Section (Labels)
		
		JLabel tables_aepDateReleaseLbl = new JLabel("AEP Date Released:");
		tables_aepDateReleaseLbl.setForeground(Color.WHITE);
		tables_aepDateReleaseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepDateReleaseLbl.setBounds(230, 476, 192, 29);
		tables_inputPanel.add(tables_aepDateReleaseLbl);
		
		JLabel tables_agendaLbl = new JLabel("Permit Date Filed:");
		tables_agendaLbl.setForeground(Color.WHITE);
		tables_agendaLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_agendaLbl.setBounds(20, 529, 190, 31);
		tables_inputPanel.add(tables_agendaLbl);
		
		JLabel tables_visaReleaseLbl = new JLabel("Permit Date Released:");
		tables_visaReleaseLbl.setForeground(Color.WHITE);
		tables_visaReleaseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaReleaseLbl.setBounds(230, 531, 197, 29);
		tables_inputPanel.add(tables_visaReleaseLbl);
		
		JLabel tables_chooseLbl = new JLabel("Choose a client's Lastname:");
		tables_chooseLbl.setForeground(Color.WHITE);
		tables_chooseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_chooseLbl.setBounds(20, 34, 190, 18);
		tables_inputPanel.add(tables_chooseLbl);
		

		tables_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "SELECT * FROM jdl_accounts.clients WHERE client_lastname=?";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					
					statement.setString(1,(String)tables_comboBox.getSelectedItem().toString());
					ResultSet rs = statement.executeQuery();
					
					while (rs.next()) {
					tables_clientIdTxt.setText(rs.getString("client_id"));
					tables_clientFirstNameTxt.setText(rs.getString("client_firstname"));
					
				} 
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				Connection conn2;
				try {
					conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					Statement stat1=conn2.createStatement();
					ResultSet rs2=stat1.executeQuery("SELECT * FROM jdl_accounts.transactions WHERE client_id ="+tables_clientIdTxt.getText());
					
					tables_comboBox1.removeAllItems();
			       	
					while(rs2.next()){        
						 	String transactions = rs2.getString("trans_transId");
					       	tables_comboBox1.addItem(transactions);
					       	
					       	clientSelectedName = tables_comboBox.getSelectedItem().toString();
					    }
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel tables_idLbl = new JLabel("Client ID:");
		tables_idLbl.setForeground(Color.WHITE);
		tables_idLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_idLbl.setBounds(20, 156, 89, 29);
		tables_inputPanel.add(tables_idLbl);
		
		tables_clientFirstNameTxt = new JTextField();
		tables_clientFirstNameTxt.setEditable(false);
		tables_clientFirstNameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientFirstNameTxt.setColumns(10);
		tables_clientFirstNameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientFirstNameTxt.setBounds(20, 123, 397, 23);
		tables_inputPanel.add(tables_clientFirstNameTxt);
		
		JLabel lblClientsFirstName = new JLabel("Client's First Name:");
		lblClientsFirstName.setForeground(Color.WHITE);
		lblClientsFirstName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblClientsFirstName.setBounds(20, 90, 298, 41);
		tables_inputPanel.add(lblClientsFirstName);
		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		JLabel tables_clientInformationLbl = new JLabel("------------------------------- Client Selection ------------------------------");
		tables_clientInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientInformationLbl.setForeground(Color.WHITE);
		tables_clientInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientInformationLbl.setBounds(20, 11, 411, 23);
		tables_inputPanel.add(tables_clientInformationLbl);
		
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
		tables_clientStatusTableLbl.setForeground(Color.WHITE);
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
		tables_line.setBounds(1023, 96, 57, 22);
		tables_line.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/line.png")));
		tables_line.setHorizontalAlignment(SwingConstants.CENTER);
		tables_line.setForeground(Color.WHITE);
		tables_line.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_updateTransactionLbl = new JLabel("Update Transaction", SwingConstants.CENTER);
		tables_updateTransactionLbl.setBounds(626, 48, 249, 37);
		tables_updateTransactionLbl.setForeground(Color.LIGHT_GRAY);
		tables_updateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_addClientLbl = new JLabel("Add New Client", SwingConstants.CENTER);
		tables_addClientLbl.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				new TablesAddClient().setVisible(true);
				dispose();
			}
		});
		tables_addClientLbl.setBounds(25, 48, 295, 37);
		tables_addClientLbl.setForeground(Color.LIGHT_GRAY);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_allClientTransactionLbl = new JLabel("All Recorded Client Status (For Permit Filing)");
		tables_allClientTransactionLbl.setBounds(493, 362, 412, 37);
		tables_allClientTransactionLbl.setForeground(Color.WHITE);
		tables_allClientTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JLabel lblSpecificClient = new JLabel("Specific Client Status (For Permit Filing)");
		lblSpecificClient.setBounds(493, 169, 402, 37);
		lblSpecificClient.setForeground(Color.WHITE);
		lblSpecificClient.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		// Add to Panels 
		
		getContentPane().setLayout(null);
		getContentPane().add(tables_titlePanel);
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
		
		JLabel tables_acrReleaseLbl = new JLabel("ACR-I Card Release:");
		tables_acrReleaseLbl.setForeground(Color.WHITE);
		tables_acrReleaseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_acrReleaseLbl.setBounds(20, 585, 190, 29);
		tables_inputPanel.add(tables_acrReleaseLbl);
		
		tables_acrReleaseTxt = new JTextField();
		tables_acrReleaseTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_acrReleaseTxt.setColumns(10);
		tables_acrReleaseTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_acrReleaseTxt.setBounds(20, 613, 407, 23);
		tables_inputPanel.add(tables_acrReleaseTxt);
		
		JLabel lblSelectATransaction = new JLabel("Select Transaction ID:");
		lblSelectATransaction.setForeground(Color.WHITE);
		lblSelectATransaction.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblSelectATransaction.setBounds(197, 153, 146, 37);
		tables_inputPanel.add(lblSelectATransaction);

		JButton tables_registerBtn = new JButton("Insert Status");
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn3;
				try {
					String sql = "INSERT INTO jdl_accounts.status_visa (statusV_documentation, statusV_dateFiled, statusV_immigrant, statusV_earlyHearing, statusV_hearingDate, statusV_agenda, statusV_visaReleased, statusV_waiverECC, statusV_acrIcard, "
							+ "statusV_docComplete, client_id, trans_transId)  values (?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE statusV_documentation = ?, statusV_dateFiled = ?, statusV_immigrant = ?, statusV_earlyHearing = ?, statusV_hearingDate = ?, statusV_agenda = ?, statusV_visaReleased = ?, statusV_waiverECC = ?, statusV_acrIcard = ?," 
							+ "statusV_docComplete = ?, client_id = ?, trans_transId = ? ";
					conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&compensateOnDuplicateKeyUpdateCounts=false","root","password");
					PreparedStatement statement2= conn3.prepareStatement(sql);
					
					statement2.setString(1, tables_instructionsTxt.getText());
					
					if(dateReceivedPicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(2, null);
					else
						statement2.setDate(2, java.sql.Date.valueOf(dateReceivedPicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(3, tables_aepCancellationTxt.getText());
					
					if(tables_permitDateFiledTxt.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(4, null);
					else
						statement2.setDate(4, java.sql.Date.valueOf(tables_permitDateFiledTxt.getJFormattedTextField().getText().toString()));
					
					if(aepDateReleasePicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(5, null);
					else
						statement2.setDate(5, java.sql.Date.valueOf(aepDateReleasePicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(6, tables_agendaTxt.getText());
					statement2.setString(7, tables_visaReleaseTxt.getText());
					statement2.setString(8, tables_waiverEccTxt.getText());
					statement2.setString(9, tables_acrReleaseTxt.getText());
					statement2.setString(10, tables_documentationCompleteTxt.getText());
					statement2.setString(11, tables_clientIdTxt.getText());
					statement2.setString(12, tables_comboBox1.getSelectedItem().toString());
					statement2.setString(13, tables_instructionsTxt.getText());
					
					if(dateReceivedPicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(14, null);
					else
						statement2.setDate(14, java.sql.Date.valueOf(dateReceivedPicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(15, tables_aepCancellationTxt.getText());
					
					if(tables_permitDateFiledTxt.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(16, null);
					else
						statement2.setDate(16, java.sql.Date.valueOf(tables_permitDateFiledTxt.getJFormattedTextField().getText().toString()));
					
					if(aepDateReleasePicker.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(17, null);
					else
						statement2.setDate(17, java.sql.Date.valueOf(aepDateReleasePicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(18, tables_agendaTxt.getText());
					statement2.setString(19, tables_visaReleaseTxt.getText());
					statement2.setString(20, tables_waiverEccTxt.getText());
					statement2.setString(21, tables_acrReleaseTxt.getText());
					statement2.setString(22, tables_documentationCompleteTxt.getText());
					statement2.setString(23, tables_clientIdTxt.getText());
					statement2.setString(24, tables_comboBox1.getSelectedItem().toString());
					
					statement2.executeUpdate();
					tables_inputPanel.revalidate();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tables_registerBtn.setBounds(128, 664, 175, 41);
		tables_inputPanel.add(tables_registerBtn);
		

		tables_registerBtn.setBackground(new Color(255, 204, 51));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel tables_clientDetailsLbl = new JLabel("-------------------------------- Client Details -----------------------------");
		tables_clientDetailsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientDetailsLbl.setForeground(Color.WHITE);
		tables_clientDetailsLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientDetailsLbl.setBounds(20, 222, 411, 23);
		tables_inputPanel.add(tables_clientDetailsLbl);
		
		tables_aepDowngradingTxt = new JTextField();
		tables_aepDowngradingTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_aepDowngradingTxt.setColumns(10);
		tables_aepDowngradingTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_aepDowngradingTxt.setBounds(227, 339, 197, 23);
		tables_inputPanel.add(tables_aepDowngradingTxt);
		
		JLabel tables_aepExitClearance = new JLabel("AEP Exit Clearance:");
		tables_aepExitClearance.setForeground(Color.WHITE);
		tables_aepExitClearance.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepExitClearance.setBounds(20, 362, 192, 37);
		tables_inputPanel.add(tables_aepExitClearance);
		
		tables_aepExitClearanceTxt = new JTextField();
		tables_aepExitClearanceTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_aepExitClearanceTxt.setColumns(10);
		tables_aepExitClearanceTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_aepExitClearanceTxt.setBounds(20, 396, 197, 23);
		tables_inputPanel.add(tables_aepExitClearanceTxt);
		
		tables_updatedVisaExtendTxt = new JTextField();
		tables_updatedVisaExtendTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_updatedVisaExtendTxt.setColumns(10);
		tables_updatedVisaExtendTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_updatedVisaExtendTxt.setBounds(227, 396, 197, 23);
		tables_inputPanel.add(tables_updatedVisaExtendTxt);
		
		JLabel tables_updatedVisaExtendLbl = new JLabel("Updated Visa Extend:");
		tables_updatedVisaExtendLbl.setForeground(Color.WHITE);
		tables_updatedVisaExtendLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_updatedVisaExtendLbl.setBounds(228, 362, 192, 37);
		tables_inputPanel.add(tables_updatedVisaExtendLbl);
		
		JLabel tables_documentationLbl = new JLabel("Documentation");
		tables_documentationLbl.setForeground(Color.WHITE);
		tables_documentationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_documentationLbl.setBounds(20, 422, 197, 26);
		tables_inputPanel.add(tables_documentationLbl);
		
		tables_documentationTxt = new JTextField();
		tables_documentationTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_documentationTxt.setColumns(10);
		tables_documentationTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_documentationTxt.setBounds(20, 452, 197, 23);
		tables_inputPanel.add(tables_documentationTxt);
		
		tables_addReqTxt = new JTextField();
		tables_addReqTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_addReqTxt.setColumns(10);
		tables_addReqTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_addReqTxt.setBounds(227, 452, 197, 23);
		tables_inputPanel.add(tables_addReqTxt);
		
		JLabel tables_addReqLbl = new JLabel("Add Requirements:");
		tables_addReqLbl.setForeground(Color.WHITE);
		tables_addReqLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_addReqLbl.setBounds(227, 422, 197, 26);
		tables_inputPanel.add(tables_addReqLbl);
		
		JLabel tables_permitDateFiledLbl = new JLabel("AEP Date Filed:");
		tables_permitDateFiledLbl.setForeground(Color.WHITE);
		tables_permitDateFiledLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitDateFiledLbl.setBounds(20, 476, 192, 29);
		tables_inputPanel.add(tables_permitDateFiledLbl);
		getContentPane().add(tables_allClientTransactionLbl);
		getContentPane().add(scrollPane);
		getContentPane().add(scrollPane_1);
		getContentPane().add(lblSpecificClient);
		
		JButton btnVisa = new JButton("Visa");
		btnVisa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TablesStatus().setVisible(true);
				dispose();
			}
		});
		btnVisa.setForeground(Color.WHITE);
		btnVisa.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnVisa.setBorder(null);
		btnVisa.setBackground(new Color(155, 177, 166));
		btnVisa.setBounds(169, 125, 86, 38);
		getContentPane().add(btnVisa);
		
		JButton btnPermit = new JButton("Permits");
		btnPermit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPermit.setForeground(Color.WHITE);
		btnPermit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnPermit.setBorder(null);
		btnPermit.setBackground(new Color(255, 153, 0));
		btnPermit.setBounds(265, 125, 86, 38);
		getContentPane().add(btnPermit);
	}
}

