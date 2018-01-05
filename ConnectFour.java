public class ConnectFour{

  public static void main(String[]args){
    ConnectFour c = new ConnectFour(4,5,"blue","red");
    System.out.println(c);
    c.addPiece(2);
    c.addPiece(3);
    System.out.println(c);
  }

  //----------Instance Variables--------------

  private Piece[][] data;
  private Piece Player1;
  private Piece Player2;
  private int width;
  private int height;
  private boolean win;
  private boolean draw;
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

    isFirstPlayerTurn = true;
  }

  public boolean hasWon (){
    return win;
  }

  public boolean hasDraw (){
    return draw;
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

    public void hasWon(Piece p){
	return;
    }
}
