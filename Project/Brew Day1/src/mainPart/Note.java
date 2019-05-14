package mainPart;

public class Note {
	
	private java.sql.Date date;
	private java.sql.Time time;
	private String content;
	private Brew brew;
	
	public Note(String inputContent){
		this.content = inputContent;
		date = new java.sql.Date(System.currentTimeMillis());
		time = new java.sql.Time(System.currentTimeMillis());
	}
	
	public void edit(String editingNote) {
		this.content = editingNote;
	}
	
	public void addBrew(Brew inputBrew) {
		this.brew = inputBrew;
	}
}
