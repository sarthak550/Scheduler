package ProjectManagement;
import Trie.Trie;
public class Job implements Comparable<Job> {

	
	String name;
	Project project;
	User user;
	int runtime;
	int totaltime = 0;
	boolean Completed;
	public Job(String name,Project project, User user,int runtime) {
		this.name = name;
		this.project = project;
		this.user = user;
		this.runtime = runtime;
		Completed = false;
	}
    public int compareTo(Job job) {
    	
        if(project.priority>job.project.priority) {
        	return 1;
        }
        else if(project.priority<job.project.priority) {
        	return -1;
        }
        
        return 0;
        
    }
}