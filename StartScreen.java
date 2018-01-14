import java.util.*;
import java.awt.*;

public class StartScreen{
 
  boolean stop = false;
  Scanner scan = new Scanner(System.in);
  ArrayList<String> parameters = new ArrayList<String>();
    
  public StartScreen(){
    heightInput();
    widthInput();
    color1Input();
    color2Input();
    instructions();
	
  }

  public int getHeight(){
    return Integer.parseInt(parameters.get(0));
  }

  public int getWidth(){
    return Integer.parseInt(parameters.get(1));
  }

  public String getColor1(){
    return parameters.get(2);
  }

  public String getColor2(){
    return parameters.get(3);
  }

  public void heightInput() {
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
  }


  public void widthInput() {
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
  }

  public void color1Input(){

    while (parameters.size() == 2){
	    System.out.println("\n\n----------------------------\n"+
                         "Player 1: Enter your color"+
                         "\n----------------------------\n");
	    String next = scan.next();
	    parameters.add(next);

    }

  }
    
  public void color2Input(){
    while (parameters.size() == 3){
	    System.out.println("\n\n----------------------------\n"+
                         "Player 2: Enter your color"+
                         "\n----------------------------\n");
	    String next = scan.next();
	    parameters.add(next);
    }

  }

  public void instructions(){

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
  }

    
  public boolean startGame(){
    return(parameters.size() == 5);
  }
    
}
