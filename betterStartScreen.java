import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class betterStartScreen extends JFrame implements ActionListener{

    private Container pane;
    private JTextField text;
    private JButton START;

    private JComboBox<String> colors1;
    private JComboBox<String> colors2;
    private String[] colorsList = new String[] {"red", "orange", "yellow", "green", "blue", "cyan", "magenta", "purple", "pink", "brown"};
    
    private JComboBox<Integer> height;
    private JComboBox<Integer> width;
    private Integer[] dimensions = new Integer[] {5,6,7,8,9,10};

    
    public betterStartScreen(){
	this.setTitle("Flippy Four");
	this.setSize(400,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

	colors1 = new JComboBox<String>(colorsList);
	colors1.addActionListener(this);
	pane.add(colors1);

	colors2 = new JComboBox<String>(colorsList);
	colors2.addActionListener(this);
	pane.add(colors2);

	height = new JComboBox<Integer>(dimensions);
	height.addActionListener(this);
	pane.add(height);
	
	width = new JComboBox<Integer>(dimensions);
	width.addActionListener(this);
	pane.add(width);

	
	
	START = new JButton("START");
	START.addActionListener(this);
	pane.add(START);

	
	
    }


   public void actionPerformed(ActionEvent e){
	String str = e.getActionCommand();
	System.out.println(str);
	
	
    }
  
	
    public static void main(String[]args){
	betterStartScreen x = new betterStartScreen();

	x.setVisible(true);
	
    }
}
