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

public class TablesStatus extends JFrame{
	
	private JTextField tables_documentationTxt;
	private JTextField tables_immigrantTxt;
	private JTextField tables_agendaTxt;
	private JTextField tables_visaReleaseTxt;
	private String clientSelectedName;
	private JTextField tables_clientFirstNameTxt;
	private JTextField tables_clientIdTxt;
	private boolean tables_validator = true;
	private JTable table_1;
	private JTextField tables_waiverEccTxt;
	private JTextField tables_acrReleaseTxt;
	private JTextField tables_documentationCompleteTxt;
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
		
		
		JLabel tables_documentationLbl = new JLabel("Documentation:");
		tables_documentationLbl.setForeground(new Color(255, 255, 255));
		tables_documentationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_documentationLbl.setBounds(20, 235, 131, 54);
		tables_inputPanel.add(tables_documentationLbl);
		
		tables_documentationTxt = new JTextField();
		tables_documentationTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_documentationTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_documentationTxt.setBounds(20, 282, 400, 23);
		tables_inputPanel.add(tables_documentationTxt);
		tables_documentationTxt.setColumns(10);
		
		JLabel tables_dateFiledLbl = new JLabel("Date Filed:");
		tables_dateFiledLbl.setForeground(new Color(255, 255, 255));
		tables_dateFiledLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_dateFiledLbl.setBounds(20, 309, 197, 29);
		tables_inputPanel.add(tables_dateFiledLbl);
		
		JLabel tables_immigrantLbl = new JLabel("Immigrant:");
		tables_immigrantLbl.setForeground(new Color(255, 255, 255));
		tables_immigrantLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_immigrantLbl.setBounds(20, 365, 190, 29);
		tables_inputPanel.add(tables_immigrantLbl);
		
		tables_immigrantTxt = new JTextField();
		tables_immigrantTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_immigrantTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_immigrantTxt.setColumns(10);
		tables_immigrantTxt.setBounds(20, 396, 400, 23);
		tables_inputPanel.add(tables_immigrantTxt);
		
		JLabel tables_earlyHearingDateLbl = new JLabel("Early Hearing Date:");
		tables_earlyHearingDateLbl.setForeground(new Color(255, 255, 255));
		tables_earlyHearingDateLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_earlyHearingDateLbl.setBounds(20, 420, 190, 29);
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

		dateFiledPicker.setLocation(20, 337);
		dateFiledPicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		dateFiledPicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		dateFiledPicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		dateFiledPicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		dateFiledPicker.setSize(400, 23);

		tables_inputPanel.add(dateFiledPicker);
		
		//Hearing Date
		UtilDateModel hearingDateModel = new UtilDateModel();
		Properties hearingDate = new Properties();
		hearingDate.put("text.today", "Date Today");
		hearingDate.put("text.month", "Month");
		hearingDate.put("text.year", "Year");
		
		JDatePanelImpl hearingDatePanel = new JDatePanelImpl(hearingDateModel, hearingDate);

		JDatePickerImpl hearingDatePicker = new JDatePickerImpl(hearingDatePanel, new DateLabelFormatter());

		hearingDatePicker.setLocation(230, 451);
		hearingDatePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		hearingDatePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		hearingDatePicker.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		hearingDatePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		hearingDatePicker.setSize(190, 23);

		tables_inputPanel.add(hearingDatePicker);
	
		
		//Early Hearing Date
		UtilDateModel earlyHearingDateModel = new UtilDateModel();
		Properties earlyHearingDate = new Properties();
		earlyHearingDate.put("text.today", "Date Today");
		earlyHearingDate.put("text.month", "Month");
		earlyHearingDate.put("text.year", "Year");

		JDatePanelImpl earlyHearingDatePanel = new JDatePanelImpl(earlyHearingDateModel, earlyHearingDate);

		JDatePickerImpl earlyHearingDatePicker = new JDatePickerImpl(earlyHearingDatePanel, new DateLabelFormatter());

		earlyHearingDatePicker.setLocation(20, 451);
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
		tables_hearingDateLbl.setBounds(230, 420, 192, 29);
		tables_inputPanel.add(tables_hearingDateLbl);
		
		JLabel tables_agendaLbl = new JLabel("Agenda:");
		tables_agendaLbl.setForeground(Color.WHITE);
		tables_agendaLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_agendaLbl.setBounds(20, 475, 190, 29);
		tables_inputPanel.add(tables_agendaLbl);
		
		tables_agendaTxt = new JTextField();
		tables_agendaTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_agendaTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_agendaTxt.setColumns(10);
		tables_agendaTxt.setBounds(20, 506, 197, 23);
		tables_inputPanel.add(tables_agendaTxt);
		
		JLabel tables_visaReleaseLbl = new JLabel("9G Visa Released:");
		tables_visaReleaseLbl.setForeground(Color.WHITE);
		tables_visaReleaseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaReleaseLbl.setBounds(230, 475, 197, 29);
		tables_inputPanel.add(tables_visaReleaseLbl);
		
		tables_visaReleaseTxt = new JTextField();
		tables_visaReleaseTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_visaReleaseTxt.setColumns(10);
		tables_visaReleaseTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_visaReleaseTxt.setBounds(230, 506, 197, 23);
		tables_inputPanel.add(tables_visaReleaseTxt);
		
		JLabel tables_waiverEccLbl = new JLabel("Waiver/ECC Payment/ Others :");
		tables_waiverEccLbl.setForeground(Color.WHITE);
		tables_waiverEccLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_waiverEccLbl.setBounds(20, 532, 190, 29);
		tables_inputPanel.add(tables_waiverEccLbl);
		
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
		
		JLabel tables_allClientTransactionLbl = new JLabel("All Recorded Client Status (For Visa Filing)");
		tables_allClientTransactionLbl.setBounds(493, 362, 382, 37);
		tables_allClientTransactionLbl.setForeground(Color.WHITE);
		tables_allClientTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JLabel lblSpecificClient = new JLabel("Specific Client Status (For Visa Filing)");
		lblSpecificClient.setBounds(493, 169, 382, 37);
		lblSpecificClient.setForeground(Color.WHITE);
		lblSpecificClient.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		// Add to Panels 
		
		getContentPane().setLayout(null);
		getContentPane().add(tables_titlePanel);
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
		tables_waiverEccTxt.setBounds(20, 563, 197, 23);
		tables_inputPanel.add(tables_waiverEccTxt);
		
		JLabel tables_acrReleaseLbl = new JLabel("ACR-I Card Release:");
		tables_acrReleaseLbl.setForeground(Color.WHITE);
		tables_acrReleaseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_acrReleaseLbl.setBounds(230, 533, 190, 26);
		tables_inputPanel.add(tables_acrReleaseLbl);
		
		tables_acrReleaseTxt = new JTextField();
		tables_acrReleaseTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_acrReleaseTxt.setColumns(10);
		tables_acrReleaseTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_acrReleaseTxt.setBounds(230, 563, 197, 23);
		tables_inputPanel.add(tables_acrReleaseTxt);
		
		JLabel tables_documentationCompleteLbl = new JLabel("Documentation Complete:");
		tables_documentationCompleteLbl.setForeground(Color.WHITE);
		tables_documentationCompleteLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_documentationCompleteLbl.setBounds(20, 589, 190, 26);
		tables_inputPanel.add(tables_documentationCompleteLbl);
		
		tables_documentationCompleteTxt = new JTextField();
		tables_documentationCompleteTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_documentationCompleteTxt.setColumns(10);
		tables_documentationCompleteTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_documentationCompleteTxt.setBounds(20, 619, 407, 23);
		tables_inputPanel.add(tables_documentationCompleteTxt);
		
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
					statement2.setString(11, tables_clientIdTxt.getText());
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
					statement2.setString(23, tables_clientIdTxt.getText());
					statement2.setString(24, tables_comboBox1.getSelectedItem().toString());
					
					statement2.executeUpdate();
					tables_inputPanel.revalidate();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tables_registerBtn.setBounds(129, 664, 175, 41);
		tables_inputPanel.add(tables_registerBtn);
		

		tables_registerBtn.setBackground(new Color(255, 204, 51));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel tables_clientDetailsLbl = new JLabel("-------------------------------- Client Details -----------------------------");
		tables_clientDetailsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientDetailsLbl.setForeground(Color.WHITE);
		tables_clientDetailsLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientDetailsLbl.setBounds(20, 222, 411, 23);
		tables_inputPanel.add(tables_clientDetailsLbl);
		getContentPane().add(tables_allClientTransactionLbl);
		getContentPane().add(scrollPane);
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
		btnPermit.setBackground(new Color(155, 177, 166));
		btnPermit.setBounds(265, 125, 86, 38);
		getContentPane().add(btnPermit);
		
		JButton button = new JButton("Delete");
		button.setIcon(new ImageIcon(TablesStatus.class.getResource("/jdl/Assets/button_delete.png")));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		button.setBorder(null);
		button.setBackground(new Color(255, 0, 51));
		button.setBounds(1241, 159, 138, 38);
		getContentPane().add(button);
	}
}

