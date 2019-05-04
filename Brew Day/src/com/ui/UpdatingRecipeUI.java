package com.ui;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
public class UpdatingRecipeUI extends JFrame{
	public UpdatingRecipeUI(){
		this.setTitle("Updating a Recipe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,400,450,400);
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		JLabel label=new JLabel("List of recipe:");
		contentPane.add(label);
		JComboBox comboBox=new JComboBox();
		comboBox.addItem("recipe 1");
		comboBox.addItem("recipe 2");
		comboBox.addItem("recipe 3");
		contentPane.add(comboBox);
		
		JButton b1 = new JButton("Back");
		JButton b2 = new JButton("Continue");
		
		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainRecipeUI();
			}
		});
		
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new UpdatingRecipeModificationUI();
			}
		});
		
		contentPane.add(b1);
		contentPane.add(b2);
		
		this.setVisible(true);
	}
    protected void closeThis() {
		this.setVisible(false);
		
	}

}