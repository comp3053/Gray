package com.controller;
import mainPart.*;
public class ControllerAddingNewNote {
	private Note note;
	public ControllerAddingNewNote(){
		
	}
	// This is the method for controller to adding a new note
	public void ControllerAddingNewNote(int index,String content) {
		//set new note
		note = new Note(content, index);
		note.writeNoteForBrew();
	}
}

