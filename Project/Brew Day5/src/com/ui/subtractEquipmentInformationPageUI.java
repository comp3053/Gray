package com.ui;
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
public class subtractEquipmentInformationPageUI extends JFrame{
	private Equipment equip;
	private ControllerEquipmentDeleting controllerED;
	public  subtractEquipmentInformationPageUI() throws SQLException {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Font f=new Font("ÂÞÂí",Font.BOLD,20);
		this.setTitle("View/Delete an equipment");
		this.setSize(800, 600);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(700,300,450,400);
		
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBounds(10, 10, 500, 200);
		contentPane.setLayout(new GridLayout(7,1,10,10));
	
		this.setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		JLabel label=new JLabel("List of Equipment size(ml):");
		contentPane.add(label);
		JComboBox comboBox=new JComboBox();
		equip = new Equipment();
		
		label.setFont(f);
		ArrayList<Double> equipmentList = equip.getAllEquipment();
		for(int i=0;i<equipmentList.size();i++) {
			comboBox.addItem(equipmentList.get(i));
		}
		contentPane.add(comboBox);
		
		comboBox.setFont(f);
		JButton b1 = new JButton("Back");
		JButton b2 = new JButton("Delete");
		b1.setFont(f);
		b2.setFont(f);
		
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
					JOptionPane.showMessageDialog(null, "Successfully Delete the equipment "+(double) comboBox.getSelectedItem(), "Equipment Deleting",JOptionPane.INFORMATION_MESSAGE); 
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

