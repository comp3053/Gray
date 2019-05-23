package com.ui;
import com.controller.*;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.controller.ControllerAddingIngredient;

import mainPart.Equipment;

public class AddUpdateEquipmentInformationPageUI extends View {
	private Equipment equip;
	private ControllerEquipmentAdding controllerEA;
	public  AddUpdateEquipmentInformationPageUI() throws SQLException {
		Font f=new Font("ÂÞÂí",Font.BOLD,19);
		int gap = 0;
		this.setTitle("Add New");
		this.setSize(300, 400);
		this.setLocation(700, 200);
		this.setLayout(new FlowLayout());

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(6,1,gap,gap));

		JLabel AddUpdateEquipmentInformation = new JLabel("Add Equipment Information");
		JLabel Capicity = new JLabel("Capicity(ml):");
		JTextField CapicityText = new JTextField();
		AddUpdateEquipmentInformation.setFont(f);
		Capicity.setFont(f);

		JButton AddUpdateSelection = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		equip = new Equipment();
		ArrayList<Double> equipmentList = equip.getAllEquipment();

		AddUpdateSelection.setFont(f);
		cancel.setFont(f);
		
		AddUpdateSelection.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(CapicityText.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Empty input is not allowed, try again!", "Warning",JOptionPane.WARNING_MESSAGE); 
				}
				else {
					equip = new Equipment();
					int status = equip.checkValidNumber(CapicityText.getText());
					// Negative
					if(status==1) {
						JOptionPane.showMessageDialog(null, "The input number should be larger than 0", "Warning",JOptionPane.WARNING_MESSAGE); 
						closeThis();
						try {
							new AddUpdateEquipmentInformationPageUI();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					// Good
					if(status==2) {
						double capacity = Integer.parseInt(CapicityText.getText());
						if(equipmentList.contains(capacity)==true) {
							JOptionPane.showMessageDialog(null, "Such equipment has existed", "Warning",JOptionPane.WARNING_MESSAGE); 
						}
						else {
							controllerEA = new ControllerEquipmentAdding();
							controllerEA.conEquipmentAdding(capacity);
							JOptionPane.showMessageDialog(null, "Successfully Adding Equipment "+capacity, "Equipment Adding",JOptionPane.INFORMATION_MESSAGE); 
							closeThis();
							new MaintainEquipmentInformationPageUI();
						}
					}
					// No valid input
					if(status==3) {
						JOptionPane.showMessageDialog(null, "Input should be number!", "Warning",JOptionPane.WARNING_MESSAGE); 
						closeThis();
						try {
							new AddUpdateEquipmentInformationPageUI();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});



		cancel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainEquipmentInformationPageUI();
			}
		});

		pInput.add(AddUpdateEquipmentInformation);
		pInput.add(Capicity);
		pInput.add(CapicityText);
		this.add(AddUpdateSelection);
		this.add(cancel);

		
		AddUpdateSelection.setBounds(30, 100 +30, 150, 40);
		cancel.setBounds(250, 100 + 30, 150, 40);
		
		
		this.add(pInput);
		this.add(AddUpdateSelection);
		this.add(cancel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setVisible(true);

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}
