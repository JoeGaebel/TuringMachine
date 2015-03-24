import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.in;

import java.io.*;
public class TuringMachine {
	static StateMachine machine;
	static ArrayList<String> file;

	public static boolean getFile(Scanner keyboard){
		file = new ArrayList<String>();
		out.println("Input a filename");
		String filename = keyboard.next();
		File filehandle = new File(filename);
		Scanner fileScanner = null;
		try{
			fileScanner = new Scanner(filehandle);
		}
		catch(FileNotFoundException e){
			out.println("File not found!");
			return false;
		}
		while(fileScanner.hasNext())
			file.add(fileScanner.nextLine());
		fileScanner.close();
		return true;

	}
	public static void createMachine(ArrayList<String> file){
		//Put the file reader back at the start
		for (int i=0; i<file.size(); i++){ //Loop through the entirety of the file
			String arr[];
			arr = file.get(i).split(" "); //Split the string into it's parts
			if (arr[0].charAt(0) =='t'){ //Only do stuff to the transition lines
				//t 0 a 1 x R
				//t: currentState inputSymbol nextState writeToTape moveDirection
				int id = Integer.parseInt(arr[1]);
				int nextState = Integer.parseInt(arr[3]);
				char writeSymbol = arr[4].charAt(0);
				char moveDirection = arr[5].charAt(0);
				char inputSymbol = arr[2].charAt(0);

				if (!machine.containsState(id)) //Create the state, if it doesn't exist already
					machine.addState(id);
				State state = machine.getStateById(id); //get the state reference out
				state.addTransition(nextState, writeSymbol, moveDirection, inputSymbol); //Add a transition to that state
			}

			if(arr[0].charAt(0) == 'f'){ //Set accepting states
				for (int j=1; j<arr.length; j++){
					int id = Integer.parseInt(arr[j]);
					//Sometimes states are defined in the accepting states.. RYAN....
					if (!machine.containsState(id))
						machine.addState(id);
					machine.getStateById(id).setAccepting();
				}

			}
		}
	}


	public static ArrayList<ArrayList<Character>> getInputSets(ArrayList<String> file){
		ArrayList<ArrayList<Character>> container = new ArrayList<ArrayList<Character>>();
		for (int i=0; i<file.size(); i++){ //Loop through the entirety of the file
			String line = file.get(i);
			if (line.charAt(0) == 'i'){
				ArrayList<Character> list = new ArrayList<Character>();
				if (line.equals("i:") || line.equals("i: ")){
					list.add('\0');
				}
				else{
				String input = line.split(" ")[1];
				for (int j =0; j <input.length(); j++)
					list.add(input.charAt(j));
				//System.out.println(list);
				container.add(list);
				}
			}	
		}
		return container;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(in);
		while(!getFile(keyboard));
		keyboard.close();
		machine = new StateMachine();
		createMachine(file);
		//machine.print();
		ArrayList<ArrayList<Character>> inputSet;
		inputSet = getInputSets(file);
		machine.print();
		for (ArrayList<Character> input : inputSet)
		{
			machine.reset();
			machine.loadInput((ArrayList<Character>) input.clone());
			machine.compute();
			machine.compareDecimals(input);
			machine.output();
		}
		

		//t current state input symbol next state write symbol head movement direction


	}





}
