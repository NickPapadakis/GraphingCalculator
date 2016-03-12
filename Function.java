import java.util.ArrayList;
/**
    * will hold function. the Parser is very particular 
    * about formatting, especially around variables. Avoid a uniary minus
    * with a variable. ex. -x instead write as -1*x. Also it will call any 
    * number smaller than 10^-10 zero due to double impercision. 
    *
    *
    *
    */
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
      return Parser.parse(function, x);
   }

   /**
   * Calculates the intersection of this Function and another based off of a
   * a range of x values. Only one intersection can exist between the given
   * x values. If an intersection is not detected, an 
   * IntersectionNotFoundException will be thrown.
   *
   * @param other The other Function to find an intersection with.
   * @param leftXBound The left x bound of the domain to look for an 
   * intersection.
   * @param rightXBound The right x bound of the domain to look for an 
   * intersection.
   * @return The x value where the intersection occurs.
   * @throws IntersectionNotFoundException Throws this exception if there is no 
   * intersection between the given x values;
   */
   public double calculateIntersection(Function other, double leftXBound, 
      double rightXBound) throws IntersectionNotFoundException
   {
      double stepSize = (rightXBound - leftXBound) / steps;
      double intersectionXValue = 0;
      boolean foundValue = false;
      ArrayList<Double> xValues = new ArrayList<Double>();
      ArrayList<Double> thisYValues = new ArrayList<Double>();
      ArrayList<Double> otherYValues = new ArrayList<Double>();

      do
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
         foundValue = false;
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
     
         // If it found no evidence of an intersection, throw an exception
         if(!intersectionDetected)
         {
            throw new IntersectionNotFoundException();
         }
      } while(!foundValue);

      return intersectionXValue;
   }
}
