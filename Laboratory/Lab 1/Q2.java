import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class Q2 {
    static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Light");
        frame.setSize(250, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LightComponent light = new LightComponent();
        frame.add(light);
        frame.setVisible(true);
    }
}

class LightComponent extends JComponent {
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double box = new Rectangle2D.Double(50, 20, 100, 270);
        g2.setColor(Color.WHITE);
        g2.fill(box);
        g2.setColor(Color.BLACK);
        g2.draw(box);

        // 2. Draw the Red Light (Top)
        Ellipse2D.Double redLight = new Ellipse2D.Double(70, 40, 60, 60);
        g2.setColor(Color.RED);
        g2.fill(redLight);

        // 3. Draw the Yellow Light (Middle)
        Ellipse2D.Double yellowLight = new Ellipse2D.Double(70, 120, 60, 60);
        g2.setColor(Color.YELLOW);
        g2.fill(yellowLight);

        // 4. Draw the Green Light (Bottom)
        Ellipse2D.Double greenLight = new Ellipse2D.Double(70, 200, 60, 60);
        g2.setColor(Color.GREEN);
        g2.fill(greenLight);
    }
}