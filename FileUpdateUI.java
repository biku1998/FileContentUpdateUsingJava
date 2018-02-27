import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.swing.*;

public class FileUpdateUI extends JFrame implements ActionListener{
	
	JLabel filep,filed;
	JTextField filepath;
	JTextArea filedata;
	JScrollPane scroll;
	JButton open , update;

	public FileUpdateUI() {
		super.setTitle("File Update");
		super.setBounds(50, 30, 700, 700);
		// --

		filep = new JLabel("File Path :");
		filep.setBounds(80, 20, 60, 35);
		super.add(filep);
		
		filepath =  new  JTextField();
		filepath.setBounds(170, 20, 470, 30);
		super.add(filepath);
		
		filed = new JLabel("File data :");
		filed.setBounds(80, 50, 60, 35);
		super.add(filed);
		
		filedata = new JTextArea();
		scroll = new JScrollPane(filedata);
		scroll.setBounds(10, 80, 665, 530);
		super.add(scroll);
		
		open =  new JButton("Open");
		open.setBounds(100, 620, 100, 30);
		super.add(open);
		open.addActionListener(this);
		
		update =  new JButton("Update");
		update.setBounds(430, 620, 140, 30);
		super.add(update);
		update.addActionListener(this);
		
		
		
		
		// --

		super.setLayout(null);
		super.setVisible(true);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		FileUpdateUI ref = new FileUpdateUI();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 String path="";
		if(e.getSource()==open) {
			Font f = new Font("Franklin Gothic", Font.BOLD, 13);
			filedata.setFont(f);
			
			
			FileDialog fd = new FileDialog(this, "Open File to Update", FileDialog.LOAD);
			fd.setVisible(true);
			
			String fname = fd.getFile();
			String dirname = fd.getDirectory();
			
			 path = dirname.concat(fname);
			 
			
			filepath.setText(path);
			filepath.setFont(f);
			
			try {
				StringBuilder data = new StringBuilder();
				FileInputStream fis = new FileInputStream(path);
				while(true) {
				int x = fis.read();
				if(x==-1) {
					break;
				}
				
				data.append((char)x);
				}
				
				String d =data.toString();
				filedata.setText(d);
				filedata.setFont(f);
				
				
			} catch (Exception e2) {
				System.out.println("Error "+e);
			}
			
		}
		else if(e.getSource()==update) {
			try {
			  String p = filepath.getText();
				FileOutputStream fos = new FileOutputStream(p,false);
				String textdata = filedata.getText();
				
				byte[] d = textdata.getBytes();
				for (int i = 0; i < d.length; i++) {
					fos.write(d[i]);
				}
				
				JOptionPane.showMessageDialog(this, "File Updated");
			
			} catch (Exception e2) {
				System.out.println("Error   "+e);
			}
			
			
			
		}
		
	}

}
