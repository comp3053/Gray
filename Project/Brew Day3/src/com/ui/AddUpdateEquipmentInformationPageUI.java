package com.ui;
import com.controller.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.controller.ControllerAddingIngredient;

import mainPart.Equipment;

public class AddUpdateEquipmentInformationPageUI extends JFrame {
	private Equipment equip;
	private ControllerEquipmentAdding controllerEA;
	public  AddUpdateEquipmentInformationPageUI() throws SQLException {
		int gap = 10;
		this.setTitle("add/update equipment information");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		//set the location
		this.setLayout(null);

		JPanel pInput = new JPanel();
//////////////////////
ImageIcon image = new ImageIcon("1.jpg");//insert the picture
JLabel label = new JLabel();
label.setIcon(image);//add the picture into the label
this.add(label);
/////////////////////
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(6,1,gap,gap));


		JLabel AddUpdateEquipmentInformation = new JLabel("Add equipment information:");
		JLabel Capicity = new JLabel("Capicity(ml):");
		JTextField CapicityText = new JTextField();

		JButton AddUpdateSelection = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		equip = new Equipment();
		ArrayList<Double> equipmentList = equip.getAllEquipment();

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
							JOptionPane.showMessageDialog(null, "Successfully Adding Equipment", "Equipment Adding",JOptionPane.INFORMATION_MESSAGE); 
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
		pInput.add(AddUpdateSelection);
		pInput.add(cancel);

		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
		AddUpdateSelection.setBounds(30, 100 +30, 150, 40);
		cancel.setBounds(250, 100 + 30, 150, 40);
		ta.setBounds(gap, 150 + 600, 375, 120);


		this.add(pInput);
		this.add(AddUpdateSelection);
		this.add(cancel);
		this.add(ta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setVisible(true);

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}
