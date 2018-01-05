public class ConnectFour{

  //----------Instance Variables--------------

  private Piece[][] data;
  private Piece Player1;
  private Piece Player2;
  private int width;
  private int height;
  private boolean win;
  private boolean draw;
  private boolean isFirstPlayerTurn;

  //----------Methods------------

  public ConnectFour (int width, int height, String playerOneColor, String playerTwoColor){
    data = new Piece[width][height];
    for (int i=0; i<width; i++){
      for (int j=0; j<height; j++){
        data[i][j] = new Piece(0,"white");
      }
    }

    Player1 = new Piece(1, playerOneColor);
    Player2 = new Piece(2, playerTwoColor);
  }

  public void hasWon (){
    return win;
  }

  public void hasDraw (){
    return draw;
  }

  public void addPiece (int index){
    if (isFirstPlayerTurn){
      data[index][findNextColumn(index)] = Player1;
    }
    else {
      data[index][findNextColumn(index)] = Player2;
    }
  }

  public String toString () {
    String output = "";
    for (int y=height-1; y>=0; y--){
      for (int x=0; x<width; x++){
        output += data[x][y] + " "
      }
    }
    return output;
  }

}
