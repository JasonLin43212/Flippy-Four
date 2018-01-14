import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Animation extends JPanel implements ActionListener{

  //---------Instance Variables--------

  private FlippyFour game;
  private boolean isRotating;
  private int rotateInt;
  private String rotationalDirection;

  //---------Other Variables---------

  Timer timer = new Timer(10,this);
  //-------------Methods-----------

  public Animation (FlippyFour game) {
    this.game = game;
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
      if (game.getIsRotated()) {
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
      arrowXCor[0] = startWidth + 10 + arrowAdjustment;
      arrowXCor[1] = startWidth + 40 + arrowAdjustment;
      arrowXCor[2] = startWidth + 25 + arrowAdjustment;
      arrowYCor[0] = startHeight - 24;
      arrowYCor[1] = startHeight - 24;
      arrowYCor[2] = startHeight - 10;
      g.fillPolygon(arrowXCor,arrowYCor,3);
      g.setColor(Color.BLACK);
      g.drawPolygon(arrowXCor,arrowYCor,3);

      //Drawing win message
      if (!game.getWinState().equals("Continue Game")) {
        g.setFont(new Font("TimesRoman",Font.PLAIN,16));
        int adjustment = Math.max(game.getBoardWidth(),game.getBoardHeight());
        g.drawString(game.getWinState(),
                     adjustment*78/2 - 50,
                     adjustment*9);
      }
    }
  }

  private void rotateOnce(String direction) {
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
    }
  }

  public void animateRotate(String direction){
    rotationalDirection = direction;
    rotateInt = 0;
    isRotating = true;
  }
  public boolean getIsRotating(){
    return isRotating;
  }
}
