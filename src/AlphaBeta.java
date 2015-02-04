//package alphaBeta;
import java.util.*;
/**
 * AlphaBeta pruning
 * @author zeli
 *
 */
public class AlphaBeta {
	private int numNodes;
	private ArrayList<Action> finalactions;
	private Map<String,Integer> candactions;
	
	public AlphaBeta(String s){
		numNodes = 0;
		Action a = new Action(s);
		finalactions = new ArrayList<Action>();
		candactions = new HashMap<String,Integer>();
		finalactions.add(a);		
		alphaBetaDecision(s,a.getCurrent().maxMove());
	}
	
	
	public int maxValue(String str, int a, int b){
		Action ac = new Action(str);
		if(ac.getCurrent().terminalTest()){
			return ac.getCurrent().getValue();
		}
		numNodes++;
		int value = -2;
		for(int i = 0; i < ac.getNext().size();i++){
			String s = ac.getNext().get(i);
			
			value = max(value,minValue(s,a,b));
			if(value >= b)
				return value;
			Action act = new Action(s);
			act.setName(ac.getPossibleActions().get(i));
			act.setCurrentValue(value);
			candactions.put(s,value);
			a = max(a,value);
		}
		return value;
	}
	
	
	public int minValue(String str, int a, int b){
		Action ac = new Action(str);
		if(ac.getCurrent().terminalTest()){
			return ac.getCurrent().getValue();
		}
		numNodes++;
		int value = 2;
		for(int i = 0; i < ac.getNext().size();i++){
			String s = ac.getNext().get(i);
			value = min(value,maxValue(s,a,b));
			if(value <= a)
				return value;
			Action act = new Action(s);
			act.setName(ac.getPossibleActions().get(i));
			act.setCurrentValue(value);
			candactions.put(s,value);		
			b = min(b,value);
		}
		return value;
	}
	
	
	private int max(int a, int b){
		return a>b?a:b;
	}
	private int min(int a, int b){
		return a<b?a:b;
	}
	
	public int alphaBetaDecision(String str, boolean isPlayer){
		Action ac = new Action(str);
		if(ac.getCurrent().terminalTest()){
			ac.getCurrent().displayStates();
			return numNodes;
		}
		int value;
		value = maxValue(str,-2,2);
		for(int i = 0 ; i < ac.getNext().size();i++){
			String s = ac.getNext().get(i);
			int v = candactions.get(s);
			if(v == value){
				Action act = new Action(s);
				act.setName(ac.getPossibleActions().get(i));
				act.setCurrentValue(value);
				finalactions.add(act);
				return numNodes;
			}
		}
		return numNodes;
	}
	
	public void displayOptimal(){
		finalactions.get(0).getCurrent().displayStates();
		if(finalactions.size() == 1){
			System.out.println("Already a finished board. No more moves to make.");
			return;
		}
		
		System.out.print("<");
		int i;
		for(i = 1; i < finalactions.size()-1;i++){
			System.out.print(finalactions.get(i).getName() + ",");
		}
		System.out.println(finalactions.get(i).getName()+ ">");
//		System.out.println("Number of nodes visited: " + numNodes);
	}
	
	public int getNodesExpanded(){
		return numNodes;
	}

	
//	public static void main(String args[]){
//		String s = "b X b b O b b b b";
//		int numabnodes,nummmnodes;
//		AlphaBeta ab = new AlphaBeta(s);
//		numabnodes = ab.getNodesExpanded();
//		MinMax mm = new MinMax(s);
//		nummmnodes = mm.minimaxDecision(s, true);
//		System.out.println("Alpha Beta Pruning:");
//		ab.displayOptimal();
//		System.out.println("Number of nodes expanded: " + numabnodes);
//		System.out.println("Minimax pruning:");
//		mm.displayActions();
//		System.out.println("Number of nodes expanded: " + nummmnodes);
//
//		
//	}
}
