import java.awt.Point;
import java.util.ArrayList;
public class Function
{
   private String function;

   public Function(String function)
   {
      this.function = function;
   }

   public double getValue(double x)
   {
      return Parser.parse(replaceVariables(x));
   }
   private String replaceVariables(double x)
   {
      ArrayList<Character> supportedFunctions = new ArrayList<Character>();
      supportedFunctions.add('s'); //sin
      supportedFunctions.add('c'); //cos
      supportedFunctions.add('t'); //tan
      supportedFunctions.add('S'); //arcsin
      supportedFunctions.add('C'); //arccos
      supportedFunctions.add('T'); //arctan
      supportedFunctions.add('L'); //arctan
      supportedFunctions.add('l'); //arctan
      supportedFunctions.add('n'); //arctan
      String s = function;
      //System.out.println(s);
      for(int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i) == 'x')
         {
            s = s.substring(0,i) + x + s.substring(i+1);
         }
      }
      for(int i = 0; i < s.length(); i++)  //two loops because variable must be replaced first
      {
         if(supportedFunctions.contains(s.charAt(i)))
         {
            s = replaceWithFunction(i, s);
         }
      }
      //System.out.println(s);
      return s;
   }
   private String replaceWithFunction(int i, String s)
   {
      char fchar = s.charAt(i); //trig
      if(fchar == 's')
      {
         s = Parser.sin(i,s);
      }
      else if(fchar == 'c')
      {
         s = Parser.cos(i,s);
      }
      else if(fchar == 't')
      {
         s = Parser.tan(i,s);
      }
      else if(fchar == 'S')
      {
         s = Parser.arcsin(i,s);
      }
      else if(fchar == 'C')
      {
         s = Parser.arccos(i,s);
      }
      else if(fchar == 'T')
      {
         s = Parser.arctan(i,s);
      }
      else if(fchar == 'L') //log
      {
         s = Parser.logBaseTwo(i,s);
      }
      else if(fchar == 'l')
      {
         s = Parser.logBase10(i,s);
      }
      else if(fchar == 'n')
      {
         s = Parser.naturalLog(i,s);
      }
      return s;
   }

   public Point calculateIntersection(Function other, double leftXBound, 
      double rightXBound)
   {
      return null;
   }
}
