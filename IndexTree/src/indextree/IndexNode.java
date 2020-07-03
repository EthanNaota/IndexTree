package indextree;

import java.util.ArrayList;
import java.util.List;

public class IndexNode  {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurences;
	// A list of line numbers for this word.
	List<Integer> list; 
	
	
	
	IndexNode left;
	IndexNode right;
	
	
	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
        
        
	public IndexNode(String w, int occ, int line){
            this.list = new ArrayList();
            
            this.word = w;
            this.occurences = occ;
            this.list.add(line);
            
            left = null;
            right = null;
            
        }
	
	public void addLine(int line){
            this.list.add(line);
        }
        
	// Complete This
	// return the word, the number of occurrences, and the lines it appears on.
	// string must be one line
	
        @Override
	public String toString(){
            String a;
            a = "Word: " + this.word + " | # of Occurrences: " + this.occurences + " | Appears on Lines: ";
            for (int i = 0; i < list.size(); i++){
                if ((i == list.size() - 1)){
                    a = a + this.list.get(i);
                    
                } else {

                    a = a + this.list.get(i) + ", ";
                }
            }
		return a;
	}
	
	
	
}
