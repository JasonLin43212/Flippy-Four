import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Animation extends JPanel implements ActionListener{

  //---------Instance Variables--------

  private ConnectFour game;
  private boolean isRotating;
  private int rotateInt;
  private String rotationalDirection;
  private boolean isRotated;

  //---------Other Variables---------

  Timer timer = new Timer(10,this);
  //-------------Methods-----------

  public Animation (ConnectFour game) {
    this.game = game;
    isRotated = false;
    timer.start();
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);

    //Drawing the board
    game.getBoard().drawObject(g);

    //Drawing the Pieces
    for (int x=0; x<game.getBoardWidth(); x++){
      for (int y=game.getBoardHeight()-1; y>=0; y--){
        game.getPieceAt(x,y).drawObject(g);
      }
    }

    // Drawing the arrow
    if (!(isRotating || game.getIsDropping())) {
      int[] arrowXCor = new int[3];
      int[] arrowYCor = new int[3];
      int arrowAdjustment = game.getSelectorIndex()*50;
      int startWidth;
      int startHeight;
      if (isRotated) {
        startWidth = game.getStartHeight();
        startHeight = game.getStartWidth();
      }
      else {
        startWidth = game.getStartWidth();
        startHeight = game.getStartHeight();
      }

      if (game.getIsFirstPlayerTurn()){
        g.setColor(game.getFirstColor());
      }
      else {
        g.setColor(game.getSecondColor());
      }
      arrowXCor[0] = startWidth + 15 + arrowAdjustment;
      arrowXCor[1] = startWidth + 35 + arrowAdjustment;
      arrowXCor[2] = startWidth + 25 + arrowAdjustment;
      arrowYCor[0] = startHeight - 20;
      arrowYCor[1] = startHeight - 20;
      arrowYCor[2] = startHeight - 10;
      g.fillPolygon(arrowXCor,arrowYCor,3);

    }
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
      rotateInt = 0;
      isRotating = false;
      game.animateDrop();
      System.out.println("done rotating");
    }
  }

  public void animateRotate(String direction){
    rotationalDirection = direction;
    rotateInt = 0;
    isRotating = true;
    isRotated = !isRotated;
  }
  public boolean getIsRotating(){
    return isRotating;
  }
}
