import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;

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
    for (int x=0; x<game.getBoardWidth(); x++){
      for (int y=game.getBoardHeight()-1; y>=0; y--){
        g.fillRect(100+x*50,100+y*50,50,50);
      }
    }
  }
}
