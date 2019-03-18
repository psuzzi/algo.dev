package jcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Main method to compute the solutions
 */
public class Main {
	
	static String inputs[] = { "a_example.txt", "b_lovely_landscapes.txt", "c_memorable_moments.txt", "d_pet_pictures.txt",	"e_shiny_selfies.txt" };
	static String outputs[] = { "a_example.txt.out", "b_lovely_landscapes.txt.out", "c_memorable_moments.txt.out", "d_pet_pictures.txt.out", "e_shiny_selfies.txt.out" };
	
//	static String inputs[] = { "d_pet_pictures.txt"};
//	static String outputs[] = { "d_pet_pictures.txt.out"};
	
	/**
	 * Execute the algorithm
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.printf("Executing solution%n");
		List<String> info = new ArrayList<>();
		int total = 0;
		Solver solver = new Solver();
		for(int i=0; i<inputs.length; i++) {
			Input in = new Input();
			in.type = inputs[i].charAt(0);
			// all the inputs are in "input/" 
			in.read("input/"+inputs[i]);
			Output out = solver.solve(in);
//			info.add(solver.info());
			// all the outputs in "output/"
			out.write("output/"+outputs[i]);
			total += out.score();
			System.out.printf(" - %-25s %n", inputs[i]);
		}
		System.out.printf(" === total score: %9s === %n", total);
		// analytics
		for(String infoLine : info ) {
			System.out.println(infoLine);
		}
		System.exit(0);
	}

}
