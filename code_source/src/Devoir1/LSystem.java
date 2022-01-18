package Devoir1;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.*;


public class LSystem {
	
	HashMap<Character, Symbol> alphabet = new HashMap<>();
	ArrayList axiom = new ArrayList<Symbol>();
	JSONObject parametres;
	
	
    /**
     * constructeur vide monte un système avec alphabet vide et sans règles
     */
    public LSystem(String file){
    	try {
    		readJSONFile(file);
    	}
    	catch(Exception e) {
    		
    	}
    }
    
    
    //Lit le fichier JSON et prend les données utiles
    public void readJSONFile(String file) throws java.io.IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(file)));
        JSONArray alphabet = input.getJSONArray("alphabet");
        String sym;
        for (int i = 0; i<alphabet.length(); i++) {
        	sym = (String) alphabet.get(i);
    		this.alphabet.put(sym.charAt(0), new Symbol(sym.charAt(0)));
    	}
        setAxiom(input.getString("axiom"));
        JSONObject regles = input.getJSONObject("rules");
        String symRegle;
        
        for(Object nom : regles.names()) {
        	symRegle = (String) nom;
        	int nbRegles = regles.getJSONArray(symRegle).length();
        	String[] tabRegles = new String[nbRegles];
        	for (int i = 0; i<nbRegles; i++) {
        		tabRegles[i] = regles.getJSONArray(symRegle).getString(i);
        	}
        	addRule(this.alphabet.get(symRegle.charAt(0)), tabRegles);
        }
        JSONObject actions = input.getJSONObject("actions");
        String symActions;
        for(Object nom : actions.names()) {
        	symActions = (String) nom;
        	setAction(this.alphabet.get(symActions.charAt(0)), actions.getString(symActions));
        }
        parametres = input.getJSONObject("parameters");
    }
    /* méthodes d'initialisation de système */
    public Symbol addSymbol(char sym) {
    	return new Symbol(sym);
    }
    public void addRule(Symbol sym, String[] expansion) {
    	sym.setRule(expansion);
    }
    public void setAction(Symbol sym, String action) {
    	sym.setAction(action);
    }
    public void setAxiom(String str){
    	for (int i = 0; i<str.length(); i++) {
    		this.axiom.add(this.alphabet.get(str.charAt(i)));
    	}
    }
 
    /* accès aux règles et exécution */
    public ArrayList<Symbol> getAxiom(){
    	return axiom;
    }
    
    //Réécrit l'axiome en appliquant les règles
    public void rewrite(){
    	ArrayList nouvListe = new ArrayList<Symbol>();
    	String rule;
    	Symbol sym;
    	for (int i = 0; i<axiom.size(); i++) {
    		sym = (Symbol) axiom.get(i);
    		rule = sym.getRule();
    		if (rule != null) {
    			for (int j = 0; j<rule.length(); j++) {
        			nouvListe.add(alphabet.get(rule.charAt(j)));
        		}
			}
    		
    	}
    	axiom = nouvListe;
    }
    
    //Applique les règles un nombre n fois
    public void applyRules(int n) {
    	for(int i = 0; i<n; i++) {
    		rewrite();
    	}
    }
    
    //Fait faire les actions à la tortue
    public void tell(Turtle turtle, Symbol sym) {
    	switch(sym.getAction()) {
	    	case "draw":
	    		turtle.draw();
	    		break;
	    	case "move":
	    		turtle.move();;
	    		break;
	    	case "turnR":

	    		turtle.turnR();
	    		break;
	    	case "turnL":

	    		turtle.turnL();
	    		break;
	    	case "push":

	    		turtle.push();
	    		break;
	    	case "pop":

	    		turtle.pop();
	    		break;
	    	default:
	    		turtle.stay();
	    		break;
    	}
    }
    
    //Permet de récupérer les paramètre du fichier JSON
    public double getParametres(int numPara) {
    	switch(numPara){
    		case 0:
    			return this.parametres.getInt("step");
    		case 1:
    			return this.parametres.getInt("angle");
    		case 2:
    			return this.parametres.getJSONArray("start").getInt(0);
    		case 3:
    			return this.parametres.getJSONArray("start").getInt(1);
    		case 4:
    			return this.parametres.getJSONArray("start").getInt(2);
    		default:
    			return 0;
    	}
    }
    

}