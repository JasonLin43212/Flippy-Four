import java.awt.*;

public class Piece extends RotatableObject{

  //---------------Instance Variables---------------

  private Color color;
  private int id;

  //---------------Methods---------------
   
  //---------------Initialize rotation features, color, and ID---------------
  public Piece (int id, Color color, int[] x, int[] y) {
    super(x,y);
    this.color = color;
    this.id = id;
  }

  //---------------Accessor Methods---------------
  public int getId () {
    return id;
  }

  public String toString () {
    return "" + id;
  }

  //---------------Implement Inherited Method---------------
  public void drawObject(Graphics g) {
    g.setColor(color);
    g.fillOval(xGui[0],yGui[0],40,40);
  }

  //---------------Dropping Animation ---------------
  public void drop() {
    y[0] += 50;
    yGui[0] += 50;
  }

  public void startScreenDrop(){
    y[0] += 5;
    yGui[0] += 5;
  }
}
