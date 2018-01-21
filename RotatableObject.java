import java.awt.*;

public abstract class RotatableObject{

  //---------------Instance Variables---------------

  public double[] x;
  public double[] y;
  public int[] xGui;
  public int[] yGui;

  //---------------Methods---------------

  public RotatableObject (int[] x, int[] y) {
    int numOfPoints = x.length;
    this.x = new double[numOfPoints];
    this.y = new double[numOfPoints];
    this.xGui = new int[numOfPoints];
    this.yGui = new int[numOfPoints];

    for (int i=0; i<numOfPoints; i++){
      this.x[i] = x[i];
      this.y[i] = y[i];
      xGui[i] = x[i];
      yGui[i] = y[i];
    }
  }

  public abstract void drawObject(Graphics g);

  public void rotate(String direction, int xCenter, int yCenter) {
    int degrees = 1;
    if (direction.equals("left")) {
      degrees *= -1;
    }
    for (int i=0; i<x.length; i++){
      double usefulX = x[i] - xCenter;
      double usefulY = y[i] - yCenter;
      x[i] = (usefulX*Math.cos(Math.toRadians(degrees))) -
        (usefulY*Math.sin(Math.toRadians(degrees))) + xCenter;
      y[i] = (usefulX*Math.sin(Math.toRadians(degrees))) +
        (usefulY*Math.cos(Math.toRadians(degrees))) + yCenter;
      xGui[i] = (int) x[i];
      yGui[i] = (int) y[i];
    }
  }
}
