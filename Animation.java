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
  private String rotationalDirection;

  //---------Other Variables---------

  Timer timer = new Timer(10,this);
  //-------------Methods-----------

  public Animation (ConnectFour game) {
    this.game = game;
    timer.start();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    game.getBoard().drawObject(g);

    for (int x=0; x<game.getBoardWidth(); x++){
      for (int y=game.getBoardHeight()-1; y>=0; y--){
        game.getPieceAt(x,y).drawObject(g);
      }
    }

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
      rotateInt++;
    }
    if (rotateInt == 90) {
      isRotating = false;
      //animateDrop();
    }
    /*
    if (isDropping) {
      game.dropOne();
      dropInt++;
    }
    if (dropInt == game.getHeight()-1){
      isDropping = false;
      }*/
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
