import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectFour extends JFrame implements ActionListener, KeyListener{

  public static void main(String[]args){
    boolean stop = false;
    Scanner scan = new Scanner(System.in);
    ArrayList<String> parameters = new ArrayList<String>();

	while (!stop){

	    while (parameters.size() == 0){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n----------------------------\n"+
                           "Enter the HEIGHT of your board."+
                           "\n----------------------------\n"+
                           "**This should be an integer between 5 and 11, inclusive.**\n");
        String next = scan.next();
        int h = 0;
        try {
          h = Integer.parseInt(next);

        } catch (NumberFormatException e) {
        }
        if(h >= 5 && h <= 11){
          parameters.add(next);
        }
	    }
	    while (parameters.size() == 1){
        System.out.println("\n\n----------------------------\n"+
                           "Enter the WIDTH of your board."+
                           "\n----------------------------\n"+
                           "**This should be an integer between 5 and 11, inclusive.**\n");

        String next = scan.next();
        int w = 0;
        try {
          w = Integer.parseInt(next);

        } catch (NumberFormatException e) {
        }
        if(w >= 5 && w <= 11){
          parameters.add(next);
        }
	    }

	    while (parameters.size() == 2){
        System.out.println("\n\n----------------------------\n"+
                           "Player 1: Enter your color"+
                           "\n----------------------------\n");
        String next = scan.next();
        parameters.add(next);
	    }

	    while (parameters.size() == 3){
        System.out.println("\n\n----------------------------\n"+
                           "Player 2: Enter your color"+
                           "\n----------------------------\n");
        String next = scan.next();
        parameters.add(next);
	    }
	    while (parameters.size() == 4){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nYou are done setting up! \nNow for some instructions: \n\n" +
                           "[<-]: moves arrow left \n" +
                           "[->]: moves arrow right \n" +
                           "[space]: drops piece\n" +
                           "[q]: rotates board left \n" +
                           "[e]: rotates board right \n" +
                           "\n\n\n Enter \"OK\" to start!"

                           );
        String next = scan.next();
        if (next.toLowerCase().equals("ok")){
          parameters.add(next);
        }
	    }

	    if (parameters.size() == 5){
        stop = true;
	    }


    }

    ConnectFour c = new ConnectFour(9,8,Color.RED,Color.GREEN);
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
  private boolean isRotated;

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

  public ConnectFour (int width, int height, Color playerOneColor, Color playerTwoColor){

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
    startWidth = (75*adjustment-width*50)/2;
    startHeight = (75*adjustment-height*50)/2;

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
    
    newGame = new JButton("New Game");
    newGame.addActionListener(this);
    newGame.setFocusable(false);

    pane = this.getContentPane();
    animation = new Animation(this);
    pane.add(animation);
    animation.add(newGame);
    addKeyListener(this);
    timer.start();
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
    updateWin();
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

  public void dropOne(){
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

  private Piece makePiece(int id, Color color, int x, int y) {
    int[] xCor = new int[1];
    int[] yCor = new int[1];
    if (isRotated) {
      xCor[0] = startHeight + 4 + 50*x;
      yCor[0] = startWidth + 4 + 50*(height-1-y);
    }
    else {
      xCor[0] = startWidth + 4 + 50*x;
      yCor[0] = startHeight + 4 + 50*(height-1-y);

    }
    return new Piece(id,color,xCor,yCor);
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

  public void animateDrop() {
    dropInt = 0;
    System.out.println("dropping");
    isDropping = true;
  }

  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    if (s == "New Game" && !isDropping && !animation.getIsRotating()) {
      restartData();
      animation.repaint();
      if (isRotated) {  
        animation.animateRotate("left");
        rotate("left");
      }
      isRotated = false;
      selectorIndex = width/2;
      isFirstPlayerTurn = true;
    }
    
    if (isDropping & !animation.getIsRotating()) {
      dropOne();
      dropInt++;
    }
    if (dropInt == height){
      isDropping = false;
    }

  }

  public boolean getIsDropping(){
    return isDropping;
  }

  public boolean getIsFirstPlayerTurn() {
    return isFirstPlayerTurn;
  }

  public boolean isFull(int index) {
    return !(data[index][height-1].getId() == 0);
  }
   
  //------------For Key Listener------------
  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    if (isDropping || animation.getIsRotating()){
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
    System.out.println(this);
    animation.repaint();
  }

  //Just so the file can compile
  public void keyTyped(KeyEvent e){}
  public void keyReleased(KeyEvent e){}
}
