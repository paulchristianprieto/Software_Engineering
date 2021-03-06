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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OptionList extends JFrame{
	
	private JTextField adminAcc_usernameTxt;
	private String admin_username;
	private String admin_password;
	private JTextField adminAcc_passwordTxt;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionList window = new OptionList();
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
	public OptionList() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptionList.class.getResource("/jdl/Assets/login_small.png")));
		
		//Main Panel
		
		setTitle("JDL: Options");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setMinimumSize(new Dimension(690, 480));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setBackground(new Color(90, 103, 115));
		getContentPane().setLayout(null);
		
		//Images
		
		JLabel options_tableIcon = new JLabel("");
		options_tableIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String user = adminAcc_usernameTxt.getText();
				String pass = adminAcc_passwordTxt.getText();
				Tables transactions = new Tables();
			    transactions.setUser(user);
			    transactions.setPass(pass);
			    
				transactions.setVisible(true);
				setVisible(false);
			}
		});
		
		JLabel options_close = new JLabel("");
		options_close.setBounds(654, 0, 26, 46);
		getContentPane().add(options_close);
		options_close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		options_close.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel options_profileView = new JLabel("");
		options_profileView.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				String user = adminAcc_usernameTxt.getText();
				String pass = adminAcc_passwordTxt.getText();
				
				ViewAdministratorAccount viewAccount = new ViewAdministratorAccount();
			    viewAccount.setUser(user);
			    viewAccount.setPass(pass);
			    
				viewAccount.setVisible(true);
				setVisible(false);
			}
		});
		
		options_profileView.setBounds(10, 11, 26, 29);
		getContentPane().add(options_profileView);
		options_profileView.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_viewUser.png")));
		
		JLabel options_logout = new JLabel("");
		options_logout.setBounds(74, 11, 37, 29);
		getContentPane().add(options_logout);
		options_logout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
				 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
				 	UIManager.put("Button.background", Color.WHITE);
				 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
				 	
				 
			    int reply = JOptionPane.showConfirmDialog(null, "<html><font color = #ffffff> Are you sure you want to logout of the system? </font color = #ffffff></html>", "Logout?", JOptionPane.YES_NO_OPTION);
			    	if (reply == JOptionPane.YES_OPTION) {
			    		new Login().setVisible(true);
			    		dispose();
			    	}
			}
		});
		options_logout.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_logout.png")));
		
		JLabel options_Welcome = new JLabel("Welcome Administrator");
		options_Welcome.setBounds(256, 0, 168, 46);
		getContentPane().add(options_Welcome);
		options_Welcome.setForeground(new Color(255, 255, 255));
		options_Welcome.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		options_tableIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_tableSheet.png")));
		options_tableIcon.setBounds(159, 148, 52, 58);
		getContentPane().add(options_tableIcon);
		
		JLabel options_reportIcon = new JLabel("");
		options_reportIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new GenerateReport().setVisible(true);
				dispose();
			}
		});
		options_reportIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_generateReport.png")));
		options_reportIcon.setBounds(477, 148, 52, 58);
		getContentPane().add(options_reportIcon);
		
		JLabel options_manageIcon = new JLabel("");
		options_manageIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String user = adminAcc_usernameTxt.getText();
				String pass = adminAcc_passwordTxt.getText();
				
				AccountManagement manager = new AccountManagement();
			    manager.setUser(user);
			    manager.setPass(pass);
			    
				manager.setVisible(true);
				setVisible(false);
			}
		});
		options_manageIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_management.png")));
		options_manageIcon.setBounds(149, 322, 62, 58);
		getContentPane().add(options_manageIcon);
		
		JLabel options_historyIcon = new JLabel("");
		options_historyIcon.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/options_history.png")));
		options_historyIcon.setBounds(477, 322, 62, 58);
		getContentPane().add(options_historyIcon);
		
		//Labels
		
		
		JLabel options_instructionLbl = new JLabel("What do you want to do?");
		options_instructionLbl.setForeground(Color.WHITE);
		options_instructionLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		options_instructionLbl.setBounds(231, 76, 232, 19);
		getContentPane().add(options_instructionLbl);
		
		JLabel options_seeTablesLbl = new JLabel("SEE TABLES");
		options_seeTablesLbl.setForeground(Color.WHITE);
		options_seeTablesLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		options_seeTablesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_seeTablesLbl.setBounds(106, 220, 151, 25);
		getContentPane().add(options_seeTablesLbl);
		
		JLabel options_generateLbl = new JLabel("GENERATE REPORT");
		options_generateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_generateLbl.setForeground(Color.WHITE);
		options_generateLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		options_generateLbl.setBounds(429, 220, 166, 25);
		getContentPane().add(options_generateLbl);
		
		JLabel options_manageLbl = new JLabel("MANAGE ACCOUNTS");
		options_manageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_manageLbl.setForeground(Color.WHITE);
		options_manageLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		options_manageLbl.setBounds(92, 391, 188, 25);
		getContentPane().add(options_manageLbl);
		
		JLabel options_employeeLbl = new JLabel("EMPLOYEE ACTIVITY");
		options_employeeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		options_employeeLbl.setForeground(Color.WHITE);
		options_employeeLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
		options_employeeLbl.setBounds(415, 391, 180, 25);
		getContentPane().add(options_employeeLbl);
		
		JLabel options_upload = new JLabel("");
		options_upload.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				CSVLoaderMain csvImport = new CSVLoaderMain();
				csvImport.run();
			}
		});
		options_upload.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/button_upload.png")));
		options_upload.setBounds(42, 11, 26, 29);
		getContentPane().add(options_upload);
		
		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(OptionList.class.getResource("/jdl/Assets/background_optionList4.jpg")));
		options_background.setBounds(0, 0, 690, 480);
		getContentPane().add(options_background);
		
		adminAcc_usernameTxt = new JTextField();
		adminAcc_usernameTxt.setBounds(10, 464, 0, 0);
		getContentPane().add(adminAcc_usernameTxt);
		adminAcc_usernameTxt.setColumns(10);
		
		adminAcc_passwordTxt = new JTextField();
		adminAcc_passwordTxt.setColumns(10);
		adminAcc_passwordTxt.setBounds(675, 464, 0, 0);
		getContentPane().add(adminAcc_passwordTxt);
		
	}
	
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
    
    
    
}
