package jdl;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Calendar;
import java.util.Date;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class Tables extends JFrame{
	private JTextField tables_passportNoTxt;
	private JTextField tables_tinIdTxt;
	private JTextField tables_visaTypeTxt;
	private JTextField tables_permitTypeTxt;
	private JTextField tables_aepIdTxt;
	private String clientSelectedName;
	private JTextField tables_clientFirstNameTxt;
	private JTextField tables_clientIdTxt;
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
	
	public static boolean DateCheck(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean approved = false;
		if(date1 == "" && date2 == "") {
			return approved = true;
		} else {
			try {
				Date datex = sdf.parse(date1);
				Date datey = sdf.parse(date2);
				if (datex.compareTo(datey) > 0) {
					//System.out.println("Date1 is after Date2"); FALSE
					approved = false;
				} else if (datex.compareTo(datey) < 0) {
					//System.out.println("Date1 is before Date2");TRUE
					approved = true;
				} else if (datex.compareTo(datey) == 0) {
					//System.out.println("Date1 is equal to Date2"); FALSE
					approved = false;
				} else {
					//System.out.println("How to get here?"); FALSE
					approved = false;
				}
				
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return approved;
	}

	/**
	 * Create the application.
	 */
	public Tables() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Transactions");
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
		scrollPane.setBounds(493, 405, 1040, 485);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(1040, 155);
		scrollPane_1.setLocation(493, 208);
		scrollPane.setBounds(493, 405, 1040, 485);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JTable table = new JTable();
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(32);
		table.setBorder(null);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Calibri", Font.PLAIN, 16));
		table_1.setRowHeight(32);
		table_1.setBorder(null);
		table_1.setBounds(492, 217, 1040, 138);
	
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JTableHeader header_1 = table_1.getTableHeader();
		header_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
	    header_1.setBackground(new Color(155, 177, 166));
	    header_1.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Input Section (Declaration of Panel) and Client_id Textfield
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(25, 169, 450, 721);
		tables_inputPanel.setBackground(new Color (112, 128, 144));
		tables_inputPanel.setLayout(null);
		
		
		tables_clientIdTxt = new JTextField();
		tables_clientIdTxt.setText("1");
		tables_clientIdTxt.setEditable(false);
		tables_clientIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientIdTxt.setColumns(10);
		tables_clientIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientIdTxt.setBounds(331, 143, 89, 23);
		tables_inputPanel.add(tables_clientIdTxt);
		
		//Buttons
		
		JComboBox tables_comboBox = new JComboBox();
		tables_comboBox.addItem("Select a registered client last name");
		
		Connection conn1;
		try {
			conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
			Statement stat=conn1.createStatement();
			ResultSet rs1=stat.executeQuery("SELECT * FROM jdl_accounts.clients");
			
			 while(rs1.next()){        
				 	String client_lastname = rs1.getString("client_lastname");
				 	rs1.getString("client_firstname");
			
			       	tables_comboBox.addItem(client_lastname);
			       	clientSelectedName = tables_comboBox.getSelectedItem().toString();
			       	
			    }
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		tables_comboBox.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_comboBox.setBounds(20, 79, 400, 29);
		
		AutoCompletion.enable(tables_comboBox);
		tables_inputPanel.add(tables_comboBox);
		
		JButton tables_reloadBtn = new JButton("Reload");
		tables_reloadBtn.setBounds(1395, 159, 138, 38);
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					Statement stat=conn.createStatement();
					Statement stat1=conn.createStatement();
					
					ResultSet rs=stat.executeQuery("SELECT client_id AS 'Client ID',"
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
							" FROM jdl_accounts.transactions ORDER BY trans_transId DESC");
	
					ResultSet rs1 = stat1.executeQuery("SELECT client_id AS 'Client ID',"
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
							" FROM jdl_accounts.transactions WHERE client_id ="+Integer.parseInt(tables_clientIdTxt.getText())+" ORDER BY trans_transId DESC");
					
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
		tables_orderByBtn.setBounds(1247, 159, 138, 38);
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
		
		JLabel tables_inputSectionLbl = new JLabel("Input Section");
		tables_inputSectionLbl.setBounds(25, 120, 255, 37);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		JLabel tables_passportNoLbl = new JLabel("Passport Number:");
		tables_passportNoLbl.setForeground(new Color(255, 255, 255));
		tables_passportNoLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_passportNoLbl.setBounds(20, 200, 204, 41);
		tables_inputPanel.add(tables_passportNoLbl);
		
		tables_passportNoTxt = new JTextField();
		tables_passportNoTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_passportNoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_passportNoTxt.setBounds(20, 237, 400, 23);
		tables_inputPanel.add(tables_passportNoTxt);
		tables_passportNoTxt.setColumns(10);
		
		JLabel tables_tinIdLbl = new JLabel("TIN ID:");
		tables_tinIdLbl.setForeground(new Color(255, 255, 255));
		tables_tinIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_tinIdLbl.setBounds(20, 264, 197, 29);
		tables_inputPanel.add(tables_tinIdLbl);
		
		tables_tinIdTxt = new JTextField();
		tables_tinIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_tinIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_tinIdTxt.setColumns(10);
		tables_tinIdTxt.setBounds(20, 293, 400, 23);
		tables_inputPanel.add(tables_tinIdTxt);
		
		JLabel tables_visaLbl = new JLabel("Visa Type:");
		tables_visaLbl.setForeground(new Color(255, 255, 255));
		tables_visaLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaLbl.setBounds(20, 320, 190, 29);
		tables_inputPanel.add(tables_visaLbl);
		
		tables_visaTypeTxt = new JTextField();
		tables_visaTypeTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_visaTypeTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_visaTypeTxt.setColumns(10);
		tables_visaTypeTxt.setBounds(20, 351, 400, 23);
		tables_inputPanel.add(tables_visaTypeTxt);
		
		JLabel tables_visaStartLbl = new JLabel("Visa Start Date:");
		tables_visaStartLbl.setForeground(new Color(255, 255, 255));
		tables_visaStartLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaStartLbl.setBounds(20, 380, 190, 29);
		tables_inputPanel.add(tables_visaStartLbl);
		
		//Date Input
		
		// Start Dates
	
		//VISA
		UtilDateModel visaModel = new UtilDateModel();
		Properties visa = new Properties();
		visa.put("text.today", "Date Today");
		visa.put("text.month", "Month");
		visa.put("text.year", "Year");
		
		JDatePanelImpl visaDatePanel = new JDatePanelImpl(visaModel, visa);

		JDatePickerImpl visaStartPick = new JDatePickerImpl(visaDatePanel, new DateLabelFormatter());

		visaStartPick.setLocation(230, 411);
		visaStartPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		visaStartPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		visaStartPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		visaStartPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		visaStartPick.setSize(190, 23);

		tables_inputPanel.add(visaStartPick);
		
		//PERMIT
		UtilDateModel permitModel = new UtilDateModel();
		Properties permit = new Properties();
		permit.put("text.today", "Date Today");
		permit.put("text.month", "Month");
		permit.put("text.year", "Year");
		
		JDatePanelImpl permitDatePanel = new JDatePanelImpl(permitModel, visa);

		JDatePickerImpl permitStartPick = new JDatePickerImpl(permitDatePanel, new DateLabelFormatter());

		permitStartPick.setLocation(20, 520);
		permitStartPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		permitStartPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		permitStartPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		permitStartPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		permitStartPick.setSize(190, 23);

		tables_inputPanel.add(permitStartPick);
		
		//AEP
		UtilDateModel aepModel = new UtilDateModel();
		Properties aep = new Properties();
		aep.put("text.today", "Date Today");
		aep.put("text.month", "Month");
		aep.put("text.year", "Year");
		
		JDatePanelImpl aepDatePanel = new JDatePanelImpl(aepModel, visa);

		JDatePickerImpl aepStartPick = new JDatePickerImpl(aepDatePanel, new DateLabelFormatter());

		aepStartPick.setLocation(20, 629);
		aepStartPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		aepStartPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		aepStartPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		aepStartPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		aepStartPick.setSize(190, 23);
		
		tables_inputPanel.add(aepStartPick);
		
		// End Dates
		
		//VISA
		UtilDateModel visaModel1 = new UtilDateModel();
		Properties visa1 = new Properties();
		visa1.put("text.today", "Date Today");
		visa1.put("text.month", "Month");
		visa1.put("text.year", "Year");

		JDatePanelImpl visaDatePanel1 = new JDatePanelImpl(visaModel1, visa1);

		JDatePickerImpl visaEndPick = new JDatePickerImpl(visaDatePanel1, new DateLabelFormatter());

		visaEndPick.setLocation(20, 411);
		visaEndPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		visaEndPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		visaEndPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		visaEndPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		visaEndPick.setSize(190, 23);
		
		tables_inputPanel.add(visaEndPick);
		
		//PERMIT
		UtilDateModel permitModel1 = new UtilDateModel();
		Properties permit1 = new Properties();
		permit1.put("text.today", "Date Today");
		permit1.put("text.month", "Month");
		permit1.put("text.year", "Year");
		
		JDatePanelImpl permitDatePanel1 = new JDatePanelImpl(permitModel1, permit1);

		JDatePickerImpl permitEndPick = new JDatePickerImpl(permitDatePanel1, new DateLabelFormatter());

		permitEndPick.setLocation(230, 520);
		permitEndPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		permitEndPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		permitEndPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		permitEndPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		permitEndPick.setSize(192, 23);
		
		tables_inputPanel.add(permitEndPick);
		
		//AEP
		UtilDateModel aepModel1 = new UtilDateModel();
		Properties aep1 = new Properties();
		aep1.put("text.today", "Date Today");
		aep1.put("text.month", "Month");
		aep1.put("text.year", "Year");
		
		JDatePanelImpl aepDatePanel1 = new JDatePanelImpl(aepModel1, aep1);

		JDatePickerImpl aepEndPick = new JDatePickerImpl(aepDatePanel1, new DateLabelFormatter());

		aepEndPick.setLocation(228, 629);
		aepEndPick.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		aepEndPick.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		aepEndPick.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		aepEndPick.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		aepEndPick.setSize(192, 23);
		
		tables_inputPanel.add(aepEndPick);
		
		//Input Section (Labels)
		
		JLabel tables_visaExpireLbl = new JLabel("Visa Expiry Date:");
		tables_visaExpireLbl.setForeground(Color.WHITE);
		tables_visaExpireLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_visaExpireLbl.setBounds(230, 380, 192, 29);
		tables_inputPanel.add(tables_visaExpireLbl);
		
		JLabel tables_permitLbl = new JLabel("Permit Type:");
		tables_permitLbl.setForeground(Color.WHITE);
		tables_permitLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitLbl.setBounds(20, 435, 190, 29);
		tables_inputPanel.add(tables_permitLbl);
		
		tables_permitTypeTxt = new JTextField();
		tables_permitTypeTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_permitTypeTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_permitTypeTxt.setColumns(10);
		tables_permitTypeTxt.setBounds(20, 466, 400, 23);
		tables_inputPanel.add(tables_permitTypeTxt);
		
		JLabel tables_permitStartLbl = new JLabel("Permit Start Date:");
		tables_permitStartLbl.setForeground(Color.WHITE);
		tables_permitStartLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitStartLbl.setBounds(20, 492, 190, 29);
		tables_inputPanel.add(tables_permitStartLbl);
		
		JLabel tables_permitExpireLbl = new JLabel("Permit Expiry Date:");
		tables_permitExpireLbl.setForeground(Color.WHITE);
		tables_permitExpireLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_permitExpireLbl.setBounds(230, 492, 192, 29);
		tables_inputPanel.add(tables_permitExpireLbl);
		
		JLabel tables_aepIdLbl = new JLabel("AEP ID:");
		tables_aepIdLbl.setForeground(Color.WHITE);
		tables_aepIdLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_aepIdLbl.setBounds(20, 544, 197, 29);
		tables_inputPanel.add(tables_aepIdLbl);
		
		tables_aepIdTxt = new JTextField();
		tables_aepIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_aepIdTxt.setColumns(10);
		tables_aepIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_aepIdTxt.setBounds(20, 575, 400, 23);
		tables_inputPanel.add(tables_aepIdTxt);
		
		JLabel lblAepStartDate = new JLabel("AEP Start Date:");
		lblAepStartDate.setForeground(Color.WHITE);
		lblAepStartDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblAepStartDate.setBounds(20, 600, 190, 29);
		tables_inputPanel.add(lblAepStartDate);
		
		JLabel lblAepExpiryDate = new JLabel("AEP Expiry Date:");
		lblAepExpiryDate.setForeground(Color.WHITE);
		lblAepExpiryDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblAepExpiryDate.setBounds(228, 600, 192, 29);
		tables_inputPanel.add(lblAepExpiryDate);
		
		JLabel tables_chooseLbl = new JLabel("Choose a client's Lastname:");
		tables_chooseLbl.setForeground(Color.WHITE);
		tables_chooseLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_chooseLbl.setBounds(20, 40, 190, 41);
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
			}
		});
		
		
		JLabel tables_idLbl = new JLabel("ID:");
		tables_idLbl.setForeground(Color.WHITE);
		tables_idLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_idLbl.setBounds(331, 106, 89, 41);
		tables_inputPanel.add(tables_idLbl);
		
		tables_clientFirstNameTxt = new JTextField();
		tables_clientFirstNameTxt.setEditable(false);
		tables_clientFirstNameTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientFirstNameTxt.setColumns(10);
		tables_clientFirstNameTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientFirstNameTxt.setBounds(20, 143, 298, 23);
		tables_inputPanel.add(tables_clientFirstNameTxt);
		
		JLabel lblClientsFirstName = new JLabel("Client's First Name:");
		lblClientsFirstName.setForeground(Color.WHITE);
		lblClientsFirstName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblClientsFirstName.setBounds(20, 106, 298, 41);
		tables_inputPanel.add(lblClientsFirstName);
		
		JLabel lblClientTransaction = new JLabel("------------------------ Client Transaction Details -----------------------");
		lblClientTransaction.setHorizontalAlignment(SwingConstants.LEFT);
		lblClientTransaction.setForeground(Color.WHITE);
		lblClientTransaction.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblClientTransaction.setBounds(20, 170, 400, 41);
		tables_inputPanel.add(lblClientTransaction);
		
		JButton tables_registerBtn = new JButton("Register Transaction");
		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String vs = visaStartPick.getJFormattedTextField().getText().toString();
				String ve = visaEndPick.getJFormattedTextField().getText().toString();
				String ps = permitStartPick.getJFormattedTextField().getText().toString();
				String pe = permitEndPick.getJFormattedTextField().getText().toString();
				String as = aepStartPick.getJFormattedTextField().getText().toString();
				String ae = aepEndPick.getJFormattedTextField().getText().toString();
				
				System.out.println("VISA START: " + vs);
				System.out.println("VISA END: " + ve);
				System.out.println("PERMIT START:" + ps);
				System.out.println("PERMIT END: " + pe);
				System.out.println("AEP START: " + as);
				System.out.println("AEP END: " + ae);
				
				if(tables_passportNoTxt.getText() != "") {
					if(tables_tinIdTxt.getText() != "") {
						if(tables_visaTypeTxt.getText() != ""){
							if(!((vs != null && !vs.isEmpty()) && (ve != null && !ve.isEmpty())) || !((ps != null && !ps.isEmpty()) && (pe != null && !pe.isEmpty())) || !((as != null && !as.isEmpty()) && (ae != null && !ae.isEmpty())) ) {
								Register(); //No VISA, PERMIT OR AEP
								System.out.print("REGISTERED WITHOUT DATES");
								if(DateCheck(vs, ve)) {
									System.out.println("Valid Date");
								}
								else {
									System.out.println("Date Invalid");
								}
								
							}
							else {
								System.out.print("ERROR EMPTY");
							}
						}
						else {
							//tables_visaTypeTxt cannot be empty
							System.out.print("err");
						}
					}
					else {
						//tables_tinIdTxt cannot be empty
						System.out.print("err");
					}
				}
				else {
					//tables_passportNoTxt cannot be empty
					System.out.print("err");
				}
				
				
			}// end of action performed
			
		public void Register() {
			Connection conn2;
			try {
				String sql = "INSERT INTO jdl_accounts.transactions (trans_passportNo, trans_tinID, trans_visaType, trans_visaStartDate, trans_visaEndDate, trans_permitType, trans_permitStartDate, trans_permitEndDate, trans_aepID, "
						+ "trans_aepStartDate, trans_aepEndDate, client_id, trans_transTimestamp) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
				PreparedStatement statement1 = conn2.prepareStatement(sql);
				
				statement1.setString(1, tables_passportNoTxt.getText());
				statement1.setString(2, tables_tinIdTxt.getText());
				statement1.setString(3, tables_visaTypeTxt.getText());
				
				
				if(visaStartPick.getJFormattedTextField().getText().toString().equals(""))
					statement1.setDate(5, null);
				else
					statement1.setDate(5, java.sql.Date.valueOf(visaStartPick.getJFormattedTextField().getText().toString()));
				
				
				if(visaEndPick.getJFormattedTextField().getText().toString().equals(""))
					statement1.setDate(4, null);
				else
					statement1.setDate(4, java.sql.Date.valueOf(visaEndPick.getJFormattedTextField().getText().toString()));
				
				statement1.setString(6, tables_permitTypeTxt.getText());
				
				if(permitStartPick.getJFormattedTextField().getText().toString().equals(""))
					statement1.setDate(7, null);
				else
					statement1.setDate(7, java.sql.Date.valueOf(permitStartPick.getJFormattedTextField().getText().toString()));
				
				
				if(permitEndPick.getJFormattedTextField().getText().toString().equals(""))
					statement1.setDate(8, null);
				else
					statement1.setDate(8, java.sql.Date.valueOf(permitEndPick.getJFormattedTextField().getText().toString()));
				
				
				statement1.setString(9, tables_aepIdTxt.getText());
				
				if(aepStartPick.getJFormattedTextField().getText().toString().equals(""))
					statement1.setDate(10, null);
				else
					statement1.setDate(10, java.sql.Date.valueOf(aepStartPick.getJFormattedTextField().getText().toString()));
				
				
				if(aepEndPick.getJFormattedTextField().getText().toString().equals(""))
					statement1.setDate(11, null);
				else
					statement1.setDate(11, java.sql.Date.valueOf(aepEndPick.getJFormattedTextField().getText().toString()));
				
				
				statement1.setString(12, tables_clientIdTxt.getText());
				
				Calendar calendar = Calendar.getInstance();
			    java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
			    
				statement1.setDate(13, currentDate);
				
				statement1.executeUpdate();
				tables_inputPanel.revalidate();
			}

			 catch (SQLException e1) {
				e1.printStackTrace();
					
			}
		}
	});//end of action listener
		
		tables_registerBtn.setBackground(new Color(255, 204, 51));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_registerBtn.setBounds(134, 675, 173, 35);
		tables_inputPanel.add(tables_registerBtn);
		
		JLabel lblClientInformation = new JLabel("------------------------------ Client Selection -----------------------------");
		lblClientInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblClientInformation.setForeground(Color.WHITE);
		lblClientInformation.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblClientInformation.setBounds(20, 11, 400, 41);
		tables_inputPanel.add(lblClientInformation);
		
		JLabel tables_clientCreateTransactionLbl = new JLabel("Create New Transaction", SwingConstants.CENTER);
		tables_clientCreateTransactionLbl.setBounds(330, 48, 227, 37);
		tables_clientCreateTransactionLbl.setForeground(Color.WHITE);
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
		tables_line.setBounds(412, 96, 57, 22);
		tables_line.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/line.png")));
		tables_line.setHorizontalAlignment(SwingConstants.CENTER);
		tables_line.setForeground(Color.WHITE);
		tables_line.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_updateTransactionLbl = new JLabel("Update Transaction", SwingConstants.CENTER);
		tables_updateTransactionLbl.setBounds(626, 48, 249, 37);
		tables_updateTransactionLbl.setForeground(Color.LIGHT_GRAY);
		tables_updateTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_addClientLbl = new JLabel("Add New Client", SwingConstants.CENTER);
		tables_addClientLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new TablesAddClient().setVisible(true);
				dispose();
			}
		});
		tables_addClientLbl.setBounds(25, 48, 295, 37);
		tables_addClientLbl.setForeground(Color.LIGHT_GRAY);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_allClientTransactionLbl = new JLabel("All Client Transactions");
		tables_allClientTransactionLbl.setBounds(493, 362, 204, 37);
		tables_allClientTransactionLbl.setForeground(Color.WHITE);
		tables_allClientTransactionLbl.setFont(new Font("Segoe UI", Font.BOLD, 19));
		
		JLabel lblSpecificClient = new JLabel("Specific Client Transactions");
		lblSpecificClient.setBounds(493, 169, 276, 37);
		lblSpecificClient.setForeground(Color.WHITE);
		lblSpecificClient.setFont(new Font("Segoe UI", Font.BOLD, 19));
		
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
		getContentPane().add(tables_allClientTransactionLbl);
		getContentPane().add(scrollPane);
		getContentPane().add(scrollPane_1);
		getContentPane().add(lblSpecificClient);
	}
}

