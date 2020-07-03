package indextree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IndexTree {

	private IndexNode root;

	public IndexTree(){
            root = null;
        }

	public void add(String word, int lineNumber) {
	    add(root, word, lineNumber);
    }
        
	// recursive method for add
	// When you add the word to the index, if it already exists,
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber){
	    if(root == null) {
            return root = new IndexNode(word, 1, lineNumber);

        }

        if(root.word.compareToIgnoreCase(word) > 0){

            root.left = add(root.left, word, lineNumber);

        } else if(root.word.compareToIgnoreCase(word) < 0){

            root.right = add(root.right, word, lineNumber);

        } else {
            root.occurences++;
            root.list.add(lineNumber);
            return root;
        }

        return root;

	}

	// returns true if the word is in the index
    public boolean contains(String word){
            return contains(root, word);
        }
        
	public boolean contains(IndexNode root, String word){
            if(root.word.compareToIgnoreCase(word) == 0){
                return true;
            } else if (root.word.compareToIgnoreCase(word) > 0){
                if(root.left == null){
                    return false;
                } else {
                    return contains(root.left, word);
                } 
            } else if(root.word.compareToIgnoreCase(word) < 0){
                if(root.right == null){
                    return false;
                } else {
                    return contains(root.right, word);
                }
                
            }
            return false;
	}


	// call recursive method
	public void delete(String word){
            root = delete(root, word);
	}

	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word){
            if(root == null){
                return root;
            }
            
            if(root.word.compareToIgnoreCase(word) > 0){
                root.left = delete(root.left, word);
            } else if (root.word.compareToIgnoreCase(word) < 0){
                root.right = delete(root.right, word);
            } else {
                
                //deleting node with one child
                if(root.left == null){
                    return root.right;
                } else if(root.right == null){
                    return root.left;
                }
                
                //deleting node with two children
                
                //get the inorder successor - smallest value in right subtree
                root.word = min(root.right);
                
                // delete inorder successor
                root.right = delete(root.right, root.word);
                
            }
            
            return root;
        }
        
        private String min(IndexNode root){
            String minString = root.word;
            while(root.left != null){
                minString = root.left.word;
                root = root.left;
            }
            
            return minString;
        }


	// prints all the words in the index in inorder order
    public void printIndex() {
        printIndex(root);
    }

    // print this node and all of its descendants
    private void printIndex(IndexNode node) {

        if(node != null) {
            // print everything before this node
            printIndex(node.left);

            // print this node
            System.out.println(node.toString());

            // print everything after this node
            printIndex(node.right);
        }
    }

	
	public static void main(String[] args){
            // add all the words to the tree
            IndexTree index = new IndexTree();
            
	    File file = new File("pg100.txt");
	    String temp;
	    int lineNum = 1;
            int j = 0;

        try {
            Scanner sc = new Scanner(file);
            
            
            while(sc.hasNext()){
                
                temp = sc.nextLine();
                temp = temp.replaceAll("[^a-zA-Z0-9\\s]", "");

                String[] list = temp.split("\\s+");

                for(int i = 0; i < list.length; i++){
                        if(!list[i].equals("")) {
                            //System.out.println(list[i]);
                            if(!list[i].matches("[0-9]+")){
                                if(j < 1){
                                    IndexNode node = new IndexNode(list[i], 1, 0);
                                    index.root = node;

                                    j++;
                                } else{
                                    index.add(list[i], lineNum);
                                }
                            }
                        } else{

                        }

                }
                lineNum++;

            }
        } catch (FileNotFoundException e) {
            System.out.println("File Cannot Be Found");
        }

        
		// print out the index
		index.printIndex();
                
		// test removing a word from the index
                index.delete("believe");
                
                System.out.println("______________________");
                index.printIndex();
                
                // search
                
                System.out.println(index.contains("ethan"));
                System.out.println(index.contains("name"));
                System.out.println(index.contains("what"));
                System.out.println(index.contains("blue"));
                
                System.out.println(index.contains("would"));

		
	}


}
