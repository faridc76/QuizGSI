package fr.ucfar0.quizzgsi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import fr.ucfar0.quizzgsi.model.Score;
import fr.ucfar0.quizzgsi.model.db.ScoreDB;

public class EvaluerActivity extends Activity implements OnClickListener {
	//On déclare la base de données
	ScoreDB bdd = new ScoreDB(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluer);
		Button but = (Button) findViewById(R.id.evaluerButton);
		but.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.evaluer, menu);
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
		RatingBar rb = (RatingBar) findViewById(R.id.evaluerRatingBar);
		float evaluation = rb.getRating();
		Score s = new Score();
		s.setType(ScoreDB.EVALUATION);
		s.setNote(evaluation);
		bdd.ajouterScore(s);
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
