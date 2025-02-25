import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VaultGUI {

  public static class VGPanel extends JPanel implements ActionListener {
    private JLabel lu = new JLabel("username:");
    private JLabel lp = new JLabel("password:");
    private JLabel res = new JLabel("");
    private JTextField tfuname = new JTextField(20);
    private JTextField tfpsswd = new JPasswordField(20);
    private JButton but = new JButton("login");

    public VGPanel() {
      res.setPreferredSize(new Dimension(340,25));
      add(lu); add(tfuname);
      add(lp); add(tfpsswd);
      add(but); add(res);
      but.addActionListener(this);      
    }

    public void actionPerformed(ActionEvent e) {
      String uname = tfuname.getText();
      char[] pswd = tfpsswd.getText().toCharArray();
      System.out.println("test: '" + uname + "' '"
			 + new String(pswd) + "'");
      res.setText("what happened?");
    }
  }
  
  public static void main(String[] args) {
    JFrame f = new JFrame();
    f.setTitle("Vault");
    f.add(new VGPanel());
    f.pack();
    f.setVisible(true);
  }
}