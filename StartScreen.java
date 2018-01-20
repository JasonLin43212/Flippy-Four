import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StartScreen extends JFrame implements ActionListener{

  private Container pane;
  private StartScreenPanel panel;
    
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


  private String[] rotationType = new String[] {"Player Only", "Random Rotation", "Set Interval"};
  private JComboBox<String> rotationInput = new JComboBox<String>(rotationType);

  private JCheckBox playerRotateInput = new JCheckBox("Allow Player To Rotate Board");
  //---------- JLabels for clarity---------
    

  private JLabel WELCOME = new JLabel("Welcome to Flippy-Four!");
  private JLabel COLOR1 = new JLabel("Player 1: Choose your color");
  private JLabel COLOR2 = new JLabel("Player 2: Choose your color");
  private JLabel HEIGHT = new JLabel("<html> Set your board height </html>");
  private JLabel WIDTH = new JLabel("Set your board width");
  private JLabel ROTATION = new JLabel("Choose rotation type");
  private JLabel ERROR = new JLabel("Please choose two different colors.");
  
  //---------- button to start game ---------

  private JButton START;

  //---------- instance variables ---------
  private String color1 = "red";
  private String color2 = "yellow";
  private int height = 6;
  private int width = 7;
  private String rotation;

  //---------- set up window & add labels/menus/buttons ---------
  public StartScreen(){
	   	
    this.setTitle("Flippy Four");
    this.setSize(400,600);
    this.setLocation(100,100);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
    
    pane = this.getContentPane();
    panel = new StartScreenPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    pane.add(panel);

    WELCOME.setPreferredSize(new Dimension(100,100));
    panel.add(WELCOME);
	
    panel.add(COLOR1);
    colors1.setSelectedIndex(0);
    colors1.setMaximumSize(new Dimension(50,9));
    colors1.setAlignmentX(Component.LEFT_ALIGNMENT);
    panel.add(colors1);

	
    panel.add(COLOR2); 
    colors2.setSelectedIndex(2);
    panel.add(colors2);
 
	
    panel.add(HEIGHT);
    heightInput.setSelectedIndex(1);
    panel.add(heightInput);

         
    panel.add(WIDTH);
    widthInput.setSelectedIndex(2);
    panel.add(widthInput);

    panel.add(ROTATION);
    panel.add(rotationInput);

    panel.add(playerRotateInput);
    
    START = new JButton("START");
    START.addActionListener(this);
    panel.add(START);

	       
    panel.add(ERROR);
    ERROR.setVisible(false);
	
    
    this.setVisible(true);  
    panel.repaint(); 
  }


  //---------- start game when given valid parameters ---------
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
	    FlippyFour f = new FlippyFour(width,
                                    height,
                                    colors.get(Arrays.asList(JCBoxColors).
                                               indexOf(color1)),
                                    colors.get(Arrays.asList(JCBoxColors).
                                               indexOf(color2)),
                                    rotation,
                                    playerRotateInput.isSelected());
	    f.setVisible(true);
	    ERROR.setVisible(false);
    }
	
  }

}
