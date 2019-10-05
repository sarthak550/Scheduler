package PriorityQueue;

import java.util.Queue;

public class Pair<T extends Comparable<T>> {
	 Pair<T> left,right,parent;
	public T obj;
	public int count;
	Queue<T> q = null;
	 public Pair(T obj,int count){
		 this.obj = obj;
		 this.count = count;
//		 q.add(obj);
	 }
	 public T object() {
		return obj;
		 
	 }
	 
//	public int compareTo(Pair<T> o) {
//
//			if(obj.compareTo(o.object())<0) {
//				
////				System.out.println("1");
//				return 1;
//			}
//			else if(obj.compareTo(o.object())>0) {
////				System.out.println("-1");
//				return -1;
//			}
//		
//			else if((obj).compareTo((o.object()))==0) {
//				if(count<o.count) {
//					return -1;
//				}
//				else if(count>o.count) {
//					return 1;
//				}
//				else {
//					return 0;
//				}
//			}
//			return 0;
//		
//		}
}
