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

    private JLabel label;

    
    public betterStartScreen(){
	this.setTitle("Flippy Four");
	this.setSize(400,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

	colors1 = new JComboBox<String>(colorsList);
	//colors1.addActionListener(this);
	pane.add(colors1);

	colors2 = new JComboBox<String>(colorsList);
	//colors2.addActionListener(this);
	pane.add(colors2);

	heightInput = new JComboBox<Integer>(dimensions);
	//heightInput.addActionListener(this);
	pane.add(heightInput);
	
	widthInput = new JComboBox<Integer>(dimensions);
	//widthInput.addActionListener(this);
	pane.add(widthInput);	
	
	START = new JButton("START");
	START.addActionListener(this);
	pane.add(START);

        label = new JLabel("Please choose two different colors.");
	pane.add(label);
	label.setVisible(false);
	
    }


    public void actionPerformed(ActionEvent e){
	Object c1 = colors1.getSelectedItem();
	String color1 = c1.toString();

	Object c2 = colors2.getSelectedItem();
	String color2 = c2.toString();

	Object h = heightInput.getSelectedItem();
	int height = (int)h;

	Object w = widthInput.getSelectedItem();
	int width = (int)w;	

	if (color1.equals(color2)){
	    label.setVisible(true);
	}
	
    }
  
	
    public static void main(String[]args){
	betterStartScreen x = new betterStartScreen();

	x.setVisible(true);
	
    }
}
