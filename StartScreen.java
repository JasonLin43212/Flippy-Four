import java.util.*;
public class StartScreen{
    
    public static void main(String[]args){
	boolean stop = false;
	Scanner scan = new Scanner(System.in);
	ArrayList<String> parameters = new ArrayList<String>();

	while (!stop){

	    while (parameters.size() == 0){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------\n"+
				   "Enter the HEIGHT of your board."+
				   "\n----------------------------\n"+
				   "**This should be an integer between 5 and 11, inclusive.**\n");
		String next = scan.next();
		int h = 0;
		try {
		    h = Integer.parseInt(next);

		} catch (NumberFormatException e) {
		}
		if(h >= 5 && h <= 11){
		    parameters.add(next);
		}
	    }
	    while (parameters.size() == 1){
		System.out.println("\n\n----------------------------\n"+
				   "Enter the WIDTH of your board."+
				   "\n----------------------------\n"+
				   "**This should be an integer between 5 and 11, inclusive.**\n");

		String next = scan.next();
		int w = 0;
		try {
		    w = Integer.parseInt(next);

		} catch (NumberFormatException e) {
		}
		if(w >= 5 && w <= 11){
		    parameters.add(next);
		}
	    }

	    while (parameters.size() == 2){
		System.out.println("\n\n----------------------------\n"+
				   "Player 1: Enter your color"+
				   "\n----------------------------\n");
		String next = scan.next();
		parameters.add(next);
	    }

	    while (parameters.size() == 3){
		System.out.println("\n\n----------------------------\n"+
				   "Player 2: Enter your color"+
				   "\n----------------------------\n");
		String next = scan.next();
		parameters.add(next);
	    }
	    while (parameters.size() == 4){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nYou are done setting up! \nNow for some instructions: \n\n" +
				   "[<-]: moves arrow left \n" +
				   "[->]: moves arrow right \n" +
				   "[space]: drops piece\n" +
				   "[q]: rotates board left \n" +
				   "[e]: rotates board right \n" +
				   "\n\n\n Enter \"OK\" to start!"

				   );
		String next = scan.next();
		if (next.toLowerCase().equals("ok")){
		    parameters.add(next);
		}
	    }

	    if (parameters.size() == 5){
		stop = true;
	    }

	}
    }
}
