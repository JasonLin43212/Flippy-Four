import javax.swing.*;
import java.awt.*;

public class StartScreenPanel extends JPanel{

  public StartScreenPanel () {}

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.RED);
    g.fillOval(50,50,50,50);
  }
}
