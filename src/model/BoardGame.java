package model;

import java.util.Random;
import util.Node;

import util.DoubleLinkedList;

public class BoardGame {
	
	public DoubleLinkedList list;
	public int time;
	public long startTime;
	public long endTime;
	public int startPipe;
	public int endPipe;
	public int numPipes;
	
	public BoardGame() {
		this.startTime=System.currentTimeMillis()/1000;
		this.time=0;
		this.list = new DoubleLinkedList();
		
		int i=0;
		while(i<64) {
			list.addLast("x");
			i++;
		}
		
		int a=generateRandomPos();
		int b=generateRandomPos();
		int f=0,d=0;
		if(a<b) {
			f=a;
			d=b;
		}else {
			f=b;
			d=a;
		}
		
		this.startPipe=f;
		this.endPipe=d;
		addPipe(f,0);
		addPipe(d,4);
		this.numPipes=0;
		
	}

	/**
	 * Creates a random number between 0 and 63 in order to place the beggining pipe in a random node
	 * @return x random position
	 */
	public int generateRandomPos() {
		
		Random rand=new Random();
		int x = rand.nextInt(63 - 1);
		return x;
		
	}
	
	public String printBoard(){
		String out="║   0 1 2 3 4 5 6 7 ║\n";
		
		Node primero = list.getFirst();
		for(int i=0; i<8; i++) {
			out+= "║ "+i+" ";
			for(int j=0; j<8; j++) {
				out += primero.getContent().toString()+" ";
				primero = primero.getNext();
			}
			out += "║\n";
		}
		return out;
	}
	
	/**
	 * return a type of pipe as a string according to the option given
	 * @param opc option of pipe
	 * @return type of pipe (0: F, 1: =, 2: ||, 3: o, 4: D)
	 */
	public String getPipeType(int opc) {
		String out="";
		
		switch(opc) {
			case 0: out="F";
					break;
			case 1: out="=";
					break;
			case 2: out="║";
					break;
			case 3: out="o";
					break;
			case 4: out="D";
					break;	
		}
		return out;
	}
	
	/**
	 * Add pipe according to the coordinates of the node in the board
	 * <p>If the position of the new pipe is an out of the board bounds,
	 * or it is to be located in the place of the end or start pipe, the pipe is not added</p>
	 * @param x x coordinate of the node (in base 0)
	 * @param y y coordinate of the node (in base 0)
	 * @param type number corresponding to the option of the pipe type
	 * @return if the pipe could be added or not
	 */
	public boolean addPipe(int x, int y, int type) {
		boolean out=false;
		int pos=calculatePos(x,y);
		String pipe=getPipeType(type);
		
		if(pos==-1) {
			out=false;
		}else if(pos==startPipe || pos==endPipe) {
			out=false;
		}else{
			list.searchNodePosition(pos).setContent(pipe);
			
			out=true;
			numPipes++;
		}
		
		
		return out;
	}
	
	/**
	 * Adds pipe according to the number of the node
	 *
	 * @param index position of the node
	 * @param type  number corresponding to the option of the pipe type
	 */
	public void addPipe(int index, int type) {
		boolean out=false;
		
		String pipe=getPipeType(type);
		
		if(!(index<0 || index>63)) {
			list.searchNodePosition(index).setContent(pipe);
		}

	}
	
	/**
	 * Calculated the number of the node (in a LinkedList of 64 nodes) according to its location in an 8x8 display.
	 * @param x coordinate x of the node
	 * @param y coordinate y of the node
	 * @return int representing the number of the node that corresponds to said coordinate
	 */
	public int calculatePos(int x,int y) {
		int position=-1;
		
		if(x<0 || y<0 || x>7 || y>7) {
			position=-1;
		}else {
			position=8*y+x;
		}
		
		
		return position;
	}
	
	/**
	 * Method used to calculate the players score
	 * MUST ONLY BE USED IF METHOD VERIFIED IS TRUE, because only if the game is right, the player enters the ranking
	 * @return score of the player
	 */
	public long calculateScore() {
		long score=0;
		score=(100-numPipes)*10 - getOverallPlayTime(this.startTime,this.endTime);

		return Math.abs(score);
	}

	/**
	 * Calculates the length of the game of the user
	 * @param start start time of the game in miliseconds
	 * @param end end time of the game in miliseconds
	 * @return out overall play time in seconds
	 */
	public long getOverallPlayTime(long start, long end){
		long out=0;

		out=(end-start);

		return out;
	}
	
	/**
	 * Verifies if this BoardGame is correctly developed
	 * <p>if true, takes the time in miliseconds</p>
	 * @return (true: the pipe system is correctly build) (false: there's a mistake in the result)
	 */
	public boolean verify() {
		
		boolean result=isSuccesful(list.searchNodePosition(startPipe),1);
		
		if(result) 
			endTime=System.currentTimeMillis()/1000;
		return result;
		
	}
	/**
	 * Verifies wheter the water flow is correctly built
	 * <p>Checks the current node, if the next node in the flow is correct (according to the game rules),
	 * checks the next node in the flow.</p>
	 * <p>Otherwise, if the user placed a pipe incorrectly, returns false</p>
	 * @param actual node that is being evaluated
	 * @param direction direction of the flow of the water (1: hacia la izq)
	 * 													   (2: hacia la der)
	 * 													   (3: hacia arriba)
	 * 													   (4: hacia abajo)
	 * @return (true: the pipe system is correctly build) (false: there's a pipe wronly placed or the game is not finished
	 */
	public boolean isSuccesful(Node actual,int direction) {
		boolean out=false;
		Node abajo=moveOfLineDown(actual);
		Node arriba=moveOfLineUp(actual);
		Node antes=actual.getPrev();
		Node despues=actual.getNext();
		
		if(actual==null || actual.getContent().equals("x")) {
			out=false;
		}else {
			
			if(actual.getContent().equals("F")) {
			
			if(despues!=null && despues.getContent().equals("="))
				out=isSuccesful(despues,2);
			else if(antes!=null && antes.getContent().equals("="))
				out=isSuccesful(antes,1);
			else if(abajo!=null && abajo.getContent().equals("║"))
				out=isSuccesful(abajo,4);
			else if(arriba!=null && arriba.getContent().equals("║"))
				out=isSuccesful(arriba,3);

			
		}else if(actual.getContent().equals("=") && direction==1) {
		
			if(antes==null) {
				out=false;
			}else { 
				if(antes.getContent().equals("D"))
					out=true;
				else if(antes.getContent().equals("=") || antes.getContent().equals("o"))
					out=isSuccesful(antes,1);

			}
			
		}else if(actual.getContent().equals("=") && direction==2) {
			
			if(despues==null) {
				out=false;
			}else {
				if(despues.getContent().equals("D"))
					out=true;
				else if(despues.getContent().equals("=") || despues.getContent().equals("o"))
					out=isSuccesful(despues,2);

			}
			
		}else if(actual.getContent().equals("║") && direction==3) {
			
			if(arriba==null) {
				out=false;
			}else { 
				if(arriba.getContent().equals("D"))
					out=true;
				else if(arriba.getContent().equals("║") || arriba.getContent().equals("o"))
					out=isSuccesful(arriba, 3);

			}
			
		}else if(actual.getContent().equals("║") && direction==4) {
			
			if(abajo==null) {
				out=false;
			}else {
				if(abajo.getContent().equals("D"))
					out=true;
				else if(abajo.getContent().equals("║") || abajo.getContent().equals("o"))
					out=isSuccesful(abajo, 4);

			}
			
		}else if(actual.getContent().equals("o") && (direction==1 || direction==2)) {
			
			if(arriba!=null && arriba.getContent().equals("║"))
				out=isSuccesful(arriba,3);
			else if(abajo!=null && abajo.getContent().equals("║"))
				out=isSuccesful(abajo,4);

			
		}else if(actual.getContent().equals("o") && (direction==3 || direction==4)) {
			
			if(antes!=null && antes.getContent().equals("="))
				out=isSuccesful(antes,1);
			else if(despues!=null && despues.getContent().equals("=")) {
				out=isSuccesful(despues,2);
			}
			
		}
	}
		return out;
	}
	/**
	 *
	 * @param actual node being evaluated
	 * @return node that is 8 nodes below the actual node (node below in the board game)
	 */
	public Node moveOfLineDown(Node actual) {
		
		for(int i=0;i<8;i++) {
			actual=actual.getNext();
			if(actual==null) {
				return null;
			}
		}
		
		return actual;
		
	}
	/**
	 *
	 * @param actual node being evaluated
	 * @return node that is 8 nodes on top the actual node (node from the previous line in the board game)
	 */
	public Node moveOfLineUp(Node actual) {
		
		for(int i=0;i<8;i++) {
			actual=actual.getPrev();
			if(actual==null) {
				return null;
			}
		}
		
		return actual;	
	}
}