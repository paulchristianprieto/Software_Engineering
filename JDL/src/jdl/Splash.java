package jdl;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Rectangle;
import javax.swing.border.EmptyBorder;

public class Splash extends JWindow {

    static boolean isRegistered;
    private static JProgressBar splash_progressBar = new JProgressBar();
    private static Splash execute;
    private static int count;
    private static Timer time;

    public Splash() {
    	getContentPane().setForeground(Color.WHITE);
    	setForeground(Color.WHITE);
    	
    	//Panels and Containers
    	
    	getContentPane().setBackground(new Color(19, 19, 19));
    	setBounds(new Rectangle(417, 0, 217, 225));
    	getContentPane().setBounds(new Rectangle(417, 0, 217, 225));
        Container container = getContentPane();
        container.setLayout(null);
        
        //Labels
        
        JLabel splash_textDisplay = new JLabel("Setting up your workspace, please wait...");
        splash_textDisplay.setForeground(new Color(255, 255, 255));
        splash_textDisplay.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
        splash_textDisplay.setBounds(107, 228, 277, 22);
        getContentPane().add(splash_textDisplay);
        
        //Images 
        
        JLabel splash_loaderIcon = new JLabel("");
        splash_loaderIcon.setIcon(new ImageIcon(Splash.class.getResource("/jdl/Assets/Splash_hourglass.gif")));
        splash_loaderIcon.setBounds(143, 44, 202, 188);
        getContentPane().add(splash_loaderIcon);
        loadProgressBar();
        setSize(499, 279);
        setLocationRelativeTo(null);
        setVisible(true);

        
        //Progress Bar
  
        splash_progressBar.setBackground(new Color(255, 255, 255));
        splash_progressBar.setForeground(new Color(255, 204, 51));
        splash_progressBar.setMaximum(50);
        splash_progressBar.setBounds(0, 261, 499, 18);
        splash_progressBar.setBorder(new EmptyBorder(0, 0, 0, 0));
        container.add(splash_progressBar);
        
        //Progress Bar Function
    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;
                splash_progressBar.setValue(count);

                if (count == 200) {
                    loadFrame();
                    execute.setVisible(false);
                    time.stop();
                }
            }
            
        //Load Login Frame

            private void loadFrame() throws HeadlessException {
                new Login().setVisible(true);
                dispose();
            }
        };
        
        time = new Timer(50, al);
        time.start();
    }

    public static void main(String[] args) {
        execute = new Splash();
    }
};