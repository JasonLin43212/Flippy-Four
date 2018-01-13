import java.awt.*;

public class Board extends RotatableObject {

  public Board (int[] x, int[] y) {
    super(x,y);
  }

  public void drawObject(Graphics g) {
    g.fillPolygon(xGui,yGui,x.length);
  }
}
