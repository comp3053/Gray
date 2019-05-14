package mainPart;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.dataBase.DataBase;
import com.exception.LargerThanEquip;

public class Note {
	
	private java.sql.Date date;
	private java.sql.Time time;
	private int brewID;
	private String content;
	
	public Note(String inputContent,int brewID){
		this.brewID = brewID;
		this.content = inputContent;
		date = new java.sql.Date(System.currentTimeMillis());
		time = new java.sql.Time(System.currentTimeMillis());
	}
	
	public Note() {
		// TODO Auto-generated constructor stub
	}

	public void edit(String editingNote) {
		this.content = editingNote;
	}
	
	public void writeNoteForBrew() {
		DataBase.writeNoteForBrew(this.brewID, this.content, this.date, this.time);
	}
	
	public ArrayList<Brew> getBrewWithNote() throws SQLException, LargerThanEquip, ParseException {
		return DataBase.getBrewWithNoteWritten();
	}
	
	public Note getSpecificNote(int index) throws SQLException, ParseException {
		return DataBase.getSpecificNote(index);
	}
	
	public void updateNoteInfo(Note note) {
		DataBase.updateNoteInfor(note);
	}
	
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
