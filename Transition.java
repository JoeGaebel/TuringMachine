//currentState inputSymbol nextState writeToTape moveDirection
public class Transition {
public int nextState; //ID of next state
public char writeSymbol;
public char moveDirection;
public char inputSymbol;
	
	public Transition(int nextState, char writeSymbol, char moveDirection, char inputSymbol){
		this.nextState = nextState;
		this.writeSymbol = writeSymbol;
		this.moveDirection = moveDirection;
		this.inputSymbol = inputSymbol;
	}
	
	public String toString(){
		return "Go to "+nextState+" write "+writeSymbol+" then move "+moveDirection;
	}

}
