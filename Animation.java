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
  }
}
