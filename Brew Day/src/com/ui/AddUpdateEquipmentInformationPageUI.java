package com.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddUpdateEquipmentInformationPageUI extends JFrame {
	public  AddUpdateEquipmentInformationPageUI() {
		int gap = 10;
		this.setTitle("add/update equipment information");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(6,1,gap,gap));
		
		
		JLabel AddUpdateEquipmentInformation = new JLabel("Add equipment information:");
		JLabel Capicity = new JLabel("Capicity:");
		JTextField CapicityText = new JTextField();
		
		JButton AddUpdateSelection = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		
		
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
