import java.awt.*;

public abstract class RotatableObject{

  //---------Instance Variables--------

  int[] x;
  int[] y;
  double[] xGui;
  double[] yGui;

  //-------------Methods--------------

  public RotatableObject (int[] x, int[] y) {
    int numOfPoints = x.length;
    this.x = new int[numOfPoints];
    this.y = new int[numOfPoints];
    this.xGui = new double[numOfPoints];
    this.yGui = new double[numOfPoints];

    for (int i=0; i<numOfPoints; i++){
      this.x[i] = x[i];
      this.y[i] = y[i];
      xGui[i] = x[i];
      yGui[i] = y[i];
    }
  }
}
