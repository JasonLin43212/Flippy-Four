import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class betterStartScreen extends JFrame implements ActionListener{

    private Container pane;
    private JTextField text;
    private JButton START;

    private JComboBox<String> colors1 = new JComboBox<String>();
    private JComboBox<String> colors2 = new JComboBox<String>();
    private String[] colorsList = new String[] {"red", "orange", "yellow"};

    
    public betterStartScreen(){
	this.setTitle("Flippy Four");
	this.setSize(400,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

	text = new JTextField(5);
	text.addActionListener(this);
	pane.add(text);

	START = new JButton("START");
	START.addActionListener(this);
	pane.add(START);

	colors1 = new JComboBox<String>(colorsList);
	colors1.addActionListener(this);
	pane.add(colors1);
	
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
