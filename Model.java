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
   private static boolean showGraph;
   private static double xScale, yScale;
   private static void draw()
   {
      pen.setColor(Color.WHITE);
      pen.fillRect(0,0,600,320);
      if(showGraph)
      {
         int r,g,b;
         r = g = b = 0;
         for(Function f : userFunctions)
         {
            Color c = new Color(r,g,b);
            graph(f,c);
            //potential color shifting but needs some work
            if(r+10<=255)
            {
               r+=10;
            }else if(g+10<=255)
            {
               g+=10;
            }
            else
            {
               b+=10;
            }
         }
         pen.setColor(Color.BLACK);
         pen.drawLine(0,150,500,150);
         pen.drawLine(250,0,250,300);
         return;
      }
      else
      {
         pen.setColor(Color.BLACK);
         pen.drawString(currentFunction,0,300);
         for(int i = 0; i < previousFunctions.size();i++)
         {
               pen.drawString(previousFunctions.get(i),0,280-(20*i));      
         }
      }
   }
   private static void graph(Function f, Color c)
   {
      pen.setColor(c);
      for(int i = -250; i < 250; i++)
      {
         try
         {
            double y = f.getValue(i/xScale);
            int xPixel = 250 + i;
            int yPixel = 150 - ((int)(y*yScale));
            if(yPixel<=300 && yPixel>=0)
            {
               pen.fillRect(xPixel,yPixel,2,2);
            }
         }
         catch(Exception e)
         {
            //System.out.println("err");
            //function either does not exist at this x value, 
            //or is incorrectly formatted
         }
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
         else if(src.getText().equals("^"))
         {
            currentFunction += " ^ ";
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
         checkGraphs(src);
         
         //System.out.println(currentFunction);
         draw();
      }
      private void checkGraphs(JButton src)
      {
         if(src.getText().equals("x"))
         {
            currentFunction += "(x)";
         }
         else if(src.getText().equals("Graph"))
         {
            showGraph = true;
         }
         else if(src.getText().equals("add to y="))
         {
            userFunctions.add(new Function(currentFunction));
         }
         else if(src.getText().equals("Hide"))
         {
           showGraph = false;
         }
         else if(src.getText().equals("Clear Y ="))
         {
            userFunctions.clear();
         }
         else if(src.getText().equals("Intersect"))
         {
            //add Intersection Interface
         }
         else if(src.getText().equals("x scale +"))
         {
            xScale++;
         }
         else if(src.getText().equals("x scale -"))
         {
            
            xScale--;
            if(xScale<=0)
            {
               xScale = 1;
            }
         }
         else if(src.getText().equals("y scale +"))
         {
            yScale--;
            if(yScale<=0)
            {
               yScale = 1;
            }
         }
         else if(src.getText().equals("y scale -"))
         {
            yScale++;
         }
         
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
               currentFunction = currentFunction.substring(0,
                                                   currentFunction.length()-1);
            }else{
               previousFunctions.clear();
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
      xScale = 10;
      yScale = 10;
      userFunctions = new ArrayList<Function>();
      previousFunctions = new ArrayList<String>();
      currentFunction = "";
      View v = new View(new ButtonHandler());
      pen = v.getPen();
      pen.setFont(new Font(null,20,20));
   }
}