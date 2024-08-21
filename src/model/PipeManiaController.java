package model;
import util.BST;

public class PipeManiaController {

    public BST ranking;

    public Player actual;
    /**
     * PipeManiaController's class constructor
     * Method in charge of initializing the objects
     */
    public PipeManiaController(){
        this.ranking=new BST();
        this.actual=null;
    }

    /**
     * Method that returns the current player's name
     * @return actual.getNickname(), String, the current player's name
     */
    public String getPlayerName(){
        return actual.getNickname();
    }

    /**
     * Method that sets the current player's name
     * @param name, String, the current player's name
     */
    public void setPlayer(String name){
        actual=new Player(name);
    }

    /**
     * Method that shows a new game's board
     * @return msg, String, the new game´s board with an F and D placed randomly
     */
    public String printBoard(){
		String msg = "";
        BoardGame boardGame = new BoardGame();
		msg += actual.showBoard();
		return msg;
    }
    /**
     * Method that shows a new game's board
     * @param x, int is the x coordenate of the pipe choosen by user
     * @param y, int is the y coordenate of the pipe choosen by user
     * @param type, int is the type of pipe choosen by user
     * @return output, String, confirmation message of the pipe's placement
     */
    public String addPipe(int x, int y, int type){

        boolean out=actual.getBoardGame().addPipe(x,y,type);
        String output;

        if(out)
            output="Pipe added!";
        else
            output="You can't add that pipe in here :(";

        return output;
    }
    /**
     * Method that verifies a game's solution provided by the user and in case it's 
     * correct, it adds the user's score to the ranking
     * @return out, boolean, verification result of the game's solution
     */
    public boolean verifyGame(){
        boolean out=actual.verifyGame();

        if(out){
            addPlayerToRanking();
            actual=null;
        }
        return out;
    }
    /**
     * Method that adds a user's score to the ranking when their
     * implemented solution is correct
     */
    public void addPlayerToRanking(){
        ranking.add(actual);
    }

    /**
     * Method that shows the game's ranking to the user
     * @return ranking.print();, String, the ranking lists
     */
    public String printRanking(){
        return ranking.print();
    }

}
