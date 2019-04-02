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

public class TablesStatus extends JFrame{
	
	private JTextField tables_documentationTxt;
	private JTextField tables_immigrantTxt;
	private JTextField tables_agendaTxt;
	private JTextField tables_visaReleaseTxt;
	private String clientSelectedName;
	private boolean tables_validator = true;
	private JTable table_1;
	private JTextField tables_waiverEccTxt;
	private JTextField tables_acrReleaseTxt;
	private JTextField tables_documentationCompleteTxt;
	private String client_id = "";
	private String client_name = "";
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
	public TablesStatus() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Status");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(1550, 850));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(1036, 280);
		scrollPane_1.setLocation(491, 209);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(495, 532, 1036, 307);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(32);
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setBorder(null);
		table.setBounds(495, 541, 1036, 298);
		scrollPane.setViewportView(table);
		
		table_1 = new JTable();
		table_1.setRowHeight(32);
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setBorder(null);
		table_1.setBounds(492, 217, 1040, 138);
		
		JTableHeader header_1 = table_1.getTableHeader();
		header_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header_1.setBackground(new Color(155, 177, 166));
	    header_1.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		JButton tables_reloadBtn = new JButton("Reload");
		tables_reloadBtn.setBounds(1389, 159, 138, 38);
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		
		//Input Section (Declaration of Panel) and Client_id Textfield
		
		JLabel tables_clientTransactionsLbl = new JLabel("Client Transactions");
		tables_clientTransactionsLbl.setForeground(Color.WHITE);
		tables_clientTransactionsLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tables_clientTransactionsLbl.setBounds(495, 492, 543, 37);
		getContentPane().add(tables_clientTransactionsLbl);
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(25, 174, 450, 670);
		tables_inputPanel.setBackground(new Color (255, 255, 255, 60));
		tables_inputPanel.setLayout(null);
		
		JComboBox tables_comboBox1 = new JComboBox();
		tables_comboBox1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_comboBox1.setBounds(20, 132, 407, 25);
		tables_inputPanel.add(tables_comboBox1);
	
		
		JComboBox tables_comboBox = new JComboBox();
		tables_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				Connection conn2;
				
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "SELECT * FROM jdl_accounts.clients WHERE client_id=?";
					String sql2 = "SELECT * FROM jdl_accounts.transactions WHERE client_id=?";
					String sql3 = "SELECT client_id AS 'Client ID' "
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
							" FROM jdl_accounts.status_visa WHERE client_id = ? ORDER BY trans_transId DESC";
					PreparedStatement statement = conn.prepareStatement(sql);
					PreparedStatement statement3 = conn.prepareStatement(sql2);
					PreparedStatement statement4 = conn.prepareStatement(sql3);
					
					String info = (String)tables_comboBox.getSelectedItem().toString();
					
					int temp = Integer.parseInt(info.substring(info.lastIndexOf(",")+2, info.length()));
					
					client_id = String.valueOf(temp);
					
					statement.setInt(1, temp);
					statement3.setInt(1, temp);
					statement4.setInt(1, temp);
					
					tables_comboBox1.removeAllItems();
					
					ResultSet rs = statement.executeQuery();
					
					 while(rs.next()) {
						 tables_clientTransactionsLbl.setText(info.substring(0, info.lastIndexOf(","))+" Transactions");
						}
					
					ResultSet rs1 = statement3.executeQuery();
						
						 while(rs1.next()){        
						       	tables_comboBox1.addItem(rs1.getString("trans_transId"));       
								    }
						 
					ResultSet rs2 = statement4.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs2));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table);
					tca.adjustColumns();
						 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				tables_reloadBtn.doClick();
			}
		});
			
		Connection conn1;
		try {
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
			Statement stat=conn1.createStatement();
			ResultSet rs1=stat.executeQuery("SELECT * FROM jdl_accounts.clients");
			 while(rs1.next()){        
				 	String client_lastname = rs1.getString("client_lastname");
				 	String client_firstname = rs1.getString("client_firstname");
				 	client_id = rs1.getString("client_id");
			
			       	tables_comboBox.addItem(client_lastname+", "+client_firstname+", "+client_id);
			       
			       	clientSelectedName = tables_comboBox.getSelectedItem().toString();
			       	
			    }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		tables_comboBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		tables_comboBox.setBounds(20, 75, 407, 25);
		
		AutoCompletion.enable(tables_comboBox);
		tables_inputPanel.add(tables_comboBox);
		
		tables_comboBox1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_comboBox1.setBounds(20, 132, 407, 25);
		tables_inputPanel.add(tables_comboBox1);
		
	
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					Statement stat1=conn.createStatement();
	
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
							" FROM jdl_accounts.status_visa WHERE client_id ="+client_id+" ORDER BY trans_transId DESC");
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs1));
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
					tca1.adjustColumns();
					
					Statement stat2=conn.createStatement();
					
					ResultSet rs2 = stat2.executeQuery("SELECT client_id AS 'Client ID',"
							+ "trans_transId AS 'Transaction ID'" +
							",trans_passportNo AS 'Passport No' "+ 
							", trans_tinID AS 'TIN ID' " + 
							", trans_visaType AS 'Visa Type' " + 
							", trans_visaStartDate AS 'Visa Start Date' " + 
							", trans_visaEndDate AS 'Visa Expiry Date' " + 
							", trans_permitType AS 'Permit Type' " + 
							", trans_permitStartDate AS 'Permit Start Date' " + 
							", trans_permitEndDate AS 'Permit Expiry Date' " + 
							", trans_aepID AS 'AEP ID' " + 
							", trans_aepStartDate AS 'AEP Start Date' " + 
							", trans_aepEndDate AS 'AEP Expiry Date' " + 
							" FROM jdl_accounts.transactions WHERE client_id ="+Integer.parseInt(client_id)+" ORDER BY trans_transId DESC");
					
					table.setModel(DbUtils.resultSetToTableModel(rs2));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table);
					tca.adjustColumns();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		tables_reloadBtn.doClick();
		
		tables_reloadBtn.setBackground(new Color(0, 102, 102));
		tables_reloadBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		tables_reloadBtn.setBorder(null);
		tables_reloadBtn.setBorder(null);
		
		//Input Section (Labels and Associated Textfields)
		
		JLabel tables_inputSectionLbl = new JLabel("Input Section:");
		tables_inputSectionLbl.setBounds(27, 125, 132, 37);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		JLabel tables_documentationLbl = new JLabel("Documentation:");
		tables_documentationLbl.setForeground(new Color(255, 255, 255));
		tables_documentationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_documentationLbl.setBounds(20, 187, 131, 54);
		tables_inputPanel.add(tables_documentationLbl);
		
		tables_documentationTxt = new JTextField();
		tables_documentationTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_documentationTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_documentationTxt.setBounds(20, 234, 407, 23);
		tables_inputPanel.add(tables_documentationTxt);
		tables_documentationTxt.setColumns(10);
		
		JLabel tables_dateFiledLbl = new JLabel("Date Filed:");
		tables_dateFiledLbl.setForeground(new Color(255, 255, 255));
		tables_dateFiledLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_dateFiledLbl.setBounds(20, 261, 197, 29);
		tables_inputPanel.add(tables_dateFiledLbl);
		
		JLabel tables_immigrantLbl = new JLabel("Immigrant:");
		tables_immigrantLbl.setForeground(new Color(255, 255, 255));
		tables_immigrantLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_immigrantLbl.setBounds(20, 317, 190, 29);
		tables_inputPanel.add(tables_immigrantLbl);
		
		tables_immigrantTxt = new JTextField();
		tables_immigrantTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_immigrantTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_immigrantTxt.setColumns(10);
		tables_immigrantTxt.setBounds(20, 348, 407, 23);
		tables_inputPanel.add(tables_immigrantTxt);
		
		JLabel tables_earlyHearingDateLbl = new JLabel("Early Hearing Date:");
		tables_earlyHearingDateLbl.setForeground(new Color(255, 255, 255));
		tables_earlyHearingDateLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_earlyHearingDateLbl.setBounds(20, 372, 190, 29);
		tables_inputPanel.add(tables_earlyHearingDateLbl);
		
		//Date Input
		
		//Date Filed
		UtilDateModel dateFiledModel = new UtilDateModel();
		Properties dateFiled = new Properties();
		dateFiled.put("text.today", "Date Today");
		dateFiled.put("text.month", "Month");
		dateFiled.put("text.year", "Year");
		
		JDatePanelImpl dateFiledPanel = new JDatePanelImpl(dateFiledModel, dateFiled);

		JDatePickerImpl dateFiledPicker = new JDatePickerImpl(dateFiledPanel, new DateLabelFormatter());

		dateFiledPicker.setLocation(20, 289);
		dateFiledPicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		dateFiledPicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		dateFiledPicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		dateFiledPicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		dateFiledPicker.setSize(407, 23);

		tables_inputPanel.add(dateFiledPicker);
		
		//Hearing Date
		UtilDateModel hearingDateModel = new UtilDateModel();
		Properties hearingDate = new Properties();
		hearingDate.put("text.today", "Date Today");
		hearingDate.put("text.month", "Month");
		hearingDate.put("text.year", "Year");
		
		JDatePanelImpl hearingDatePanel = new JDatePanelImpl(hearingDateModel, hearingDate);

		JDatePickerImpl hearingDatePicker = new JDatePickerImpl(hearingDatePanel, new DateLabelFormatter());

		hearingDatePicker.setLocation(230, 403);
		hearingDatePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		hearingDatePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		hearingDatePicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		hearingDatePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		hearingDatePicker.setSize(197, 23);

		tables_inputPanel.add(hearingDatePicker);
	
		
		//Early Hearing Date
		UtilDateModel earlyHearingDateModel = new UtilDateModel();
		Properties earlyHearingDate = new Properties();
		earlyHearingDate.put("text.today", "Date Today");
		earlyHearingDate.put("text.month", "Month");
		earlyHearingDate.put("text.year", "Year");

		JDatePanelImpl earlyHearingDatePanel = new JDatePanelImpl(earlyHearingDateModel, earlyHearingDate);

		JDatePickerImpl earlyHearingDatePicker = new JDatePickerImpl(earlyHearingDatePanel, new DateLabelFormatter());

		earlyHearingDatePicker.setLocation(20, 403);
		earlyHearingDatePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		earlyHearingDatePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		earlyHearingDatePicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		earlyHearingDatePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		earlyHearingDatePicker.setSize(190, 23);
		
		tables_inputPanel.add(earlyHearingDatePicker);
		
		//Input Section (Labels)
		
		JLabel tables_hearingDateLbl = new JLabel("Hearing Date:");
		tables_hearingDateLbl.setForeground(Color.WHITE);
		tables_hearingDateLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_hearingDateLbl.setBounds(230, 372, 192, 29);
		tables_inputPanel.add(tables_hearingDateLbl);
		
		JLabel tables_agendaLbl = new JLabel("Agenda:");
		tables_agendaLbl.setForeground(Color.WHITE);
		tables_agendaLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_agendaLbl.setBounds(20, 427, 190, 29);
		tables_inputPanel.add(tables_agendaLbl);
		
		tables_agendaTxt = new JTextField();
		tables_agendaTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_agendaTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_agendaTxt.setColumns(10);
		tables_agendaTxt.setBounds(20, 458, 197, 23);
		tables_inputPanel.add(tables_agendaTxt);
		
		JLabel tables_visaReleaseLbl = new JLabel("9G Visa Released:");
		tables_visaReleaseLbl.setForeground(Color.WHITE);
		tables_visaReleaseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaReleaseLbl.setBounds(230, 427, 197, 29);
		tables_inputPanel.add(tables_visaReleaseLbl);
		
		tables_visaReleaseTxt = new JTextField();
		tables_visaReleaseTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_visaReleaseTxt.setColumns(10);
		tables_visaReleaseTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_visaReleaseTxt.setBounds(230, 458, 197, 23);
		tables_inputPanel.add(tables_visaReleaseTxt);
		
		JLabel tables_waiverEccLbl = new JLabel("Waiver/ECC Payment/ Others :");
		tables_waiverEccLbl.setForeground(Color.WHITE);
		tables_waiverEccLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_waiverEccLbl.setBounds(20, 484, 190, 29);
		tables_inputPanel.add(tables_waiverEccLbl);
		
		JLabel tables_chooseLbl = new JLabel("Choose a Client:");
		tables_chooseLbl.setForeground(Color.WHITE);
		tables_chooseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_chooseLbl.setBounds(20, 40, 190, 34);
		tables_inputPanel.add(tables_chooseLbl);
		
		JLabel tables_lastnameLbl = new JLabel("Lastname:");
		tables_lastnameLbl.setForeground(Color.WHITE);
		tables_lastnameLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_lastnameLbl.setBounds(624, 570, 418, 31);
		getContentPane().add(tables_lastnameLbl);

		JLabel tables_firstnameLbl = new JLabel("Firstname:");
		tables_firstnameLbl.setForeground(Color.WHITE);
		tables_firstnameLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_firstnameLbl.setBounds(624, 604, 414, 31);
		getContentPane().add(tables_firstnameLbl);
		
		JLabel tables_aliasLbl = new JLabel("Alias:");
		tables_aliasLbl.setForeground(Color.WHITE);
		tables_aliasLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_aliasLbl.setBounds(624, 634, 418, 37);
		getContentPane().add(tables_aliasLbl);
		
		JLabel tables_nationalityLbl = new JLabel("Nationality:");
		tables_nationalityLbl.setForeground(Color.WHITE);
		tables_nationalityLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_nationalityLbl.setBounds(624, 715, 418, 31);
		getContentPane().add(tables_nationalityLbl);
		
		JLabel tables_birthdateLbl = new JLabel("Birthdate:");
		tables_birthdateLbl.setForeground(Color.WHITE);
		tables_birthdateLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_birthdateLbl.setBounds(624, 749, 414, 31);
		getContentPane().add(tables_birthdateLbl);
		
		JLabel tables_genderLbl = new JLabel("Gender:");
		tables_genderLbl.setForeground(Color.WHITE);
		tables_genderLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_genderLbl.setBounds(624, 779, 418, 37);
		getContentPane().add(tables_genderLbl);
		
		JLabel tables_companyLbl = new JLabel("Company:");
		tables_companyLbl.setForeground(Color.WHITE);
		tables_companyLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_companyLbl.setBounds(1170, 712, 358, 37);
		getContentPane().add(tables_companyLbl);
		
		JLabel tables_emaiLbl = new JLabel("Email:");
		tables_emaiLbl.setForeground(Color.WHITE);
		tables_emaiLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_emaiLbl.setBounds(1170, 570, 361, 37);
		getContentPane().add(tables_emaiLbl);
		
		JLabel tables_contactLbl = new JLabel("Contact No.:");
		tables_contactLbl.setForeground(Color.WHITE);
		tables_contactLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_contactLbl.setBounds(1170, 604, 361, 37);
		getContentPane().add(tables_contactLbl);
		
		JLabel tables_companyPositionLbl = new JLabel("Company Position:");
		tables_companyPositionLbl.setForeground(Color.WHITE);
		tables_companyPositionLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		tables_companyPositionLbl.setBounds(1170, 749, 358, 31);
		getContentPane().add(tables_companyPositionLbl);

		
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
		tables_addClientLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TablesAddClient().setVisible(true);
				dispose();
			}
		});
		tables_addClientLbl.setBounds(25, 48, 295, 37);
		tables_addClientLbl.setForeground(Color.LIGHT_GRAY);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblSpecificClient = new JLabel("Client Status (For Visa Filing)");
		lblSpecificClient.setBounds(493, 169, 382, 37);
		lblSpecificClient.setForeground(Color.WHITE);
		lblSpecificClient.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		// Add to Panels 
		
		getContentPane().setLayout(null);
		getContentPane().add(tables_inputSectionLbl);
		getContentPane().add(tables_reloadBtn);
		getContentPane().add(tables_addClientLbl);
		getContentPane().add(tables_clientCreateTransactionLbl);
		getContentPane().add(tables_updateTransactionLbl);
		getContentPane().add(tables_clientStatusTableLbl);
		getContentPane().add(label);
		getContentPane().add(tables_clientRemarksTableLbl);
		getContentPane().add(tables_line);
		getContentPane().add(tables_inputPanel);
		
		tables_waiverEccTxt = new JTextField();
		tables_waiverEccTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_waiverEccTxt.setColumns(10);
		tables_waiverEccTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_waiverEccTxt.setBounds(20, 515, 197, 23);
		tables_inputPanel.add(tables_waiverEccTxt);
		
		JLabel tables_acrReleaseLbl = new JLabel("ACR-I Card Release:");
		tables_acrReleaseLbl.setForeground(Color.WHITE);
		tables_acrReleaseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_acrReleaseLbl.setBounds(230, 485, 190, 26);
		tables_inputPanel.add(tables_acrReleaseLbl);
		
		tables_acrReleaseTxt = new JTextField();
		tables_acrReleaseTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_acrReleaseTxt.setColumns(10);
		tables_acrReleaseTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_acrReleaseTxt.setBounds(230, 515, 197, 23);
		tables_inputPanel.add(tables_acrReleaseTxt);
		
		JLabel tables_documentationCompleteLbl = new JLabel("Documentation Complete:");
		tables_documentationCompleteLbl.setForeground(Color.WHITE);
		tables_documentationCompleteLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_documentationCompleteLbl.setBounds(20, 541, 190, 26);
		tables_inputPanel.add(tables_documentationCompleteLbl);
		
		tables_documentationCompleteTxt = new JTextField();
		tables_documentationCompleteTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_documentationCompleteTxt.setColumns(10);
		tables_documentationCompleteTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_documentationCompleteTxt.setBounds(20, 571, 407, 23);
		tables_inputPanel.add(tables_documentationCompleteTxt);
		
		JLabel lblSelectATransaction = new JLabel("Select Transaction ID:");
		lblSelectATransaction.setForeground(Color.WHITE);
		lblSelectATransaction.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblSelectATransaction.setBounds(20, 103, 146, 29);
		tables_inputPanel.add(lblSelectATransaction);

		JButton tables_registerBtn = new JButton("Insert Status");
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
			 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
			 	UIManager.put("Button.background", Color.WHITE);
			 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
			 	
				Connection conn3;
				try {
					String sql = "INSERT INTO jdl_accounts.status_visa (statusV_documentation, statusV_dateFiled, statusV_immigrant, statusV_earlyHearing, statusV_hearingDate, statusV_agenda, statusV_visaReleased, statusV_waiverECC, statusV_acrIcard, "
							+ "statusV_docComplete, client_id, trans_transId)  values (?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE statusV_documentation = ?, statusV_dateFiled = ?, statusV_immigrant = ?, statusV_earlyHearing = ?, statusV_hearingDate = ?, statusV_agenda = ?, statusV_visaReleased = ?, statusV_waiverECC = ?, statusV_acrIcard = ?," 
							+ "statusV_docComplete = ?, client_id = ?, trans_transId = ? ";
					conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&compensateOnDuplicateKeyUpdateCounts=false","root","password");
					PreparedStatement statement2= conn3.prepareStatement(sql);
					
					statement2.setString(1, tables_documentationTxt.getText());
					
					if(dateFiledPicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(2, null);
					else
						statement2.setDate(2, java.sql.Date.valueOf(dateFiledPicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(3, tables_immigrantTxt.getText());
					
					if(earlyHearingDatePicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(4, null);
					else
						statement2.setDate(4, java.sql.Date.valueOf(earlyHearingDatePicker.getJFormattedTextField().getText().toString()));
					
					if(hearingDatePicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(5, null);
					else
						statement2.setDate(5, java.sql.Date.valueOf(hearingDatePicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(6, tables_agendaTxt.getText());
					statement2.setString(7, tables_visaReleaseTxt.getText());
					statement2.setString(8, tables_waiverEccTxt.getText());
					statement2.setString(9, tables_acrReleaseTxt.getText());
					statement2.setString(10, tables_documentationCompleteTxt.getText());
					statement2.setString(11, client_id);
					statement2.setString(12, tables_comboBox1.getSelectedItem().toString());
					statement2.setString(13, tables_documentationTxt.getText());
					
					if(dateFiledPicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(14, null);
					else
						statement2.setDate(14, java.sql.Date.valueOf(dateFiledPicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(15, tables_immigrantTxt.getText());
					
					if(earlyHearingDatePicker.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(16, null);
					else
						statement2.setDate(16, java.sql.Date.valueOf(earlyHearingDatePicker.getJFormattedTextField().getText().toString()));
					
					if(hearingDatePicker.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(17, null);
					else
						statement2.setDate(17, java.sql.Date.valueOf(hearingDatePicker.getJFormattedTextField().getText().toString()));
					
					statement2.setString(18, tables_agendaTxt.getText());
					statement2.setString(19, tables_visaReleaseTxt.getText());
					statement2.setString(20, tables_waiverEccTxt.getText());
					statement2.setString(21, tables_acrReleaseTxt.getText());
					statement2.setString(22, tables_documentationCompleteTxt.getText());
					statement2.setString(23, client_id);
					statement2.setString(24, tables_comboBox1.getSelectedItem().toString());
					
					statement2.executeUpdate();
					tables_inputPanel.revalidate();
					tables_reloadBtn.doClick();
					
					JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Visa Status has been made to this transaction.</font color = #ffffff></html>", "Visa Status Inserted", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tables_registerBtn.setBounds(131, 619, 170, 34);
		tables_inputPanel.add(tables_registerBtn);
		

		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel tables_clientDetailsLbl = new JLabel("------------------------- Client Transaction Details ------------------------");
		tables_clientDetailsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientDetailsLbl.setForeground(Color.WHITE);
		tables_clientDetailsLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientDetailsLbl.setBounds(20, 176, 411, 23);
		tables_inputPanel.add(tables_clientDetailsLbl);
		getContentPane().add(scrollPane_1);
		getContentPane().add(lblSpecificClient);
		
		JButton btnVisa = new JButton("Visa");
		btnVisa.setForeground(Color.WHITE);
		btnVisa.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnVisa.setBorder(null);
		btnVisa.setBackground(new Color(255, 153, 51));
		btnVisa.setBounds(169, 125, 86, 38);
		getContentPane().add(btnVisa);
		
		JButton btnPermit = new JButton("Permits");
		btnPermit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TablesStatusPermits().setVisible(true);
				dispose();
			}
		});
		btnPermit.setForeground(Color.WHITE);
		btnPermit.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		btnPermit.setBorder(null);
		btnPermit.setBackground(new Color(0, 102, 102));
		btnPermit.setBounds(265, 125, 86, 38);
		getContentPane().add(btnPermit);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(TablesStatus.class.getResource("/jdl/Assets/client_infoIcon.png")));
		label_1.setBounds(508, 556, 104, 115);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(TablesStatus.class.getResource("/jdl/Assets/client_secondaryInfoIcon.png")));
		label_2.setBounds(508, 701, 104, 115);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(TablesStatus.class.getResource("/jdl/Assets/client_emails.png")));
		label_3.setBounds(1054, 556, 104, 115);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(TablesStatus.class.getResource("/jdl/Assets/client_company1.png")));
		label_4.setBounds(1057, 701, 104, 115);
		getContentPane().add(label_4);
		
		JLabel tables_seeTablesLbl = new JLabel("See Tables");
		tables_seeTablesLbl.setBounds(690, 0, 168, 37);
		getContentPane().add(tables_seeTablesLbl);
		tables_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tables_seeTablesLbl.setForeground(Color.WHITE);
		tables_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
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
		
		JLabel tables_background = new JLabel("");
		tables_background.setIcon(new ImageIcon(TablesStatus.class.getResource("/jdl/Assets/background_tables4.jpg")));
		tables_background.setBounds(0, 0, 1551, 848);
		getContentPane().add(tables_background);
	}
	
	
}

