import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Strip extends JPanel {
  private String val;
  private JLabel lab;
  private JComboBox<String> cbox;
  private String item;

  private class Selector implements ActionListener {
    public void actionPerformed(ActionEvent e) { update(); }
  }
  
  public Strip(String sin) {
    super(new FlowLayout());
    val = sin;
    lab = new JLabel(val);
    lab.setPreferredSize(new Dimension(180,40));
    cbox = new JComboBox<String>(new String[]{"3.5", "3.75", "4.0", "4.25", "4.5", "4.75", "5", "5.25", "5.5", "5.75", "6", "6.25", "6.5", "6.75", "7", "7.25", "7.5" });
    cbox.addActionListener(new Selector());
    add(lab);
    add(cbox);
  }
  
  public void update() {
    int i = cbox.getSelectedIndex();
    item = cbox.getItemAt(i);
  }

  public String getItem(){
    return item;
  }
}
