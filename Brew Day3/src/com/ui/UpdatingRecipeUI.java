package com.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dataBase.DataBase;
 
public class UpdatingRecipeUI extends JFrame{
	public UpdatingRecipeUI() throws SQLException{
		this.setTitle("Updating a Recipe");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,400,450,400);
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.setContentPane(contentPane);
		this.setLocation(200, 200);
		//set the location
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		JLabel label=new JLabel("List of recipe:");
		contentPane.add(label);
		JComboBox comboBox=new JComboBox();
		ArrayList<String> recipeList = DataBase.getAllRecipe();
		for(int i=0;i<recipeList.size();i++) {
			comboBox.addItem(recipeList.get(i));
		}
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
				// Get the selected item from the box
				new UpdatingRecipeModificationUI((String) comboBox.getSelectedItem());
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