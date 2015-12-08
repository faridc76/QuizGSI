
package fr.ucfar0.quizzgsi.model.db;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.ucfar0.quizzgsi.model.Question;


public class QuestionDB {
	
	public static final String MY_PREFS_NAME = "MY_PERFS_QUIZ";
	//Ajouter à la base de données
	public static final String ANDROID = "Android";
	public static final String JEE = "JEE";
	
	
	
	private static final String TABLE_QUESTION = "questions";
	//ID
	private static final String COL_ID = "id";
	private static final int NUM_COL_ID = 0;
	//type
	private static final String COL_TYPE = "type";
	private static final int NUM_COL_TYPE = 1;		
	//question
	private static final String COL_QUESTION = "question";
	private static final int NUM_COL_QUESTION = 2;
	//bonne reponse
	private static final String COL_BONNE_REPONSE = "bonneReponse";
	private static final int NUM_COL_BONNE_REPONSE = 3;
	//mauvaise reponse 1
	private static final String COL_MAUVAISE_REPONSE_UN = "mauvaiseRepUn";
	private static final int NUM_COL_MAUVAISE_REPONSE_UN = 4;
	//mauvaise reponse 1
	private static final String COL_MAUVAISE_REPONSE_DEUX = "mauvaiseRepDeux";
	private static final int NUM_COL_MAUVAISE_REPONSE_DEUX = 5;	
	//mauvaise reponse 1
	private static final String COL_MAUVAISE_REPONSE_TROIS = "mauvaiseRepTrois";
	private static final int NUM_COL_MAUVAISE_REPONSE_TROIS = 6;
	
	private static SQLiteDatabase bdd;
	
	private BaseQuiz maBase;
	
	
	public QuestionDB(Context context) {
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
	
	private List<Question> questions = new LinkedList<Question>();

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	private void initializeQuestionList() {
		Cursor c = bdd.query(TABLE_QUESTION, new String [] {COL_ID, COL_TYPE, COL_QUESTION,
				COL_BONNE_REPONSE, COL_MAUVAISE_REPONSE_UN, COL_MAUVAISE_REPONSE_DEUX,
				COL_MAUVAISE_REPONSE_TROIS}, null, null, null, null, null);
		CursorToQuestions(c);
	}
	
	public long ajouterQuestion(Question q) {
		//Création d'un content (HashMap)
		ContentValues values = new ContentValues();
		//On ajoute les infos
		values.put(COL_TYPE, q.getType()); //type
		values.put(COL_QUESTION, q.getQuestion()); //question
		values.put(COL_BONNE_REPONSE, q.getBonnneReponse()); //bonne reponse
		values.put(COL_MAUVAISE_REPONSE_UN, q.getMauvaiseRepUn()); //mauvaise reponse 1
		values.put(COL_MAUVAISE_REPONSE_DEUX, q.getMauvaiseRepDeux()); //mauvaise reponse 2
		values.put(COL_MAUVAISE_REPONSE_TROIS, q.getMauvaiseRepTrois()); // mauvaise reponse 3
		
		return bdd.insert(TABLE_QUESTION, null, values);
	}
	
	public int majQuestion(int id, Question q) {
		//Création d'un content (HashMap)
		ContentValues values = new ContentValues();
		//On ajoute les infos
		values.put(COL_TYPE, q.getType()); //type
		values.put(COL_QUESTION, q.getQuestion()); //question
		values.put(COL_BONNE_REPONSE, q.getBonnneReponse()); //bonne reponse
		values.put(COL_MAUVAISE_REPONSE_UN, q.getMauvaiseRepUn()); //mauvaise reponse 1
		values.put(COL_MAUVAISE_REPONSE_DEUX, q.getMauvaiseRepDeux()); //mauvaise reponse 2
		values.put(COL_MAUVAISE_REPONSE_TROIS, q.getMauvaiseRepTrois()); // mauvaise reponse 3
		
		return bdd.update(TABLE_QUESTION, values, COL_ID + " - ", null);
	}
	
	public int supprimerQuestionParId(int id) {
		return bdd.delete(TABLE_QUESTION, COL_ID + " = " + id, null);
	}
	
	
	private void CursorToQuestions(Cursor c) {
		//sinon en se place sur le premier element 
		c.moveToFirst();
		//On crée une question est on lui passe toute les infos
		do {
			Question q = new Question();
			q.setId(c.getInt(NUM_COL_ID));
			q.setType(c.getString(NUM_COL_TYPE));
			q.setQuestion(c.getString(NUM_COL_QUESTION));
			q.setBonneReponse(c.getString(NUM_COL_BONNE_REPONSE));
			q.setMauvaiseRepUn(c.getString(NUM_COL_MAUVAISE_REPONSE_UN));
			q.setMauvaiseRepDeux(c.getString(NUM_COL_MAUVAISE_REPONSE_DEUX));
			q.setMauvaiseRepTrois(c.getString(NUM_COL_MAUVAISE_REPONSE_TROIS));
			questions.add(q);
		} while (c.moveToNext());
		
		c.close();
	}
	
	public List<Question> getQuestionsFromType(String type) {
		initializeQuestionList();
		List<Question> questionList = new LinkedList<Question>();
		Iterator<Question> it = questions.iterator();
		while (it.hasNext()) {
			Question q = it.next();
			if (q.isSameType(type)) {
				questionList.add(q);
			}
		}
		return questionList;
	}
	
		//sera récupéré de la base par la suite
	public static String[] themes() {
		String[] items = {QuestionDB.JEE, QuestionDB.ANDROID};
		return items;
	}
}
