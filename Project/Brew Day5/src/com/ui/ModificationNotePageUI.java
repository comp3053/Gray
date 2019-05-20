package com.ui;
import mainPart.*;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.exception.LargerThanEquip;

public class ModificationNotePageUI extends JFrame {
	private Note thisNote;
	public  ModificationNotePageUI() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int gap = 10;
		JFrame f = new JFrame("View/Modification note");
		this.setSize(600, 600);
		this.setTitle("Note Modification");
		this.setLocation(600, 200);
		this.setLayout(new FlowLayout());
		Font f1=new Font("ÂÞÂí",Font.BOLD,20);
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(4,1,gap,gap));
		
		JLabel recipeSelection = new JLabel("Which note do you want to modify?");
		JTextField recipeSelectionText = new JTextField();
		recipeSelection.setFont(f1);
		JButton addNote = new JButton("Continue");
		JButton cancel = new JButton("Cancel");
		addNote.setFont(f1);
		cancel.setFont(f1);
		thisNote = new Note();
		ArrayList<Brew> brewList = null;
		try {
			brewList = thisNote.getBrewWithNote();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LargerThanEquip e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		
		JLabel j1 = new JLabel();
		j1.setFont(f1);
		Collection<Integer> c = selected.values();
        Object[] integerList = c.toArray();
        Arrays.sort(integerList);
		try {
			j1.setText(thisNote.getSpecificNote((Integer)integerList[0]).getContent());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		jcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				if (selected.containsKey(combo.getSelectedItem())) {
					try {
						thisNote = thisNote.getSpecificNote(selected.get(combo.getSelectedItem()));
						j1.setText(thisNote.getContent());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}	 
			}
		});
		
		JLabel noteContent = new JLabel("Note content:");
		noteContent.setFont(f1);
		pInput.add(recipeSelection);
		pInput.add(jcb);
		pInput.add(noteContent);
		pInput.add(j1);
		
		
		addNote.setBounds(30, 180, 150, 40);
		cancel.setBounds(250, 180, 150, 40);
		


		this.add(pInput);
		this.add(addNote);
		this.add(cancel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		addNote.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					try {
						new NoteTrueModificationUI(selected.get(jcb.getSelectedItem()),(String) jcb.getSelectedItem());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
