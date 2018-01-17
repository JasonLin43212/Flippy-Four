import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class betterStartScreen extends JFrame implements ActionListener{
    private ArrayList<Integer> parameters;
    
    private Container pane;
    private JButton START;

    private JComboBox<String> colors1;
    private JComboBox<String> colors2;
    private String[] colorsList = new String[] {"red", "orange", "yellow", "green", "blue", "cyan", "magenta", "purple", "pink", "brown"};
    
    private JComboBox<Integer> heightInput;
    private JComboBox<Integer> widthInput;
    private Integer[] dimensions = new Integer[] {5,6,7,8,9,10};

    private JLabel error;

    String color1;
    String color2;
    int height;
    int width;

    private boolean StartGame = false;

    
    public betterStartScreen(){
	this.setTitle("Flippy Four");
	this.setSize(400,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

	colors1 = new JComboBox<String>(colorsList);
	//colors1.addActionListener(this);
	colors1.setSelectedIndex(0);
	pane.add(colors1);

	colors2 = new JComboBox<String>(colorsList);
	//colors2.addActionListener(this);
	colors2.setSelectedIndex(2);
	pane.add(colors2);

	heightInput = new JComboBox<Integer>(dimensions);
	//heightInput.addActionListener(this);
	heightInput.setSelectedIndex(1);
	pane.add(heightInput);
	
	widthInput = new JComboBox<Integer>(dimensions);
	//widthInput.addActionListener(this);
	widthInput.setSelectedIndex(2);
	pane.add(widthInput);	
	
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
	    StartGame = true;	    
	}
	
    }

    public boolean ready(){return StartGame;}
    
    public String P1_color(){return color1;}

    public String P2_color(){return color2;}

    public int boardHeight(){return height;}

    public int boardWidth(){return width;}
  
	
    public static void main(String[]args){
	betterStartScreen x = new betterStartScreen();

	x.setVisible(true);
	
    }
}
