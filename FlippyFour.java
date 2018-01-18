import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FlippyFour extends JFrame implements ActionListener, KeyListener{

  public static void main(String[]args){
    betterStartScreen input = new betterStartScreen();

 
      //f.setUp1();
      //f.setUp2();
    
  }

  //----------Instance Variables For Game--------------

  private Piece[][] data;
  private int width;
  private int height;
  private int selectorIndex;
  private Color playerOneColor;
  private Color playerTwoColor;
  private String winState;
  private boolean isFirstPlayerTurn;
  private boolean isRotated;
  private Animation animation;

  //----------Instance Variables for GUI--------------

  private Container pane;
  private JButton newGame;
  private Board board;
  private int xCenter;
  private int yCenter;
  private int startWidth;
  private int startHeight;
  private int dropInt;
  private boolean isDropping;

  //---------------Other Variables-------------

  Color emptyColor = Color.WHITE;
  Timer timer = new Timer(100,this);

  //----------Methods------------

  public FlippyFour (int width,
                     int height,
                     Color playerOneColor,
                     Color playerTwoColor){

    //For game
    this.height = height;
    this.width = width;
    this.playerOneColor = playerOneColor;
    this.playerTwoColor = playerTwoColor;
    isRotated = false;
    selectorIndex = width/2;

    dropInt = height - 1;
    isDropping = false;
    
    int adjustment = (Math.max(width,height));
    startWidth = (78*adjustment-width*50)/2;
    startHeight = (78*adjustment-height*50)/2;

    data = new Piece[width][height];
    restartData();

    winState = "Continue Game";
    isFirstPlayerTurn = true;

    //For Gui
    this.setTitle("Flippy Four");
    this.setSize(adjustment*78,adjustment*78);
    this.setLocation(100,0);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setResizable(false);

    xCenter = startWidth + 4 +(int)((width-1)/2.*50);
    yCenter = startHeight + 4  +(int)((height-1)/2.*50);

    //Setting up the board
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

    //Setting up the button
    newGame = new JButton("New Game");
    newGame.addActionListener(this);
    newGame.setFocusable(false);

    //Adding everything onto Gui
    pane = this.getContentPane();
    animation = new Animation(this);
    pane.add(animation);
    animation.add(newGame);
    addKeyListener(this);
    timer.start();
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

  private void restartData(){
    for (int i=0; i<width; i++){
	    for (int j=0; j<height; j++){
        data[i][j] = makePiece(0,emptyColor,i,j);
	    }
    }
  }

  private void addPiece (int index){
    if (isFirstPlayerTurn) {
      data[index][height-1] = makePiece(1,playerOneColor,index,height-1);
    }
    else {
      data[index][height-1] = makePiece(2,playerTwoColor,index,height-1);
    }
    isFirstPlayerTurn = !isFirstPlayerTurn;
    animateDrop();
  }

  private void updateWin() {
    boolean one = hasWon(1);
    boolean two = hasWon(2);
    if ((one && two) || isBoardFull()) {
      winState = "     Draw";
    }
    else if (one) {
      winState = "Player 1 Wins";
    }
    else if (two) {
      winState = "Player 2 Wins";
    }
    else {
      winState = "Continue Game";
    }
    animation.repaint();
  }

  private boolean hasWon(int id){
    //checking vertical wins
    for (int i=0; i<width; i++){
      for (int j=0; j<height-3; j++){
        if (checkWin(id,i,j,0,1)){
          return true;
        }
      }
    }
    //checking horizontal wins
    for (int i=0; i<width-3; i++){
      for (int j=0; j<height; j++){
        if (checkWin(id,i,j,1,0)){
          return true;
        }
      }
    }
    //checking diagonal // wins
    for (int i=0; i<width-3; i++){
      for (int j=0; j<height-3; j++){
        if (checkWin(id,i,j,1,1)){
          return true;
        }
      }
    }
    //checking diagonal \\ wins
    for (int i=0; i<width-3; i++){
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

  private boolean isBoardFull() {
    for (int i=0; i<width; i++){
      if (!isFull(i)){
        return false;
      }
    }
    return true;
  }

  private boolean isFull(int index) {
    for (int i=0; i<height; i++) {
      if (data[index][i].getId() == 0) {
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
    }
    if (direction.equals("left")){
      for(int x = 0; x < height; x ++){
        for (int y = 0; y < width; y++)
          temp[x][y] = data[y][height-x-1];
      }
    }
    data = temp;
    height = width;
    width = data.length;
    isRotated = !isRotated;
    selectorIndex = width/2;
    isFirstPlayerTurn = !isFirstPlayerTurn;
  }

  private void dropOnce(){
    for (int x = 0; x < width; x++){
      for (int y = 1; y < height; y++){
        if (data[x][y-1].getId() == 0){
          data[x][y-1] = data[x][y];
          data[x][y-1].drop();
          data[x][y] = makePiece(0,emptyColor,x,y);
        }
      }
    }
    animation.repaint();
  }

  public void animateDrop() {
    dropInt = 0;
    isDropping = true;
  }

  private Piece makePiece(int id, Color color, int x, int y) {
    int[] xCor = new int[1];
    int[] yCor = new int[1];
    if (isRotated) {
      xCor[0] = startHeight + 5 + 50*x;
      yCor[0] = startWidth + 5 + 50*(height-1-y);
    }
    else {
      xCor[0] = startWidth + 5 + 50*x;
      yCor[0] = startHeight + 5 + 50*(height-1-y);
    }
    return new Piece(id,color,xCor,yCor);
  }

  //------------------Action Listener------------------------
  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    if (s == "New Game" && !isDropping && !animation.getIsRotating()) {
      restartData();
      animation.repaint();
      if (isRotated) {  
        animation.animateRotate("left");
        rotate("left");
      }
      winState = "Continue Game";
      isRotated = false;
      selectorIndex = width/2;
      isFirstPlayerTurn = true;
    }
    if (isDropping) {
      dropOnce();
      dropInt++;
    }
    if (dropInt == height){
      isDropping = false;
      animation.repaint();
    }
    if (!isDropping) {
      updateWin();
      animation.repaint();
    }
  }
   
  //------------Key Listener------------
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    if (isDropping || animation.getIsRotating() ||
        !winState.equals("Continue Game")){
      key = -1;
    }
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
    if (key == KeyEvent.VK_SPACE && !isFull(selectorIndex)) {
      addPiece(selectorIndex);
    }
    if (key == KeyEvent.VK_Q){
      rotate("left");
      animation.animateRotate("left");
    }
    if (key == KeyEvent.VK_E){
      rotate("right");
      animation.animateRotate("right");
    }
    //System.out.println(this);
    animation.repaint();
  }

  //Just so the file can compile
  public void keyTyped(KeyEvent e){}
  public void keyReleased(KeyEvent e){}

  // ----------------Assessor Methods--------------

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

  public int getStartWidth() {
    return startWidth;
  }

  public int getStartHeight() {
    return startHeight;
  }

  public Board getBoard(){
    return board;
  }

  public Color getFirstColor() {
    return playerOneColor;
  }

  public Color getSecondColor() {
    return playerTwoColor;
  }

  public boolean getIsDropping(){
    return isDropping;
  }

  public boolean getIsFirstPlayerTurn() {
    return isFirstPlayerTurn;
  }

  public int getSelectorIndex() {
    return selectorIndex;
  }

  public Piece getPieceAt(int x, int y) {
    return data[x][y];
  }

  public boolean getIsRotated() {
    return isRotated;
  }

  public String getWinState() {
    return winState;
  }

  //-------------Some positions for demo--------

  public void setUp1() {
    addPiece(0);
    addPiece(1);

    addPiece(2);
    addPiece(3);

    addPiece(4);
    addPiece(5);
    try{
      Thread.sleep(500);
    }catch(InterruptedException e){}

    addPiece(1);
    addPiece(5);

    addPiece(2);
    addPiece(6);
    
    try{
      Thread.sleep(500);
    }catch(InterruptedException e){}

    addPiece(2);
    addPiece(1);

    addPiece(0);
  }

  public void setUp2() {
    addPiece(0);
    addPiece(1);

    addPiece(2);
    addPiece(3);

    addPiece(4);
    addPiece(6);

    addPiece(5);

    try{
      Thread.sleep(500);
    }catch(InterruptedException e){}

    addPiece(0);
    addPiece(1);

    addPiece(5);
    addPiece(4);
    
    try{
      Thread.sleep(500);
    }catch(InterruptedException e){}

    addPiece(0);
    addPiece(1);

    addPiece(5);

    try{
      Thread.sleep(500);
    }catch(InterruptedException e){}

    addPiece(0);
    addPiece(1);

  }
}
