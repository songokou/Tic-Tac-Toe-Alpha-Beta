//package alphaBeta;




import java.util.*;
/**
 * An action class to keep track of the possible actions and states
 * @author zeli, zli48, 676755673
 *
 */
public class Action {
	private ArrayList<Integer> possibleActions;
	private State current;
	private ArrayList<String> next;
	private ArrayList<Integer> values;
	private int currentValue;
	private int name;
	
	public Action(String s){
		possibleActions = new ArrayList<Integer>();
		values = new ArrayList<Integer>();
		current = new State(s);
		currentValue = -2;
		next = new ArrayList<String>();
		name = 0;
		setupActions();
		setupNext();
	}
	

	
	public void setName(int i){
		name = i;
	}
	public int getName(){
		return name;
	}
	public void setupActions(){
//		System.out.println("##########");
		for(int i = 0; i < 9; i++){

			if(current.getStates().get(i).equals("b")){
				possibleActions.add(i+1);
			}
			
		}
//		System.out.println("##########");
	}
	
	public void setCurrentValue(int i){
		currentValue = i;
	}
	
	public int getCurrentValue(){
		return currentValue;
	}
	public void setupNext(){
		for(int i = 0; i < possibleActions.size();i++){
			String s = "";
			int j = possibleActions.get(i);

			s = current.getStateString().substring(0,(j-1)*2);
			if(current.maxMove())
				s+="X";
			else
				s+="O";
			s+=current.getStateString().substring(2*(j-1)+1);
			State state = new State(s);
			values.add(state.getValue());
			if(currentValue < state.getValue()&&state.maxMove())
				currentValue = state.getValue();//when a potential move could result in winning
			next.add(s);
//			System.out.println(s);	
		}
	}
	
	public ArrayList<Integer> getPossibleActions() {
		return possibleActions;
	}

	
	public void setPossibleActions(ArrayList<Integer> possibleActions) {
		this.possibleActions = possibleActions;
	}

	public State getCurrent() {
		return current;
	}

	public ArrayList<String> getNext(){
		return next;
	}
	
	public ArrayList<Integer> getValues(){
		return values;
	}
	public void setCurrent(State current) {
		this.current = current;
	}
	public void setNext(ArrayList<String> n){
		next = n;
	}
	public void displayNext(){
		for(int i = 0; i < next.size();i++)
			System.out.println(next.get(i));
	}

	public void displayPossibleActions(){
		int size = possibleActions.size();
		if(size == 0||!current.maxMove())
			return;
		
		System.out.print("X: <");
		for( int i = 0; i<size-1;i++)
			System.out.print(possibleActions.get(i)+" ");
		System.out.println(possibleActions.get(size-1) + ">");
//		System.out.println(possibleActions);
	}
	
//	public static void main(String args[]){
//		Action a = new Action("b X b b b b b b b");
//		a.displayPossibleActions();
//		a.displayNext();
//	}

}
