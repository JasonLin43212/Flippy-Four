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
   
  private String[] JCBoxColors = new String[] {"Red", "Orange", "Yellow", "Green", "Blue", "Cyan", "Magenta", "Purple", "Pink", "Brown"};
    

  //---------- insert JComboBox content ---------
    
  private JComboBox<String> colors1 = new JComboBox<String>(JCBoxColors);
  private JComboBox<String> colors2 = new JComboBox<String>(JCBoxColors);
  private Integer[] dimensions = new Integer[] {5,6,7,8,9,10,11};
  private JComboBox<Integer> heightInput = new JComboBox<Integer>(dimensions);
  private JComboBox<Integer> widthInput = new JComboBox<Integer>(dimensions);


  private String[] rotationType = new String[] {"Player Only", "Random Rotation", "Set Interval"};
  private JComboBox<String> rotationInput = new JComboBox<String>(rotationType);

  private JCheckBox playerRotateInput = new JCheckBox("Allow Player To Rotate Board");
  private JCheckBox singleplayerInput = new JCheckBox("Allow SinglePlayer");

  private Integer[] numToWin = new Integer[] {4,5};
  private JComboBox<Integer> winningInput = new JComboBox<Integer>(numToWin);
  //---------- JLabels for clarity---------

  private JLabel WELCOME = new JLabel("Welcome to Flippy-Four!");
  private JLabel COLOR1 = new JLabel("Player 1: Choose your color");
  private JLabel COLOR2 = new JLabel("Player 2: Choose your color");
  private JLabel HEIGHT = new JLabel("Set your board height");
  private JLabel WIDTH = new JLabel("Set your board width");
  private JLabel ROTATION = new JLabel("Choose rotation type");
  private JLabel WINNUM = new JLabel("Choose the winning number");
  private JLabel ERROR = new JLabel("Please choose two different colors.");
  private JLabel WARNING = new JLabel("Make sure to choose different colors");
  
  //---------- button to start game ---------

  private JButton START = new JButton("Start");

  //---------- instance variables ---------
  
  private String color1 = "red";
  private String color2 = "yellow";
  private int height = 6;
  private int width = 7;
  private String rotation;
  private int numberToWin = 4;

  //---------- set up window & add labels/menus/buttons ---------
  public StartScreen(){
    
    this.setTitle("Flippy Four");
    this.setSize(800,690);
    this.setLocation(100,100);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
    pane = this.getContentPane();
    panel = new StartScreenPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
    pane.add(panel);

    WELCOME.setPreferredSize(new Dimension(200,70));
    WELCOME.setAlignmentX(Component.CENTER_ALIGNMENT);
    WELCOME.setFont(new Font("Monospaced",Font.BOLD,50));
    panel.add(Box.createRigidArea(new Dimension(10,10)));
    panel.add(WELCOME);
    panel.add(Box.createRigidArea(new Dimension(10,10)));

    configureLabel(COLOR1);
    configureLabel(COLOR2);
    configureLabel(HEIGHT);
    configureLabel(WIDTH);
    configureLabel(ROTATION);
    configureLabel(WINNUM);
    configureLabel(ERROR);
    configureLabel(WARNING);

    configureBox(colors1);
    configureBox(colors2);
    configureBox(heightInput);
    configureBox(widthInput);
    configureBox(rotationInput);
    configureBox(winningInput);

    colors1.setSelectedIndex(0);
    colors2.setSelectedIndex(2);
    heightInput.setSelectedIndex(1);
    widthInput.setSelectedIndex(2);

    START.addActionListener(this);

    //For configuring the checkbox
    playerRotateInput.setPreferredSize(new Dimension(100,50));
    playerRotateInput.setAlignmentX(Component.CENTER_ALIGNMENT);
    playerRotateInput.setSelected(true);
    playerRotateInput.setBackground(new Color(135,206,250));

    singleplayerInput.setPreferredSize(new Dimension(100,50));
    singleplayerInput.setAlignmentX(Component.CENTER_ALIGNMENT);
    singleplayerInput.setBackground(new Color(135,206,250));
    //For configuring the Start button
    START.setAlignmentX(Component.CENTER_ALIGNMENT);
    //Change color of ERROR
    ERROR.setForeground(Color.RED);

    addToPanel(COLOR1);
    addToPanel(colors1);
    addToPanel(ERROR);
    addToPanel(COLOR2);
    addToPanel(colors2);
    addToPanel(HEIGHT);
    addToPanel(heightInput);
    addToPanel(WIDTH);
    addToPanel(widthInput);
    addToPanel(ROTATION);
    addToPanel(rotationInput);
    addToPanel(WINNUM);
    addToPanel(winningInput);
    addToPanel(playerRotateInput);
    addToPanel(singleplayerInput);
    addToPanel(START);
    addToPanel(ERROR);
    addToPanel(WARNING);

    ERROR.setVisible(false);
    this.setVisible(true);

    panel.repaint();
  }
  
  private void configureLabel(JLabel j){
    j.setPreferredSize(new Dimension(100,30));
    j.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  private void configureBox(JComboBox c){
    c.setMaximumSize(new Dimension(150,25));
    c.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.setMaximumRowCount(10);
  }

  private void addToPanel(JComponent c){
     panel.add(Box.createRigidArea(new Dimension(10,6)));
     panel.add(c);
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

    Object n = winningInput.getSelectedItem();
    numberToWin = (int)n;

    if (color1.equals(color2)){
	    ERROR.setVisible(true);
      WARNING.setVisible(false);
    }

    else {
      this.dispose();
	    FlippyFour f = new FlippyFour(width,
                                    height,
                                    colors.get(Arrays.asList(JCBoxColors).indexOf(color1)),
                                    colors.get(Arrays.asList(JCBoxColors).indexOf(color2)),
                                    rotation,
                                    playerRotateInput.isSelected(),
                                    singleplayerInput.isSelected(),
                                    numberToWin);
	    f.setVisible(true);
    }
	
  }

}
