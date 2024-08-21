package util;

public class Node {
	
	Node next;
	Node prev;
	Object content;
	/**
     * This method constructs a new Node object with the specified content.
     * @param n Is the content to be stored in this node
     */
	public Node(Object n) {
		this.next=null;
		this.prev=null;
		this.content=n;
	}
	/**
     * This method gets the note in the next position
     * @return Node, the next node
     */
	public Node getNext() {
		return next;
	}
	/**
     * This method sets a new Node as the "next" node
     * @param next the node that will be set as the "next" node
     */
	public void setNext(Node next) {
		this.next = next;
	}
	/**
     * This method gets the note in the previous position
     * @return Node, the previous node
     */
	public Node getPrev() {
		return prev;
	}
	/**
     * This method sets a new Node as the "prev" node
     * @param prev the node that will be set as the "prev" node
     */
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	/**
     * This method gets the content inside of the node 
     * @return Object, the content of the node
     */
	public Object getContent() {
		return content;
	}
	/**
     * This method sets the content of a node
     * @param content the content that will be setted as the content of a node
     */
	public void setContent(Object content) {
		this.content = content;
	}
    
}
