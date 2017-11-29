package projectTest;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
//  w  w  w  .ja v  a 2  s  .  c  o m
public class Testtest {
  public static void main(final String args[]) {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(300, 300);
    f.setLocationRelativeTo(null);

    f.setUndecorated(true);
    f.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

    JPanel panel = new JPanel();
    panel.setBackground(java.awt.Color.white);
    f.setContentPane(panel);

    MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
    try {
      UIManager.setLookAndFeel(new MetalLookAndFeel());
    } catch (Exception e) {
      e.printStackTrace();
    }

    SwingUtilities.updateComponentTreeUI(f);

    f.setVisible(true);
  }
}

class MyDefaultMetalTheme extends DefaultMetalTheme {
  public ColorUIResource getWindowTitleInactiveBackground() {
    return new ColorUIResource(java.awt.Color.BLACK);
  }

  public ColorUIResource getWindowTitleBackground() {
    return new ColorUIResource(java.awt.Color.BLACK);
  }

  public ColorUIResource getPrimaryControlHighlight() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getPrimaryControlDarkShadow() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getPrimaryControl() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getControlHighlight() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getControlDarkShadow() {
    return new ColorUIResource(java.awt.Color.orange);
  }

  public ColorUIResource getControl() {
    return new ColorUIResource(java.awt.Color.orange);
  }
}