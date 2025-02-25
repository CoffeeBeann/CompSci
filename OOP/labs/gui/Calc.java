import java.util.*;
import java.io.*;
import java.lang.Math;

public class Calc {

  private double original, loan, interest, pay, cost;
  private int months = 0;

  public Calc (double l, double i, double p){
    loan = l;
    original = l;
    interest = i;
    pay = p;

    // Make Calculations
    double temp, total = 0;
    while (loan >= 0){
      temp = (loan * interest) / 1200;
      loan = loan + temp;
      loan = loan - pay;
      total = total + pay;
      months++;
    }
    cost = total - Math.abs(loan) - original;
    cost = (Math.round(cost*100));
    cost = cost/100.0;
  }
      
  public String getMonths(){
    return "" + months;
  }

  public String getCost(){
    return "" + cost;
  }
    
  
  public static void main (String[] args){

    if (args.length != 3){
      System.out.println("usage: java Calc <amount> <rate> <payment>");
      System.exit(1);
    }

    Calc c = new Calc(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
    System.out.println(c.getMonths());
    System.out.println(c.getCost());


  }
}
