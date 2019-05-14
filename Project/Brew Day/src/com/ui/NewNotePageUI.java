package com.ui;
import mainPart.*;
import java.awt.BorderLayout;

import java.awt.Container;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

import com.exception.LargerThanEquip;

import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.controller.*;
public class NewNotePageUI extends JFrame {
	private ControllerAddingNewNote controllerANN;
	private Brew thisBrew;
	//4.1
	public  NewNotePageUI() throws SQLException, LargerThanEquip, ParseException {
		int gap = 10;
		JFrame f = new JFrame("New note");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		//set the windows in the center
		this.setLayout(null);

		this.setTitle("NewNotePageUI");
		this.setSize(800, 600);


		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(4,1,gap,gap));


		JLabel note = new JLabel("Write your note here:");
		JTextField NoteText = new JTextField();
		JLabel recipeSelection = new JLabel("Which brewing experience you want to write?");
		JTextField recipeSelectionText = new JTextField();

		JButton addNote = new JButton("Add this note");
		JButton cancel = new JButton("Cancel");
		thisBrew = new Brew();
		ArrayList<Brew> brewList = thisBrew.getAllBrewExperienceWithoutNote();
		Map<String,Integer> selected = new HashMap();
		JComboBox<String> jcb = new JComboBox<String>();
		for(int i=0;i<brewList.size();i++) {
			Brew b = brewList.get(i);
			int index = b.getIndex();
			double size = b.getBatchSize();
			String recipeName = b.getRecipeName();
			Time time = b.getTime();
			Date date = b.getDate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df1 = new SimpleDateFormat("HH-mm-ss");
			String dateSet = df.format(date);
			String timeSet = df1.format(time);
			String combine = "num:"+index+"/ "+"name:"+recipeName+"/ "+"size:"+size+"/ "+"date:"+dateSet+"/ "+"time:"+timeSet+" ";
			selected.put(combine, index);
			jcb.addItem("num:"+index+"/ "+"name:"+recipeName+"/ "+"size:"+size+"/ "+"date:"+dateSet+"/ "+"time:"+timeSet+" ");
		}


		//this.add(pInput);


		pInput.add(note);
		pInput.add(NoteText);
		pInput.add(recipeSelection);
		pInput.add(jcb);
		//pInput.add(recipeSelectionText);

		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
		addNote.setBounds(30, 180, 120, 40);
		cancel.setBounds(250, 180, 120, 40);
		ta.setBounds(50, 150 + 600, 375, 120);



		this.add(pInput);
		this.add(addNote);

		this.add(cancel);
		this.add(ta);



		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		addNote.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(NoteText.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Empty note is not allowed to be stored", "Warning",JOptionPane.WARNING_MESSAGE); 
				}
				else {
					closeThis();
					controllerANN = new ControllerAddingNewNote();
					controllerANN.ControllerAddingNewNote(selected.get(jcb.getSelectedItem()),NoteText.getText());
					JOptionPane.showMessageDialog(null, "Successfully Adding Note", "Note Adding",JOptionPane.INFORMATION_MESSAGE);
					new WriteNotePageUI();
				}
			}
		});

		cancel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new WriteNotePageUI();
			}
		});




		this.setVisible(true);

	}

	protected void closeThis() {
		this.setVisible(false);
	}



}

