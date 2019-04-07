package jdl;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import java.awt.Color;
import java.awt.Font;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import au.com.bytecode.opencsv.CSVReader;

public class CSVLoaderMain {
	
	String word = "NULL";
		public void run() {
		//Finds the file in the directory
		
		JButton open = new JButton();
		JFileChooser	fc = new JFileChooser();
		fc.setCurrentDirectory(new java.io.File("C:/"));
		fc.setDialogTitle("Find CSV File");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
			//
		}
		String directory = fc.getSelectedFile().getAbsolutePath(); //The CSV file location
		System.out.println(directory);
		
		//Read CSV
		try {
			
			UIManager.put("OptionPane.background",new ColorUIResource(90, 103, 115));
		 	UIManager.put("Panel.background",new ColorUIResource(90, 103, 115));
		 	UIManager.put("OptionPane.messageFont", new Font("Segoe UI Semibold", Font.BOLD, 14));
		 	UIManager.put("Button.background", Color.WHITE);
		 	UIManager.put("OptionPane.foreground",new ColorUIResource(90, 103, 115));
		 	
			Connection conn1=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdl_accounts?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password");
				
				Path path = Paths.get(directory);
				Charset charset = StandardCharsets.UTF_8;
				String content = new String(Files.readAllBytes(path), charset);
				Files.write(path, content.getBytes(charset));
				
			CSVLoader loader = new CSVLoader(conn1);
			loader.loadCSV(directory, "transactions", true);
			
			JOptionPane.showMessageDialog(null, "<html><font color = #ffffff>Data import from path "+directory+"has been successful</font color = #ffffff></html>", "Insert Successful", JOptionPane.INFORMATION_MESSAGE);
			
		}catch(Exception exc) {
			exc.printStackTrace();
			
		}
	} 
}
