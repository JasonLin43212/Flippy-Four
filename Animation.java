import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Animation extends JPanel{

  //---------Instance Variables--------

  private ConnectFour game;
  private Board board;

  //---------Other Variables---------


  //-------------Methods-----------

  public Animation (ConnectFour game) {
    this.game = game;

    //Setting up the Board
    int[] boardXCor = new int[4];
    int[] boardYCor = new int[4];
    int endWidth = 100+game.getBoardWidth()*50;
    int endHeight = 100+game.getBoardHeight()*50;
    boardXCor.add(100);
    boardXCor.add(endWidth);
    boardXCor.add(endWidth);
    boardXCor.add(100);
    boardYCor.add(100);
    boardYCor.add(100);
    boardYCor.add(100);
    boardYCor.add(endHeight);
    boardYCor.add(endHeight);
    board = new Board(boardXCor,boardYCor);
  }

  public void paintComponent(Graphics g){
    System.out.println("painting");
    super.paintComponent(g);
    for (int x=0; x<game.getBoardWidth(); x++){
      for (int y=game.getBoardHeight()-1; y>=0; y--){
        int cellSize = 50;
        g.drawRect(2*cellSize+x*cellSize,2*cellSize+y*cellSize,cellSize,cellSize);
      }
    }
  }
}
