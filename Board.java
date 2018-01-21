import java.awt.*;

public class Board extends RotatableObject {

  //---------------Methods---------------

  public Board (int[] x, int[] y) {
    super(x,y);
  }

  public void drawObject(Graphics g) {
    g.setColor(new Color(50,50,50));
    g.fillPolygon(xGui,yGui,x.length);
  }
}
