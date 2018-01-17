import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class betterStartScreen extends JFrame implements ActionListener{

    private Container pane;
    private JTextField text;
    private JButton START;

    
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

    }

    public void actionPerformed(ActionEvent e){
	String str = e.getActionCommand();
	System.out.println(str); 

	if (str.equals("START")){
	    //double result = FC(Double.parseDouble(text.getText()));
	    //text.setText(result + "");
	
	}
    }


    public static void main(String[]args){
	betterStartScreen x = new betterStartScreen();
	x.setVisible(true);
	
    }
}
