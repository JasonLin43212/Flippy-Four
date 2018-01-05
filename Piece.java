public class Piece{

  //------Instance Variables--------

  private String color;
  private int id;

  //------Methods------
  public Piece (int id, String color) {
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
}
