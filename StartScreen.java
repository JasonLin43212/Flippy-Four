import java.util.*;
public class StartScreen{
        private static final int BRIGHT = 1;
    private static final int DARK = 2;
    private static final int ITALICS = 3;
    private static final int BLACK = 30;
    private static final int RED = 31;
    private static final int GREEN = 32;
    private static final int YELLOW = 33;
    private static final int BLUE = 34;
    private static final int MAGENTA = 35;
    private static final int CYAN = 36;
    private static final int WHITE = 37;
    private static final String CLEAR_SCREEN =  "\033[2J";
    private static final String HIDE_CURSOR =  "\033[?25l";
    private static final String SHOW_CURSOR =  "\033[?25h";

    private static final String RESET = color(40,37)+SHOW_CURSOR;

    private static String color(int a, int b){
        return ("\033[0;" + a+ ";" + b + "m");
    }

    private static String color(int a, int b,int c){
        return ("\033[0;" + a+ ";" + b + ";" + c+ "m");
    }
    private static String color(int a, int b,int c, int d){
        return ("\033[0;" + a+ ";" + b + ";" + c + ";" + d + "m");
    }

     public static int background(int color){
	return color + 10;
    }

    //terminal specific character to move the cursor to a location
    //top left is 1,1
    private static String go(int x,int y){
        return ("\033[" + x + ";" + y + "H");
    }
 


    
    public static void main(String[]args){
	boolean stop = false;
	Scanner scan = new Scanner(System.in);
	ArrayList<String> parameters = new ArrayList<String>();

	while (!stop){

	    while (parameters.size() == 0){
		//System.out.println(go(i+1,j+19)+color(30+i,40+j,DARK,ITALICS) + "#");
		System.out.println(go(1,1) + color(BLACK, background(WHITE), BRIGHT, ITALICS) +
				   "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------\n"+
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
		System.out.println(RESET);
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
