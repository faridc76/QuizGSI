package fr.ucfar0.quizzgsi.model;

public class Score {
	private int id;
	private String type;
	private double note;
	public Score() {
		
	}
	public double getNote() {
		return note;
	}
	public void setNote(double d) {
		this.note = d;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
