import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener{

  private static JTextField loan, payment, months, cost;
  private static Strip s1;
  private static JButton calc;

  public GUI() {
    JFrame f = new JFrame();  
    f.setSize(1000,200);
    f.setTitle("Lab 10 Calculator");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JPanel south = new JPanel();

    north.setPreferredSize(new Dimension(300,50));
    center.setPreferredSize(new Dimension(300,50));
    south.setPreferredSize(new Dimension(300,50));

    f.getContentPane().add(north, BorderLayout.NORTH);
    f.getContentPane().add(center, BorderLayout.CENTER);
    f.getContentPane().add(south, BorderLayout.SOUTH);

    loan = new JTextField(20);
    payment = new JTextField(20);
    months = new JTextField(20);
    cost = new JTextField(20);
    s1 = new Strip("interest rate");

    calc = new JButton("calculate");
    calc.addActionListener(this);

    JLabel loanLab = new JLabel("loan amount");
    JLabel payLab = new JLabel("monthly payment");
    JLabel monthsLab = new JLabel("months to payoff");
    JLabel costLab = new JLabel("cost");
    
 
    north.add(loanLab, BorderLayout.WEST);
    north.add(loan, BorderLayout.WEST);
    north.add(s1, BorderLayout.CENTER);
    north.add(payLab, BorderLayout.EAST);
    north.add(payment, BorderLayout.EAST);
    center.add(monthsLab, BorderLayout.WEST);
    center.add(months, BorderLayout.CENTER);
    center.add(calc, BorderLayout.EAST);
    south.add(costLab, BorderLayout.WEST);
    south.add(cost, BorderLayout.CENTER);

    f.setVisible(true);
  }
 
  public void actionPerformed(ActionEvent e){
    double amt = Double.parseDouble(loan.getText());
    double rate  = Double.parseDouble(s1.getItem());
    double monthly = Double.parseDouble(payment.getText());

    Calc c = new Calc(amt, rate, monthly);
    months.setText(c.getMonths());
    cost.setText(c.getCost());
  }

  public static void main(String[] args) {
    new GUI();
  }
}
