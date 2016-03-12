import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
public class Model
{
   private static ArrayList<Function> userFunctions;
   private static ArrayList<String> previousFunctions;
   private static String currentFunction;
   private static Graphics pen;
   public static void draw()
   {
      pen.setColor(Color.WHITE);
      pen.fillRect(0,0,600,320);
      pen.setColor(Color.BLACK);
      pen.drawString(currentFunction,0,300);
      for(int i = 0; i < previousFunctions.size();i++)
      {
         pen.drawString(previousFunctions.get(i),0,280-(20*i));
      }
   }
   
   
   
   
   private static class ButtonHandler implements ActionListener
   {
      //incomplete, here is where the buttons call functions
      public void actionPerformed(ActionEvent e) {
			JButton src = (JButton)(e.getSource());
         if(src.getText().equals("+"))
         {
            currentFunction += " + ";
         }
         else if(src.getText().equals("-"))
         {
            currentFunction += " - ";
         }
         else if(src.getText().equals("/"))
         {
            currentFunction += " / ";
         }
         else if(src.getText().equals("*"))
         {
            currentFunction += " * ";
         }
         else if(src.getText().equals("="))
         {
            try
            {
               //System.out.println(currentFunction);
               Function tempf = new Function(currentFunction);
               previousFunctions.add(0,currentFunction);
               //System.out.println(tempf.getValue(5));
               currentFunction = Double.toString(tempf.getValue(0));
            }
            catch(SyntaxException se)
            {
               System.out.println("Syntax Error");
               currentFunction = se.getText();
            }
         }
         checkNumbers(src);
         checkFunctions(src);
         
         //System.out.println(currentFunction);
         draw();
      }
      private void checkNumbers(JButton src)
      {
         if(src.getText().equals("0"))
         {
            currentFunction += "0";
         }
         else if(src.getText().equals("1"))
         {
            currentFunction += "1";
         }
         else if(src.getText().equals("2"))
         {
            currentFunction += "2";
         }
         else if(src.getText().equals("3"))
         {
            currentFunction += "3";
         }
         else if(src.getText().equals("4"))
         {
            currentFunction += "4";
         }
         else if(src.getText().equals("5"))
         {
            currentFunction += "5";
         }
         else if(src.getText().equals("6"))
         {
            currentFunction += "6";
         }
         else if(src.getText().equals("7"))
         {
            currentFunction += "7";
         }
         else if(src.getText().equals("8"))
         {
            currentFunction += "8";
         }
         else if(src.getText().equals("9"))
         {
            currentFunction += "9";
         }
         else if(src.getText().equals("."))
         {
            currentFunction+=".";
         }
         else if(src.getText().equals("del"))
         {
            if(currentFunction.length()>0)
            {
               currentFunction = currentFunction.substring(0,currentFunction.length()-1);
            }
         }
      }
      
      private void checkFunctions(JButton src)
      {
         //trig
         if(src.getText().equals("Sin("))
         {
            currentFunction+="s(";
         }
         else if(src.getText().equals("Cos("))
         {
            currentFunction+="c(";
         }
         else if(src.getText().equals("Tan("))
         {
            currentFunction+="t(";
         }
         else if(src.getText().equals("ArcSin("))
         {
            currentFunction+="S(";
         }
         else if(src.getText().equals("ArcCos("))
         {
            currentFunction+="C(";
         }
         else if(src.getText().equals("ArcTan("))
         {
            currentFunction+="T(";
         }
         //log
         else if(src.getText().equals("Log(10)"))
         {
            currentFunction+="l(";
         }
         else if(src.getText().equals("Log(2)"))
         {
            currentFunction+="L(";
         }
         else if(src.getText().equals("ln"))
         {
            currentFunction+="n(";
         }
         //parends
         else if(src.getText().equals("("))
         {
            currentFunction+="(";
         }
         else if(src.getText().equals(")"))
         {
            currentFunction+=")";
         }
      }
      
   }
   
   
   
   
   
   
   
   public static void main(String[] args)
   {
      previousFunctions = new ArrayList<String>();
      currentFunction = "";
      View v = new View(new ButtonHandler());
      pen = v.getPen();
      pen.setFont(new Font(null,20,20));
   }
}