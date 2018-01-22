import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FlippyFour extends JFrame implements ActionListener, KeyListener {

  //---------------Main Method---------------

  public static void main(String[] args) {
    StartScreen input = new StartScreen();
  }

  //---------------Instance Variables For Game---------------

  private Piece[][] data;
  private int width;
  private int height;
  private int selectorIndex;
  private int numToWin;
  private Color playerOneColor;
  private Color playerTwoColor;
  private String winState;
  private boolean isFirstPlayerTurn;
  private boolean isRotated;
  private Animation animation;
  private String rotationMode;
  private int rotationNum;
  private boolean canPlayerRotate;
  private boolean isSinglePlayer;

  //---------------Instance Variables for GUI---------------

  private Container pane;
  private JButton newGame;
  private JButton mainMenu;
  private Board board;
  private int xCenter;
  private int yCenter;
  private int startWidth;
  private int startHeight;
  private int dropInt;
  private boolean isDropping;

  //---------------Other Variables---------------

  Color emptyColor = Color.WHITE;
  Timer timer = new Timer(80, this);

  //---------------Methods---------------

  //Constructor for FlippyFour class
  public FlippyFour(int width,
                    int height,
                    Color playerOneColor,
                    Color playerTwoColor,
                    String rotationMode,
                    boolean canPlayerRotate,
                    boolean isSinglePlayer,
                    int numToWin) {

    //For game
    this.height = height;
    this.width = width;
    this.playerOneColor = playerOneColor;
    this.playerTwoColor = playerTwoColor;
    this.rotationMode = rotationMode;
    this.canPlayerRotate = canPlayerRotate;
    this.isSinglePlayer = isSinglePlayer;
    this.numToWin = numToWin;

    isRotated = false;
    selectorIndex = width / 2;
    rotationNum = 1;
    dropInt = height - 1;
    isDropping = false;

    int adjustment = (Math.max(width, height));
    startWidth = (78 * adjustment - width * 50) / 2;
    startHeight = (78 * adjustment - height * 50) / 2;

    data = new Piece[width][height];
    restartData();

    winState = "Continue Game";
    isFirstPlayerTurn = true;

    //For Gui
    this.setTitle("Flippy Four");
    this.setSize(adjustment * 78, adjustment * 78);
    this.setLocation(100, 0);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setResizable(false);

    xCenter = startWidth + 4 + (int)((width - 1) / 2. * 50);
    yCenter = startHeight + 4 + (int)((height - 1) / 2. * 50);

    //Setting up the board
    int[] boardXCor = new int[4];
    int[] boardYCor = new int[4];
    int endWidth = startWidth + width * 50;
    int endHeight = startHeight + height * 50;

    boardXCor[0] = startWidth;
    boardXCor[1] = endWidth;
    boardXCor[2] = endWidth;
    boardXCor[3] = startWidth;
    boardYCor[0] = startHeight;
    boardYCor[1] = startHeight;
    boardYCor[2] = endHeight;
    boardYCor[3] = endHeight;

    board = new Board(boardXCor, boardYCor);

    //Setting up the button
    newGame = new JButton("New Game");
    newGame.addActionListener(this);
    newGame.setFocusable(false);

    mainMenu = new JButton("Main Menu");
    mainMenu.addActionListener(this);
    mainMenu.setFocusable(false);

    //Adding everything onto Gui
    pane = this.getContentPane();
    animation = new Animation(this);
    pane.add(animation);
    animation.add(newGame);
    animation.add(mainMenu);
    addKeyListener(this);
    timer.start();
  }

  //Used just for debugging
  //Returns the data in 2D array form
  public String toString() {
    String output = "";
    for (int y = height - 1; y >= 0; y--) {
      for (int x = 0; x < width; x++) {
        output += data[x][y] + " ";
      }
      output += "\n";
    }
    return output;
  }

  //Converts all the Pieces in data into Empty Pieces
  private void restartData() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        data[i][j] = makePiece(0, emptyColor, i, j);
      }
    }
  }

  //Adds a piece to a specific index
  private void addPiece(int index) {
    if (isFirstPlayerTurn) {
      data[index][height - 1] = makePiece(1, playerOneColor, index, height - 1);
    } else {
      data[index][height - 1] = makePiece(2, playerTwoColor, index, height - 1);
    }
    isFirstPlayerTurn = !isFirstPlayerTurn;
    animateDrop();
    randomRotations();
  }

  //Updates the winState according to the data
  private void updateWin() {
    boolean one = hasWon(1);
    boolean two = hasWon(2);
    if ((one && two) || isBoardFull()) {
      winState = "     Draw";
    } else if (one) {
      winState = "Player 1 Wins";
    } else if (two) {
      winState = "Player 2 Wins";
    } else {
      winState = "Continue Game";
    }
    animation.repaint();
  }

  //Checks if a player has won according to the data
  private boolean hasWon(int id) {
    //checking vertical wins
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height - (numToWin - 1); j++) {
        if (checkWin(id, i, j, 0, 1)) {
          return true;
        }
      }
    }
    //checking horizontal wins
    for (int i = 0; i < width - (numToWin - 1); i++) {
      for (int j = 0; j < height; j++) {
        if (checkWin(id, i, j, 1, 0)) {
          return true;
        }
      }
    }
    //checking diagonal // wins
    for (int i = 0; i < width - (numToWin - 1); i++) {
      for (int j = 0; j < height - (numToWin - 1); j++) {
        if (checkWin(id, i, j, 1, 1)) {
          return true;
        }
      }
    }
    //checking diagonal \\ wins
    for (int i = 0; i < width - (numToWin - 1); i++) {
      for (int j = height - 1; j > (numToWin - 2); j--) {
        if (checkWin(id, i, j, 1, -1)) {
          return true;
        }
      }
    }
    return false;
  }

  //checks if there are pieces of the same id in a row given the direction and starting point
  private boolean checkWin(int id, int x, int y, int xIncrement, int yIncrement) {
    for (int i = 0; i < numToWin; i++) {
      if (!(data[x + xIncrement * i][y + yIncrement * i].getId() == id)) {
        return false;
      }
    }
    return true;
  }

  //checks if the board is filled up with Pieces
  private boolean isBoardFull() {
    for (int i = 0; i < width; i++) {
      if (!isFull(i)) {
        return false;
      }
    }
    return true;
  }

  //checks if a certain column is full of Pieces
  private boolean isFull(int index) {
    for (int i = 0; i < height; i++) {
      if (data[index][i].getId() == 0) {
        return false;
      }
    }
    return true;
  }

  //Rotates the board given a direction
  private void rotate(String direction) {
    Piece[][] temp = new Piece[height][width];
    if (direction.equals("right")) {
      for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++)
          temp[x][y] = data[width - y - 1][x];
      }
    }
    if (direction.equals("left")) {
      for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++)
          temp[x][y] = data[y][height - x - 1];
      }
    }
    data = temp;
    height = width;
    width = data.length;
    isRotated = !isRotated;
    selectorIndex = width / 2;
    if ((rotationMode.equals("Set Interval") && rotationNum % 6 != 0) ||
        (rotationMode.equals("Random Rotation") && rotationNum % 4 != 0) ||
        rotationMode.equals("None")) {
      isFirstPlayerTurn = !isFirstPlayerTurn;
    }
  }

  //Makes all the pieces in the data drop down by one slot if there
  //is an empty piece below it
  private void dropOnce() {
    for (int x = 0; x < width; x++) {
      for (int y = 1; y < height; y++) {
        if (data[x][y - 1].getId() == 0) {
          data[x][y - 1] = data[x][y];
          data[x][y - 1].drop();
          data[x][y] = makePiece(0, emptyColor, x, y);
        }
      }
    }
    animation.repaint();
  }

  //Starts the animation of Pieces dropping
  public void animateDrop() {
    dropInt = 0;
    isDropping = true;
  }

  //Changes the rotationNum into a different number
  private void randomRotations() {
    if (rotationMode.equals("Set Interval")) {
      rotationNum++;
    }
    if (rotationMode.equals("Random Rotation")) {
      rotationNum = (int)(Math.random() * 16);
    }
  }

  //Creates an instance of Piece given color, id and position
  private Piece makePiece(int id, Color color, int x, int y) {
    int[] xCor = new int[1];
    int[] yCor = new int[1];
    if (isRotated) {
      xCor[0] = startHeight + 5 + 50 * x;
      yCor[0] = startWidth + 5 + 50 * (height - 1 - y);
    } else {
      xCor[0] = startWidth + 5 + 50 * x;
      yCor[0] = startHeight + 5 + 50 * (height - 1 - y);
    }
    return new Piece(id, color, xCor, yCor);
  }

  //---------------Action Listener---------------

  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    //Controls starting a new game
    if (s == "New Game" && !isDropping && !animation.getIsRotating()) {
      restartData();
      animation.repaint();
      if (isRotated) {
        animation.animateRotate("left");
        rotate("left");
      }
      winState = "Continue Game";
      isRotated = false;
      selectorIndex = width / 2;
      rotationNum = 1;
      isFirstPlayerTurn = true;
    }
    //Controls going back to the main menu
    if (s == "Main Menu") {
      this.dispose();
      StartScreen input = new StartScreen();
    }
    //For animating the dropping
    if (isDropping) {
      dropOnce();
      dropInt++;
    }
    //For things after the pieces are done dropping
    if (dropInt == height) {
      isDropping = false;
      animation.repaint();
      //Rotates the board for "set interval" mode
      if (rotationMode.equals("Set Interval") && rotationNum % 6 == 0) {
        rotate("right");
        animation.animateRotate("right");
        rotationNum++;
      }
      //Rotates the board for "random rotation" mode
      if (rotationMode.equals("Random Rotation") && rotationNum % 4 == 0) {
        int randomInt = (int)(Math.random() * 1000);
        if (randomInt % 2 == 0) {
          rotate("right");
          animation.animateRotate("right");
        } else {
          rotate("left");
          animation.animateRotate("left");
        }
        rotationNum++;
      }
    }
    //Updates the winState of the board after Pieces are done dropping
    if (!isDropping) {
      updateWin();
      animation.repaint();
    }
    //Controls the computer for singleplayer
    if (!isFirstPlayerTurn &&
        isSinglePlayer &&
        !isDropping &&
        !animation.getIsRotating() &&
        winState.equals("Continue Game")) {
      int randomRange = width;
      if (canPlayerRotate) {
        randomRange += 2;
      }
      int selectedChoice = (int)(Math.random() * randomRange);
      if (selectedChoice == width) {
        rotate("left");
        animation.animateRotate("left");
      } else if (selectedChoice == width + 1) {
        rotate("right");
        animation.animateRotate("right");
      } else {
        while (isFull(selectedChoice)) {
          System.out.println(selectedChoice);
          selectedChoice = (int)(Math.random() * width);
        }
        addPiece(selectedChoice);
      }
    }
  }

  //---------------Key Listener---------------

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    //Stops user input when the game is rotating, dropping, or finished
    if (isDropping || animation.getIsRotating() ||
        !winState.equals("Continue Game")) {
      key = -1;
    }
    //Move piece to the left
    if (key == KeyEvent.VK_LEFT) {
      if (selectorIndex > 0) {
        selectorIndex--;
      }
    }
    //Move piece to the right
    if (key == KeyEvent.VK_RIGHT) {
      if (selectorIndex < width - 1) {
        selectorIndex++;
      }
    }
    //Drops the pieces down
    if (key == KeyEvent.VK_SPACE && !isFull(selectorIndex)) {
      addPiece(selectorIndex);
    }
    //Controls rotating left and right
    if (canPlayerRotate) {
      if (key == KeyEvent.VK_Q) {
        rotate("left");
        animation.animateRotate("left");
      }
      if (key == KeyEvent.VK_E) {
        rotate("right");
        animation.animateRotate("right");
      }
    }
    animation.repaint();
  }

  //To avoid compilation errors
  public void keyTyped(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {}

  //---------------Accessor Methods---------------

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

  public Board getBoard() {
    return board;
  }

  public Color getFirstColor() {
    return playerOneColor;
  }

  public Color getSecondColor() {
    return playerTwoColor;
  }

  public boolean getIsDropping() {
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

  public boolean getCanPlayerRotate() {
    return canPlayerRotate;
  }

  public String getRotationMode() {
    return rotationMode;
  }

  public String getRotationNum() {
    return 6 - (rotationNum % 6) + "";
  }
}
