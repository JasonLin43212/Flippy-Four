import java.util.*;
import java.awt.*;

public class StartScreen{

  //------------Instance Variables--------------
  public ArrayList<Integer> parameters;
  public ArrayList<String> colorNames;

  //--------------- Other Variables-------------
  String dimensionError = " should be an integer between 5 and 11**\n";
  String firstColor = "";
  boolean stop = false;
  Scanner scan = new Scanner(System.in);

  //---------------Methods-------------
  public StartScreen(){
    parameters = new ArrayList<Integer>();
    colorNames = new ArrayList<String>();
    
    colorNames.add("red");
    colorNames.add("orange");
    colorNames.add("yellow");
    colorNames.add("green");
    colorNames.add("blue");
    colorNames.add("cyan");
    colorNames.add("magenta");
    colorNames.add("purple");
    colorNames.add("pink");
    colorNames.add("brown");
  
    
    heightInput();
    widthInput();
    color1Input();
    color2Input();
    instructions();
	
  }

  public int getHeight(){
    return parameters.get(0);
  }

  public int getWidth(){
    return parameters.get(1);
  }

  public int getColor1(){
    return parameters.get(2);
  }

  public int getColor2(){
    return parameters.get(3);
  }

  public void heightInput() {
    int h = 0;
    while (parameters.size() == 0){
       System.out.println(
                       "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                       "----------------------------\n"+
                       "Enter the HEIGHT of your board."+
                       "\n----------------------------\n"+
                       "**This should be an integer between 5 and 11,"+
                       " inclusive.**\n");
    
	    String next = scan.next();
	    try {
        h = Integer.parseInt(next);

	    } catch (NumberFormatException e) {}
	    if(h >= 5 && h <= 11){
        parameters.add(h);
	    }
    }
  }


  public void widthInput() {
    int w =0;
    while (parameters.size() == 1){
      System.out.println("\n\n----------------------------\n"+
                         "Enter the WIDTH of your board."+
                         "\n----------------------------\n"+
                         "**This should be an integer between 5 and 11,"+
                         " inclusive.**\n");

      String next = scan.next();
      try {
		    w = Integer.parseInt(next);

      } catch (NumberFormatException e) {
      }
      if(w >= 5 && w <= 11){
		    parameters.add(w);
      }
    }
  }

  public void color1Input(){

    while (parameters.size() == 2){
	    System.out.println("\n\n----------------------------\n"+
                         "Player 1: Enter your color\n"+
                         "Choose one of the following colors:\n");
      for (int i=0; i<colorNames.size(); i++) {
        String name = colorNames.get(i);
        System.out.print(name.substring(0,1).toUpperCase() +
                         name.substring(1) + "  ");
      }
      System.out.println("\n----------------------------\n");
	    String next = scan.next();
      if (colorNames.contains(next.toLowerCase())){
        parameters.add(colorNames.indexOf(next));
        firstColor = next;
      }
    }

  }
    
  public void color2Input(){
    while (parameters.size() == 3){
	    System.out.println("\n\n----------------------------\n"+
                         "Player 2: Enter your color"+
                         "\n----------------------------\n");
      for (int i=0; i<colorNames.size(); i++) {
        String name = colorNames.get(i);
        if (!name.equals(firstColor)) {
          System.out.print(name.substring(0,1).toUpperCase() +
                           name.substring(1) + "  ");
        }
      }
      System.out.println("\n----------------------------\n");
	    String next = scan.next();
      if (colorNames.contains(next.toLowerCase()) &&
          !next.equals(firstColor)) {
        parameters.add(colorNames.indexOf(next));
      }
    }

  }

  public void instructions(){

    while (parameters.size() == 4){
      System.out.println("\n\n\n\n"+
                         "You are done setting up! \nNow for some instructions:"+
                         " \n\n" +
                         "[<-]: Moves arrow left \n" +
                         "[->]: Moves arrow right \n" +
                         "[space]: Drops piece\n" +
                         "[q]: Rotates board left \n" +
                         "[e]: Rotates board right \n" +
                         "\n\n\nEnter \"OK\" to start!"

                         );
      String next = scan.next();
      if (next.toLowerCase().equals("ok")){
		    parameters.add(-1);

      }
    }
  }

  public boolean startGame(){
    return(parameters.size() == 5);
  }
}
