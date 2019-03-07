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

public class Tables extends JFrame{
	private JTextField tables_passportNoTxt;
	private JTextField tables_tinIdTxt;
	private JTextField tables_visaTypeTxt;
	private JTextField tables_permitTypeTxt;
	private JTextField tables_aepIdTxt;
	private String clientSelectedName;
	private JTextField tables_clientFirstNameTxt;
	private JTextField tables_clientIdTxt;
	private boolean tables_validator = true;
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
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JTable table = new JTable();
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setRowHeight(32);
		table.setBorder(null);
	
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
	    header.setBackground(new Color(155, 177, 166));
	    header.setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
		    defaults.put("Table.alternateRowColor", new Color(155, 177, 166));
		
		//Buttons
		
		JButton tables_reloadBtn = new JButton("Reload");
		tables_reloadBtn.setForeground(new Color(255, 255, 255));
		tables_reloadBtn.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/main_refresh.png")));
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					Statement stat=conn.createStatement();
					ResultSet rs=stat.executeQuery("SELECT trans_passportNo AS 'Passport No' "+ 
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
							" FROM jdl_accounts.transactions");
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca = new TableColumnAdjuster(table);
					tca.adjustColumns();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		
		tables_reloadBtn.doClick();
		
		
		JButton tables_orderByBtn = new JButton("Order By");
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
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBackground(new Color (112, 128, 144));
		tables_inputPanel.setLayout(null);
		
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
		tables_chooseLbl.setBounds(20, 46, 190, 41);
		tables_inputPanel.add(tables_chooseLbl);
		
		JComboBox tables_comboBox = new JComboBox();
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
		
		tables_comboBox.addItem("Select a registered client last name");
		tables_comboBox.setToolTipText("");
		tables_comboBox.setName("");
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
	
		tables_comboBox.setFont(new Font("Microsoft Tai Le", Font.BOLD, 15));
		tables_comboBox.setBounds(20, 83, 400, 23);
		tables_inputPanel.add(tables_comboBox);
		
		JLabel tables_idLbl = new JLabel("ID:");
		tables_idLbl.setForeground(Color.WHITE);
		tables_idLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_idLbl.setBounds(331, 106, 89, 41);
		tables_inputPanel.add(tables_idLbl);
		
		tables_clientIdTxt = new JTextField();
		tables_clientIdTxt.setEditable(false);
		tables_clientIdTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_clientIdTxt.setColumns(10);
		tables_clientIdTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_clientIdTxt.setBounds(331, 143, 89, 23);
		tables_inputPanel.add(tables_clientIdTxt);
		
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
		
		JButton tables_registerBtn = new JButton("Register Info");
		
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn2;
				try {
					String sql = "UPDATE jdl_accounts.transactions SET trans_passportNo = ?, trans_tinID = ?, trans_visaType = ?, trans_visaStartDate = ?, trans_visaEndDate = ?, trans_permitType = ?, trans_permitStartDate = ?, trans_permitEndDate = ?, trans_aepID = ?, trans_aepStartDate = ?,"
							+ "trans_aepEndDate = ? WHERE client_id =?";
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
					
					statement1.executeUpdate();
					tables_inputPanel.revalidate();
				}

				 catch (SQLException e1) {
					e1.printStackTrace();
						
				}
			}
	});
		
		tables_registerBtn.setBackground(new Color(255, 204, 51));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_registerBtn.setBounds(134, 675, 173, 35);
		tables_inputPanel.add(tables_registerBtn);
		
		JLabel lblClientInformation = new JLabel("------------------------ Client Information Details -----------------------");
		lblClientInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblClientInformation.setForeground(Color.WHITE);
		lblClientInformation.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		lblClientInformation.setBounds(20, 11, 400, 41);
		tables_inputPanel.add(lblClientInformation);
		
		JLabel tables_clientTransactionTableLbl = new JLabel("Client Transaction Table", SwingConstants.CENTER);
		tables_clientTransactionTableLbl.setForeground(Color.WHITE);
		tables_clientTransactionTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_clientStatusTableLbl = new JLabel("Client Status Table", SwingConstants.CENTER);
		tables_clientStatusTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new TablesStatus().setVisible(true);
				dispose();
			}
		});
		tables_clientStatusTableLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientStatusTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel tables_clientRemarksTableLbl = new JLabel("Client Remarks Table", SwingConstants.CENTER);
		tables_clientRemarksTableLbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new TablesRemarks().setVisible(true);
				dispose();
			}
		});
		tables_clientRemarksTableLbl.setForeground(Color.LIGHT_GRAY);
		tables_clientRemarksTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_line = new JLabel("");
		tables_line.setIcon(new ImageIcon(Tables.class.getResource("/jdl/Assets/line.png")));
		tables_line.setHorizontalAlignment(SwingConstants.CENTER);
		tables_line.setForeground(Color.WHITE);
		tables_line.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tables_titlePanel, GroupLayout.PREFERRED_SIZE, 1551, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(360)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tables_clientTransactionTableLbl, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(tables_clientStatusTableLbl, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tables_clientRemarksTableLbl, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(tables_inputSectionLbl, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addGap(235)
					.addComponent(tables_line, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(671)
					.addComponent(tables_orderByBtn, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(tables_reloadBtn, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(tables_inputPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1040, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tables_titlePanel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(tables_clientTransactionTableLbl, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(tables_clientStatusTableLbl, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(tables_clientRemarksTableLbl, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(23)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(tables_inputSectionLbl, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addComponent(tables_orderByBtn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addComponent(tables_reloadBtn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tables_line, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tables_inputPanel, GroupLayout.PREFERRED_SIZE, 721, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 721, GroupLayout.PREFERRED_SIZE)))
		);
		
		getContentPane().setLayout(groupLayout);
		
	}
}

