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
      c.addPiece(3);

      c.addPiece(5);
      c.addPiece(3);

      c.addPiece(3);
      c.addPiece(1);

      c.addPiece(5);
      c.addPiece(4);

      c.addPiece(1);
      c.addPiece(5);

      c.addPiece(0);
    */

    /*
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
    c.rotate("left");
    System.out.println(c);
    c.rotate("right");
    System.out.println(c);
    c.restartData();
    System.out.println(c);
    */
    
  }

  //----------Instance Variables--------------

  private Piece[][] data;
  private Piece Player1;
  private Piece Player2;
  private int width;
  private int height;
  public String winState;
  private boolean isFirstPlayerTurn;

  //----------Other Variables------------

  Piece emptyPiece = new Piece(0, "white");

  //----------Methods------------

  public ConnectFour (int width, int height, String playerOneColor, String playerTwoColor){


    Player1 = new Piece(1, playerOneColor);
    Player2 = new Piece(2, playerTwoColor);

    this.height = height;
    this.width = width;

    data = new Piece[width][height];
    restartData();

    winState = "Continue Game";
    isFirstPlayerTurn = true;
  }

    public void restartData(){
	for (int i=0; i<width; i++){
	    for (int j=0; j<height; j++){
		data[i][j] = emptyPiece;
	    }
	}
    }

  public void addPiece (int index){
    if (isFirstPlayerTurn){
      data[index][findNextColumn(index)] = Player1;
    }
    else {
      data[index][findNextColumn(index)] = Player2;
    }
    isFirstPlayerTurn = !isFirstPlayerTurn;
    updateWin();
    System.out.println(winState);
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

  public void updateWin() {
    boolean one = hasWon(Player1);
    boolean two = hasWon(Player2);
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

  private boolean hasWon(Piece p){
    //checking vertical wins
    for (int i=0; i<width; i++){
      for (int j=0; j<height-4; j++){
        if (checkWin(p,i,j,0,1)){
          return true;
        }
      }
    }

    //checking horizontal wins
    for (int i=0; i<width-4; i++){
      for (int j=0; j<height; j++){
        if (checkWin(p,i,j,1,0)){
          return true;
        }
      }
    }

    //checking diagonal // wins
    for (int i=0; i<width-4; i++){
      for (int j=0; j<height-4; j++){
        if (checkWin(p,i,j,1,1)){
          return true;
        }
      }
    }

    //checking diagonal \\ wins
    for (int i=0; i<width; i++){
      for (int j=height-1; j>2; j--){
        if (checkWin(p,i,j,1,-1)){
          return true;
        }
      }
    }
    return false;
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



  public void rotate(String direction){
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
    isFirstPlayerTurn = !isFirstPlayerTurn;
  }

  public void gravity(){

  }
}
