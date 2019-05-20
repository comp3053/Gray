package com.controller;
import mainPart.Note;
import com.dataBase.*;
public class ControllerModificationNote {
	private Note note;
	public ControllerModificationNote() {
	}
	public void conModificationNote(int index, String newContent) {
		note = new Note(newContent,index);
		note.updateNoteInfo(note);
	}
}
