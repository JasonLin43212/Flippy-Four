import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreenPanel extends JPanel implements ActionListener{

  //---------------Other Variables-----------

  Timer timer = new Timer(15,this);

  int[] xBoardCor1 = new int[] {100,100,250,250};
  int[] yBoardCor1 = new int[] {120,270,270,120};
  Board fakeBoard1 = new Board(xBoardCor1,yBoardCor1);

  int[] xBoardCor2 = new int[] {550,550,650,650};
  int[] yBoardCor2 = new int[] {250,350,350,250};
  Board fakeBoard2 = new Board(xBoardCor2,yBoardCor2);

  int[] xBoardCor3 = new int[] {60,60,110,110};
  int[] yBoardCor3 = new int[] {450,500,500,450};
  Board fakeBoard3 = new Board(xBoardCor3,yBoardCor3);


  //------------------Methods-----------------

  public StartScreenPanel () {
    for (int i=0; i<120; i++){
      fakeBoard2.rotate("left",600,300);
      if (i%3==0){
        fakeBoard3.rotate("right",85,475);
      }
    }
    timer.start();
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(135,206,250));
    g.fillRect(0,0,800,800);

    g.setColor(new Color(105,105,105));
    fakeBoard1.drawObject(g);
    fakeBoard2.drawObject(g);
    fakeBoard3.drawObject(g);
  }

  //----------Action Performed for Timer----------

  public void actionPerformed(ActionEvent e) {
    fakeBoard1.rotate("left",175,195);
    fakeBoard2.rotate("right",600,300);
    fakeBoard3.rotate("right",85,475);
    repaint();
  }
}
