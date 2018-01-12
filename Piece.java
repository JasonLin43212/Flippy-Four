import java.awt.*;

public class Piece extends RotatableObject{

  //------Instance Variables--------

  private Color color;
  private int id;

  //------Methods------
  public Piece (int id, Color color, int[] x, int[] y) {
    super(x,y);
    this.color = color;
    this.id = id;
  }

  public int getId () {
    return id;
  }

  public String toString () {
    return "" + id;
  }

  public void drawObject(Graphics g) {
    g.setColor(color);
    g.fillOval(xGui[0],yGui[0],40,40);
    //System.out.println(xGui[0] + "," + yGui[0] + "||" + x[0]+ ","+y[0]);
  }

  public void drop() {
    y[0] += 50;
    yGui[0] += 50;
  }
}
