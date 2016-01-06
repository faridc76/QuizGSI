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
import fr.ucfar0.quizzgsi.model.RequeteHTTP;

public class AProposActivity extends Activity implements OnClickListener {

	Button but;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apropos);
		//On recupere le button de retour
		but = (Button) findViewById(R.id.aProposButton);
		
		//On afiche le contnu de http://cabani.free.fr/gsi/index.php
		String webContent = new RequeteHTTP("http://cabani.free.fr/gsi/index.php").execute();
		TextView tv = (TextView) findViewById(R.id.textViewAffichage);
		tv.setText(webContent);
		//On active l'écouteur dessus
		but.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.apropos, menu);
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
		Intent intent;
		if (v == but) {
			// On crée une activité pour retourné au menu principale
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			//On quitte l'activité
			finish();
		} else { 
			intent = new Intent(this, MapActivity.class);
			startActivity(intent);
		}
	}
}