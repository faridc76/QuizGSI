package fr.ucfar0.quizzgsi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import fr.ucfar0.quizzgsi.model.Question;
import fr.ucfar0.quizzgsi.model.Score;
import fr.ucfar0.quizzgsi.model.db.QuestionDB;
import fr.ucfar0.quizzgsi.model.db.ScoreDB;

public class QuizActivity extends Activity implements OnClickListener {
	static List<Question> questions; 
	TextView tvQuestion;
	TextView rang;
	RadioButton[] reponse;
	Iterator<Question> it;
	Question q;
	Button valider;
	int numeroQuestion = 1;
	String[] reponsesAuxQuestions;
	ScoreDB bddScore = new ScoreDB(this);
	QuestionDB bddQuestion = new QuestionDB(this);
	int score = 0;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		Bundle bundle = getIntent().getExtras();
		questions = new LinkedList<Question>();
		questions = bddQuestion.getQuestionsFromType(bundle.getString("typeQuiz"));
		//questions = QuestionDB.getQuestionsFromType("Android");
		//On cré un itérator sur les questions
		it = questions.iterator();
		//On avanve à la premiere question
		q = it.next();
		
		//On ajoute laquestion
		tvQuestion = (TextView) findViewById(R.id.QuestionQuiz);
		tvQuestion.setText(q.getQuestion());
		
		reponsesAuxQuestions = q.getRandomAnswer();
		
		//Initialise tableau des radioBouton
	    reponse = new RadioButton[4];
		//On ajoute tous les radio boutton a la main (pas le choix...)
	    reponse[0] = (RadioButton) findViewById(R.id.radio1);
	    reponse[0].setText(reponsesAuxQuestions[0]);
	    reponse[1] = (RadioButton) findViewById(R.id.radio2);
	    reponse[1].setText(reponsesAuxQuestions[1]);
	    reponse[2] = (RadioButton) findViewById(R.id.radio3);
	    reponse[2].setText(reponsesAuxQuestions[2]);
	    reponse[3] = (RadioButton) findViewById(R.id.radio4);
	    reponse[3].setText(reponsesAuxQuestions[3]);
		
		rang = (TextView) findViewById(R.id.textViewRang);
		rang.setText(numeroQuestion + "/" + questions.size());
		
		valider = (Button) findViewById(R.id.valider);
		valider.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		int idReponse = ((RadioGroup) findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
		String repChoisi = ((RadioButton) findViewById(idReponse)).getText().toString();
		if (q.estLaBonneReponse(repChoisi)){
			score = score + 1;
		}
		//rang.setText("" + q.estLaBonneReponse(repChoisi));
		if (it.hasNext()) {
			numeroQuestion++;
			q = it.next();
			reponsesAuxQuestions = q.getRandomAnswer();
			tvQuestion.setText(q.getQuestion());
			reponse[0].setText(reponsesAuxQuestions[0]);
			reponse[1].setText(reponsesAuxQuestions[1]);
			reponse[2].setText(reponsesAuxQuestions[2]);
			reponse[3].setText(reponsesAuxQuestions[3]);
			rang.setText(numeroQuestion + "/" + questions.size());
		} else {
			Score s = new Score();
			s.setType(ScoreDB.NOTE);
			float note = score * 20 / numeroQuestion;
			s.setNote(note);
			bddScore.ajouterScore(s);
			// On crée une activité pour retourné au menu principale
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			//On quitte l'activité
			finish();
		}
	}
}
