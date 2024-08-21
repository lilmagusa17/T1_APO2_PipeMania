package model;

import util.DoubleLinkedList;

public class Player implements Comparable<Player>{
    private String nickname;
    private long score;
    private BoardGame boardGame;
    /**
     * Player's class constructor
     * Method in charge of initializing the objects
     */
    public Player(String nickname) {
        this.nickname = nickname;
        this.score = 0;
        this.boardGame =new BoardGame();
    }
    /**
     * Method that returns the board game
     * @return boardGame
     */
    public BoardGame getBoardGame() {
        return this.boardGame;
    }
    /**
     * Method that shows the board to the user 
     * @return boardGame.printBoard();, String, the board of the game
     */
    public String showBoard() {
        return boardGame.printBoard();
    }

    /**Compares the score of a player to another player to create and updates the ranking
     * @return the result of the comparison
     */
    @Override
    public int compareTo(Player player) {

        int compare = 0;
        if(this.score>player.score){
            compare = 1;
        }else if(this.score<player.score){
            compare = -1;
        }
        return compare;
    }
    /**Verifies the solution of the board done by a player 
     * @return the result of the verification
     */
    public boolean verifyGame(){
        boolean verify = boardGame.verify();
        if(verify){
            setScore();
        }
       return verify;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setScore() {
        this.score = boardGame.calculateScore();
    }
    /**Generates the toString of the class
     * @return out, string of this class
     */
    public String toString() {
    	String out="";
    	out+=nickname+":  "+score + "\n";
    	return out;
    }
}