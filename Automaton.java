/** CS1B_Assignment_3
 * Creeger, Adam/ Kishore, Nand/ Lo, YaFan
 */

public class Automaton {

	// class constants
	public final static int MAX_DISPLAY_WIDTH = 121;

	// private members
	private boolean rules[]; // allocate rules[8] in constructor!
	private String thisGen; // same here
	String extremeBit; // bit, "*" or " ", implied everywhere "outside"
	int displayWidth; // an odd number so it can be perfectly centered

	// public constructors, mutators, etc. (need to be written)

	/*
	 * Through the mutator we'll sanitize rule and then convert it to our internal
	 * representation. We also need to establish the seed: a single 1 in a sea of
	 * 0s, our first generation.
	 */
	public Automaton(int new_rule) {
		
		this.resetFirstGen();
		boolean[] array = new boolean[8];
		boolean isInRange = this.setRule(new_rule);
		
		for (int i = 0; i < 8; i++) {
			array[i] = rules[i];
			System.out.println(array[i]);
		}
		if (!isInRange)
			System.out.println("the rule typed in is invalid.");

	}

	public void resetFirstGen() {
		thisGen = new String("*");
		extremeBit = new String(" ");
	}

	/*
	 * mutator: converts the int newRule, a number from 0-255, to an array of eight
	 * booleans. this must sanitize the int so that only values in the legal range
	 * 0-255 are allowed
	 */
	public boolean setRule(int new_rule) {
		rules = new boolean[8];
		int bitToExamine, index = 0;
		bitToExamine = 1 << index;
		if (0 <= new_rule && new_rule <= 255) {

			for (index = 0; index < rules.length; index++) {
				bitToExamine = 1 << index;
				if ((new_rule & bitToExamine) != 0)
					rules[index] = true;
				else
					rules[index] = false;

			}

			return true;
		} else
			return false;
		
	}

	/*
	 * mutator: described by the description of displayWidth and MAX_DISPLAY_WIDTH.
	 * only odd widths are allowed (for centering purposes)
	 */
	public boolean setDisplayWidth(int width) {
		
		if (width<0 || width > MAX_DISPLAY_WIDTH || (width % 2) == 0)
		{
			System.out.println("MAX DISPLAY WIDTH = 121, only odd widths are allowed");
		
			return false;
		}
		else
		{
			displayWidth = width;
			return true;
		}
	}

	/*
	 * This returns a string consisting of the current generation, thisGen, but does
	 * so by embedding it in a return String whose length is exactly displayWidth
	 * long.
	 */
	public String toStringCurrentGen()
	   {
		  String retString="";
		  int difference = thisGen.length()-displayWidth; // The difference between string length and displayWidth
		   
		  /* if thisGen is smaller than displayWidth, it is positioned in the 
		    * center of the larger return string, with the excess padded by extremeBit characters*/
 
			   if (difference<0)
			   {   
				   difference = -difference; 
						   
					   for (int i=0; i< (difference/2); i++)
						   
					   {
						   thisGen= thisGen+extremeBit;
					   
						   thisGen= extremeBit+thisGen;
					   }   
					   retString = thisGen;
				 }	
				   
			  
		  /* If thisGen is longer than displayWidth, it has to be truncated (on both ends) 
		   * so that it is perfectly centered in the returned string, any excess trimmed off 
		   * both ends, equally*/
				else if (difference>0)
				{
					retString = thisGen.substring(difference/2, thisGen.length()-difference/2);
					
				   //return thisGen;
				}
			
				return retString;  	
	   }

	/*
	 * use the three private members thisGen, extremeBit and rule[], and create the
	 * next generation from them. first append two extremeBits to each side of
	 * thisGen in order to provide it with enough bits (or chars) on each end needed
	 * by rule (adds four chars to thisGen, temporarily). then apply rule in a loop
	 * to the new, larger, thisGen, which creates a separate (local) nextGen String
	 * Then, replace the old thisGen with new nextGen (copy that back to thisGen to
	 * complete the cycle) finally, apply rule to three consecutive extremeBits to
	 * figure out what the new extremeBit will be for the next generation (" " or
	 * "*"?)
	 */
	public void propagateNewGeneration() {
		String nextGen = "";

		
		thisGen =  extremeBit+extremeBit+thisGen+extremeBit+extremeBit;
		
		// apply the rule
		for (int i = 1; i <=(thisGen.length() - 2); i++) {

			// calculate the value of one of the inputs
			int totalVal = 0;
			for (int iOfInput = 2; iOfInput >= 0; iOfInput--) {
				int val;
				if (thisGen.substring(i-1, i + 2).charAt(iOfInput) == '*') {
					val = 4 >> iOfInput;
					totalVal = totalVal + val;
				}
			}

			if (rules[totalVal])
				nextGen = nextGen + "*";
			else
				nextGen = nextGen + " ";

		}
		
		//thisGen = new String(nextGen);
		thisGen =nextGen;
		
		/*
		 * calculate what the new extremeBit will be for the next generation (" " or
		 * "*"?) apply rule to an int representing a 3-bit pattern inside the old
		 * generation. In this case, all the3 bits are the same: extremeBit. So each
		 * input will be three 0s or three 1s -- an int 0 or an int 7 -- depending on
		 * the value of extremeBit.*\
		 */
//		if (extremeBit == "*" && rules[7]) {
//			extremeBit = "*";
//		}
//		if (extremeBit == " " && rules[0]) {
//			extremeBit = "*";
//		}
		
		if (extremeBit == "*" && rules[7] ) {
			
				extremeBit = "*";
		}
		
		if (extremeBit == " " && rules[0] ) {
			extremeBit = "*";
		}
//		if (extremeBit == " " && rules[7] ) {
//			extremeBit = "*";
//		}
//		if (extremeBit == " " && rules[7] ) {
//			
//			extremeBit = "*";
	

	}
}
