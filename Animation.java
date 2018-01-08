import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Animation extends JPanel{

  //---------Instance Variables--------

  private ConnectFour game;

  //---------Other Variables---------


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
}
