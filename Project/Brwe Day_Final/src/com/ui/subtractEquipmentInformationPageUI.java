package com.ui;
// import packages
import com.controller.*;
import com.dataBase.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.dataBase.DataBase;

import mainPart.Equipment;
public class subtractEquipmentInformationPageUI extends View{ // this class extends View
	private Equipment equip;
	private ControllerEquipmentDeleting controllerED;
	public  subtractEquipmentInformationPageUI() throws SQLException {
		Font f=new Font("ÂÞÂí",Font.BOLD,20); // font
		this.setTitle("View/Delete an equipment"); //set title
		this.setSize(800, 600);  // set size 
		this.setLocation(500, 200); // set location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(700,300,450,400); // set bounds
		
		JPanel contentPane=new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5,5,5,5)); // set border
		contentPane.setBounds(10, 10, 500, 200); // set bounds 
		contentPane.setLayout(new GridLayout(7,1,10,10)); // set layout
	
		this.setContentPane(contentPane);// set the content pane
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		JLabel label=new JLabel("List of Equipment size(ml):");
		contentPane.add(label); // add label
		JComboBox comboBox=new JComboBox(); // define JComboBox
		equip = new Equipment(); // a new equipment
		
		label.setFont(f);
		ArrayList<Double> equipmentList = equip.getAllEquipment();
		for(int i=0;i<equipmentList.size();i++) {
			comboBox.addItem(equipmentList.get(i));
		}
		contentPane.add(comboBox);
		
		comboBox.setFont(f);
		JButton b1 = new JButton("Back");   // create button 1
		JButton b2 = new JButton("Delete"); // create button 2
		b1.setFont(f); // set font of button1
		b2.setFont(f); // set font of button2
		
		b1.addActionListener(new ActionListener() {	// add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event
				closeThis(); // close
				new MaintainEquipmentInformationPageUI();
			}
		});
		
		b2.addActionListener(new ActionListener() {	 // add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event
				try {
					controllerED = new ControllerEquipmentDeleting();
					controllerED.conEquipmentDeleting((double) comboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Successfully Delete the equipment "+(double) comboBox.getSelectedItem(), "Equipment Deleting",JOptionPane.INFORMATION_MESSAGE); 
					closeThis(); // close
					new subtractEquipmentInformationPageUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
		contentPane.add(comboBox); // add to contentPane
		contentPane.add(b1); // add to contentPane
		contentPane.add(b2); // add to contentPane
		this.setVisible(true); // set to visible
	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

