//package alphaBeta;

public class TicTacToe {

	public static void main(String args[]){
		String s = "b X b b O b b b b";
		int numabnodes,nummmnodes;
		AlphaBeta ab = new AlphaBeta(s);
		numabnodes = ab.getNodesExpanded();
		MinMax mm = new MinMax(s);
		nummmnodes = mm.minimaxDecision(s, true);
		System.out.println("Alpha Beta Pruning:");
		ab.displayOptimal();
		System.out.println("Number of nodes expanded: " + numabnodes);
		System.out.println("Minimax pruning:");
		mm.displayActions();
		System.out.println("Number of nodes expanded: " + nummmnodes);
	}
}
