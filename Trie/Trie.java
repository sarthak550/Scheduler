package Trie;

import java.util.ArrayList;
import java.util.HashMap;

public class Trie<T> implements TrieInterface<T> {
	
    
	HashMap<String,Integer> longest = new HashMap<String,Integer>();

	TrieNode<T> root = new TrieNode<T>(' ',null);

	
    public void printTrie(TrieNode trieNode) {
    	
    	if(trieNode.childList!=null) {
    		startWithHelper(trieNode,trieNode.prefix);
    	}
    }

    
    public boolean delete(String word) {
    	
    	TrieNode<T> current = root;
    	if(search(word)==null) {
//    		System.out.println("ERROR DELETING");
    		return false;
    	}
    	for(int i = 0;i<word.length();i++) {
            TrieNode<T> node = current.childList[word.charAt(i)];

    		if(node.count<=1) {
    			current.childList[word.charAt(i)]=null;
//    	    	System.out.println("DELETED");

    			return true;
    		}
    		else {
    			node.count--;
    			current = node;
    		}
    	}
    	longest.remove(word);
    	current.isEnd=false;
//    	System.out.println("DELETED");
    	return false;
    }

    
    public TrieNode<T> search(String word) {
    	TrieNode<T> current = root;
    	for(int i = 0;i<word.length();i++) {
    		char c=word.charAt(i);
    		int index = c;
    		if(current.childList[index]!=null) {
    			current = current.childList[index];
    		}
    		else {
    			return null;
    		}
    	}
    	if(current==root) {
    		return null;
    	}
    	if(current.value==null) {
    		return null;
    	}
        return current;
    }

    private void startWithHelper(TrieNode<T> root, String prefix){
    	if(root.isEnd) {

    		System.out.println(search(prefix).getValue());
    	}
    	if(root.childList==null) {
    		return;
    	}
    	for(int i=0;i<128;i++) {
    		if(root.childList[i]!=null) {
    			startWithHelper(root.childList[i],prefix+(char)i);
    		}
    	}


    }
    
    public TrieNode<T> startsWith(String prefix) {
    	TrieNode<T> current = root;    
    	
    	for(int i=0;i<prefix.length();i++) {
    		char c = prefix.charAt(i);
    		int index = c;
    		if(current.childList[index]==null) {
    			return null;
    		}
    		if(current.childList[index]!=null) {
    			current = current.childList[index];
    		}


    	}

    	current.prefix=prefix;
    	return current;
    	
    	
    }

    public boolean insert(String word, T value) {
    	longest.put(word,word.length());
    	TrieNode<T> current= root;

    	for(int i =0;i<word.length()-1;i++) {
    		TrieNode<T> child = current.childList[word.charAt(i)];
    		if(child != null) {
    			current = child;
    		}
    		else {
    			current.childList[word.charAt(i)]=new TrieNode<T>(word.charAt(i),null);
    			current = current.childList[word.charAt(i)];
    			current.isEnd=false;
    		}
    		current.count++;
    	}
    	current.childList[word.charAt(word.length()-1)]=new TrieNode<T>(word.charAt(word.length()-1),value);
		current = current.childList[word.charAt((word.length()-1))];
    	current.isEnd = true;
        return true;
    }
    String abc;

    private String printString(int level) {
    	abc="";
    	printLevelHelper(root,level);
    	return abc;
    }
    
    private void printLevelHelper(TrieNode<T> current, int level) {
    	if(current == null) {
    		return;
    	}
    	if(level==1) {
    		if(current.childList!=null) {
    			for(int x =33;x<128;x++) {
    				if(current.childList[x]!=null) {
    					abc+=(char)x;
    			    	abc = abc.replaceAll("\\s+", "");    			    	
    				}
        		}
    		}  		
    	}
    	else if(level>1){

			for(int j=0;j<128;j++) {
				if(current.childList[j]!=null) {
					printLevelHelper(current.childList[j],level-1);
				}				
			}
    	}
    }
    
    public void printLevel(int level ) {
    	
    	System.out.print("Level "+level+": ");

    	String str = printString(level);
		char[] chars = str.toCharArray();

		for(int i=0;i<str.length();i++) {
			for(int j=i+1;j<str.length();j++) {
				if(chars[i]>chars[j]) {
					char temp = chars[i];
					chars[i] = chars[j];
					chars[j] = temp;
				}
			}
		}
		String s = Character.toString(chars[0]);
		for(int i=1;i<chars.length;i++) {
			s+=","+chars[i];
		}
    	System.out.println(s);   	
    		
    }
    
    
    
    public void print() {
    	System.out.println("-------------");
    	ArrayList<Integer> array = new ArrayList<Integer>();
    	System.out.println("Printing Trie");
    	for (String name: longest.keySet()){
            String key = name;
            array.add(longest.get(key));          
    	} 
    	int max = array.get(0);
    	for(int i =1;i<array.size();i++) {
    		if(array.get(i)>max) {
    			max = array.get(i);    			
    		}
    	}
    	int level=1;
    	
    	for(int i=0;i<=max;i++) {
    		if(printString(level).compareTo("")>0) {
    		printLevel(level);}
    		else {
    			System.out.println("Level "+level+": ");
    		}
    		level++;
    	}

    	System.out.println("-------------");

    }    
}

