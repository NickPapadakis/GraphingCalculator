import java.awt.Point;

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
      String s = function;
      for(int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i) == 'x')
         {
            s = s.substring(0,i) + x + s.substring(i+1);
         }
      }
      return s;
   }

   public Point calculateIntersection(Function other, double leftXBound, 
      double rightXBound)
   {
      return null;
   }
}
