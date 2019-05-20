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
public class OneNotePageUI extends JFrame {
	private ControllerAddingNewNote controllerANN;
	private Brew thisBrew;
	//4.1
	public  OneNotePageUI() throws SQLException, LargerThanEquip, ParseException {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Font f1=new Font("ÂÞÂí",Font.BOLD,20);
		Font f2=new Font("ÂÞÂí",Font.BOLD,15);
		int gap = 10;
		JFrame f = new JFrame("New note");
		this.setSize(600, 600);
		this.setLocation(600, 200);
		this.setLayout(new FlowLayout());

		this.setTitle("NewNotePageUI");
		this.setSize(800, 600);


		JPanel pInput = new JPanel();
		JPanel buttonP = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(4,1,gap,gap));


		JLabel note = new JLabel("Write your note here:");
		note.setFont(f1);
		JTextArea NoteText = new JTextArea(5,20);
		NoteText.setLineWrap(true); 
		NoteText.setFont(f2);

		JButton addNote = new JButton("Add this note");
		JButton cancel = new JButton("Cancel");
		addNote.setFont(f1);
		cancel.setFont(f1);
		thisBrew = new Brew();
		thisBrew = thisBrew.getLastBrew();

		int index = thisBrew.getIndex();
		double size = thisBrew.getBatchSize();
		String recipeName = thisBrew.getRecipeName();
		Time time = thisBrew.getTime();
		Date date = thisBrew.getDate();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("HH-mm-ss");
		String dateSet = df.format(date);
		String timeSet = df1.format(time);
		String combine = "num:"+index+"/ "+"name:"+recipeName+"/ "+"size:"+size+"/ "+"date:"+dateSet+"/ "+"time:"+timeSet+" ";

		JLabel lastBrew = new JLabel(combine);
		lastBrew.setFont(f1);



		pInput.add(note);
		pInput.add(NoteText);
		pInput.add(lastBrew);

		addNote.setBounds(30, 180, 120, 40);
		cancel.setBounds(250, 180, 120, 40);

		this.add(pInput);
		pInput.add(buttonP);
		buttonP.add(addNote);

		buttonP.add(cancel);
	



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
					controllerANN.ControllerAddingNewNote(thisBrew.getIndex(),NoteText.getText());
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

