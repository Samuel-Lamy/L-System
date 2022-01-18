package Devoir1;

import java.util.Random;

public class Symbol 
{
    private final char value;
    private String[] rule;
    private String action;
    public Symbol(char c){
        this.value = c;
    }
    
    @Override
    public String toString(){
        return Character.toString(value);
    }
    
    public void setRule(String[] rule) {
    	this.rule = rule;
    }
    
    public String getRule() {
    	if(rule != null) {
    		int indexRegle;
            Random rand = new Random();
            indexRegle = rand.nextInt(rule.length);
        	return this.rule[indexRegle];
    	}
    	return value + "";
    }
    
    public void setAction(String action) {
    	this.action = action;
    }
    
    public String getAction() {
    	return this.action;
    }
    
    
    public interface Seq extends Iterable<Symbol>{
    	
    }    
}