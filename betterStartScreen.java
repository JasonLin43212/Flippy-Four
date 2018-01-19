import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class betterStartScreen extends JFrame implements ActionListener{

    private Container pane;
    
    //---------- Lists/ArrayLists for color ---------
    
    
    private ArrayList<Color> colors = new ArrayList<Color>(){{
	    add(Color.RED);
	    add(new Color(255,165,0));
	    add(Color.YELLOW);
	    add(new Color(0,128,0));
	    add(Color.BLUE);
	    add(Color.CYAN);
	    add(Color.MAGENTA);
	    add(new Color(138,43,226));
	    add(new Color(255,20,147));
	    add(new Color(139,69,19));
	}}; 
   
    private String[] JCBoxColors = new String[] {"red", "orange", "yellow", "green", "blue", "cyan", "magenta", "purple", "pink", "brown"};
    

    //---------- insert JComboBox content ---------
    
    
    private JComboBox<String> colors1 = new JComboBox<String>(JCBoxColors);
    private JComboBox<String> colors2 = new JComboBox<String>(JCBoxColors);
    
    private Integer[] dimensions = new Integer[] {5,6,7,8,9,10,11};
    private JComboBox<Integer> heightInput = new JComboBox<Integer>(dimensions);
    private JComboBox<Integer> widthInput = new JComboBox<Integer>(dimensions);


    private String[] rotationType = new String[] {"Player only", "Random", "Set interval"};
    private JComboBox<String> rotationInput = new JComboBox<String>(rotationType);

    
    //---------- JLabels for clarity---------
    

    private JLabel WELCOME = new JLabel("<html> <br/> Welcome to Flippy-Four! <br/> <br/> </html>");
    
    private JLabel COLOR1 = new JLabel("<html> Player 1: Choose your color  </html>");
    private JLabel COLOR2 = new JLabel("<html> Player 2: Choose your color </html>");
    private JLabel HEIGHT = new JLabel("<html> Set your board height </html>");
    private JLabel WIDTH = new JLabel("Set your board width");
    private JLabel ROTATION = new JLabel("Choose rotation type");
    private JLabel ERROR = new JLabel("Please choose two different colors.");
    private JLabel INSTRUCTIONS = new JLabel("<html>"+
					     "[<-]: Moves arrow left <br/>" +
					     "[->]: Moves arrow right <br/>" +
					     "[space]: Drops piece <br/>" +
					     "[q]: Rotates board left <br/>" +
					     "[e]: Rotates board right <br/>" +
					     "</html>" );
    
      

    //---------- button to start game ---------

    
    private JButton START;  
    

    //---------- instance variables ---------
    String color1;
    String color2;
    int height;
    int width;
    String rotation;

    

    
    public betterStartScreen(){
	   
	
	this.setTitle("Flippy Four");
	this.setSize(275,350);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

        
	pane.add(WELCOME);

        
	pane.add(COLOR1);
	
       
	//colors1.addActionListener(this);
	colors1.setSelectedIndex(0);
	pane.add(colors1);

	
	pane.add(COLOR2);

	
	//colors2.addActionListener(this);
	colors2.setSelectedIndex(2);
	pane.add(colors2);

	
	pane.add(HEIGHT);

	
	//heightInput.addActionListener(this);
	heightInput.setSelectedIndex(1);
	pane.add(heightInput);

        
	pane.add(WIDTH);
	
	
	//widthInput.addActionListener(this);
	widthInput.setSelectedIndex(2);
	pane.add(widthInput);

	
	pane.add(ROTATION);
	

	pane.add(rotationInput);
	

      
	pane.add(INSTRUCTIONS);
        INSTRUCTIONS.setVisible(true);
	
	START = new JButton("START");
	START.addActionListener(this);
	pane.add(START);

	

        
	pane.add(ERROR);
	ERROR.setVisible(false);

	color1 = "red";
	color2 = "yellow";

	height = 6;
	width = 7;
    
	

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

	Object r = rotationInput.getSelectedItem();
	rotation = r.toString();

	if (color1.equals(color2)){
	    ERROR.setVisible(true);
	}

	else {
	    FlippyFour f = new FlippyFour(boardWidth(), boardHeight(),colors.get(P1_color()), colors.get(P2_color()));
	    f.setVisible(true);
	    ERROR.setVisible(false);
	}
	
    }
    
    public int P1_color(){return Arrays.asList(JCBoxColors).indexOf(color1);}
    public int P2_color(){return Arrays.asList(JCBoxColors).indexOf(color2);}
    public int boardHeight(){return height;}
    public int boardWidth(){return width;}
    public String rotationChoice(){return rotation;}
  
	
 
}

