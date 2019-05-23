package com.controller;
import mainPart.Note;
import com.dataBase.*;
public class ControllerModificationNote {
	private Note note;
	public ControllerModificationNote() {
	}
	// This is the method for controller to modification note
	public void conModificationNote(int index, String newContent) {
		//set new note
		note = new Note(newContent,index);
		note.updateNoteInfo(note);
	}
}
