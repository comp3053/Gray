package com.ui;
import mainPart.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.controller.ControllerUpdatingRecipe;
import com.dataBase.DataBase;
import com.exception.LargerThanEquip;

import mainPart.Recipe;
import mainPart.RecipeIngredient;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.controller.*;


public class NoteTrueModificationUI extends JFrame {
	private ControllerModificationNote controllerMN;
	private Note thisNote;
	public  NoteTrueModificationUI(int index, String noteInfo) throws SQLException, ParseException{
		int gap = 100;
		this.setTitle("Adding amount of ingredient");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 500, 200);
		pInput.setLayout(new GridLayout(3,1,10,10));


		// Recipe Name

		//!!!
		JLabel malts = new JLabel("Notes:");
		JTextField maltsText = new JTextField();
		thisNote = new Note();
		thisNote = thisNote.getSpecificNote(index);
		maltsText.setText(thisNote.getContent());
		JLabel maltsNewValue = new JLabel("Brew info:"+noteInfo+"\nLast modified:"+thisNote.getDate()+" "+thisNote.getTime());
		JLabel maltsNewValue2 = new JLabel("Last modified:"+thisNote.getDate()+" "+thisNote.getTime());

		JButton b1 = new JButton("Modify");
		JButton b2 = new JButton("Back");


		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Empty input 
				if(maltsText.getText().length()==0) {
					int result = JOptionPane.showConfirmDialog(null, "Do you want to wirte empty note for this brew?",    "Empty warning", JOptionPane.YES_NO_OPTION); 
					if(result==JOptionPane.YES_OPTION) {
						controllerMN = new ControllerModificationNote();
						controllerMN.conModificationNote(index,maltsText.getText());
						closeThis();
						JOptionPane.showMessageDialog(null, "Successfully Updating Note", "Note Updating",JOptionPane.INFORMATION_MESSAGE); 
						new ModificationNotePageUI();
					}
					else {
						//closeThis();
						//new InputUI();
					}
				}
				else {
					controllerMN = new ControllerModificationNote();
					controllerMN.conModificationNote(index,maltsText.getText());
					closeThis();
					JOptionPane.showMessageDialog(null, "Successfully Updating Note", "Note Updating",JOptionPane.INFORMATION_MESSAGE); 
					new ModificationNotePageUI();
				}
			}
		});

		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new ModificationNotePageUI();
			}
		});


		pInput.add(malts);
		pInput.add(maltsText);
		pInput.add(maltsNewValue);
		pInput.add(maltsNewValue2);

		b1.setBounds(150, 380, 120, 50);
		b2.setBounds(350, 380, 120, 50);


		this.add(pInput);
		this.add(b1);
		this.add(b2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setVisible(true);

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}
