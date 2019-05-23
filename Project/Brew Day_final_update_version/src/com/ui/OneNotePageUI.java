package com.ui;
// import packages
import mainPart.*;
import java.awt.*;
import javax.swing.*;

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
public class OneNotePageUI extends View { // this class extends View
	private ControllerAddingNewNote controllerANN;
	private Brew thisBrew;
	//4.1
	public  OneNotePageUI() throws SQLException, ParseException {
		Font f1=new Font("ÂÞÂí",Font.BOLD,20); // font
		Font f2=new Font("ÂÞÂí",Font.BOLD,15); // font
		int gap = 10;
		JFrame f = new JFrame("New note");
		this.setSize(600, 600); // set size
		this.setLocation(600, 200); // set location
		this.setLayout(new FlowLayout());

		this.setTitle("NewNotePageUI"); //set title
		this.setSize(800, 600);  


		JPanel pInput = new JPanel(); // JPanel1
		JPanel buttonP = new JPanel(); //JPanel2
		pInput.setBounds(gap, gap, 375, 120); // set bounds
		pInput.setLayout(new GridLayout(4,1,gap,gap)); // set layout


		JLabel note = new JLabel("Write your note here:");
		note.setFont(f1); // set font of note 
		JTextArea NoteText = new JTextArea(5,20); // a text area
		NoteText.setLineWrap(true); 
		NoteText.setFont(f2);

		JButton addNote = new JButton("Add this note"); //JButton1
		JButton cancel = new JButton("Cancel"); //JButton2
		addNote.setFont(f1);
		cancel.setFont(f1);
		thisBrew = new Brew(); // new Brew
		thisBrew = thisBrew.getLastBrew(); // get the last brew

		int index = thisBrew.getIndex(); //index of the brew
		double size = thisBrew.getBatchSize(); // size of the brew
		String recipeName = thisBrew.getRecipeName();// get recipe Name
		Time time = thisBrew.getTime(); // time
		Date date = thisBrew.getDate(); // data
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // defined format
		DateFormat df1 = new SimpleDateFormat("HH-mm-ss");// defined format
		String dateSet = df.format(date); // datae
		String timeSet = df1.format(time); //time
		String combine = "num:"+index+"/ "+"name:"+recipeName+"/ "+"size:"+size+"/ "+"date:"+dateSet+"/ "+"time:"+timeSet+" ";

		JLabel lastBrew = new JLabel(combine);
		lastBrew.setFont(f1);



		pInput.add(note); // add note to JPanel pInput
		pInput.add(NoteText); // add NoteText to JPanel pInput
		pInput.add(lastBrew); // add lastBrew to JPanel pInput

		addNote.setBounds(30, 180, 120, 40); // set bounds of add note
		cancel.setBounds(250, 180, 120, 40); // set bounds of cancel

		this.add(pInput); // add to pInput
		pInput.add(buttonP); // add  buttonP
		buttonP.add(addNote); // add addNote

		buttonP.add(cancel); // add cancle
	



		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		addNote.addActionListener(new ActionListener() {	 // add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add event listener
				if(NoteText.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "Empty note is not allowed to be stored", "Warning",JOptionPane.WARNING_MESSAGE); 
				}
				else {
					closeThis(); //close
					controllerANN = new ControllerAddingNewNote(); // controller
					controllerANN.ControllerAddingNewNote(thisBrew.getIndex(),NoteText.getText());
					JOptionPane.showMessageDialog(null, "Successfully Adding Note", "Note Adding",JOptionPane.INFORMATION_MESSAGE);
					new WriteNotePageUI(); // new page
				}
			}
		});

		cancel.addActionListener(new ActionListener() {	// add action listener
			@Override
			public void actionPerformed(ActionEvent e) {// add event listener
				closeThis(); // close
				new WriteNotePageUI();
			}
		});




		this.setVisible(true); // set to visible

	}

	protected void closeThis() {
		this.setVisible(false); // close
	}



}

