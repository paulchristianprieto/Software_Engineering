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

public class TablesRemarks extends JFrame{
	private String clientSelectedName;
	private boolean tables_validator = true;
	private JTable table_1;
	private String client_id = "";
	private JTable table;
	private JTextField tables_remindersTxt;
	private JTextField tables_remarksTxt;
	private JTextField tables_toDoTxt;
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
	public TablesRemarks() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tables.class.getResource("/jdl/Assets/login_small.png")));	
		
		//Main Panel
	
		setTitle("JDL: Remarks");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(1550, 850));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(495, 530, 1036, 309);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(32);
		table.setFont(new Font("Calibri", Font.PLAIN, 16));
		table.setBorder(null);
		table.setBounds(495, 541, 1036, 298);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setSize(1036, 280);
		scrollPane_1.setLocation(495, 209);
		
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
		tables_clientTransactionsLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tables_clientTransactionsLbl.setBounds(495, 492, 677, 37);
		getContentPane().add(tables_clientTransactionsLbl);
		
		JPanel tables_inputPanel = new JPanel();
		tables_inputPanel.setBounds(27, 234, 450, 587);
		tables_inputPanel.setBackground(new Color (255, 255, 255, 60));
		tables_inputPanel.setLayout(null);

		JComboBox tables_comboBox1 = new JComboBox();
		tables_comboBox1.setBounds(17, 129, 407, 25);
		tables_inputPanel.add(tables_comboBox1);
		tables_comboBox1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		
		tables_comboBox1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		
		JComboBox tables_comboBox = new JComboBox();
		tables_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				Connection conn2;
				
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "SELECT * FROM jdl_accounts.clients WHERE client_id=?";
					String sql2 = "SELECT * FROM jdl_accounts.transactions WHERE client_id=?";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					PreparedStatement statement3= conn2.prepareStatement(sql2);
					
					String info = (String)tables_comboBox.getSelectedItem().toString();
					
					int temp = Integer.parseInt(info.substring(info.lastIndexOf(",")+2, info.length()));
					
					client_id = String.valueOf(temp);
					statement.setInt(1, temp);
					statement3.setInt(1, temp);
					
					tables_comboBox1.removeAllItems();
					ResultSet rs = statement.executeQuery();
					
					 while(rs.next()) {
						 tables_clientTransactionsLbl.setText(info.substring(0, info.lastIndexOf(","))+" Transactions");
						}
					
					ResultSet rs1 = statement3.executeQuery();
						
						 while(rs1.next()){        
						       	tables_comboBox1.addItem(rs1.getString("trans_transId"));       
								    }
						 
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
		tables_comboBox.setBounds(17, 73, 407, 25);
		
		AutoCompletion.enable(tables_comboBox);
		tables_inputPanel.add(tables_comboBox);
		
		tables_reloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					Statement stat1=conn.createStatement();
	
					ResultSet rs1 = stat1.executeQuery("SELECT client_id AS 'Client ID' "
							+ ", trans_transId AS 'Transaction ID' " + 
							", remarks_dateReceived AS 'Date Received' " +
							", remarks_dateUpdated AS 'Date Updated' " +
							", remarks_reminders AS 'Reminders' " +
							", remarks_toDo AS 'To Do' " +
							", remarks_transaction AS 'Transaction Remark' " +
							" FROM jdl_accounts.remarks WHERE client_id ="+client_id+" ORDER BY trans_transId DESC");
					
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
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs1));
					table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					TableColumnAdjuster tca1 = new TableColumnAdjuster(table_1);
					tca1.adjustColumns();
					
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
		tables_inputSectionLbl.setBounds(32, 186, 132, 37);
		tables_inputSectionLbl.setForeground(new Color(255, 255, 255));
		tables_inputSectionLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		
		JLabel tables_dateReceivedLbl = new JLabel("Date Received:");
		tables_dateReceivedLbl.setForeground(new Color(255, 255, 255));
		tables_dateReceivedLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_dateReceivedLbl.setBounds(17, 197, 91, 45);
		tables_inputPanel.add(tables_dateReceivedLbl);
		
		//Date Input
		
		//Date Filed
		UtilDateModel dateFiledModel = new UtilDateModel();
		Properties dateFiled = new Properties();
		dateFiled.put("text.today", "Date Today");
		dateFiled.put("text.month", "Month");
		dateFiled.put("text.year", "Year");
		
		JDatePanelImpl dateFiledPanel = new JDatePanelImpl(dateFiledModel, dateFiled);

		JDatePickerImpl tables_dateReceivedTxt = new JDatePickerImpl(dateFiledPanel, new DateLabelFormatter());

		tables_dateReceivedTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_dateReceivedTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_dateReceivedTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_dateReceivedTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_dateReceivedTxt.setSize(407, 23);

		tables_inputPanel.add(tables_dateReceivedTxt);
		
		//Hearing Date
		UtilDateModel hearingDateModel = new UtilDateModel();
		Properties hearingDate = new Properties();
		hearingDate.put("text.today", "Date Today");
		hearingDate.put("text.month", "Month");
		hearingDate.put("text.year", "Year");
		
		JDatePanelImpl hearingDatePanel = new JDatePanelImpl(hearingDateModel, hearingDate);
	
		
		//Early Hearing Date
		UtilDateModel earlyHearingDateModel = new UtilDateModel();
		Properties earlyHearingDate = new Properties();
		earlyHearingDate.put("text.today", "Date Today");
		earlyHearingDate.put("text.month", "Month");
		earlyHearingDate.put("text.year", "Year");

		JDatePanelImpl earlyHearingDatePanel = new JDatePanelImpl(earlyHearingDateModel, earlyHearingDate);


		tables_dateReceivedTxt.setLocation(17, 237);
		tables_dateReceivedTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_dateReceivedTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_dateReceivedTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_dateReceivedTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_dateReceivedTxt.setSize(407, 23);

		tables_inputPanel.add(tables_dateReceivedTxt);
		
		JLabel tables_aepCancellationLbl = new JLabel("AEP Cancellation:");
		tables_dateReceivedTxt.add(tables_aepCancellationLbl);
		tables_aepCancellationLbl.setForeground(new Color(255, 255, 255));
		tables_aepCancellationLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JLabel tables_dateUpdatedLbl = new JLabel("Date Updated:");
		tables_dateUpdatedLbl.setForeground(Color.WHITE);
		tables_dateUpdatedLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_dateUpdatedLbl.setBounds(17, 262, 190, 29);
		tables_inputPanel.add(tables_dateUpdatedLbl);
		
		//Permit Date Filed
		UtilDateModel permitDateFiledModel = new UtilDateModel();
		Properties permitDateFiled = new Properties();
		permitDateFiled.put("text.today", "Date Today");
		permitDateFiled.put("text.month", "Month");
		permitDateFiled.put("text.year", "Year");
		
		JDatePanelImpl permitDateFiledPanel = new JDatePanelImpl(permitDateFiledModel, permitDateFiled);
		
		//Permit Date Released
		UtilDateModel permitDateReleasedModel = new UtilDateModel();
		Properties permitDateReleased = new Properties();
		permitDateReleased.put("text.today", "Date Today");
		permitDateReleased.put("text.month", "Month");
		permitDateReleased.put("text.year", "Year");
		
		JDatePanelImpl permitDateReleasedPanel = new JDatePanelImpl(permitDateReleasedModel, permitDateReleased);

		
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		
		JLabel tables_clientInformationLbl = new JLabel("------------------------------- Client Selection ------------------------------");
		tables_clientInformationLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientInformationLbl.setForeground(Color.WHITE);
		tables_clientInformationLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientInformationLbl.setBounds(17, 11, 411, 33);
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
		tables_clientStatusTableLbl.setBackground(Color.WHITE);
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
		tables_clientRemarksTableLbl.setForeground(Color.WHITE);
		tables_clientRemarksTableLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel label = new JLabel("");
		label.setBounds(1178, 48, 57, 37);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel tables_line = new JLabel("");
		tables_line.setBounds(1330, 96, 57, 22);
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
		tables_addClientLbl.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				new TablesAddClient().setVisible(true);
				dispose();
			}
		});
		tables_addClientLbl.setBounds(25, 48, 295, 37);
		tables_addClientLbl.setForeground(Color.LIGHT_GRAY);
		tables_addClientLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JLabel lblSpecificClient = new JLabel("Client Remarks");
		lblSpecificClient.setBounds(493, 169, 382, 37);
		lblSpecificClient.setForeground(Color.WHITE);
		lblSpecificClient.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		JDatePickerImpl tables_dateUpdatedTxt = new JDatePickerImpl(earlyHearingDatePanel, new DateLabelFormatter());
		tables_dateUpdatedTxt.setBounds(17, 290, 407, 23);
		tables_inputPanel.add(tables_dateUpdatedTxt);
		tables_dateUpdatedTxt.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		tables_dateUpdatedTxt.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		tables_dateUpdatedTxt.getJFormattedTextField().setForeground(new Color(220, 20, 60));
		tables_dateUpdatedTxt.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
				
				JLabel tables_remarksLbl = new JLabel("Transaction Remark:");
				tables_remarksLbl.setForeground(Color.WHITE);
				tables_remarksLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
				tables_remarksLbl.setBounds(17, 431, 190, 29);
				tables_inputPanel.add(tables_remarksLbl);
				
				tables_remarksTxt = new JTextField();
				tables_remarksTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
				tables_remarksTxt.setColumns(10);
				tables_remarksTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
				tables_remarksTxt.setBounds(17, 460, 407, 23);
				tables_inputPanel.add(tables_remarksTxt);
		
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
		
		JLabel lblSelectATransaction = new JLabel("Select Transaction ID:");
		lblSelectATransaction.setForeground(Color.WHITE);
		lblSelectATransaction.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblSelectATransaction.setBounds(20, 105, 146, 25);
		tables_inputPanel.add(lblSelectATransaction);

		JButton tables_registerBtn = new JButton("Insert Remark");
		tables_registerBtn.setForeground(new Color(255, 255, 255));
		tables_registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn3;
				try {
					String sql = "INSERT INTO jdl_accounts.remarks (remarks_dateReceived, remarks_dateUpdated, remarks_reminders, remarks_toDo, remarks_transaction, client_id, trans_transId)  values (?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE remarks_dateReceived = ?, remarks_dateUpdated = ?, remarks_reminders = ?,  remarks_toDo = ?, remarks_transaction = ?, "
							+ " client_id = ?, trans_transId = ? ";
					conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&compensateOnDuplicateKeyUpdateCounts=false","root","password");
					PreparedStatement statement2= conn3.prepareStatement(sql);
					
					if(tables_dateReceivedTxt.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(1, null);
					else
						statement2.setDate(1, java.sql.Date.valueOf(tables_dateReceivedTxt.getJFormattedTextField().getText().toString()));

					
					if(tables_dateUpdatedTxt.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(2, null);
					else
						statement2.setDate(2, java.sql.Date.valueOf(tables_dateUpdatedTxt.getJFormattedTextField().getText().toString()));
				
					statement2.setString(3, tables_remindersTxt.getText());
					statement2.setString(4, tables_toDoTxt.getText());
					statement2.setString(5, tables_remarksTxt.getText());
					statement2.setString(6, client_id);
					statement2.setString(7, tables_comboBox1.getSelectedItem().toString());
					
					if(tables_dateReceivedTxt.getJFormattedTextField().getText().toString().equals("")) 
						statement2.setDate(8, null);
					else
						statement2.setDate(8, java.sql.Date.valueOf(tables_dateReceivedTxt.getJFormattedTextField().getText().toString()));

					
					if(tables_dateUpdatedTxt.getJFormattedTextField().getText().toString().equals(""))
						statement2.setDate(9, null);
					else
						statement2.setDate(9, java.sql.Date.valueOf(tables_dateUpdatedTxt.getJFormattedTextField().getText().toString()));
				
					statement2.setString(10, tables_remindersTxt.getText());
					statement2.setString(11, tables_toDoTxt.getText());
					statement2.setString(12, tables_remarksTxt.getText());
					statement2.setString(13, client_id);
					statement2.setString(14, tables_comboBox1.getSelectedItem().toString());
					
					statement2.executeUpdate();
					tables_inputPanel.revalidate();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tables_registerBtn.setBounds(136, 523, 170, 34);
		tables_inputPanel.add(tables_registerBtn);
		

		tables_registerBtn.setBackground(new Color(0, 102, 102));
		tables_registerBtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		
		JLabel tables_clientDetailsLbl = new JLabel("------------------------- Client Transaction Details ------------------------");
		tables_clientDetailsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		tables_clientDetailsLbl.setForeground(Color.WHITE);
		tables_clientDetailsLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		tables_clientDetailsLbl.setBounds(17, 173, 411, 23);
		tables_inputPanel.add(tables_clientDetailsLbl);
		
		JLabel lblSelectClient = new JLabel("Choose a Client:");
		lblSelectClient.setForeground(Color.WHITE);
		lblSelectClient.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblSelectClient.setBounds(17, 45, 146, 29);
		tables_inputPanel.add(lblSelectClient);
		
		JLabel tables_remindersLbl = new JLabel("Reminders:");
		tables_remindersLbl.setForeground(Color.WHITE);
		tables_remindersLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_remindersLbl.setBounds(17, 318, 197, 29);
		tables_inputPanel.add(tables_remindersLbl);
		
		tables_remindersTxt = new JTextField();
		tables_remindersTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_remindersTxt.setColumns(10);
		tables_remindersTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_remindersTxt.setBounds(17, 347, 407, 23);
		tables_inputPanel.add(tables_remindersTxt);
		
		tables_toDoTxt = new JTextField();
		tables_toDoTxt.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		tables_toDoTxt.setColumns(10);
		tables_toDoTxt.setBorder(new EmptyBorder(0, 0, 0, 0));
		tables_toDoTxt.setBounds(17, 401, 407, 23);
		tables_inputPanel.add(tables_toDoTxt);
		
		JLabel tables_toDoLbl = new JLabel("To Do:");
		tables_toDoLbl.setForeground(Color.WHITE);
		tables_toDoLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		tables_toDoLbl.setBounds(17, 372, 197, 29);
		tables_inputPanel.add(tables_toDoLbl);
						
		getContentPane().add(scrollPane_1);
		
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
		tables_seeTablesLbl.setBounds(735, 0, 168, 37);
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
		
		getContentPane().add(lblSpecificClient);
						
						JLabel tables_background = new JLabel("");
						tables_background.setIcon(new ImageIcon(TablesRemarks.class.getResource("/jdl/Assets/background_tables4.jpg")));
						tables_background.setBounds(0, 0, 1550, 850);
						getContentPane().add(tables_background);
	}
	
	
}

