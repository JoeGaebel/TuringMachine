import java.util.ArrayList;
import java.util.ListIterator;


public class Tape {
	private ArrayList<Character> tape;
	private int head; //Keep track of position


	public String toString(){
		return tape.toString();
	}
	
	public void print(){
		System.out.println(tape);
		System.out.print(' ');
		for (int i= 0; i < head; i++)
		System.out.print("   ");
		System.out.print("^\n");
		
	}
	
	@SuppressWarnings("unchecked")
	public void prettyPrint(){
		ArrayList<Character> temp = (ArrayList<Character>) tape.clone();
		ListIterator<Character> i = temp.listIterator();
		while(i.hasNext())
			if (i.next() == 'Z')
				i.remove();
		System.out.println(temp);
		
	}
	
	public ArrayList<Character> prettyTape(){
			@SuppressWarnings("unchecked")
			ArrayList<Character> temp = (ArrayList<Character>) tape.clone();
			ListIterator<Character> i = temp.listIterator();
			while(i.hasNext())
				if (i.next() == 'Z')
					i.remove();
			return temp;
			
		
	}
	
	public Tape(ArrayList<Character> input){
		head = 0;
		tape = input;
	}
	
	public char read(){
		return tape.get(head);
	}
	
	


	public void write(char Symbol, char Direction){
		if (Symbol != 'Z')
		tape.set(head, Symbol);
		if (Direction == 'R'){
			shiftRight();
		}
		if (Direction == 'L')
			shiftLeft();

	}
	
	//Shift right and shift left make sure the tape appears 'infinite'
	//As long as the writes and head movements are step wise, there will always be an empty element to write or read to

	public void shiftRight(){
		head++; //Increment head
		if (head == tape.size()){
		tape.add('Z'); //add an underscore to end
		}
	}

	public void shiftLeft(){
		//No need to decrement head, taken care of by shifting
		
		if (head == 0){ //Getting close to left end, append _ to beginning
			ArrayList<Character> temp = new ArrayList<Character>();
			temp.add('Z');
			for (int i=0; i<tape.size(); i++)
				temp.add(tape.get(i));
			tape = temp;
		}
		else
			head--;
	}
}
