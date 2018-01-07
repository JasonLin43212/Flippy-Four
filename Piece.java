import java.awt.Color;

public class Piece extends RotatableObject{

    //------Instance Variables--------

    private Color color;
    private int id;

    //------Methods------
    public Piece (int id, Color color) {
	this.color = color;
	this.id = id;
    }

    public int getId () {
	return id;
    }

    public String toString () {
	return "" + id;
    }

    public boolean equals (Piece other) {
	return id == other.getId();
    }

    public static void main(String[] args){
	//Piece x = new Piece(0, "red");
	//System.out.println(x.getId());
    }
}
