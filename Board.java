import java.awt.*;

public class Board extends RotatableObject {

  //---------------Methods---------------

  //Constructor for Board
  public Board (int[] x, int[] y) {
    super(x,y);
  }

  //Draws the board
  public void drawObject(Graphics g) {
    g.setColor(new Color(50,50,50));
    g.fillPolygon(xGui,yGui,x.length);
  }
}
