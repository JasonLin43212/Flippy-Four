public class ConnectFour{

  public static void main(String[]args){
    ConnectFour c = new ConnectFour(7,6,"blue","red");
    /*
    c.addPiece(6);
    c.addPiece(0);

    c.addPiece(0);
    c.addPiece(1);

    c.addPiece(0);
    c.addPiece(1);

    c.addPiece(0);
    c.addPiece(1);

    c.addPiece(5);
    c.addPiece(3);

    c.addPiece(3);
    c.addPiece(1);

    c.addPiece(5);
    c.addPiece(4);

    c.addPiece(1);
    c.addPiece(5);
    */
    c.addPiece(3);
    c.addPiece(2);
    c.addPiece(2);
    c.addPiece(1);
    c.addPiece(1);
    c.addPiece(3);
    c.addPiece(1);
    c.addPiece(1);
    c.addPiece(2);
    c.addPiece(2);
    c.addPiece(0);
    c.addPiece(4);
    c.addPiece(0);
    c.addPiece(0);
    c.addPiece(0);
    c.addPiece(3);
    c.addPiece(3);
    System.out.println(c);
  }

  //----------Instance Variables--------------

  private Piece[][] data;
  private Piece Player1;
  private Piece Player2;
  private int width;
  private int height;
  private String winState;
  private boolean isFirstPlayerTurn;

  //----------Other Variables------------

  Piece emptyPiece = new Piece(0, "white");

  //----------Methods------------

  public ConnectFour (int width, int height, String playerOneColor, String playerTwoColor){

    data = new Piece[width][height];
    for (int i=0; i<width; i++){
      for (int j=0; j<height; j++){
        data[i][j] = emptyPiece;
      }
    }

    Player1 = new Piece(1, playerOneColor);
    Player2 = new Piece(2, playerTwoColor);

    this.height = height;
    this.width = width;

    winState = "Continue Game";
    isFirstPlayerTurn = true;
  }

  public void addPiece (int index){
    if (isFirstPlayerTurn){
      data[index][findNextColumn(index)] = Player1;
    }
    else {
      data[index][findNextColumn(index)] = Player2;
    }
    isFirstPlayerTurn = !isFirstPlayerTurn;
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

  public int findNextColumn (int xIndex) {
    int yIndex = 0;
    for (int i=0; i<height; i++){
      if (!data[xIndex][i].equals(emptyPiece)){
        yIndex++;
      }
    }
    return yIndex;
  }

  private void hasWon(Piece p){
    //checking vertical wins
    for (int i=0; i<width; i++){
      for (int j=0; j<height-4; j++){
        if (checkWin(p,i,j,0,1)){
          winState = "Player " + p.getId() + " wins";
          return;
        }
      }
    }

    //checking horizontal wins
    for (int i=0; i<width-4; i++){
      for (int j=0; j<height; j++){
        if (checkWin(p,i,j,1,0)){
          winState = "Player " + p.getId() + " wins";
          return;
        }
      }
    }

    //checking diagonal // wins
    for (int i=0; i<width-4; i++){
      for (int j=0; j<height-4; j++){
        if (checkWin(p,i,j,1,1)){
          winState = "Player " + p.getId() + " wins";
          return;
        }
      }
    }

    //checking diagonal \\ wins
    for (int i=0; i<width; i++){
      for (int j=height-1; j>2; j--){
        if (checkWin(p,i,j,1,-1)){
          winState = "Player " + p.getId() + " wins";
          return;
        }
      }
    }
  }

  private boolean checkWin(Piece p, int x, int y, int xIncrement, int yIncrement){
    //might change the i<4 to something else for connect 5
    for (int i=0; i<4; i++){
      if (!data[x+xIncrement*i][y+yIncrement*i].equals(p)){
        return false;
      }
    }
    return true;
  }
}
