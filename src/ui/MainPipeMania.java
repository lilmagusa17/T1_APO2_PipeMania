package ui;

import model.PipeManiaController;
import java.util.Scanner;


/**
 * The Pipe Mania game consists of a sewer system simulation.
 * In this game, the player can locate three different types of “pipes” within an 8x8 board, with the objective of connecting the “water source” to the “draining pipe”.
 * The users can also view a players’ ranking according to the scores gained, with each of the players’ names.
 * @version 1.0
 * @since August 2023
 */
public class MainPipeMania {
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[34m";
    public static final String YELLOW = "\u001B[33m";

    public static final String ORANGE = "\u001B[38;5;208m";

    public static final String PURPLE = "\u001B[35m"; // Magenta


    /**
     * con is the association relationship between the
     * MainPipeMania class and the PipeManiaController class
     */
    private PipeManiaController con;
    /**
     * sc is the name given to the object that allows the program to read the user's input
     */
    private Scanner sc;

    /**
     * MainPipeMania class constructor
     * Method in charge of initializing the objects
     */
    public MainPipeMania() {
        //Initializing objects
        sc= new Scanner(System.in);
        con = new PipeManiaController();
    }


    public static void main (String[] a){
        // Creating an object of the class
        MainPipeMania obPpal = new MainPipeMania();
        // Variable to store the option chosen by the user
        int option= 0;

        //Loop to show the menu until the user
        // Exit option is 0
        do {
            option =obPpal.showMenuAndGetOption();
            obPpal.answerOption(option);
        }while (option !=0);


    }


    /**
     * Method that shows the general menu and gets the option chosen by the user
     * @param userOption, int is the option chosen by the user
     */
    public void answerOption(int userOption) {
        switch(userOption) {
            case 0:
                System.out.println(BLUE + "Thanks for playing, see you soon!" + BLUE + RESET);
                break;
            case 1:
                System.out.println(BLUE + "\nNEW GAME" + " Starting...\n" + BLUE + RESET);
                System.out.println("Please enter you're nickname:");
                String nickN = sc.nextLine();
                con.setPlayer(nickN);
                int opc;
                boolean x;
                do{
                    opc=gameMenu();
                    x=doGameOption(opc);
                }while(opc!=0 && x);

                break;
            case 2:
            	seeScores();
                break;
        }

    }
    /**
     * Method that shows the general game menu once the "New Game" option is choosen in 
     * the general menu. Allows user to choose between placing a pipe or verifing
     * if the solution they implemented is correct or not
     * @param input, int is the option chosen by the user
     * @return true, boolean, the result value of the method
     */
    public boolean doGameOption(int input){
        switch(input){
            case 0:
                System.out.println( BLUE + "Thanks for playing!\n" + BLUE + RESET);
                break;
            case 1:
                addPipe();
                break;
            case 2:
                boolean verify = con.verifyGame();
                if(verify){
                    System.out.println(YELLOW + "Congratulations! You won!"+ YELLOW + RESET);
                    return false;
                }else{
                    System.out.println(PURPLE + "Aww, you lost! Keep playing"+ PURPLE + RESET);
                }
                break;

        }
        return true;
    }
    /**
     * Method that asks the user for information on the type and coordinates of 
     * the pipe they want to place in case they have selected the "addPipe" option in the 
     * new game's general menu
     */
    private void addPipe(){
        int opPipe;
        do{
            System.out.println(BLUE + "What type of pipe do you wish to place:" + BLUE + RESET);
            System.out.println("(1) =");
            System.out.println("(2) ║ ");
            System.out.println("(3) o ");
            opPipe = sc.nextInt();
        }while(opPipe<1 || opPipe>3);

        System.out.println("\nEnter the x (horizontal) coordinate where you want to place the pipe");
        int xCoord = sc.nextInt();
        System.out.println("\nEnter the y (vertical) coordinate where you want to place the pipe");
        int yCoord = sc.nextInt();
        String pipeAdded = con.addPipe(xCoord, yCoord, opPipe);
        System.out.println(pipeAdded);
    }
    /**
     * Method that shows the user the ranking of the game when they choose the "See Scores" 
     * option in the general menu
     */
	private void seeScores() {
        System.out.println("=====================================");
		System.out.println(YELLOW + "             RANKING" + YELLOW+ RESET);
        System.out.println("=====================================");
        System.out.println(con.printRanking());
	}
	/**
     *Method that shows the menu and gets the option chosen by the user
     * @return input, int is the option chosen by the user
     */
    public int showMenuAndGetOption() {
        int input;
        System.out.println(ORANGE + "\n==================================" + RESET +
                     BLUE + "\n  Hi there! It's Pipe Mania Game " + BLUE + RESET +
                ORANGE +   "\n=================================="+ RESET+
                "\nPlease choose an option: \n\n"+
                BLUE + "(1)" + RESET  +"New Game\n" +
                BLUE + "(2)"+RESET+ "See Scores\n" +

                PURPLE+ "(0)" + RESET +"To exit"

        );
        input = sc.nextInt();
        sc.nextLine();
        return input;
    }
    /**
     *Method that shows the new game's menu and gets the option chosen by the user
     * @return input, int is the option chosen by the user
     */
    public int gameMenu(){
        int input;
        System.out.println(BLUE + "\nHi there "+con.getPlayerName()+"!"+ BLUE + RESET +
                "\no===================o\n"+
                con.printBoard()+
                "o===================o\n"+
                BLUE + "\nPlease choose an option: \n\n"+ RESET+
                "(1) Add pipe\n" +
                "(2) Verify game\n" +

                "(0) To exit"
        );
        input = sc.nextInt();
        if(input<0 || input>2){
            System.out.println(PURPLE + "Please enter a valid option" + PURPLE+ RESET);
            input=sc.nextInt();
        }

        sc.nextLine();
        return input;

    }

}