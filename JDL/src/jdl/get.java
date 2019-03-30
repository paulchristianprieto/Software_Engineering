package jdl;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class get extends JFrame {
	public get() {
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(34, 40, 153, 29);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(34, 115, 153, 29);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
	
    private JTextField textField;
    private JTextField textField_1;

    // then create setters and getter
    public void setUser(String user) {this.textField.setText(user);}
    public String getUser() {return this.textField.getText();}

    public void setPass(String pass) {this.textField_1.setText(pass);}
    public String getPass() {return this.textField_1.getText();}
}