import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnectFour extends JFrame implements /*ActionListener,*/ KeyListener{

  public static void main(String[]args){
    ConnectFour c = new ConnectFour(11,6,Color.RED,Color.GREEN);
    c.setVisible(true);
  }

  //----------Instance Variables For Game--------------

  private Piece[][] data;
  private int width;
  private int height;
  private Color playerOneColor;
  private Color playerTwoColor;
  private String winState;
  private boolean isFirstPlayerTurn;
  private Animation animation;
  private int selectorIndex;

  //----------Instance Variables for GUI--------------

  private Container pane;
  private JButton newGame;
  private Board board;
  private int xCenter;
  private int yCenter;
  private int startWidth;
  private int startHeight;

  
  //----------Other Variables------------

  Color emptyColor = Color.BLUE;
  
  //----------Methods------------

  public ConnectFour (int width, int height, Color playerOneColor, Color playerTwoColor){

    //For game
    this.height = height;
    this.width = width;
    this.playerOneColor = playerOneColor;
    this.playerTwoColor = playerTwoColor;
    selectorIndex = width/2;

    int adjustment = (Math.max(width,height));
    startWidth = (75*adjustment-width*50)/2;
    startHeight = (75*adjustment-height*50)/2;

    data = new Piece[width][height];
    restartData();

    winState = "Continue Game";
    isFirstPlayerTurn = true;

    //For Gui
    this.setTitle("Flippy Four");
    this.setSize(adjustment*75,adjustment*75);
    this.setLocation(100,0);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setResizable(false);

    xCenter = startWidth + 4 +(int)((width-1)/2.*50);
    yCenter = startHeight + 4  +(int)((height-1)/2.*50);
    
    int[] boardXCor = new int[4];
    int[] boardYCor = new int[4];
    int endWidth = startWidth+width*50;
    int endHeight = startHeight+height*50;

    boardXCor[0] = startWidth;
    boardXCor[1] = endWidth;
    boardXCor[2] = endWidth;
    boardXCor[3] = startWidth;
    boardYCor[0] = startHeight;
    boardYCor[1] = startHeight;
    boardYCor[2] = endHeight;
    boardYCor[3] = endHeight;

    board = new Board(boardXCor,boardYCor);

    pane = this.getContentPane();
    animation = new Animation(this);
    pane.add(animation);
    addKeyListener(this);
  }

  private void restartData(){
    for (int i=0; i<width; i++){
	    for (int j=0; j<height; j++){
        data[i][j] = makePiece(0,emptyColor,i,j);
	    }
    }
  }

  private void addPiece (int index){
    if (isFirstPlayerTurn){
      data[index][height-1] = makePiece(1,playerOneColor,index,height-1);
    }
    else {
      data[index][height-1] = makePiece(2,playerTwoColor,index,height-1);
    }
    isFirstPlayerTurn = !isFirstPlayerTurn;
    dropAll();
    updateWin();
    animation.repaint();
  }

  public String toString () {
    String output = "";
    for (int y=height-1; y>=0; y--){
      for (int x=0; x<width; x++){
        output += data[x][y] + " ";
      }
      output += "\n";
    }
    return output;
  }

  private void updateWin() {
    boolean one = hasWon(1);
    boolean two = hasWon(2);
    if (one && two) {
      winState = "Draw";
    }
    else if (one) {
      winState = "Player 1 Wins";
    }
    else if (two) {
      winState = "Player 2 Wins";
    }
    System.out.println(winState);
  }

  private boolean hasWon(int id){
    //checking vertical wins
    for (int i=0; i<width; i++){
      for (int j=0; j<height-4; j++){
        if (checkWin(id,i,j,0,1)){
          return true;
        }
      }
    }

    //checking horizontal wins
    for (int i=0; i<width-4; i++){
      for (int j=0; j<height; j++){
        if (checkWin(id,i,j,1,0)){
          return true;
        }
      }
    }

    //checking diagonal // wins
    for (int i=0; i<width-4; i++){
      for (int j=0; j<height-4; j++){
        if (checkWin(id,i,j,1,1)){
          return true;
        }
      }
    }
    
    //checking diagonal \\ wins
    for (int i=0; i<width-4; i++){
      for (int j=height-1; j>2; j--){
        if (checkWin(id,i,j,1,-1)){
          return true;
        }
      }
    }
    return false;
  }

  private boolean checkWin(int id, int x, int y, int xIncrement, int yIncrement){
    //might change the i<4 to something else for connect 5
    for (int i=0; i<4; i++){
      if (!(data[x+xIncrement*i][y+yIncrement*i].getId() == id)){
        return false;
      }
    }
    return true;
  }
  
  private void rotate(String direction){
    Piece[][] temp = new Piece[height][width];
    if (direction.equals("right")){
	    for(int x = 0; x < height; x ++){
        for (int y = 0; y < width; y++)
          temp[x][y] = data[width-y-1][x];
	    }
	    data = temp;
    }
    if (direction.equals("left")){
	    for(int x = 0; x < height; x ++){
        for (int y = 0; y < width; y++)
          temp[x][y] = data[y][height-x-1];
      }
	    data = temp;
    }
    height = width;
    width = data.length;
    dropAll();
    //animation.repaint();
    isFirstPlayerTurn = !isFirstPlayerTurn;
  }

  private void dropOne(){
    for (int x = 0; x < width; x++){
      for (int y = 1; y < height; y++){
	      if (data[x][y-1].getId() == 9){
          data[x][y-1] = data[x][y];
          data[x][y-1].drop();
          data[x][y] = makePiece(0,emptyColor,x,y);
	      }
      }
    }
    animation.repaint();
  }

  private void dropAll(){
    for (int i = 0; i < height; i ++){
	    dropOne();
    }
  }

  public int getBoardHeight() {
    return height;
  }

  public int getBoardWidth() {
    return width;
  }

  public int getXCenter() {
    return xCenter;
  }

  public int getYCenter() {
    return yCenter;
  }
  
  public Board getBoard(){
    return board;
  }

  private Piece makePiece(int id, Color color, int x, int y) {
    int[] xCor = new int[1];
    int[] yCor = new int[1];
    xCor[0] = startWidth + 4 + 50*x;
    yCor[0] = startHeight + 4 + 50*(height-1-y);
    return new Piece(id,color,xCor,yCor);
  }

  public Piece getPieceAt(int x, int y) {
    return data[x][y];
  }

  public int getSelectorIndex() {
    return selectorIndex;
  }

  //----------For Key Listener-----------
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT){
      if (selectorIndex > 0) {
        selectorIndex--;
      }
    }
    if (key == KeyEvent.VK_RIGHT){
      if (selectorIndex <width-1){
        selectorIndex++;
      }
    }
    if (key == KeyEvent.VK_SPACE) {
      addPiece(selectorIndex);
    }
    if (key == KeyEvent.VK_Q){
      animation.animateRotate("left");
      rotate("left");
    }
    if (key == KeyEvent.VK_E){
      animation.animateRotate("right");
      rotate("right")
    }

    animation.repaint();
  }

  public void keyTyped(KeyEvent e){}
  public void keyReleased(KeyEvent e){}
}
