package jdl;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Component;
import javax.swing.SpringLayout;

public class ViewAdministratorAccount extends JFrame{
	
	private JTextField adminAcc_usernameTxt;
	private JTextField adminAcc_passwordTxt;
	private JTextField adminAcc_userIdTxt;
	private JTextField adminAcc_lastnameTxt;
	private JTextField adminAcc_firstnameTxt;
	private JTextField adminAcc_positionTxt;
	private JTextField adminAcc_contactTxt;
	private JTextField adminAcc_emailTxt;
	private JTextField adminAcc_genderTxt;
	private JTextField adminAcc_addressTxt;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAdministratorAccount window = new ViewAdministratorAccount();
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
	
	//Username
    public void setUser(String user) {
    	this.adminAcc_usernameTxt.setText(user);
    	}
    public String getUser() {
    	return this.adminAcc_usernameTxt.getText();
    	}
    
    //Password
    public void setPass(String pass) {
    	this.adminAcc_passwordTxt.setText(pass);
    	}
    public String getPass() {
    	return this.adminAcc_passwordTxt.getText();
    	}
    
	public ViewAdministratorAccount() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAdministratorAccount.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		
		setTitle("JDL: View Account");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(550, 690));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		getContentPane().setLayout(null);
		
		//Birthdate
		
		UtilDateModel birthdateModel = new UtilDateModel();
		Properties birthdate = new Properties();
		birthdate.put("text.today", "Date Today");
		birthdate.put("text.month", "Month");
		birthdate.put("text.year", "Year");
		birthdateModel.setDate(1980, 1, 1);

		JDatePanelImpl BirthdatePanel = new JDatePanelImpl(birthdateModel, birthdate);
		JDatePickerImpl birthdatePicker = new JDatePickerImpl(BirthdatePanel, new DateLabelFormatter());
		SpringLayout springLayout = (SpringLayout) birthdatePicker.getLayout();
		springLayout.putConstraint(SpringLayout.SOUTH, birthdatePicker.getJFormattedTextField(), 0, SpringLayout.SOUTH, birthdatePicker);
		birthdatePicker.getJFormattedTextField().setForeground(new Color(255, 0, 51));

		birthdatePicker.setLocation(232, 376);
		birthdatePicker.getJFormattedTextField().setBorder(UIManager.getBorder("TextField.border"));
		birthdatePicker.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		birthdatePicker.getJFormattedTextField().setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		birthdatePicker.setSize(275, 23);
		birthdatePicker.setVisible(false);
		
		getContentPane().add(birthdatePicker);
		
		JLabel adminAcc_Welcome = new JLabel("Account Details");
		adminAcc_Welcome.setBounds(222, 0, 132, 46);
		getContentPane().add(adminAcc_Welcome);
		adminAcc_Welcome.setForeground(new Color(255, 255, 255));
		adminAcc_Welcome.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel adminAcc_lastname = new JLabel("Lastname:");
		adminAcc_lastname.setForeground(Color.WHITE);
		adminAcc_lastname.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_lastname.setBounds(153, 310, 325, 27);
		getContentPane().add(adminAcc_lastname);
		
		JLabel adminAcc_firstname = new JLabel("Firstname:");
		adminAcc_firstname.setForeground(Color.WHITE);
		adminAcc_firstname.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_firstname.setBounds(153, 341, 325, 27);
		getContentPane().add(adminAcc_firstname);
		
		JLabel adminAcc_birthdate = new JLabel("Birthdate:");
		adminAcc_birthdate.setForeground(Color.WHITE);
		adminAcc_birthdate.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_birthdate.setBounds(153, 372, 325, 27);
		getContentPane().add(adminAcc_birthdate);
		
		JLabel adminAcc_companyIcon = new JLabel("");
		adminAcc_companyIcon.setIcon(new ImageIcon(ViewAdministratorAccount.class.getResource("/jdl/Assets/client_company1.png")));
		adminAcc_companyIcon.setBounds(32, 425, 111, 116);
		getContentPane().add(adminAcc_companyIcon);
		
		JLabel adminAcc_position = new JLabel("Position:");
		adminAcc_position.setForeground(Color.WHITE);
		adminAcc_position.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_position.setBounds(153, 443, 325, 27);
		getContentPane().add(adminAcc_position);
		
		JLabel adminAcc_contact = new JLabel("Contact:");
		adminAcc_contact.setForeground(Color.WHITE);
		adminAcc_contact.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_contact.setBounds(153, 473, 325, 27);
		getContentPane().add(adminAcc_contact);
		
		JLabel adminAcc_email = new JLabel("Email:");
		adminAcc_email.setForeground(Color.WHITE);
		adminAcc_email.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_email.setBounds(153, 505, 325, 27);
		getContentPane().add(adminAcc_email);
		
		JLabel adminAcc_gender = new JLabel("Gender:");
		adminAcc_gender.setForeground(Color.WHITE);
		adminAcc_gender.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_gender.setBounds(153, 566, 325, 27);
		getContentPane().add(adminAcc_gender);
		
		JLabel adminAcc_address = new JLabel("Address:");
		adminAcc_address.setForeground(Color.WHITE);
		adminAcc_address.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_address.setBounds(153, 595, 354, 27);
		getContentPane().add(adminAcc_address);
		
		
		JLabel adminAcc_otherDetails = new JLabel("");
		adminAcc_otherDetails.setIcon(new ImageIcon(ViewAdministratorAccount.class.getResource("/jdl/Assets/client_otherDetails.png")));
		adminAcc_otherDetails.setBounds(32, 538, 111, 116);
		getContentPane().add(adminAcc_otherDetails);
		

		JLabel adminAcc_accountDetails = new JLabel("");
		adminAcc_accountDetails.setIcon(new ImageIcon(ViewAdministratorAccount.class.getResource("/jdl/Assets/client_registration.png")));
		adminAcc_accountDetails.setBounds(232, 55, 111, 116);
		getContentPane().add(adminAcc_accountDetails);
		
		JLabel adminAcc_usernameLbl = new JLabel("Username: ");
		adminAcc_usernameLbl.setForeground(Color.WHITE);
		adminAcc_usernameLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_usernameLbl.setBounds(114, 182, 77, 27);
		getContentPane().add(adminAcc_usernameLbl);
		
		JLabel adminAcc_passwordLbl = new JLabel("Password:");
		adminAcc_passwordLbl.setForeground(Color.WHITE);
		adminAcc_passwordLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_passwordLbl.setBounds(114, 212, 77, 27);
		getContentPane().add(adminAcc_passwordLbl);
		
		adminAcc_usernameTxt = new JTextField();
		adminAcc_usernameTxt.setBorder(null);
		adminAcc_usernameTxt.setEditable(false);
		adminAcc_usernameTxt.setHorizontalAlignment(SwingConstants.CENTER);
		adminAcc_usernameTxt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		adminAcc_usernameTxt.setBounds(195, 182, 212, 27);
		getContentPane().add(adminAcc_usernameTxt);
		adminAcc_usernameTxt.setColumns(10);
		
		adminAcc_passwordTxt = new JTextField();
		adminAcc_passwordTxt.setBorder(null);
		adminAcc_passwordTxt.setHorizontalAlignment(SwingConstants.CENTER);
		adminAcc_passwordTxt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		adminAcc_passwordTxt.setEditable(false);
		adminAcc_passwordTxt.setColumns(10);
		adminAcc_passwordTxt.setBounds(195, 212, 212, 27);
		getContentPane().add(adminAcc_passwordTxt);
		
		adminAcc_userIdTxt = new JTextField();
		adminAcc_userIdTxt.setBounds(10, 680, 0, 0);
		getContentPane().add(adminAcc_userIdTxt);
		adminAcc_userIdTxt.setColumns(10);
		
		adminAcc_lastnameTxt = new JTextField();
		adminAcc_lastnameTxt.setBorder(null);
		adminAcc_lastnameTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		adminAcc_lastnameTxt.setBounds(232, 310, 275, 27);
		getContentPane().add(adminAcc_lastnameTxt);
		adminAcc_lastnameTxt.setColumns(10);
		adminAcc_lastnameTxt.setVisible(false);
		
		adminAcc_firstnameTxt = new JTextField();
		adminAcc_firstnameTxt.setBorder(null);
		adminAcc_firstnameTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		adminAcc_firstnameTxt.setColumns(10);
		adminAcc_firstnameTxt.setBounds(232, 341, 275, 27);
		getContentPane().add(adminAcc_firstnameTxt);
		adminAcc_firstnameTxt.setVisible(false);
		
		adminAcc_positionTxt = new JTextField();
		adminAcc_positionTxt.setBorder(null);
		adminAcc_positionTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		adminAcc_positionTxt.setColumns(10);
		adminAcc_positionTxt.setBounds(232, 443, 275, 27);
		getContentPane().add(adminAcc_positionTxt);
		adminAcc_positionTxt.setVisible(false);
		
		adminAcc_contactTxt = new JTextField();
		adminAcc_contactTxt.setBorder(null);
		adminAcc_contactTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		adminAcc_contactTxt.setColumns(10);
		adminAcc_contactTxt.setBounds(232, 474, 275, 27);
		getContentPane().add(adminAcc_contactTxt);
		adminAcc_contactTxt.setVisible(false);
		
		adminAcc_emailTxt = new JTextField();
		adminAcc_emailTxt.setBorder(null);
		adminAcc_emailTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		adminAcc_emailTxt.setColumns(10);
		adminAcc_emailTxt.setBounds(232, 505, 275, 27);
		getContentPane().add(adminAcc_emailTxt);
		adminAcc_emailTxt.setVisible(false);
		
		adminAcc_genderTxt = new JTextField();
		adminAcc_genderTxt.setBorder(null);
		adminAcc_genderTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		adminAcc_genderTxt.setColumns(10);
		adminAcc_genderTxt.setBounds(232, 564, 275, 27);
		getContentPane().add(adminAcc_genderTxt);
		adminAcc_genderTxt.setVisible(false);
		
		adminAcc_addressTxt = new JTextField();
		adminAcc_addressTxt.setBorder(null);
		adminAcc_addressTxt.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		adminAcc_addressTxt.setColumns(10);
		adminAcc_addressTxt.setBounds(232, 595, 275, 27);
		getContentPane().add(adminAcc_addressTxt);
		adminAcc_addressTxt.setVisible(false);
		
		JLabel adminAcc_lastname1 = new JLabel("Lastname:");
		adminAcc_lastname1.setForeground(Color.WHITE);
		adminAcc_lastname1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_lastname1.setBounds(153, 310, 77, 27);
		getContentPane().add(adminAcc_lastname1);
		adminAcc_lastname1.setVisible(false);
		
		JLabel adminAcc_firstname1 = new JLabel("Firstname:");
		adminAcc_firstname1.setForeground(Color.WHITE);
		adminAcc_firstname1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_firstname1.setBounds(153, 341, 77, 27);
		getContentPane().add(adminAcc_firstname1);
		adminAcc_firstname1.setVisible(false);
		
		JLabel adminAcc_birthdate1 = new JLabel("Birthdate:");
		adminAcc_birthdate1.setForeground(Color.WHITE);
		adminAcc_birthdate1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_birthdate1.setBounds(153, 372, 77, 27);
		getContentPane().add(adminAcc_birthdate1);
		adminAcc_birthdate1.setVisible(false);
		
		
		JLabel adminAcc_position1 = new JLabel("Position:");
		adminAcc_position1.setForeground(Color.WHITE);
		adminAcc_position1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_position1.setBounds(153, 443, 77, 27);
		getContentPane().add(adminAcc_position1);
		adminAcc_position1.setVisible(false);
		
		JLabel adminAcc_contact1 = new JLabel("Contact:");
		adminAcc_contact1.setForeground(Color.WHITE);
		adminAcc_contact1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_contact1.setBounds(153, 473, 77, 27);
		getContentPane().add(adminAcc_contact1);
		adminAcc_contact1.setVisible(false);
		
		JLabel adminAcc_email1 = new JLabel("Email:");
		adminAcc_email1.setForeground(Color.WHITE);
		adminAcc_email1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_email1.setBounds(153, 505, 77, 27);
		getContentPane().add(adminAcc_email1);
		adminAcc_email1.setVisible(false);
		
		JLabel adminAcc_gender1 = new JLabel("Gender:");
		adminAcc_gender1.setForeground(Color.WHITE);
		adminAcc_gender1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_gender1.setBounds(153, 566, 77, 27);
		getContentPane().add(adminAcc_gender1);
		adminAcc_gender1.setVisible(false);
		
		JLabel adminAcc_address1 = new JLabel("Address:");
		adminAcc_address1.setForeground(Color.WHITE);
		adminAcc_address1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		adminAcc_address1.setBounds(153, 595, 77, 27);
		getContentPane().add(adminAcc_address1);
		adminAcc_address1.setVisible(false);
		
		JButton adminAcc_save = new JButton("Save");
		JButton adminAcc_editCredentials = new JButton("Edit Credentials");
		adminAcc_editCredentials.setEnabled(false);
		
		adminAcc_editCredentials.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				adminAcc_save.setEnabled(true);
				adminAcc_editCredentials.setEnabled(false);
				adminAcc_usernameTxt.setEditable(true);
				adminAcc_passwordTxt.setEditable(true);
				
				adminAcc_lastname.setVisible(false);
				adminAcc_firstname.setVisible(false);	
				adminAcc_birthdate.setVisible(false);	
				adminAcc_position.setVisible(false);
				adminAcc_contact.setVisible(false);
				adminAcc_email.setVisible(false);
				adminAcc_gender.setVisible(false);	
				adminAcc_address.setVisible(false);
				adminAcc_lastnameTxt.setVisible(true);
				adminAcc_firstnameTxt.setVisible(true);
				birthdatePicker.setVisible(true);
				adminAcc_positionTxt.setVisible(true);
				adminAcc_contactTxt.setVisible(true);
				adminAcc_emailTxt.setVisible(true);
				adminAcc_genderTxt.setVisible(true);
				adminAcc_addressTxt.setVisible(true);
				adminAcc_lastname1.setVisible(true);
				adminAcc_firstname1.setVisible(true);
				adminAcc_birthdate1.setVisible(true);
				adminAcc_position1.setVisible(true);
				adminAcc_contact1.setVisible(true);
				adminAcc_email1.setVisible(true);
				adminAcc_gender1.setVisible(true);
				adminAcc_address1.setVisible(true);
							
							
			}
		});
		adminAcc_editCredentials.setForeground(Color.WHITE);
		adminAcc_editCredentials.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		adminAcc_editCredentials.setBorder(null);
		adminAcc_editCredentials.setBackground(new Color(0, 102, 102));
		adminAcc_editCredentials.setBounds(69, 645, 184, 30);
		getContentPane().add(adminAcc_editCredentials);
		
		adminAcc_save.addActionListener(new ActionListener() {
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
					String sql1 = "UPDATE users set user_username = ?, user_password = ? WHERE user_id = ?";
					String sql2 = "SELECT * FROM jdl_accounts.employees WHERE user_id=?";
					conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					PreparedStatement statement1 = conn2.prepareStatement(sql);
					PreparedStatement statement2 = conn2.prepareStatement(sql1);
					PreparedStatement statement3 = conn2.prepareStatement(sql2);
					
					if(adminAcc_lastnameTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Administrator's Lastname must not be empty.</font color = #ffffff></html>", "Detected an empty Administrator's lastname", JOptionPane.ERROR_MESSAGE);
					}else if (adminAcc_firstnameTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Administrator's Firstname must not be empty.</font color = #ffffff></html>", "Detected an empty Administrator's firstname", JOptionPane.ERROR_MESSAGE);
					}else if(adminAcc_positionTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Administrator's Position must not be empty.</font color = #ffffff></html>", "Detected an empty or undefinable company position ", JOptionPane.ERROR_MESSAGE);
					}else if(adminAcc_genderTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Administrator's Gender must not be empty.</font color = #ffffff></html>", "Detected an empty or undefinable gender", JOptionPane.ERROR_MESSAGE);
					}else if(birthdatePicker.getJFormattedTextField().getText().toString().equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Administrator's Birthdate must not be empty.</font color = #ffffff></html>", "Detected an empty Administrator's birthdate", JOptionPane.ERROR_MESSAGE);
					}else if(adminAcc_contactTxt.getText().equals("") || adminAcc_emailTxt.getText() .equals("")) {
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>There should at least one contact information available for an employee</font color = #ffffff></html>", "Detected an empty contact no. or email", JOptionPane.ERROR_MESSAGE);
					}else {
						statement1.setString(1, adminAcc_lastnameTxt.getText());
						statement1.setString(2, adminAcc_firstnameTxt.getText());
						statement1.setString(3, adminAcc_positionTxt.getText());
						statement1.setString(4, adminAcc_genderTxt.getText());
						statement1.setDate(5, java.sql.Date.valueOf(birthdatePicker.getJFormattedTextField().getText().toString()));
						statement1.setString(6, adminAcc_addressTxt.getText());
						statement1.setString(7, adminAcc_contactTxt.getText());
						statement1.setString(8, adminAcc_emailTxt.getText());
						statement1.setString(9, adminAcc_userIdTxt.getText());
						statement1.setString(10, adminAcc_lastnameTxt.getText());
						statement1.setString(11, adminAcc_firstnameTxt.getText());
						statement1.setString(12, adminAcc_positionTxt.getText());
						statement1.setString(13, adminAcc_genderTxt.getText());
						statement1.setDate(14, java.sql.Date.valueOf(birthdatePicker.getJFormattedTextField().getText().toString()));
						statement1.setString(15, adminAcc_addressTxt.getText());
						statement1.setString(16, adminAcc_contactTxt.getText());
						statement1.setString(17, adminAcc_emailTxt.getText());
						statement1.setString(18, adminAcc_userIdTxt.getText());
						
						statement2.setString(1, adminAcc_usernameTxt.getText());
						statement2.setString(2, adminAcc_passwordTxt.getText());
						statement2.setString(3, adminAcc_userIdTxt.getText());
						
						statement1.executeUpdate();
						statement2.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Your record has been successfully updated.</font color = #ffffff></html>", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
						
						
						adminAcc_save.setEnabled(false);
						adminAcc_editCredentials.setEnabled(true);
						adminAcc_lastnameTxt.setVisible(false);
						adminAcc_firstnameTxt.setVisible(false);
						birthdatePicker.setVisible(false);
						adminAcc_positionTxt.setVisible(false);
						adminAcc_contactTxt.setVisible(false);
						adminAcc_emailTxt.setVisible(false);
						adminAcc_genderTxt.setVisible(false);
						adminAcc_addressTxt.setVisible(false);
						
						statement3.setInt(1, Integer.parseInt(adminAcc_userIdTxt.getText()));
						ResultSet rs1 = statement3.executeQuery();
						
						while(rs1.next()) {
							adminAcc_lastname.setText("Lastname: "+rs1.getString("emp_lastname"));
							adminAcc_firstname.setText("Firstname: "+rs1.getString("emp_firstname"));	
							adminAcc_birthdate.setText("Birthdate: "+rs1.getString("emp_birthdate"));	
							adminAcc_position.setText("Position: "+rs1.getString("emp_position"));
							adminAcc_contact.setText("Contact: "+rs1.getString("emp_contact"));
							adminAcc_email.setText("Email: "+rs1.getString("emp_email"));
							adminAcc_gender.setText("Gender: "+rs1.getString("emp_gender"));	
							adminAcc_address.setText("Address: "+rs1.getString("emp_address"));		
							adminAcc_lastnameTxt.setText(rs1.getString("emp_lastname"));	
							adminAcc_firstnameTxt.setText(rs1.getString("emp_firstname"));
							String dateValue = String.valueOf(rs1.getString("emp_birthdate"));
							birthdateModel.setDate(Integer.parseInt(dateValue.substring(0, dateValue.indexOf("-"))), (Integer.parseInt(dateValue.substring(dateValue.indexOf("-")+1, dateValue.lastIndexOf("-"))))-1, Integer.parseInt(dateValue.substring(dateValue.lastIndexOf("-")+1, dateValue.length())));
							birthdateModel.setSelected(true);
							adminAcc_positionTxt.setText(rs1.getString("emp_position"));
							adminAcc_contactTxt.setText(rs1.getString("emp_contact"));
							adminAcc_emailTxt.setText(rs1.getString("emp_email"));
							adminAcc_genderTxt.setText(rs1.getString("emp_gender"));	
							adminAcc_addressTxt.setText(rs1.getString("emp_address"));	
						}
						
						adminAcc_lastname.setVisible(true);
						adminAcc_firstname.setVisible(true);	
						adminAcc_birthdate.setVisible(true);	
						adminAcc_position.setVisible(true);
						adminAcc_contact.setVisible(true);
						adminAcc_email.setVisible(true);
						adminAcc_gender.setVisible(true);	
						adminAcc_address.setVisible(true);
						
						adminAcc_usernameTxt.setEditable(false);
						adminAcc_passwordTxt.setEditable(false);
					}
				}

				 catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	});

		adminAcc_save.setEnabled(false);
		adminAcc_save.setForeground(Color.WHITE);
		adminAcc_save.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		adminAcc_save.setBorder(null);
		adminAcc_save.setBackground(new Color(0, 102, 102));
		adminAcc_save.setBounds(294, 645, 184, 30);
		getContentPane().add(adminAcc_save);
		
		
		JLabel adminAcc_back = new JLabel("");
		adminAcc_back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    String user = adminAcc_usernameTxt.getText();
			    String pass1 = adminAcc_passwordTxt.getText();
			    
			    OptionList OL = new OptionList();
			    OL.setUser(user);
			    OL.setPass(pass1);
			    
				OL.setVisible(true);
				setVisible(false);
				setVisible(false);
			}
		});
		JButton adminAcc_triggerBtn = new JButton("View and Edit Other Account Details");
		adminAcc_triggerBtn.setForeground(new Color(255, 255, 255));
		adminAcc_triggerBtn.setBorder(null);
		adminAcc_triggerBtn.setBackground(new Color(0, 102, 102));
		adminAcc_triggerBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		adminAcc_triggerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				adminAcc_triggerBtn.setVisible(false);
				adminAcc_editCredentials.setEnabled(true);
				
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
					String sql = "SELECT user_id FROM jdl_accounts.users WHERE user_username=?";
					String sql1 = "SELECT * FROM jdl_accounts.employees WHERE user_id=?";
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					PreparedStatement statement1 = (PreparedStatement) conn.prepareStatement(sql1);
					
					String user = adminAcc_usernameTxt.getText();
					statement.setString(1, user);
					ResultSet rs = statement.executeQuery();
					
					while(rs.next()) {
						adminAcc_userIdTxt.setText(rs.getString("user_id"));						
					}
					
					statement1.setInt(1, Integer.parseInt(adminAcc_userIdTxt.getText()));
					ResultSet rs1 = statement1.executeQuery();
					
					while(rs1.next()) {
						adminAcc_lastname.setText("Lastname: "+rs1.getString("emp_lastname"));
						adminAcc_firstname.setText("Firstname: "+rs1.getString("emp_firstname"));	
						adminAcc_birthdate.setText("Birthdate: "+rs1.getString("emp_birthdate"));	
						adminAcc_position.setText("Position: "+rs1.getString("emp_position"));
						adminAcc_contact.setText("Contact: "+rs1.getString("emp_contact"));
						adminAcc_email.setText("Email: "+rs1.getString("emp_email"));
						adminAcc_gender.setText("Gender: "+rs1.getString("emp_gender"));	
						adminAcc_address.setText("Address: "+rs1.getString("emp_address"));		
						adminAcc_lastnameTxt.setText(rs1.getString("emp_lastname"));	
						adminAcc_firstnameTxt.setText(rs1.getString("emp_firstname"));
						String dateValue = String.valueOf(rs1.getString("emp_birthdate"));
						birthdateModel.setDate(Integer.parseInt(dateValue.substring(0, dateValue.indexOf("-"))), (Integer.parseInt(dateValue.substring(dateValue.indexOf("-")+1, dateValue.lastIndexOf("-"))))-1, Integer.parseInt(dateValue.substring(dateValue.lastIndexOf("-")+1, dateValue.length())));
						birthdateModel.setSelected(true);
						adminAcc_positionTxt.setText(rs1.getString("emp_position"));
						adminAcc_contactTxt.setText(rs1.getString("emp_contact"));
						adminAcc_emailTxt.setText(rs1.getString("emp_email"));
						adminAcc_genderTxt.setText(rs1.getString("emp_gender"));	
						adminAcc_addressTxt.setText(rs1.getString("emp_address"));	
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
			}
			}});
		
		adminAcc_triggerBtn.setBounds(128, 259, 279, 30);
		getContentPane().add(adminAcc_triggerBtn);
		
		
		adminAcc_back.setIcon(new ImageIcon(ViewAdministratorAccount.class.getResource("/jdl/Assets/button_back.png")));
		adminAcc_back.setBounds(10, 11, 30, 27);
		getContentPane().add(adminAcc_back);
		
		JLabel adminAcc_userDetailsIcon = new JLabel("");
		adminAcc_userDetailsIcon.setIcon(new ImageIcon(ViewAdministratorAccount.class.getResource("/jdl/Assets/client_infoIcon.png")));
		adminAcc_userDetailsIcon.setBounds(32, 298, 111, 116);
		getContentPane().add(adminAcc_userDetailsIcon);
		
		
		JLabel lblmoreDetails = new JLabel("---------------------------------Other Details-------------------------------");
		lblmoreDetails.setForeground(Color.WHITE);
		lblmoreDetails.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		lblmoreDetails.setBounds(31, 260, 489, 27);
		getContentPane().add(lblmoreDetails);
		
		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(ViewAdministratorAccount.class.getResource("/jdl/Assets/background_viewAccount4.jpg")));
		options_background.setBounds(0, 0, 550, 690);
		getContentPane().add(options_background);
		
		
	}
	
	
	
}
