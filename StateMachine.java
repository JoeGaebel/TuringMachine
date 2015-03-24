import java.util.ArrayList;
import java.util.ListIterator;


public class StateMachine {
	private ArrayList<State> states = new ArrayList<State>();
	private State currentState;
	private Tape tape;
	private boolean halted;

	public StateMachine(){
		currentState = null;
		tape = null;
		halted = false;
	}

	public void reset(){ //Reset tape and current state
		currentState = this.getStateById(0);
		tape = null;
		halted = false;
	}

	public void compute(){
		while(!halted && currentState != null){
			Transition t = currentState.getTransitionByInput(tape.read());
			//System.out.println("Current state: "+currentState.id);
			if (t == null){ //The machine goes into the dead state - there is no transition for this input
				currentState=null;
				//System.out.println("Dead state!");
			}
			else {
				
				currentState = this.getStateById(t.nextState);
				tape.write(t.writeSymbol, t.moveDirection);

				if (t.moveDirection == 'H') //The machine is to halt
					halted = true;
			}
			//tape.print();
		}

	}
	
	public String getString(ArrayList<Character> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}
	
	public void compareDecimals(ArrayList<Character> inputSet){
		String iString = getString(inputSet);
		String oString = getString(tape.prettyTape());
		try{
		int i = Integer.parseInt(iString, 2);
		int o = Integer.parseInt(oString, 2);
		System.out.println("In: "+i+" Out: "+o);
		}
		catch(NumberFormatException e){};
		
		
	}

	public void loadInput(ArrayList<Character> input){
		tape = new Tape(input);
		//tape.print();
	}

	public boolean isAccepting(){
		if (currentState == null)
			return false;
		else return currentState.isAccepting;

	}
	
	public void output(){
		tape.prettyPrint();
		if (this.isAccepting())
		System.out.println("ACCEPT");
		else
			System.out.println("REJECT");
		
		System.out.print('\n');
		
	}

	public boolean containsState(int id){
		ListIterator<State> i = states.listIterator();
		boolean found = false;
		while(i.hasNext()){
			if (i.next().id == id)
				found = true;
		}
		return found;
	}

	public void addState(int id){
		if (this.containsState(id)) return;
		else{
			State temp = new State(id);
			states.add(temp); 
			//If the id is zero, it's the start state
			if (id == 0) this.currentState = temp;

		}

	}

	public void print(){
		ListIterator<State> i = states.listIterator();
		System.out.println("\nMachine is loaded with "+states.size()+" states");
		while(i.hasNext()){
			State temp = i.next();
			temp.print();
		}
		System.out.println("------------------------------\n");
	}

	public State getStateById(int id){
		ListIterator<State> i = states.listIterator();
		while(i.hasNext()){
			State temp = i.next();
			if (temp.equals(id)) return temp;
		}
		return null;
	}


}