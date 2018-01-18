import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class betterStartScreen extends JFrame implements ActionListener{
    private ArrayList<Integer> parameters;
    private ArrayList<String> colorNames;
    private ArrayList<Color> colors = new ArrayList<Color>();
    
    private Container pane;
    private JButton START;

    private JComboBox<String> colors1;
    private JComboBox<String> colors2;
    private String[] colorsList = new String[] {"red", "orange", "yellow", "green", "blue", "cyan", "magenta", "purple", "pink", "brown"};
    
    private JComboBox<Integer> heightInput;
    private JComboBox<Integer> widthInput;
    private Integer[] dimensions = new Integer[] {5,6,7,8,9,10};

    private JLabel WELCOME;
    private JLabel error;
    private JLabel COLOR1;
    private JLabel COLOR2;
    private JLabel HEIGHT;
    private JLabel WIDTH;
    private JLabel INSTRUCTIONS;
    

    String color1;
    String color2;
    int height;
    int width;

    private boolean StartGame = false;

    
    public betterStartScreen(){
	this.setTitle("Flippy Four");
	this.setSize(265,300);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

        WELCOME = new JLabel("<html> Welcome to Flippy-Four! <br/></html>");
	pane.add(WELCOME);
	WELCOME.setVisible(true);

        COLOR1 = new JLabel("Player 1: Choose your color");
	pane.add(COLOR1);
	COLOR1.setVisible(true);
	
	colors1 = new JComboBox<String>(colorsList);
	//colors1.addActionListener(this);
	colors1.setSelectedIndex(0);
	pane.add(colors1);

	COLOR2 = new JLabel("Player 2: Choose your color");
	pane.add(COLOR2);
	COLOR2.setVisible(true);

	colors2 = new JComboBox<String>(colorsList);
	//colors2.addActionListener(this);
	colors2.setSelectedIndex(2);
	pane.add(colors2);

	HEIGHT = new JLabel("Set your board height");
	pane.add(HEIGHT);
	HEIGHT.setVisible(true);

	heightInput = new JComboBox<Integer>(dimensions);
	//heightInput.addActionListener(this);
	heightInput.setSelectedIndex(1);
	pane.add(heightInput);

        WIDTH = new JLabel("Set your board width");
	pane.add(WIDTH);
	WIDTH.setVisible(true);
	
	widthInput = new JComboBox<Integer>(dimensions);
	//widthInput.addActionListener(this);
	widthInput.setSelectedIndex(2);
	pane.add(widthInput);

        INSTRUCTIONS = new JLabel("<html>"+
				  "[<-]: Moves arrow left <br/>" +
				  "[->]: Moves arrow right <br/>" +
				  "[space]: Drops piece <br/>" +
				  "[q]: Rotates board left <br/>" +
				  "[e]: Rotates board right <br/>" +
				  "</html>" );
	pane.add(INSTRUCTIONS);
        INSTRUCTIONS.setVisible(true);
	
	START = new JButton("START");
	START.addActionListener(this);
	pane.add(START);

        error = new JLabel("Please choose two different colors.");
	pane.add(error);
	error.setVisible(false);

	color1 = "red";
	color2 = "yellow";

	height = 6;
	width = 7;

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

	colors.add(Color.RED);
	colors.add(new Color(255,165,0));
	colors.add(Color.YELLOW);
	colors.add(new Color(0,128,0));
	colors.add(Color.BLUE);
	colors.add(Color.CYAN);
	colors.add(Color.MAGENTA);
	colors.add(new Color(138,43,226));
	colors.add(new Color(255,20,147));
	colors.add(new Color(139,69,19));

	this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
	Object c1 = colors1.getSelectedItem();
	color1 = c1.toString();

	Object c2 = colors2.getSelectedItem();
	color2 = c2.toString();

	Object h = heightInput.getSelectedItem();
	height = (int)h;

	Object w = widthInput.getSelectedItem();
	width = (int)w;	

	if (color1.equals(color2)){
	    error.setVisible(true);
	}

	else {
	    FlippyFour f = new FlippyFour(boardWidth(), boardHeight(),colors.get(P1_color()), colors.get(P2_color()));
	    f.setVisible(true);
	}
	
    }

    public boolean ready(){return StartGame;}
    
    public int P1_color(){return colorNames.indexOf(color1);}
    public int P2_color(){return colorNames.indexOf(color2);}

    public int boardHeight(){return height;}

    public int boardWidth(){return width;}
  
	
 
}

