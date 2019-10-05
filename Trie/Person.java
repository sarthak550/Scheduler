package Trie;

public class Person {

	String name;
	String phone_number;
    public Person(String name, String phone_number) {
    	 
    	this.name = name;
    	this.phone_number = phone_number;
    	
    }

    public String getName() {
        return this.name;
    }
    
    public String name() {
    	String[] s = name.split(" ");
    	String str = "";
    	for(int i = 0 ; i<s.length;i++) {
    		str+=s[i];
    	}
    	return str;
    }
    public String toString() {
    	String str = "[Name: ";
    	str+=name+", Phone="+phone_number+"]";
    	return str;
    }
}
