package com.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class NewNotePageUI extends JFrame {
	
	//4.1
	public  NewNotePageUI() {
		int gap = 10;
		JFrame f = new JFrame("New note");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		this.setTitle("NewNotePageUI");
		this.setSize(800, 600);
		
		
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(4,1,gap,gap));
		
		
		JLabel note = new JLabel("Write your note here:");
		JTextField NoteText = new JTextField();
		JLabel recipeSelection = new JLabel("which recipe you want to select?");
		JTextField recipeSelectionText = new JTextField();

		JButton addNote = new JButton("Add this note");
		JButton cancel = new JButton("Cancel");

		String[] sg= {"recipe1","recipe2","recipe3","recipe4"};

		JComboBox<String> jcb = new JComboBox<String>(sg);
		
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
				closeThis();
				//new mainPageView();
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

