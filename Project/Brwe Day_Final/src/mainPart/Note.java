package mainPart;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.dataBase.DataBase;
// Class for recording note
public class Note {
	// Class basic attribute
	private java.sql.Date date;
	private java.sql.Time time;
	private int brewID;
	private String content;
	// Initialize the note information
	public Note(String inputContent,int brewID){
		this.brewID = brewID;
		this.content = inputContent;
		date = new java.sql.Date(System.currentTimeMillis());
		time = new java.sql.Time(System.currentTimeMillis());
	}
	
	public Note() {
		// TODO Auto-generated constructor stub
	}
	// Edit the note content
	public void edit(String editingNote) {
		this.content = editingNote;
	}
	// Write the note information for each brewing
	public void writeNoteForBrew() {
		DataBase.writeNoteForBrew(this.brewID, this.content, this.date, this.time);
	}
	// Get the brew object with note written
	public ArrayList<Brew> getBrewWithNote() throws SQLException, ParseException {
		return DataBase.getBrewWithNoteWritten();
	}
	// Get specific note from the brewing experience
	public Note getSpecificNote(int index) throws SQLException, ParseException {
		return DataBase.getSpecificNote(index);
	}
	// Updating the note information
	public void updateNoteInfo(Note note) {
		DataBase.updateNoteInfor(note);
	}
	// Basic function for note setting and getting 
	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public java.sql.Time getTime() {
		return time;
	}

	public void setTime(java.sql.Time time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBrewID() {
		return brewID;
	}

	public void setBrewID(int brewID) {
		this.brewID = brewID;
	}
}
