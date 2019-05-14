package com.ui;
import com.controller.*;
import com.dataBase.*;
import java.awt.FlowLayout;
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
import javax.swing.border.EmptyBorder;

import com.dataBase.DataBase;

import mainPart.Equipment;
public class subtractEquipmentInformationPageUI extends JFrame{
	private Equipment equip;
	private ControllerEquipmentDeleting controllerED;
	public  subtractEquipmentInformationPageUI() throws SQLException {
		this.setTitle("Delete a equipment");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		//set the location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400,400,450,400);
		
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBounds(10, 10, 500, 200);
		contentPane.setLayout(new GridLayout(7,1,10,10));
	
		this.setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		JLabel label=new JLabel("List of Equipment size:");
		contentPane.add(label);
		JComboBox comboBox=new JComboBox();
		equip = new Equipment();
		
		ArrayList<Double> equipmentList = equip.getAllEquipment();
		for(int i=0;i<equipmentList.size();i++) {
			comboBox.addItem(equipmentList.get(i));
		}
		contentPane.add(comboBox);
		
		
		JButton b1 = new JButton("Back");
		JButton b2 = new JButton("Delete");
		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainEquipmentInformationPageUI();
			}
		});
		
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controllerED = new ControllerEquipmentDeleting();
					controllerED.conEquipmentDeleting((double) comboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Successfully Delete the equipment", "Equipment Deleting",JOptionPane.INFORMATION_MESSAGE); 
					closeThis();
					new subtractEquipmentInformationPageUI();
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
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

