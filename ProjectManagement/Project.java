package ProjectManagement;


public class Project implements Comparable<Project>{
	
	String project_name;
	int priority;
	int budget;
	public Project(String project_name,int priority,int budget) {
		this.project_name = project_name;
		this.priority = priority;
		this.budget = budget;
	}
    public int compareTo(Project job) {
    	
        if(priority>job.priority) {
        	return 1;
        }
        else if(priority<job.priority) {
        	return -1;
        }
        
        return 0;
        
    }
}
