import java.util.ArrayList;
import java.util.ListIterator;

public class State {
	public int id;
	public ArrayList<Transition> transitions = new ArrayList<Transition>();
	public boolean isAccepting;

	public State(int id){
		this.id = id;
	}
	public boolean equals(int id) {
		if (id == this.id)
			return true;
		else return false;
	}
	
	public void setAccepting(){
		this.isAccepting = true;
		return;
	}
	
	
	public void print(){
		System.out.print("\nState "+this.id);
		if (this.isAccepting) System.out.print("\n*ACCEPTING\n");
		else System.out.print("\n");
		ListIterator<Transition> i = transitions.listIterator();
		while(i.hasNext()){
			Transition temp = i.next();
			System.out.println("-> On <"+temp.inputSymbol+"> go to state "+temp.nextState+" write "+temp.writeSymbol+" and move "+temp.moveDirection);
		}
		
	}
	
	
	public Transition getTransitionByInput(char inputSymbol){
		ListIterator<Transition> i = transitions.listIterator();
		while(i.hasNext()){
			Transition temp = i.next();
			if (temp.inputSymbol == inputSymbol)
				return temp;
		}
		return null;
		
	}
	
	public void addTransition(int nextState, char writeSymbol, char moveDirection, char inputSymbol){
		transitions.add(new Transition(nextState, writeSymbol, moveDirection, inputSymbol));
	}
	

}
