package com.ui;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controller.ControllerDeletingRecipe;
import com.dataBase.DataBase;
 
public class DeletingRecipeUI extends JFrame{
	ControllerDeletingRecipe controllerDR;
	public DeletingRecipeUI() throws SQLException{
		this.setTitle("Delete a Recipe");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		//set the location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,400,450,400);
		
		JPanel contentPane=new JPanel();
//////////////////////
ImageIcon image = new ImageIcon("1.jpg");//insert the picture
JLabel label = new JLabel();
label.setIcon(image);//add the picture into the label
this.add(label);
/////////////////////
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBounds(10, 10, 500, 200);
		contentPane.setLayout(new GridLayout(7,1,10,10));
	
		this.setContentPane(contentPane);
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
		JButton b2 = new JButton("Delete");
		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainRecipeUI();
			}
		});
		// Button for deleting the recipe
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controllerDR = new ControllerDeletingRecipe();
					controllerDR.ConDeletingRecipe((String) comboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Successfully Delete the recipe", "Recipe Deleting",JOptionPane.INFORMATION_MESSAGE); 
					closeThis();
					new DeletingRecipeUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
		contentPane.add(comboBox);
		contentPane.add(b1);
		contentPane.add(b2);
		this.setVisible(true);
	}
    protected void closeThis() {
		this.setVisible(false);
		
	}
	
}