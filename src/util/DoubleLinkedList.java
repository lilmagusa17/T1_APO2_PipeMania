package util;

public class DoubleLinkedList implements List{

	Node first;
	Node last;
	int numItems;
	
	/**
     * DoubleLinkedList class constructor
     * Method in charge of initializing the objects
     */
	public DoubleLinkedList() {
		this.first=null;
		this.last=null;
		this.numItems=0;
	}
	/**
     * This method gets the first note in the list
     * @return Node, the first node
     */
	public Node getFirst() {
		return first;
	}
	/**
     * This method verifies if the linked list is empty
     * @return assign "first" as null
     */
	public boolean isEmpty() {
		return first == null;
	}
	/**
	 * This method adds the first node of a list in case its empty or the "next" node, in the oposite case
	 * @param n content of the node
	 * @param primero node
	 * @return recursive call to "addLastR" method
	 */
	public boolean addLastR(Object n, Node primero) {
		if (primero == null) {
			Node created = new Node(n);
			if (first == null) {
				first = created; 
			}
			if (last != null) {
				last.setNext(created);
			}
			last = created;
			numItems++; 
			return true;
		} else if (primero.getNext() == null) {
			Node created = new Node(n);
			primero.setNext(created);
			created.setPrev(primero);
			last = created;
			numItems++; 
			return true;
		} else {
			return addLastR(n, primero.getNext());
		}
	}
	/**
	 * This method adds a last node to the list
	 * @param n content of the node
	 * @return boolean verification of the addition
	 */
	public boolean addLast(Object n) {
		boolean out=addLastR(n, first);	
		return out;	
	}
	/**
	 * This method searches a node in the list
	 * @param n content of the node
	 * @return the result of the search
	 */
	public Node search(Object n) {
		Node out=searchR(n,first);
		return out;
	}
	/**
	 * This is a recursive method that searches a node in the list
	 * @param n content of the node
	 * @param primero the first node of the list
	 * @return the result of the search
	 */
	public Node searchR(Object n, Node primero) {
		Node out=null;
		
		if(primero.getContent().equals(n)) {
			out=primero;
		}else {
			searchR(n,primero.getNext());
		}
		
		return out;
	}
	/**
	 * This is a recursive method that searches a node in the list with its position
	 * @param index position of the node
	 * @param primero first node of the list
	 * @return the result of the search
	 */
	public Node searchNodePositionR(int index, Node primero) {
		int length = 0;
        // if head node is null (list is empty), then return -1
        if (primero == null) 
            return null;
        // check if current position of the LinkedList is equal to the index value
        if (length == index)
            return primero;
        // move to the next node and decrease the index value by 1
        return searchNodePositionR(index-1,primero.getNext());
	}
	/**
	 * This method searches a node in the list with its position
	 * @param index position of the node
	 * @return the result of the search
	 */
	public Node searchNodePosition(int index) {
		Node out=searchNodePositionR(index,first);
		return out;
	}
}