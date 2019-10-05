package ProjectManagement;

public class User implements Comparable<User> {


    String user1;
    public User(String user) {
    	this.user1 = user;
    }
    
    public int compareTo(User user) {
        if(user1.compareTo(user.user1)>0) {
        	return 1;
        }
        else if(user1.compareTo(user.user1)<0) {
        	return -1;
        }
        return 0;
    }
}
