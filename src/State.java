//package alphaBeta;


import java.util.*;

/**
 * To keep track of each state
 * @author zeli, zli48, 676755673
 *
 */
public class State {
	private int numX;
	private int numO;
	private String stateString;
	private ArrayList<String> states;//read the string into the layout of the board
	private int value;//a win state, value is 0; otherwise, 1
	
	
	public State(String s){
		numX = 0;
		numO = 0;
		value = 0;
		states = new ArrayList<String>();
		stateString = s;
		setStates(s);
		decideValue();
//		System.out.println(isMax);
	}
	
	public int getSpace(){
		return 9 - numX-numO;
	}
	
	public boolean terminalTest(){
		if(numX+numO==9||value == 1||value == -1)
			return true;
		return false;
	}
	public void decideValue(){
		 if(states.get(3).equals("X")&&states.get(4).equals("X")&&states.get(5).equals("X")
				||states.get(6).equals("X")&&states.get(7).equals("X")&&states.get(8).equals("X")
				||states.get(2).equals("X")&&states.get(4).equals("X")&&states.get(6).equals("X")
				||states.get(1).equals("X")&&states.get(4).equals("X")&&states.get(7).equals("X")
				||states.get(2).equals("X")&&states.get(5).equals("X")&&states.get(8).equals("X"))
			 value = 1;
		else if(states.get(0).equals("X")){
			if(states.get(1).equals("X")&&states.get(2).equals("X"))
				value = 1;
			else if(states.get(3).equals("X")&&states.get(6).equals("X"))
				value = 1;
			else if(states.get(4).equals("X")&&states.get(8).equals("X"))
				value = 1;
		}
		 if(states.get(3).equals("O")&&states.get(4).equals("O")&&states.get(5).equals("O")
					||states.get(6).equals("O")&&states.get(7).equals("O")&&states.get(8).equals("O")
					||states.get(2).equals("O")&&states.get(4).equals("O")&&states.get(6).equals("O")
					||states.get(1).equals("O")&&states.get(4).equals("O")&&states.get(7).equals("O")
					||states.get(2).equals("O")&&states.get(5).equals("O")&&states.get(8).equals("O"))
				 value = -1;
			else if(states.get(0).equals("O")){
				if(states.get(1).equals("O")&&states.get(2).equals("O"))
					value = -1;
				else if(states.get(3).equals("O")&&states.get(6).equals("O"))
					value = -1;
				else if(states.get(4).equals("O")&&states.get(8).equals("O"))
					value = -1;
			}
		 
	}
	public void setStates(String s){
		String tokens[] = s.split(" ");
		for(int i = 0; i < 9; i++){
			if(tokens[i].equals("X"))
				numX++;
			if(tokens[i].equals("O"))
				numO++;
			states.add(tokens[i]);	
//			System.out.println(states.get(i));

		}
	}
	
	public void displayStates(){
		for (int i = 0; i < 9; i++){
			System.out.print(states.get(i)+" ");
			if((i+1)%3==0){
				System.out.println();
			}
		}
	}
	public boolean maxMove(){
		if((numX-numO) == 0)
			return true;
		return false;
	}

	
	public String getStateString() {
		return stateString;
	}

	public void setStateString(String stateString) {
		this.stateString = stateString;
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public void setStates(ArrayList<String> states) {
		this.states = states;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
//	public static void main(String args[]){
//		String str = "b X b b O b b b b";
//		State s = new State(str);
//		System.out.println(s.terminal_test());
////		System.out.println(s.maxMove());
//		s.displayStates();
//	}

}
