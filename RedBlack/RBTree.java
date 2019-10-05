package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
	// red=1;black=0;


	public RedBlackNode<T,E> root;
	public RedBlackNode<T,E> refrence = new RedBlackNode<T,E>(null,null);

	
	private void colorInterchange(RedBlackNode<T,E> node) {
		if(node.color==1) {
			node.color = 0;
		}
		else if(node.color==0) {
			node.color = 1;
		}
		if(node.left.color==1) {
			node.left.color = 0;
		}
		else if(node.left.color==0) {
			node.left.color = 1;
		}
		if(node.right.color==1) {
			node.right.color = 0;
		}
		else if(node.right.color==0) {
			node.right.color = 1;
		}
	}

	private RedBlackNode<T, E> insertHelper(RedBlackNode<T,E> node,T key,E value){
		if(node==null) {
			node=new RedBlackNode<T,E>(key,value);
			node.color=1;
			return node;
		}
		
		else {
			if(key.compareTo(node.key)==0) {
				node.v.add(value);

			}
			else {
				if(key.compareTo(node.key)<0) {
					node.left = insertHelper(node.left,key,value);
				}
				else {
					node.right = insertHelper(node.right,key,value);
				}			
			}
		}
					
		if(node!=null) {
			
			if(node.right!=null && node.left == null) {
				if(node.right.color==1) {
					RedBlackNode<T,E> temp = node.right;
					node.right = temp.left;
					temp.left = node;
					temp.color = temp.left.color;
					temp.left.color = 1;
					node = temp;
				}
			}
			
			if(node.left!=null && node.right!=null) {
				if(node.right.color==1 && node.left.color==0) {
					RedBlackNode<T,E> temp = node.right;
					node.right = temp.left;
					temp.left = node;
					temp.color = temp.left.color;
					temp.left.color = 1;
					node = temp;
				}
			}

			if(node.left!=null && node.left.left!=null) {
				if(node.left.color==1 && node.left.left.color==1) {
					RedBlackNode<T,E> temp = node.left;
					node.left = temp.right;
					temp.right = node;
					temp.color = temp.right.color;
					temp.right.color = 1;
					node = temp;
				}
			}
			
			if(node.left!=null && node.right!=null) {
				if(node.left.color==1 && node.right.color==1) {

					colorInterchange(node);
				}
			}
		}
		
		
		node = refixTree(node);
		return node;

	}
	
	
	
    private RedBlackNode<T, E> refixTree(RedBlackNode<T, E> node) {
		if(node.right!=null) {
			if(node.right.color==1) {
				RedBlackNode<T,E> temp = node.right;
				node.right = temp.left;
				temp.left = node;
				temp.color = temp.left.color;
				temp.left.color = 1;
				node = temp;				
			}
			
		}
		if(node.left!=null && node.left.left!=null) {
			if(node.left.color==1 && node.left.left.color==1) {
				RedBlackNode<T,E> temp = node.left;
				node.left = temp.right;
				temp.right = node;
				temp.color = temp.right.color;
				temp.right.color = 1;
				node = temp;			}
		}
		if(node.left!=null && node.right!=null) {
			if(node.left.color==1 && node.right.color==1) {

				colorInterchange(node);
			}
		}

	
    	return node;
	}
	public void insert(T key, E value) {

    	root = insertHelper(root,key,value);

    	root.color = 0;
    }


	public RedBlackNode<T,E> searchHelper(RedBlackNode<T, E> root,T key){
		if(root == null) {
			return refrence;
		}
		else {
			if(root.key!=null) {
				if(root.key.compareTo(key)==0){
					return root;
				}
				else if((root.key).compareTo(key)>0) {
					root = searchHelper(root.left,key);
				}
				else if((root.key).compareTo(key)<0) {
					root = searchHelper(root.right,key);
				}
			}			
		}
		
		return root;
    }
    public RedBlackNode<T,E> search(T key) {

    	RedBlackNode<T,E> search = searchHelper(root,key);
    	if(search == refrence) {
    		search.v=null;
    	}
        return search;
    }
}