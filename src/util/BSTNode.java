package util;

import model.Player;

@SuppressWarnings("rawtypes")
public class BSTNode implements Tree{

    private Comparable<Player> content;
    private BSTNode left;
    private BSTNode right;

    private NodeType type;

    public BSTNode(Comparable<Player> content){
        this.content = content;
        this.left = null;
        this.right = null;
        this.type = NodeType.LEAF;
    }
    /**
     * This method checks if the node is empty
     * @return true if the node is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return left == null && right == null;
    }
    /**
     * This method adds a new content to the BSTNode based on its comparison with the current content
     * @param c The content going to be added
     */
    @Override
    public void add(Comparable<Player> c) {
        switch (type){
            case LEAF:
                BSTNode newNode = new BSTNode(c);
                int comp = content.compareTo((Player) c);
                if(comp>=0){
                    left = newNode;
                }else{
                    right = newNode;
                }
                type = NodeType.BRANCH;
                break;

            case BRANCH:
                addBranch(c);
                break;
        }

    }
    /**
     * This method adds new content to a branch node based on its comparison with the current content
     * @param c The added content
     */
    private void addBranch(Comparable<Player> c){
        int comp = content.compareTo((Player)c);
        if(comp>=0){
            if(left != null) {
                left.add(c);
            }else{
                left = new BSTNode(c);
            }
        }else{
            if(right != null) {
                right.add(c);
            }else{
                right = new BSTNode(c);
            }
        }
    }
    /**
     * This method searches for a content in the BSTNode
     * @param c The content to be searched
     * @return The found content
     */
    @Override
    public Comparable search(Comparable<Player> c) {

        Comparable found = null;
        switch (type){
            case LEAF:
                if(content.compareTo((Player) c)==0){
                    found = content;
                }
                break;

            case BRANCH:
                int comp = content.compareTo((Player) c);
                if(comp>=0){
                    if(left != null){
                        found = left.search(c);
                    }
                }else{
                    if(right != null){
                        found = right.search(c);
                    }
                }
                break;
        }
        return found;
    }
    /**
     * This method shows the content of the BSTNode
     * @return String representation of the content organized in order
     */
    @Override
    public String print() {
        return printInOrder();
    }
    /**
     * This method shows the content of the BSTNode in order
     * @return String representation of the content in order
     */
    public String printInOrder(){
        String out = "";

        switch (type){
            case LEAF:
                out = content.toString();
                break;

            case BRANCH:
                out += right!=null?right.printInOrder():"";
                out += content.toString();
                out += left!=null?left.printInOrder():"";
                break;
        }
        return out;
    }
}
