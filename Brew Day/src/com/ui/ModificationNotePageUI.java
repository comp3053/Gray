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

public class ModificationNotePageUI extends JFrame {
	//4.2
	public  ModificationNotePageUI() {
		int gap = 10;
		JFrame f = new JFrame("Modification note");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(4,1,gap,gap));
		
		
		JLabel note = new JLabel("Your current note and modify here:");
		JTextField NoteText = new JTextField();
		JLabel recipeSelection = new JLabel("which recipe you want to select?");
		JTextField recipeSelectionText = new JTextField();

		JButton addNote = new JButton("Modify this note");
		JButton cancel = new JButton("Cancel");
		
		String[] sg= {"recipe1","recipe2","recipe3","recipe4"};

		JComboBox<String> jcb = new JComboBox<String>(sg);
		

		pInput.add(note);
		pInput.add(NoteText);
		pInput.add(recipeSelection);
		pInput.add(jcb);
		
		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
		addNote.setBounds(30, 180, 150, 40);
		cancel.setBounds(250, 180, 150, 40);
		ta.setBounds(gap, 150 + 600, 375, 120);


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
