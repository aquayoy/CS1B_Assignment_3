import java.util.Scanner;

public class Foothill {

	public static void main(String[] args) {

		
	 
		
		// at least four runs which include the  rules, 4, 126, 130, and one or more of your choice. 100 generations
		// rule 4
		Automaton automata; 
		int choice;
		Scanner keyboard = new Scanner(System.in);
		do 
		{
			System.out.println("Please enter a rule(0 - 255): ");

					choice = keyboard.nextInt();
					keyboard.nextLine();
				
		
		}while( choice<0 || choice >255);
		
			automata = new Automaton(choice); 
		
			automata.setDisplayWidth(79);
		
			System.out.println("rule "+ choice+"\n    start");
		
			for (int i=0; i<100; i++)
			{
			
				System.out.println(automata.toStringCurrentGen());
				automata.propagateNewGeneration();
			
			}
				System.out.println("    end");
		
		
		
	}
}
