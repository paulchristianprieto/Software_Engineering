package jdl;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;

public class OrderBy extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderBy window = new OrderBy();
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
	public OrderBy() {
		setTitle("JDL: Order Table By");
		
		//Main Panel
		
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(112,128,144));
		
		JButton btnNewButton = new JButton("Start Date");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 204, 102));
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnNewButton.setBounds(26, 183, 159, 35);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Arrange the table by: ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(93, 63, 439, 35);
		getContentPane().add(lblNewLabel);
		
		JButton btnExpiryDate = new JButton("Expiry Date");
		btnExpiryDate.setForeground(Color.BLACK);
		btnExpiryDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnExpiryDate.setBackground(new Color(255, 204, 102));
		btnExpiryDate.setBounds(26, 239, 159, 35);
		getContentPane().add(btnExpiryDate);
		
		JButton button = new JButton("Start Date");
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		button.setBackground(new Color(255, 204, 102));
		button.setBounds(244, 183, 161, 35);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Expiry Date");
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		button_1.setBackground(new Color(255, 204, 102));
		button_1.setBounds(244, 239, 161, 35);
		getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Expiry Date");
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		button_2.setBackground(new Color(255, 204, 102));
		button_2.setBounds(450, 239, 161, 35);
		getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Start Date");
		button_3.setForeground(Color.BLACK);
		button_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		button_3.setBackground(new Color(255, 204, 102));
		button_3.setBounds(448, 183, 161, 35);
		getContentPane().add(button_3);
		
		JLabel lblVisa = new JLabel("Visa's");
		lblVisa.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisa.setForeground(Color.WHITE);
		lblVisa.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblVisa.setBounds(45, 137, 122, 35);
		getContentPane().add(lblVisa);
		
		JLabel lblPermit = new JLabel("Permit's");
		lblPermit.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermit.setForeground(Color.WHITE);
		lblPermit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblPermit.setBounds(263, 137, 122, 35);
		getContentPane().add(lblPermit);
		
		JLabel lblAep = new JLabel("AEP's");
		lblAep.setHorizontalAlignment(SwingConstants.CENTER);
		lblAep.setForeground(Color.WHITE);
		lblAep.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblAep.setBounds(468, 137, 122, 35);
		getContentPane().add(lblAep);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 102));
		panel.setBounds(0, 0, 639, 29);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel orderBy_close = new JLabel("");
		orderBy_close.setBounds(603, 0, 26, 29);
		panel.add(orderBy_close);
		orderBy_close.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		orderBy_close.setIcon(new ImageIcon(OrderBy.class.getResource("/jdl/Assets/button_close.png")));
		setBounds(100, 100, 639, 335);
		
		setMinimumSize(new Dimension(639, 335));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
