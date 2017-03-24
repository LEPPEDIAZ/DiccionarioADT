public class BinaryTree<T extends Asociasion<T>>{
	private Node<T> root;
	public BinaryTree(){
		this.root = null;
	}

	

	public T find(T id){
		Node<T> current = root;
		while(current!=null){
			if(current.data.equals(id)){
				return current.data;
			}else if(current.data.compareTo(id)>0){
				current = current.left;
			}else{
				current = current.right;
			}

		}

		return null;
	}

	public Node<T> getRoot() {
		return root;
	}



	public void setRoot(Node<T> root) {
		this.root = root;

	}
	
		if(current.left==null && current.right==null){
			if(current==root){
				root = null;
			}

			if(isLeftChild ==true){
				parent.left = null;
			}else{
				parent.right = null;
			}

		}

		else if(current.right==null){
			if(current==root){
				root = current.left;
			}else if(isLeftChild){
				parent.left = current.left;
			}else{
				parent.right = current.left;
			}
		}

		else if(current.left==null){
			if(current==root){
				root = current.right;
			}else if(isLeftChild){
				parent.left = current.right;
			}else{
				parent.right = current.right;
			}

		}else if(current.left!=null && current.right!=null){


			Node<T> successor	 = getSuccessor(current);
			if(current==root){
				root = successor;
			}else if(isLeftChild){
				parent.left = successor;
			}else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return true;		
	}

	

	public Node<T> getNext(Node<T> deleleNode){
		Node<T> next =null;
		Node<T> nextParent =null;
		Node<T> current = deleleNode.right;
		while(current!=null){
			nextParent = next;
			nexy = current;
			current = current.left;
		}
		if(next!=deleleNode.right){
			nextParent.left = next.right;
			next.right = deleleNode.right;
		}
		return next;
	}

	public void insert(T id){
		Node<T> newNode = new Node<T>(id);
		if(root==null){
			root = newNode;
			return;
		}
		Node<T> current = root;
		Node<T> parent = null;
		while(true){
			parent = current;
			if(id.compareTo(current.data)<0){				
				current = current.left;
				if(current==null){
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					parent.right = newNode;
					return;
				}
			}
		}
	}

	public void display(Node<T> root){
		if(root!=null){
			display(root.left);
			System.out.print(root.data + " ");
			display(root.right);
		}
	}
}


}