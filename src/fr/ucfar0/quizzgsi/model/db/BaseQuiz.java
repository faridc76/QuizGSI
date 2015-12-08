package fr.ucfar0.quizzgsi.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseQuiz extends SQLiteOpenHelper {
	
	private static int VERSION_BDD = 1;
	public static final String NOM_BDD = "quiz.db";
	
	public BaseQuiz(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//On crée la table question
		db.execSQL("CREATE TABLE questions (id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "type TEXT NOT NULL,"
				+ "question TEXT NOT NULL,"
				+ "bonneReponse TEXT NOT NULL,"
				+ "mauvaiseRepUn TEXT NOT NULL,"
				+ "mauvaiseRepDeux TEXT NOT NULL,"
				+ "mauvaiseRepTrois TEXT NOT NULL);");
		
		db.execSQL("INSERT INTO questions("
				+ "\"JEE\", "
				+ "\"Pourquoi le framwork Spring est-il qualifié comme un conteneur léger ?\","
				+ "\"Pour la taille des jars\","
				+ "\"La faible charge de développement nécessaire\","
				+ "\"En opposition avec EJB\","
				+ "\"La possibilité de déployer une application sur un conteneur de servlet\")");
		
		db.execSQL("INSERT INTO questions("
				+ "\"JEE\","
				+ "\"Pour qu'un attribut soit instancié avec un bean on doit utiliser l'annotation ?\","
				+ "\"@Autowired\","
				+ "\"@Value\","
				+ "\"@Controller\","
				+ "\"@Ressource\")");
		db.execSQL("INSERT INTO questions("
				+ "\"JEE\","
				+ "\"Pour garder l'ordre d'insertion des clés, on doit utiliser la classe :\" ,"
				+ "\"TreeTable\","
				+ "\"HashTable\","
				+ "\"LinkedHashSet\","
				+ "\"LinkedHashMap\")");
		
		db.execSQL("INSERT INTO questions("
				+ "\"JEE\","
				+ "\"Le fichier de configuration de Struts 2 est :\","
				+ "\"Struts.xml\","
				+ "\"Application-context.xml\" ,"
				+ "\"Struts-2-config.xml\","
				+ "\"Struts-config.xml\")");
		
		db.execSQL("INSERT INTO questions("
				+ "\"JEE\","
				+ "\"Le scope par défaut d'un bean Spring ?\","
				+ "\"Session\" ,"
				+ "\"Prototype\","
				+ "\"Spring\","
				+ "\"Singleton\")");

		db.execSQL("INSERT INTO questions("
				+ "\"Android\","
				+ "\"Quelle serait une façon classique d'instancier une vue afin de lui affecter un listener ?\","
				+ "\"Button infoBtn = (Button) findViewById(R.id.info_btn);\","
				+ "\"Button infoBtn = (Button) getView(R.id.info_btn);\","
				+ "\"View infoView = (View) getContext();\","
				+ "\"View infoView = new View(this);\")");
				
		db.execSQL("INSERT INTO questions("
				+ "\"Android\","
				+ "\"Parmi les méthodes suivantes, lesquelles peuvent-être utilisées pour sauvegarder l'êtat de l'activité ?\","
				+ "\"Activity.onFreeze()\","
				+ "\"Activity.onStop()\","
				+ "\"Activity.onDestroy()\","
				+ "\"Activity.onPause()\")");
		
		db.execSQL("INSERT INTO questions("
				+ "\"Android\","
				+ "\"Quelle propriété définit le padding pour une View en XML ?\","
				+ "\"android:layout_padding\","
				+ "\"android:view_padding\","
				+ "\"android:padding_width\","
				+ "\"android:padding\")");
		
		db.execSQL("INSERT INTO questions("
				+ "\"Android\","
				+ "\"Quel est le layout ci-dessous qui n'est pas un conteneur natif Android ?\","
				+ "\"RelativeLayout\","
				+ "\"GridLayout\","
				+ "\"TableLayout\","
				+ "\"FixedLayout\")");
		
		db.execSQL("INSERT INTO questions("
				+ "\"Android\","
				+ "\"Qu'est-ce qu'un AVD ?\","
				+ "\"Android Virus Definition\","
				+ "\"Android code versioning module\","
				+ "\"Un fichier de configuration pour le nom, l'icône et les permissions d'une application\","
				+ "\"Une configuration permettant de lancer un certain type de device sur un émulateur Android\")");
		
		
		
		
		//On crée la table score
		db.execSQL("CREATE TABLE scores (id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "type TEXT NOT NULL,"
				+ "note FLOAT NOT NULL);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Suppresion des tables
		db.execSQL("DROP TABLE IF EXISTS questions"); //table question
		db.execSQL("DROP TABLE IF EXISTS scores"); //table score
		//Création de la nouvelle base
		onCreate(db);
	}

	public static int getVERSION_BDD() {
		return VERSION_BDD;
	}

	public static void setVERSION_BDD(int vERSION_BDD) {
		VERSION_BDD = vERSION_BDD;
	}

}
