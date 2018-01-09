import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Animation extends JPanel implements ActionListener{

  //---------Instance Variables--------

  private ConnectFour game;
  private boolean isDropping;
  private boolean isRotating;
  private int dropInt;
  private int rotateInt;

  //---------Other Variables---------

  Timer timer = new Timer(1000,this);
  //-------------Methods-----------

  public Animation (ConnectFour game) {
    this.game = game;
  }

  public void paintComponent(Graphics g){
    System.out.println("painting");
    super.paintComponent(g);
    game.getBoard().drawObject(g);

    for (int x=0; x<game.getBoardWidth(); x++){
      for (int y=game.getBoardHeight()-1; y>=0; y--){
        game.getPieceAt(x,y).drawObject(g);
      }
    }

    g.setColor(Color.RED);
    g.drawRect(game.getXCenter(),game.getYCenter(),2,2);

    int[] arrowXCor = new int[3];
    int[] arrowYCor = new int[3];
    int arrowAdjustment = game.getSelectorIndex()*50;
    arrowXCor[0] = 115 + arrowAdjustment;
    arrowXCor[1] = 135 + arrowAdjustment;
    arrowXCor[2] = 125 + arrowAdjustment;
    arrowYCor[0] = 80;
    arrowYCor[1] = 80;
    arrowYCor[2] = 90;
    g.setColor(Color.MAGENTA);
    g.fillPolygon(arrowXCor,arrowYCor,3);
  }

  public void rotateOnce(String direction) {
    int xCenter = game.getXCenter();
    int yCenter = game.getYCenter();
    for (int x=0; x<game.getBoardWidth(); x++){
      for (int y=0; y<game.getBoardHeight(); y++){
        game.getPieceAt(x,y).rotate(direction,xCenter,yCenter);
      }
    }
    game.getBoard().rotate(direction,xCenter+21,yCenter+21);
    repaint();
  }

  public void actionPerformed(ActionEvent e){
    if (isRotating){
      rotateOnce(rotationalDirection);
      rotationInt++;
    }
    if (rotationInt == 6) {
      isRotating = false;
      animateDrop();
    }
    if (isDropping) {
      game.dropOne();
      dropInt++;
    }
    if (dropInt == game.getHeight()-1){
      isDropping = false;
    }
  }

  public void animateRotate(String direction){
    rotationalDirection = direction;
    rotateInt = 0;
    isRotating = true;
  }

  public void animateDrop() {
    dropInt = 0;
    isDropping = true;
  }
}
