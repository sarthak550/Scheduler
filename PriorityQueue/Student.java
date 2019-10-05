package PriorityQueue;

public class Student implements Comparable<Student>  {
//    public static int count;
	public  String name;
    public Integer marks;
    
    public Student(String trim, int parseInt) {
    	
    	this.name = trim;
    	this.marks = parseInt;
    }


    public int compareTo(Student student) {
    	
    		if(this.marks>student.marks) {
        		return 1;
        	}
        	else if(this.marks<student.marks) {
        		return -1;
        	}
        	else {
        		return 0;
        	}
    }

    public String getName() {
        return this.name;
    }
     
    public String toString() {
    	String s = "Student{name='"+name+"', marks="+marks+"}";
    	return s;
    }



}