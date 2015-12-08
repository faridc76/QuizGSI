package fr.ucfar0.quizzgsi.model.db;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.ucfar0.quizzgsi.model.Score;

public class ScoreDB {
	
	private static final String TABLE_SCORE = "scores";
	
	public static final String EVALUATION = "Evaluation";
	public static final String NOTE = "Note";
	
	//ID
	private static final String COL_ID = "id";
	private static final int NUM_COL_ID = 0;
	//type
	private static final String COL_TYPE = "type";
	private static final int NUM_COL_TYPE = 1;		
	//note
	private static final String COL_NOTE = "note";
	private static final int NUM_COL_NOTE = 2;
	
	private static SQLiteDatabase bdd;
	
	private BaseQuiz maBase;
	
	static List<Score> scores = new LinkedList<Score>();
	
	private double moyenneNote = 0.0;
	public double getMoyenneNote() {
		return moyenneNote;
	}

	public void setMoyenneNote(double moyenneNote) {
		this.moyenneNote = moyenneNote;
	}

	public double getMoyenneEvaluation() {
		return moyenneEvaluation;
	}

	public void setMoyenneEvaluation(double moyenneEvaluation) {
		this.moyenneEvaluation = moyenneEvaluation;
	}

	public int getNombreDePartie() {
		return nombreDePartie;
	}

	public void setNombreDePartie(int nombreDePartie) {
		this.nombreDePartie = nombreDePartie;
	}

	private double moyenneEvaluation = 0.0;
	private int nombreDePartie = 0;
	private int nombreDEvaluation = 0;
	
	
	public ScoreDB(Context context) {
		//On crée la base de données
		maBase = new BaseQuiz(context, BaseQuiz.NOM_BDD, null, BaseQuiz.getVERSION_BDD());
	}
	
	public void open() {
		//On ouvre la base en écriture
		bdd = maBase.getWritableDatabase();
	}
	
	public void close() {
		//On ferme la base 
		bdd.close();
	}
	
	public SQLiteDatabase getBDD() {
		return bdd;
	}
	
	public long ajouterScore(Score s) {
		//Création d'un content (HashMap)
		ContentValues values = new ContentValues();
		//On ajoute les infos
		values.put(COL_TYPE, s.getType()); //type
		values.put(COL_NOTE, s.getNote()); //Note
		
		return bdd.insert(TABLE_SCORE, null, values);
	}
	
	public int majScore(int id, Score s) {
		//Création d'un content (HashMap)
		ContentValues values = new ContentValues();
		//On ajoute les infos
		values.put(COL_TYPE, s.getType()); //type
		values.put(COL_NOTE, s.getNote()); //Note
		
		return bdd.update(TABLE_SCORE, values, COL_ID + " - ", null);
	}
	
	public int supprimerScoreParId(int id) {
		return bdd.delete(TABLE_SCORE, COL_ID + " = " + id, null);
	}
	
	private void recupScore() {
		Cursor c = bdd.query(TABLE_SCORE, new String [] {COL_ID, COL_TYPE, COL_NOTE},
				null, null, null, null, null);
		CursorToScore(c);
	}
	
	private void CursorToScore(Cursor c) {
		//sinon en se place sur le premier element 
		c.moveToFirst();
		do {
			Score s = new Score();
			s.setId(c.getInt(NUM_COL_ID));
			s.setType(c.getString(NUM_COL_TYPE));
			s.setNote(c.getDouble(NUM_COL_NOTE));
			scores.add(s);
		} while (c.moveToNext());
	}
	
	public void initialiseScore() {
		//On recupere toutes les valeurs dans la base de données
		recupScore();
		Iterator<Score> it = scores.iterator();
		while (it.hasNext()) {
			Score s = it.next();
			if (s.getType().equals(EVALUATION)) {
				moyenneEvaluation = moyenneEvaluation + s.getNote();
				nombreDEvaluation++;
			} else {
				moyenneNote = moyenneNote + s.getNote();
				nombreDePartie++;
			}
		}
		moyenneEvaluation = moyenneEvaluation * 20 / nombreDEvaluation;
		moyenneNote = moyenneNote * 20 / nombreDePartie;
	}

}
