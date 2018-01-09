import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ConnectFour extends JFrame implements /*ActionListener,*/ KeyListener{

    public static void main(String[]args){
	boolean stop = false;
	Scanner scan = new Scanner(System.in);
	ArrayList<String> parameters = new ArrayList<String>();


	while (!stop){
	    while (parameters.size() == 0){
		System.out.println("Enter the HEIGHT of your board. \nThis should be an integer between 5 and 11, inclusive. ");
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
		System.out.println("Enter the WIDTH of your board. \nThis should be an integer between 5 and 11, inclusive. ");
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

	    if (parameters.size() == 2){
		System.out.println("Player 1: Enter your color");
		String next = scan.next();
		parameters.add(next);
	    }

	    if (parameters.size() == 3){
		System.out.println("Player 2: Enter your color");
		String next = scan.next();
		parameters.add(next);
	    }

	    if (parameters.size() == 4){
		stop = true;
		System.out.println("You are done setting up! \n Now for some instructions: \n\n [<-]: moves arrow left \n" +
                                    "[->]: moves arrow right \n" +
                                    "[space]: drops piece\n" +
                                    "[l]: rotates board left \n" +
                                    "[r]: rotates board right \n"
				   );
	    }

	}
	
	ConnectFour c = new ConnectFour(7,6,Color.RED,Color.GREEN);
        c.setVisible(true);
    }

    //----------Instance Variables For Game--------------

    private Piece[][] data;
    public int width;
    public int height;
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

    //----------Other Variables------------

    Color emptyColor = Color.WHITE;
  
    //----------Methods------------

    public ConnectFour (int width, int height, Color playerOneColor, Color playerTwoColor){

	//For game
	this.height = height;
	this.width = width;
	this.playerOneColor = playerOneColor;
	this.playerTwoColor = playerTwoColor;
	selectorIndex = width/2;

	data = new Piece[width][height];
	restartData();

	winState = "Continue Game";
	isFirstPlayerTurn = true;

	//For Gui
	int sideLength = 200 + (Math.max(width,height))*50;
	this.setTitle("Flippy Four");
	this.setSize(sideLength,sideLength);
	this.setLocation(100,0);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setResizable(false);

	int[] boardXCor = new int[4];
	int[] boardYCor = new int[4];
	int endWidth = 100+width*50;
	int endHeight = 100+height*50;

	boardXCor[0] = 100;
	boardXCor[1] = endWidth;
	boardXCor[2] = endWidth;
	boardXCor[3] = 100;
	boardYCor[0] = 100;
	boardYCor[1] = 100;
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
		data[i][j] = makePiece(9,emptyColor,i,j);
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
	    System.out.println(x+xIncrement*i);
	    System.out.println(y+yIncrement*i);
	    System.out.println("------");
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
		    data[x][y] = makePiece(9,emptyColor,x,y);
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

    public Board getBoard(){
	return board;
    }

    private Piece makePiece(int id, Color color, int x, int y) {
	int[] xCor = new int[1];
	int[] yCor = new int[1];
	xCor[0] = 104 + 50*x;
	yCor[0] = 104 + 50*(height-1-y);
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
	    rotate("left");
	}
	if (key == KeyEvent.VK_E){
	    rotate("right");
	}

	animation.repaint();
    }

    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
