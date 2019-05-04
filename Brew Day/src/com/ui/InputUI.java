package com.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.dataBase.DataBase;

public class InputUI extends JFrame {
	public InputUI() {
		this.setTitle("MainPageUI");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(5, 1, 20, 10));
		// Create four buttons and add them to the panel (not the frame).
		// The four buttons always stay together even when you resize.
		JTextField recipeSelectionText = new JTextField();
		
		JTextArea ta = new JTextArea();
		p.add(new JLabel("Please input litre you wan to brew(ml)"));
		//p.add(ta);
		p.add(recipeSelectionText);
		this.add(p); // Add panel to the frame.
		JButton b1 =new JButton("Recommemd");
		JButton b2 =new JButton("Recommend with missing ingredient" );
		JButton b3 =new JButton("Cancel");
		p.add(b1);
		p.add(b2);
		p.add(b3);
		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				recipeSelectionText.getText();
				int batchSize=0; 
				try{
					batchSize=Integer.parseInt(recipeSelectionText.getText());
				}catch(Throwable e1){
					batchSize=-1; 
				}
				
				try {
					if(DataBase.checkBatchSize(batchSize)==true) {
						closeThis();
						new RecommendRecipe(batchSize);
					}
					else {
						JOptionPane.showMessageDialog(null, "You do not have satisfied equipment for this batch size", "Warning",JOptionPane.WARNING_MESSAGE); 
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new RecipeMissingSomeUI();
			}
		});
		
		b3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MainPageView();
			}
		});
		
		
		this.setVisible(true);
	
	}
	protected void closeThis() {
		this.setVisible(false);
	}
	
}
