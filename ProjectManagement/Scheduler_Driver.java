package ProjectManagement;

import PriorityQueue.MaxHeap;
import PriorityQueue.Pair;
import PriorityQueue.PriorityQueueDriverCode;
import RedBlack.RBTree;
import Trie.Trie;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler_Driver extends Thread implements SchedulerInterface {


    public static void main(String[] args) throws IOException {
        Scheduler_Driver scheduler_driver = new Scheduler_Driver();

        File file;
        if (args.length == 0) {
            URL url = PriorityQueueDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File file) throws IOException {

        URL url = Scheduler_Driver.class.getResource("INP");
        file = new File(url.getPath());

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }

            switch (cmd[0]) {
                case "PROJECT":
                	System.out.println("Creating project");
                    handle_project(cmd);
                    break;
                case "JOB":
                	System.out.println("Creating job");

                    handle_job(cmd);
                    break;
                case "USER":
                	System.out.println("Creating user");

                    handle_user(cmd[1]);
                    break;
                case "QUERY":
                	System.out.println("Querying");
                    handle_query(cmd[1]);
                    break;
                case "":
                	System.out.println("Running code");
            		System.out.println("Remaining jobs: "+temparray.size());

                    handle_empty_line();
            		System.out.println("Execution cycle completed");

                    break;

                case "ADD":
//                    System.out.println(temparray.size()+"svjkskr");

                	System.out.println("ADDING Budget");
                    handle_add(cmd);
//                    System.out.println(temparray.size()+"svjkflkwg;rekgopkteho;ktrpkhrp;thp'skr");

                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }

//        System.out.println(temparray.size()+"svjkskr");
        run_to_completion();
        
        print_stats();

    }

  
    public void run() {
        // till there are JOBS
        schedule();
    }


    public void run_to_completion() {

    	while(priorityQueue.heapSize>0) {
    		
    	  	System.out.println("Running code");
        	System.out.println("Remaining jobs: "+(priorityQueue.heapSize));
        	int size = priorityQueue.heapSize;
        	for(int i =0;i<size;i++) {
        		Pair<Job> job = priorityQueue.maxnode();
            	temparray.remove(job.obj.name);

            	int completed_time;

            	
            	if(job.obj.project.budget>=job.obj.runtime) {
            		System.out.println("Executing: "+job.obj.name+" from: "+job.obj.project.project_name);
            		
            		job_done.put(job.obj.name, job);
            		completed_queue.add(job.obj);
            		job.obj.Completed = true;
            		global_time+=job.obj.runtime;
            		completed_time = global_time;
            		job.obj.totaltime = completed_time;
            		job.obj.project.budget-=job.obj.runtime;
            		System.out.println("Project: "+job.obj.project.project_name+" budget remaining: "+job.obj.project.budget);
            		break;
            	}
            	
            		job_left.put(job.obj.name, job);
            		if(temparray.size()>0) {
            		System.out.println("Executing: "+job.obj.name+" from: "+job.obj.project.project_name);   		
            		System.out.println("Un-sufficient budget.");    			
                		
            	}
        	}
    		System.out.println("System execution completed");
    	}
    }

	Trie<Project> trie = new Trie<Project>();
	RBTree<String,Project> rbtree = new RBTree<String,Project>();
	MaxHeap<Job> priorityQueue = new MaxHeap<Job>();
	HashMap<String,Job> array = new HashMap<String,Job>();
	HashMap<String,Job> temparray = new HashMap<String,Job>();
    HashMap<String,Pair<Job>> job_done = new HashMap<String,Pair<Job>>();
    HashMap<String,Pair<Job>> job_left = new HashMap<String,Pair<Job>>();
	Queue<Job> completed_queue = new LinkedList<>();
	MaxHeap<Job> uncompleted_queue = new MaxHeap<Job>();
	RBTree<Project,Job> job_tree = new RBTree<Project,Job>();
	int global_time = 0;
	MaxHeap<Job> temppriorityQueue = new MaxHeap<Job>();
    public void handle_project(String[] cmd) {

    	Project project = new Project(cmd[1],Integer.valueOf(cmd[2]),Integer.valueOf(cmd[3]));
    	trie.insert(project.project_name,project);
    	rbtree.insert(project.project_name, trie.search(project.project_name).getValue());
    }
    

    public void handle_job(String[] cmd) {
    	if(rbtree.search(cmd[2]).getValue()!=null && trie1.search(cmd[3])!=null) {
        		Job job = new Job(cmd[1],rbtree.search(cmd[2]).getValue(),trie1.search(cmd[3]).getValue(),Integer.valueOf(cmd[4]));
            	priorityQueue.insert(job);
            	array.put(cmd[1],job);
            	temparray.put(cmd[1],job);

    	}
    	else if(rbtree.search(cmd[2]).getValue()==null){
    		System.out.println("No such project exists. "+cmd[2]);
    	}
		else if(trie1.search(cmd[3])==null){
			System.out.println("No such user exists: "+cmd[3]);
		}

    }

	Trie<User> trie1 = new Trie<User>();

    public void handle_user(String name) {
    	User user = new User(name);
    	trie1.insert(name,user);
    }

    public void handle_query(String key) {
    	if(array.containsKey(key)) {
    		if(!array.get(key).Completed) {
        		System.out.println(key+": NOT FINISHED");
    		}
    		else {
        		System.out.println(key+": COMPLETED");

    		}
    	}
    	else {
    		System.out.println(key+": NO SUCH JOB");
    	}
    }


    public void handle_empty_line() {
    	
    	    	schedule();
    }

    public void handle_add(String[] cmd) {
    	if(trie.search(cmd[1])!=null) {
    	trie.search(cmd[1]).getValue().budget+=Integer.valueOf(cmd[2]);
    	}
    	for (String name: job_left.keySet()){  		
            Pair<Job> value = job_left.get(name); 
            priorityQueue.insert(value.obj,value.count);
            temparray.put(name, value.obj);
    	}
    	job_left.clear();
//    	temparray.put(null,null);

    }

    public void print_stats() {
    	System.out.println("--------------STATS---------------") ;
    	System.out.println("Total jobs done: "+completed_queue.size());
    	while(!completed_queue.isEmpty()){  		
            Job value = completed_queue.poll();  
            System.out.println("Job{user='"+value.user.user1+"', project='"+value.project.project_name+"', jobstatus=COMPLETED, execution_time="+value.runtime+", end_time="+value.totaltime+", name='"+value.name+"'}");  
    	} 
    	
    	System.out.println("------------------------") ;
    	System.out.println("Unfinished jobs: ");
    	int x = job_left.size();
    	for (String name: job_left.keySet()){ 
            Pair<Job> value = job_left.get(name); 
            uncompleted_queue.insert(value.obj,value.count);
    	}
    	while(x>0) {
    		Pair<Job> value = uncompleted_queue.maxnode();
            System.out.println("Job{user='"+value.obj.user.user1+"', project='"+value.obj.project.project_name+"', jobstatus=REQUESTED, execution_time="+value.obj.runtime+", end_time=null"+", name='"+value.obj.name+"'}");      	
            x--;
    	}

    	System.out.println("Total unfinished jobs: "+job_left.size());
    	System.out.println("--------------STATS DONE---------------") ;

    }
    MaxHeap<Job> left_job = new MaxHeap<Job>();

    public void schedule() {
    	int size = temparray.size();
    	for(int i =0;i<size;i++) {
    		Pair<Job> job = priorityQueue.maxnode();
        	temparray.remove(job.obj.name);

        	int completed_time;

        	
        	if(job.obj.project.budget>=job.obj.runtime) {
        		System.out.println("Executing: "+job.obj.name+" from: "+job.obj.project.project_name);
        		
        		job_done.put(job.obj.name, job);
        		completed_queue.add(job.obj);
        		job.obj.Completed = true;
        		global_time+=job.obj.runtime;
        		completed_time = global_time;
        		job.obj.totaltime = completed_time;
        		job.obj.project.budget-=job.obj.runtime;
        		System.out.println("Project: "+job.obj.project.project_name+" budget remaining: "+job.obj.project.budget);
        		break;
        	}
        	
        		job_left.put(job.obj.name, job);
        		if(temparray.size()>0) {
        		System.out.println("Executing: "+job.obj.name+" from: "+job.obj.project.project_name);   		
        		System.out.println("Un-sufficient budget.");    			
            		
        	}
    	}
    	
    }
}
