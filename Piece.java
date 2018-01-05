public class Piece{

  //------Instance Variables--------

  private String color;
  private int Id;

  //------Methods------
  public Piece (int Id, String color) {
    this.color = color;
    this.Id = Id;
  }

  public int getId () {
    return Id;
  }

  public String toString () {
    return "" + Id;
  }

}
