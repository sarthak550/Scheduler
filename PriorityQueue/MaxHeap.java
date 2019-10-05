package PriorityQueue;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueueInterface<T> {

	public  int heapSize = 0;
	private int treeSize = 1000000;
	private int count = 0;
	public Pair<T>[] heap = new Pair[treeSize];
	
    private void swap(Pair<T>[] arr,int largest,int index) {
        Pair<T> temp;
        temp = arr[largest];
        arr[largest] = arr[index];
       	arr[index] = temp;
    }
    
	 private int parentindex(Pair<T>[] arr, int index) {
			 return index/2;
		 
	 }
	 
	 private int leftindex(Pair<T>[] arr, int index) {
	    	return 2*index;

	    }
	    private int rightindex(Pair<T>[] arr, int index) {
	    	return (2*index+1);

	    }

		 private void insertHelper(Pair<T>[] arr, int index, T key,int count) {
			 arr[index] = new Pair(key,count);

			 while(index>1 && (arr[parentindex(arr,index)].obj.compareTo(arr[index].obj)<0 || (arr[parentindex(arr,index)].obj.compareTo(arr[index].obj)==0 && arr[parentindex(arr,index)].count>(arr[index].count)))) {
				 swap(arr,parentindex(arr,index),index);
				 index = parentindex(arr,index);
			 }
		 }
    
	    
	    public void insert(T element) {
	    	heapSize++;
	        heap[heapSize] = null;
	        count++;

	        insertHelper(heap, heapSize, element,count);

	    }
	    public void insert(T element,int count) {
	    	heapSize++;
	    	heap[heapSize] = null;
	    	insertHelper(heap,heapSize,element,count);
	    }
	    
	    private void maxHeapify(Pair<T>[] arr,int index) {
	    	int largestindex = index;
	    	int right = rightindex(arr,index);
	    	int left = leftindex(arr,index);

	    	if ((right<=heapSize || left<=heapSize)   ) {
	    		
	    		if(arr[left].obj.compareTo(arr[right].obj)>0 || (arr[left].obj.compareTo(arr[right].obj)==0 && arr[left].count<arr[right].count)) {
		    		if((arr[left].obj).compareTo(arr[largestindex].obj)>0) {
		    			largestindex = left;

		    		}
		    		else if((arr[left].obj).compareTo(arr[largestindex].obj)==0) {
		    			if(arr[left].count<arr[largestindex].count) {
		    				largestindex = left;
		    			}
		    		}
	    		}
	    		
	    		
	    		else {
	    			
	    			if((arr[right].obj).compareTo(arr[largestindex].obj)>0) {
		    			largestindex = right;
		    		}
		    	


		    		else if((arr[right].obj).compareTo(arr[largestindex].obj)==0) {
		    			if(arr[right].count<arr[largestindex].count) {
		    				largestindex = right;
		    			}
		    		}
	    		}

	    		
	    	}
	    	
	    	if(largestindex != index) {
	    		swap(arr,largestindex,index);
	    		maxHeapify(arr,largestindex);
	    	}

	    }
	    
	    public Pair<T> maxnode(){
	    	if(heap[1]==null) {
	    		return null;
	    	}

	    	Pair<T> max = heap[1];

	    	
	    	heap[1]=heap[heapSize];
	    	heapSize--;
	    	maxHeapify(heap,1);
	    	if(heap[1]==null) {
	    		return null;
	    	}
	        return max; 
	    }
	    
	    public T extractMax() {

	    	if(heap[1]==null) {
	    		return null;
	    	}

	    	T max = heap[1].obj;

	    	
	    	heap[1]=heap[heapSize];
	    	heapSize--;
	    	maxHeapify(heap,1);
	    	if(heap[1]==null) {
	    		return null;
	    	}
	        return max; 
	   }
}