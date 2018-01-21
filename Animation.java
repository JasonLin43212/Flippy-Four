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

    int height = game.getHeight();
    int width = game.getWidth();

    //Drawing background
    int[] triangle1X = new int[] {0,0,width};
    int[] triangle1Y = new int[] {0,height-30,height-30};
    int[] triangle2X = new int[] {0,width,width};
    int[] triangle2Y = new int[] {0,0,height-30};

    g.setColor(game.getFirstColor().brighter());
    g.fillPolygon(triangle1X,triangle1Y,3);
    g.setColor(game.getSecondColor().brighter());
    g.fillPolygon(triangle2X,triangle2Y,3);


    //Drawing the board
    game.getBoard().drawObject(g);

    //Drawing the Pieces
    for (int x=0; x<game.getBoardWidth(); x++){
      for (int y=game.getBoardHeight()-1; y>=0; y--){
        game.getPieceAt(x,y).drawObject(g);
      }
    }

    // Drawing the arrow
    if (!(isRotating || game.getIsDropping()) &&
        game.getWinState().equals("Continue Game")) {
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
      arrowXCor[0] = startWidth + 5 + arrowAdjustment;
      arrowXCor[1] = startWidth + 45 + arrowAdjustment;
      arrowXCor[2] = startWidth + 25 + arrowAdjustment;
      arrowYCor[0] = startHeight - 30;
      arrowYCor[1] = startHeight - 30;
      arrowYCor[2] = startHeight - 10;
      g.fillPolygon(arrowXCor,arrowYCor,3);
      g.setColor(Color.BLACK);
      g.drawPolygon(arrowXCor,arrowYCor,3);
    }

    //Drawing win message
    if (!game.getWinState().equals("Continue Game")) {
      g.setColor(Color.BLACK);
      g.setFont(new Font("TimesRoman",Font.BOLD,25));
      int adjustment = Math.max(game.getBoardWidth(),game.getBoardHeight());
      g.drawString(game.getWinState(),
                   adjustment*78/2 - 85,
                   adjustment*11);
    }

    //Drawing instructions
    if (!isRotating){
      int[] leftArrowX = new int[] {5,15,15,30,30,15,15};
      int[] arrowY = new int[] {height-45,
                                height-55,
                                height-48,
                                height-48,
                                height-42,
                                height-42,
                                height-35};
      int[] rightArrowX = new int[] {145,135,135,120,120,135,135};

      g.setFont(new Font("Serif",Font.BOLD,14));
      g.setColor(Color.BLACK);
      g.fillPolygon(leftArrowX,arrowY,7);
      g.drawString("Left",35,height-40);
      g.fillPolygon(rightArrowX,arrowY,7);
      g.drawString("Right",73,height-40);
      g.drawString("[Space] = Drop Piece",width-175,height-40);

      //For rotation instructions
      if (game.getCanPlayerRotate()){
        int[] leftRotateX = new int[] {5,15,15,30,30,5,5,10,10,25,25,15,15};
        int[] rotateY = new int[] {10,3,8,8,33,33,20,20,28,28,12,12,17};
        int[] rightRotateX = new int[] {width-5,
                                        width-15,
                                        width-15,
                                        width-30,
                                        width-30,
                                        width-5,
                                        width-5,
                                        width-10,
                                        width-10,
                                        width-25,
                                        width-25,
                                        width-15,
                                        width-15};
        g.fillPolygon(leftRotateX,rotateY,13);
        g.drawString("Q",35,25);
        g.drawString("E",width-45,25);
        g.fillPolygon(rightRotateX,rotateY,13);
      }

      //For Set Interval rotation
      if (game.getRotationMode().equals("Set Interval")){
        g.drawString("Pieces To Insert Until Next Rotation:",10,height-58);
        String number = game.getRotationNum();
        if (number.equals("6")) {
          number = "5";
        }
        g.drawString(number,300,height-58);
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
