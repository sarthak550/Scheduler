package Trie;


//import java.util.LinkedList;

import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
    TrieNode<T> []childList;
    String prefix;
    boolean isEnd;
    int count;
	T value;
	char character;
	public TrieNode(char character,T value){
		this.value = value;
		this.character = character;
		childList = new TrieNode[128];
		isEnd = false;
		count = 0;
		
	}
	
    public T getValue() {
        return value;
    }
    



}