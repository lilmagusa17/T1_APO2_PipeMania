package util;
/**
 * The Tree interface represents a generic list data structure 
 */
public interface List {
	/**
     * This method checks if the list is empty.
     * @return True if the list is empty, false if not
     */
	public boolean isEmpty();
	/**
     * This method adds an element to the end of the list.
     * @param n The element added to the list
     * @return True if the element was added successfully, false if not
     */
	public boolean addLast(Object n);
	 /**
     * This method searches for an element within the list.
     * @param n The element to search for.
     * @return The element if found, or null if not found.
     */
	public Object search(Object n);

}
