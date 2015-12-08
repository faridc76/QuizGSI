package fr.ucfar0.quizzgsi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.ucfar0.quizzgsi.model.db.ScoreDB;

public class StatistiquesActivity extends Activity implements OnClickListener {

	ScoreDB bdd = new ScoreDB(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistiques);
		bdd.initialiseScore();
		//On recupere la valeur du textView des evaluations
		TextView tvEval = (TextView) findViewById(R.id.statistiquesEvaluation);
		//On change le texte et on affiche la moyenne des �valuations
		tvEval.setText(String.valueOf(bdd.getMoyenneEvaluation()));
		//On recupere la valeur du textView des notes
		TextView tvNote = (TextView) findViewById(R.id.statistiquesNote);
		//On change le texte et on affiche la moyenne des notes
		tvNote.setText(String.valueOf(bdd.getMoyenneNote()));
		//On recupere la valeur du textView du nombre de partie
		TextView tvNombrePartie = (TextView) findViewById(R.id.statistiquesNombrePartie);
		//On change le texte et on affiche le nombre de partie
		tvNombrePartie.setText(String.valueOf(bdd.getNombreDePartie()));
		//On recupere le Button de l'activité
		Button but = (Button) findViewById(R.id.statistiquesButton);
		//On ajoute l'écouteur au boutton
		but.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.statistiques, menu);
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
		//On crée une activité pour aller au menu principale
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		//On quitte l'activit�
		finish();
	}
}
