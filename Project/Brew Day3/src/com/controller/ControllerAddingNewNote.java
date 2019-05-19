package com.controller;
import mainPart.*;
public class ControllerAddingNewNote {
	private Note note;
	public ControllerAddingNewNote(){
		
	}
	
	public void ControllerAddingNewNote(int index,String content) {
		note = new Note(content, index);
		note.writeNoteForBrew();
	}
}

