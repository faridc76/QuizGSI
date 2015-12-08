package fr.ucfar0.quizzgsi.model;

public class Question {
	
	private int id;
	private String type;
	private String question;
	private String mauvaiseRepUn;
	private String mauvaiseRepDeux;
	private String mauvaiseRepTrois;
	private String bonneReponse;
	
	public Question() {
		
	}
	
	public Question(String t, String q, String m1, String m2, String m3, String br) {
		type = t;
		question = q;
		mauvaiseRepUn = m1;
		mauvaiseRepDeux = m2;
		mauvaiseRepTrois = m3;
		bonneReponse = br;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getMauvaiseRepUn() {
		return mauvaiseRepUn;
	}

	public void setMauvaiseRepUn(String mauvaiseRepUn) {
		this.mauvaiseRepUn = mauvaiseRepUn;
	}

	public String getMauvaiseRepDeux() {
		return mauvaiseRepDeux;
	}

	public void setMauvaiseRepDeux(String mauvaiseRepDeux) {
		this.mauvaiseRepDeux = mauvaiseRepDeux;
	}

	public String getMauvaiseRepTrois() {
		return mauvaiseRepTrois;
	}

	public void setMauvaiseRepTrois(String mauvaiseRepTrois) {
		this.mauvaiseRepTrois = mauvaiseRepTrois;
	}

	public String getBonnneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(String bonneReponse) {
		this.bonneReponse = bonneReponse;
	}
	
	public String[] getRandomAnswer() {
		String tableau[] = new String[4];
		for (int j = 0; j < tableau.length; j++) {
			tableau[j] = "";
		}
		//On g�nere un nombre al�atoire entre 0 et t3
		int nombreAleatoire = (int)(Math.random() * 4);
		//On met la bonne r�ponse al�atoirement
		tableau[nombreAleatoire] = getBonnneReponse();
		int i = 0;
		//on ajoute les trois mauvaise r�ponse
		String mauvaisesReponses[] = new String[3];
		mauvaisesReponses[0] = mauvaiseRepUn;
		mauvaisesReponses[1] = mauvaiseRepDeux;
		mauvaisesReponses[2] = mauvaiseRepTrois;
		while (i < 3) {
			nombreAleatoire = (int)(Math.random() * 4);
			//Si la case du tableau est vide 
			if (tableau[nombreAleatoire].equals("")) {
				//on ajoute une mauvaise r�ponse
				tableau[nombreAleatoire] = mauvaisesReponses[i];
                //et on incr�mente 
				i++;
            }
		}
		return tableau;
	}
	
	public boolean isSameType(String t) {
		return t == getType();
	}
	
	public boolean estLaBonneReponse(String r) {
		return getBonnneReponse().equals(r);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
