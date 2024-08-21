package util;

import model.Player;
/**
 * The Tree interface represents a generic tree data structure that can store elements of type Player 
 */
public interface Tree{
    /**
     * This method returns a string representation of the tree
     */
    public String print();
    /**
     * This method checks if the tree is empty
     */
    public boolean isEmpty();
    /**
     * This method adds a comparable element to the tree
     */
    public void add(Comparable<Player> c);
    /**
     * This method searches for a comparable element within the tree
     */
    public Comparable<Player> search(Comparable<Player> c);
}
