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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GenerateExpiry extends JFrame{
	private String admin_username;
	private String admin_password;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateExpiry window = new GenerateExpiry();
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
	public GenerateExpiry() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GenerateExpiry.class.getResource("/jdl/Assets/login_small.png")));
		
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255,255,255,60));
		panel.setBounds(29, 69, 188, 238);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel generate_selectSpanLbl = new JLabel("Select Range:");
		generate_selectSpanLbl.setBounds(20, 9, 99, 20);
		panel.add(generate_selectSpanLbl);
		generate_selectSpanLbl.setForeground(Color.WHITE);
		generate_selectSpanLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JComboBox comboBox_selectSpan = new JComboBox();
		comboBox_selectSpan.setBounds(20, 40, 149, 28);
		panel.add(comboBox_selectSpan);
		comboBox_selectSpan.setForeground(Color.BLACK);
		comboBox_selectSpan.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JComboBox comboBox_selectMonthWeek = new JComboBox();
		comboBox_selectMonthWeek.setBounds(20, 110, 149, 28);
		panel.add(comboBox_selectMonthWeek);
		comboBox_selectMonthWeek.setForeground(Color.BLACK);
		comboBox_selectMonthWeek.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel generate_selectMonthWeekLbl = new JLabel("Select Month/Week:");
		generate_selectMonthWeekLbl.setBounds(20, 79, 149, 20);
		panel.add(generate_selectMonthWeekLbl);
		generate_selectMonthWeekLbl.setForeground(Color.WHITE);
		generate_selectMonthWeekLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JComboBox comboBox_selectYear = new JComboBox();
		comboBox_selectYear.setBounds(20, 186, 149, 28);
		panel.add(comboBox_selectYear);
		comboBox_selectYear.setForeground(Color.BLACK);
		comboBox_selectYear.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		
		JLabel generate_selectYearLbl = new JLabel("Select Year:");
		generate_selectYearLbl.setBounds(20, 155, 86, 20);
		panel.add(generate_selectYearLbl);
		generate_selectYearLbl.setForeground(Color.WHITE);
		generate_selectYearLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		
		JLabel generate_minimizeBtn = new JLabel("");
		generate_minimizeBtn.setBounds(654, 0, 26, 46);
		getContentPane().add(generate_minimizeBtn);
		generate_minimizeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		generate_minimizeBtn.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/button_minimizer.png")));
		
		JLabel generate_expiry = new JLabel("Number of Expiry");
		generate_expiry.setBounds(285, 0, 138, 46);
		getContentPane().add(generate_expiry);
		generate_expiry.setForeground(new Color(255, 255, 255));
		generate_expiry.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		
		JLabel generate_reportBackBtn = new JLabel("");
		generate_reportBackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new GenerateReport().setVisible(true);
				dispose();
			}
		});
		
		generate_reportBackBtn.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/button_back.png")));
		generate_reportBackBtn.setBounds(10, 15, 48, 14);
		getContentPane().add(generate_reportBackBtn);
		
		JLabel generate_countExpiryLbl = new JLabel("");
		generate_countExpiryLbl.setForeground(Color.WHITE);
		generate_countExpiryLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 99));
		generate_countExpiryLbl.setBounds(70, 318, 110, 95);
		getContentPane().add(generate_countExpiryLbl);
		
		JLabel generate_countExpiryNameLbl = new JLabel("TOTAL EXPIRY");
		generate_countExpiryNameLbl.setForeground(Color.WHITE);
		generate_countExpiryNameLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		generate_countExpiryNameLbl.setBounds(72, 420, 110, 14);
		getContentPane().add(generate_countExpiryNameLbl);
		
		JLabel options_background = new JLabel("");
		options_background.setIcon(new ImageIcon(GenerateExpiry.class.getResource("/jdl/Assets/background_optionList4.jpg")));
		options_background.setBounds(0, 0, 690, 480);
		getContentPane().add(options_background);
		
	}
}
