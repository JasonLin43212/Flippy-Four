import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StartScreenPanel extends JPanel implements ActionListener{

  //---------------Variables---------------

  private ArrayList<Color> colors = new ArrayList<Color>(){{
      add(new Color(220,20,60));
      add(new Color(255,140,0));
      add(new Color(220,220,0));
      add(new Color(0,128,0));
      add(new Color(30,144,255));
      add(new Color(20,230,230));
      add(new Color(230,20,230));
      add(new Color(138,43,226));
      add(new Color(255,20,147));
      add(new Color(139,69,19));
    }};

  Timer timer = new Timer(15,this);

  int[] xBoardCor1 = new int[] {100,100,250,250};
  int[] yBoardCor1 = new int[] {120,270,270,120};
  Board fakeBoard1 = new Board(xBoardCor1,yBoardCor1);

  int[] xBoardCor2 = new int[] {550,550,650,650};
  int[] yBoardCor2 = new int[] {250,350,350,250};
  Board fakeBoard2 = new Board(xBoardCor2,yBoardCor2);

  int[] xBoardCor3 = new int[] {60,60,110,110};
  int[] yBoardCor3 = new int[] {450,500,500,450};
  Board fakeBoard3 = new Board(xBoardCor3,yBoardCor3);

  int dropInt1 = 0;
  Piece piece1 = makePiece(colors.get((int)(Math.random()*10)),true);

  int dropInt2 = 40;
  Piece piece2 = makePiece(colors.get((int)(Math.random()*10)),true);

  int dropInt3 = 80;
  Piece piece3 = makePiece(colors.get((int)(Math.random()*10)),true);

  int dropInt4 = 120;
  Piece piece4 = makePiece(colors.get((int)(Math.random()*10)),true);

  int dropInt5 = 160;
  Piece piece5 = makePiece(colors.get((int)(Math.random()*10)),true);

  //---------------Methods---------------

  //Constructor for StartScreenPanel
  public StartScreenPanel () {
    for (int i=0; i<120; i++){
      fakeBoard2.rotate("left",600,300);
      if (i%3==0){
        fakeBoard3.rotate("right",85,475);
      }
    }
    timer.start();
  }

  //Draws the different parts of the StartScreen
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(new Color(135,206,250));
    g.fillRect(0,0,800,800);
    fakeBoard1.drawObject(g);
    fakeBoard2.drawObject(g);
    fakeBoard3.drawObject(g);
    drawPieces(g);
    g.setColor(Color.BLACK);
    g.setFont(new Font("Serif",Font.BOLD,11));
    g.drawString("Made by Jason Lin and Karen Li",590,655);
  }

  //---------------Action Performed for Timer---------------

  public void actionPerformed(ActionEvent e) {
    updatePieces();
    fakeBoard1.rotate("left",175,195);
    fakeBoard2.rotate("right",600,300);
    fakeBoard3.rotate("right",85,475);
    repaint();
  }

  //A makePiece function specific for the StartScreen
  //Chooses a random x coordinate to drop the pieces
  private Piece makePiece(Color color, boolean isFirst) {
    int[] xCor = new int[1];
    int[] yCor;
    if (isFirst) {
      yCor = new int[] {800};
    }
    else {
      yCor = new int[] {-50};
    }
    xCor[0] = (int) (Math.random()*760);
    return new Piece(-1,color,xCor,yCor);
  }

  //Makes the pieces drop and makes new pieces
  //once a piece has reached the bottom
  private void updatePieces() {
    if (dropInt1 > 200) {
      piece1 = makePiece(colors.get((int)(Math.random()*10)),false);
      dropInt1 = 0;
    }
    if (dropInt2 > 200) {
      piece2 = makePiece(colors.get((int)(Math.random()*10)),false);
      dropInt2 = 0;
    }
    if (dropInt3 > 200) {
      piece3 = makePiece(colors.get((int)(Math.random()*10)),false);
      dropInt3 = 0;
    }
    if (dropInt4 > 200) {
      piece4 = makePiece(colors.get((int)(Math.random()*10)),false);
      dropInt4 = 0;
    }
    if (dropInt5 > 200) {
      piece5 = makePiece(colors.get((int)(Math.random()*10)),false);
      dropInt5 = 0;
    }

    piece1.startScreenDrop();
    piece2.startScreenDrop();
    piece3.startScreenDrop();
    piece4.startScreenDrop();
    piece5.startScreenDrop();

    dropInt1++;
    dropInt2++;
    dropInt3++;
    dropInt4++;
    dropInt5++;
  }

  //Draws the 5 pieces
  private void drawPieces(Graphics g) {
    piece1.drawObject(g);
    piece2.drawObject(g);
    piece3.drawObject(g);
    piece4.drawObject(g);
    piece5.drawObject(g);
  }
}
