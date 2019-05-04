package com.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class subtractEquipmentInformationPageUI extends JFrame{
	public  subtractEquipmentInformationPageUI() {
		int gap = 10;
		this.setTitle("Subtract equipment information");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(50, gap, 375, 150);
		pInput.setLayout(new GridLayout(4,1,gap,gap));
		
		
		JLabel SubtractUpdateEquipmentInformation = new JLabel("Subtract equipment information:");
		JLabel Capicity = new JLabel("Capicity:");
		
		JButton SubtractUpdateSelection = new JButton("Subtract");
		JButton cancel = new JButton("Cancel");
		
		String[] sg= {"XXX1","XXX2","XXX3","XXX4"};
		JComboBox<String> jcb = new JComboBox<String>(sg);
		
		cancel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainEquipmentInformationPageUI();
			}
		});

		pInput.add(SubtractUpdateEquipmentInformation);
		pInput.add(Capicity);
		pInput.add(jcb);
		pInput.add(SubtractUpdateSelection);
		pInput.add(cancel);
		
		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
		SubtractUpdateSelection.setBounds(50, 190, 150, 40);
		cancel.setBounds(280, 190, 150, 40);
		ta.setBounds(40, 150 + 600, 375, 120);


		this.add(pInput);
		this.add(SubtractUpdateSelection);
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

