import java.util.*;
public class StartScreen{
 
    boolean stop = false;
    Scanner scan = new Scanner(System.in);
    ArrayList<String> parameters = new ArrayList<String>();
    

    public StartScreen(){
	parameters.add( heightInput());
	parameters.add( widthInput());
	parameters.add( color1Input());
	parameters.add( color2Input());
	

    }

    public String heightInput() {
	int h = 0;
	while (parameters.size() == 0){
	    System.out.println(
			       "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------\n"+
			       "Enter the HEIGHT of your board."+
			       "\n----------------------------\n"+
			       "**This should be an integer between 5 and 11, inclusive.**\n");
	    String next = scan.next();
	    try {
		h = Integer.parseInt(next);

	    } catch (NumberFormatException e) {
	    }
	    if(h >= 5 && h <= 11){
		parameters.add(next);
	    }
	}
	return "" + h;
    }


    public String  widthInput() {
	int w =0;
	    while (parameters.size() == 1){
		System.out.println("\n\n----------------------------\n"+
				   "Enter the WIDTH of your board."+
				   "\n----------------------------\n"+
				   "**This should be an integer between 5 and 11, inclusive.**\n");

		String next = scan.next();
		try {
		    w = Integer.parseInt(next);

		} catch (NumberFormatException e) {
		}
		if(w >= 5 && w <= 11){
		    parameters.add(next);
		}
	    }
	return "" + w;
    }

    public String color1Input(){
	String c = "";
	while (parameters.size() == 2){
	    System.out.println("\n\n----------------------------\n"+
			       "Player 1: Enter your color"+
			       "\n----------------------------\n");
	    String next = scan.next();
	    parameters.add(next);
	    c = next;
	}
	return c;
    }
    
    public String color2Input(){
	String c = "";
	while (parameters.size() == 3){
	    System.out.println("\n\n----------------------------\n"+
			       "Player 2: Enter your color"+
			       "\n----------------------------\n");
	    String next = scan.next();
	    parameters.add(next);
	    c = next;
	}
	return c;
    }

    public String instructions(){
	String s = "";
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
		    s = next;
		}
	    }
	return s;
    }

    

    
}
