package RedBlack;

import Util.RBNodeInterface;

import java.util.ArrayList;
import java.util.List;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
// red=1;black=0;
	RedBlackNode<T,E> left,right,root,parent;
	T key;
	E value;
	List<E> v = null;
	int color;
	int size;
	public RedBlackNode(T key, E value){
		this.key = key;
		this.value = value;

		v=new ArrayList<E>();
//		if(value == null) {
//			v=null;
//		}
		v.add(value);
	}
    public E getValue() {
        return value;
    }

    public List<E> getValues() {
        return v;
    }
}
