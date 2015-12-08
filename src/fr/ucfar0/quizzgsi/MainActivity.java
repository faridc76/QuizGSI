package fr.ucfar0.quizzgsi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.ucfar0.quizzgsi.model.db.QuestionDB;

public class MainActivity extends Activity implements OnClickListener {

	Button jouer;
	Button evaluer;
	Button statistiques;
	Button aPropos;
	Button quitter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		aPropos = (Button) findViewById(R.id.mainAPropos);
		jouer = (Button) findViewById(R.id.mainJouer);
		evaluer = (Button) findViewById(R.id.mainEvaluer);
		statistiques = (Button) findViewById(R.id.mainStatistiques);
		quitter = (Button) findViewById(R.id.mainQuitter);
		
		aPropos.setOnClickListener(this);
		jouer.setOnClickListener(this);
		evaluer.setOnClickListener(this);
		statistiques.setOnClickListener(this);
		quitter.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		if (v == jouer) {
			final String[] items = QuestionDB.themes();
			final Intent intent = new Intent(this, QuizActivity.class);
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("Choisir le quiz")
			.setMessage("Vous voulez tester le JEE ou l'Android ?")
			.setItems(items, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					SharedPreferences sharedpreferences = getSharedPreferences(QuestionDB.MY_PREFS_NAME, Context.MODE_PRIVATE);	
					Editor editor = sharedpreferences.edit();
					editor.putString("typeQuiz", items[which]);
					editor.commit();
					startActivity(intent);
				}
			})
			.setCancelable(false);
			final AlertDialog alert = builder.create();
			alert.show();
			
		}
		if (v == evaluer) {
			Intent intent = new Intent(this, EvaluerActivity.class);
			startActivity(intent);
		}
		if (v == statistiques) {
			Intent intent = new Intent(this, StatistiquesActivity.class);
			startActivity(intent);
		}
		if (v == aPropos) {
			Intent intent = new Intent(this, AProposActivity.class);
			startActivity(intent);
		}
		if (v == quitter) {
			System.exit(0);
		}
		
		finish();
	}
}
