package com.ui;
import mainPart.*;
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
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int gap = 10;
		JFrame f = new JFrame("New note");
		Font f1=new Font("ÂÞÂí",Font.BOLD,20);
		this.setLocation(600, 200);
		this.setLayout(new FlowLayout());
		Font f2=new Font("ÂÞÂí",Font.BOLD,15);
		this.setTitle("NewNotePageUI");
		this.setSize(600, 600);


		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 600, 120);
		pInput.setLayout(new GridLayout(4,1,gap,gap));
		

		JLabel note = new JLabel("Write your note here:");
		JTextArea NoteText = new JTextArea(5,20);
		NoteText.setLineWrap(true);   
		NoteText.setFont(f2);
		JLabel recipeSelection = new JLabel("Which brewing experience you want to write?");
		note.setFont(f1);
		recipeSelection.setFont(f1);
		
		JButton addNote = new JButton("Add");
		JButton cancel = new JButton("Cancel");
		addNote.setFont(f1);cancel.setFont(f1);
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

		
		addNote.setBounds(30, 400, 30, 40);
		cancel.setBounds(250, 180, 10, 40);

		this.add(pInput);
		this.add(addNote);
		this.add(cancel);
	



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

