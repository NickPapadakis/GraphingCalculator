import java.util.ArrayList;

public class Function
{
   private static final double E = 0.00000000001;
   private static final int steps = 10;
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

   public double calculateIntersection(Function other, double leftXBound, 
      double rightXBound)
   {
      double stepSize = (rightXBound - leftXBound) / steps;
      double intersectionXValue = 0;
      ArrayList<Double> xValues = new ArrayList<Double>();
      ArrayList<Double> thisYValues = new ArrayList<Double>();
      ArrayList<Double> otherYValues = new ArrayList<Double>();

      while(true)
      {
         // Populate arrays
         for(int i = 0; i < steps; i++)
         {
           double x = leftXBound + stepSize * i;
           xValues.add(i, x);
           thisYValues.add(i, getValue(x));
           otherYValues.add(i, other.getValue(x));
         }

         // Search through the ArrayLists to find an exact intersection or 
         // a domain where and intersection occurred
         boolean intersectionDetected = false;
         boolean foundValue = false;
         for(int i = 0; i < steps; i++)
         {
            double originalDiff = 0;
            double currentDiff = 
               thisYValues.get(i).doubleValue() 
               - otherYValues.get(i).doubleValue();
            if(currentDiff < E)
            {
               foundValue = true;
               intersectionXValue = thisYValues.get(i).doubleValue();
               break;
            }
            if(i == 0)
            {
               originalDiff = currentDiff;
            }
            if((originalDiff > 0  && currentDiff < 0) ||
               (originalDiff < 0 && currentDiff > 0))
            {
               // If it found the bounds of a domain where an intersection
               // occurred, start the search over in that domain
               intersectionDetected = true;
               leftXBound = xValues.get(i);
               rightXBound = xValues.get(i + 1);      
               break;
            }
         }
     
         // If it found the exact intersection in the ArrayList, stop looking 
         if(foundValue)
         {
            System.out.println(intersectionXValue);
            break;
         }
         // If it found no evidence of an intersection, stop looking
         else if(!intersectionDetected)
         {
            System.out.println("No intersection detected");
            break;
         }
      }
      return intersectionXValue;
   }
}
