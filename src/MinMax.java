//package alphaBeta;
import java.util.*;

/**
 * 
 * @author zeli, zli48, 676755673
 * to perform the algorithm in this class
 */
public class MinMax {
//	private String s;
	private ArrayList<Action> candactions;//to keep track of the candidate actions that might be taken
	private ArrayList<Action> finalActions;//to store the final optimal actions that could be taken by X
	private int numNodes;
	/**
	 * Constructor
	 * @param str, String, a Board condition
	 */
	public MinMax(String str){
		numNodes = 0;
		candactions = new ArrayList<Action>();
		finalActions = new ArrayList<Action>();
		finalActions.add(new Action(str));
	}
	
	
	
	/**
	 * 	
	 * @param str, String, to form an Action with certain state in
	 * @param isPlayer, boolean, to decide if it's X's move
	 * @return note though no return value, the finalAction is setup
	 */
	public int minimaxDecision(String str,boolean isPlayer){
		Action a = new Action(str);
		if(a.getCurrent().terminalTest()){
			candactions.add(a);
		}
		int value = -2;//to keep track of the value
		int max = -2;
		if(isPlayer){
			for(int i = 0; i < a.getNext().size();i++){
				String s = a.getNext().get(i);
				value = minValue(s);
				if(max < value)
					max = value;
				Action ac = new Action(s);
				ac.setName(a.getPossibleActions().get(i));
				ac.setCurrentValue(value);
				candactions.add(ac);
			}
		}if(max == 0){
			System.out.println("No one will win.");
			manipulate(0);
		}if(max == 1){
				System.out.println("X will win.");
				manipulate(1);
		}if(max == -1){
			System.out.println("O wins.");
			manipulate(-1);
		}	
		return numNodes;
	}
	/**
	 * helper funcition to help doing the work in minimaxDecision
	 * @param ind
	 */
	private void manipulate(int ind){
		for(int i = 0; i < candactions.size();i++){
			if(candactions.get(i).getCurrentValue() == ind){
				String str = candactions.get(i).getCurrent().getStateString();
				Action ac = new Action(str);
				ac.setName(candactions.get(i).getName());
				ac.setCurrentValue(ind);
				finalActions.add(ac);
			}
		}
		candactions.clear();
	}
	
	/**
	 * to deal with a terminated board
	 * @param s, String, a board
	 * @return
	 */
	public boolean alreadyFinished(String s){
		Action a = new Action(s);
		if(a.getCurrent().getValue() == 1){
			System.out.println("X already wins.");
			return true;
		}
		else if(a.getCurrent().getValue() == -1){
			System.out.println("O already wins.");
			return true;
		}
		else if(a.getCurrent().terminalTest()&&a.getCurrent().getValue() == 0){
			System.out.println("Nobody wins and the board is filled.");
			return true;
		}
		return false;
		
	}
	
	/**
	 * in the algorithm, max-value(State)
	 * @param str, String, a board condition
	 * @return the utility value
	 */
	public int maxValue(String str){
		Action a = new Action(str);
		if(a.getCurrent().terminalTest()){
			return a.getCurrent().getValue();
		}
		numNodes++;
		int value = -2;
		for(String s:a.getNext()){
			value = max(value,minValue(s));	
		}
		return value;
		
	}
	
	/**
	 *in the algorithm, min-value(State)
	 * @param str, String, a board condition
	 * @return the utility value
	 */
	public int minValue(String str){
		Action a = new Action(str);
		if(a.getCurrent().terminalTest()){
			return a.getCurrent().getValue();
		}
		numNodes++;
		int value = 2;
		for (String s : a.getNext()){
			value = min(value,maxValue(s));
		}
		return value;
	}
	
	
	
	private int max(int a, int b){
		return a>b?a:b;
	}
	private int min(int a, int b){
		return a<b?a:b;
	}
	
	
	public int getNodesExpanded(){
		return numNodes;
	}

	public void displayActions(){
//		System.out.println("Initial possible moves:");
//		finalActions.get(0).displayPossibleActions();
		System.out.println("Optimal moves X can take:");
		System.out.print("<");
		int i;
		for ( i = 1; i < finalActions.size()-1;i++){	
			System.out.print(finalActions.get(i).getName()+" ");
//			finalActions.get(i).getCurrent().displayStates();
//			System.out.println(finalActions.get(i).getCurrentValue());
//			finalActions.get(i).displayPossibleActions();
		}
		System.out.println(finalActions.get(i).getName()+">");
//		System.out.println("Number of nodes visited: " + numNodes);
	}
	

}
