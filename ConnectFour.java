public class ConnectFour{

  //----------Instance Variables--------------

  private Piece[][] data;
  private Piece Player1;
  private Piece Player2;
  private boolean win;
  private boolean draw;
  private boolean isFirstPlayerTurn;

  //----------Methods------------

  public ConnectFour (int x, int y, String playerOneColor, String playerTwoColor){
    data = new Piece[x][y];
    for (int i=0; i<x; i++){
      for (int j=0; j<y; j++){
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

}
