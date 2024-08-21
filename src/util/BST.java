package util;

import model.Player;

public class BST implements Tree{

    private BSTNode root;
    private int size;

    public BST(){
        this.root = null;
    }
    /**
     * This method shows the contents of the BST
     * @return out, the contents of the BTS
     */
    @Override
    public String print() {
        String out = "";
        if(root == null)
            out="No players in the ranking yet";
        else
            out=root.print();
        return out;
    }
    /**
     * This method verifies if the BST is empty or not
     * @return true if the BST is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        //verify if the tree is empty
        return root == null;
    }
    /**
     * This method adds a player to the BST
     * @param c The player to added
     */
    @Override
    public void add(Comparable<Player> c) {
        if (root == null){
            root = new BSTNode(c);
        }else{
            root.add(c);
        }
        size++;
    }
    /**
     * This method searches a player in the BST
     * @param c The player to searched n the BST
     * @return The found player
     */
    @Override
    public Comparable<Player> search(Comparable<Player> c) {
        Object found = null;
        if(root != null){
            found = root.search(c);
        }
        return (Comparable)found;
    }
}
