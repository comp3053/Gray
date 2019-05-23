package com.ui;
import mainPart.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.controller.ControllerUpdatingRecipe;
import com.dataBase.DataBase;

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
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.controller.*;


public class NoteTrueModificationUI extends View {
	private ControllerModificationNote controllerMN;
	private Note thisNote;
	public  NoteTrueModificationUI(int index, String noteInfo) throws SQLException, ParseException{
        //set ui
		int gap = 100;
        //set the title
		this.setTitle("Note Modification");
		this.setSize(800, 700);
		this.setLocation(550, 200);
		this.setLayout(null);
		Font f1=new Font("ÂÞÂí",Font.BOLD,20);
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 600, 400);
		pInput.setLayout(new GridLayout(4,1,10,10));


		// Recipe Name
		Font f2=new Font("ÂÞÂí",Font.BOLD,16);
		
		JLabel malts = new JLabel("Notes:");
		malts.setFont(f1);
		JTextArea maltsText = new JTextArea(20,20);
		maltsText.setLineWrap(true);   
		maltsText.setFont(f2);
		thisNote = new Note();
		thisNote = thisNote.getSpecificNote(index);
		maltsText.setText(thisNote.getContent());
        //show the information for the modification
		JLabel maltsNewValue = new JLabel("Brew info:"+noteInfo+"\n");
		JLabel maltsNewValue2 = new JLabel("Last modified:"+thisNote.getDate()+" "+thisNote.getTime());
        maltsNewValue.setFont(f2);
		maltsNewValue2.setFont(f2);
        //add new buttons
		JButton b1 = new JButton("Modify");
		JButton b2 = new JButton("Back");
		b1.setFont(f1);
		b2.setFont(f1);

		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Empty input 
				if(maltsText.getText().length()==0) {
                    //if wirte empty note
					int result = JOptionPane.showConfirmDialog(null, "Do you want to wirte empty note for this brew?",    "Empty warning", JOptionPane.YES_NO_OPTION); 
					if(result==JOptionPane.YES_OPTION) {
                        //if successfully updating note
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
                    //Successfully Updating Note
					controllerMN = new ControllerModificationNote();
					controllerMN.conModificationNote(index,maltsText.getText());
					closeThis();
					JOptionPane.showMessageDialog(null, "Successfully Updating Note", "Note Updating",JOptionPane.INFORMATION_MESSAGE); 
					new ModificationNotePageUI();
				}
			}
		});
        //jump to the ModificationNotePageUI
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new ModificationNotePageUI();
			}
		});
        //add all contents into the interface
		pInput.add(malts);
		pInput.add(maltsText);
		pInput.add(maltsNewValue);
		pInput.add(maltsNewValue2);

		b1.setBounds(200, 500, 120, 50);
		b2.setBounds(400, 500, 120, 50);
        
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
