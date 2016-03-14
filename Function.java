import java.util.LinkedList;

/**
 * A single-variable function represented as a String. This class can calculate 
 * the value of the represented function for a given x and find the inersection 
 * of the function with another function on a closed, continuous domain that 
 * contains only one intersection. To calculate values, Parser is utilized.
 */
public class Function
{
   private static final double E = 1E-10;
   private static final int steps = 1000;
   private String function;

   /**
    * Constructs a function represented by the given String.
    *
    * @param function A string representation of a function based on x.
    */
   public Function(String function)
   {
      this.function = function;
   }
   
   /**
    * Calculates the value of the represented function at the given x value using 
    * Parser with precision of 10^-10.
    * 
    * @param x The x value to calculate the value of the function at.
    * @return The y value of the function for the given x value.
    */
   public double getValue(double x)
   {
      return Parser.parse(function, x);
   }

   /**
    * Calculates the intersection of this Function and another based off of a
    * a range of x values with precision of 10^-20. Only one intersection can 
    * exist between the given x values. If an intersection is not detected, an 
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
      LinkedList<Double> xValues = new LinkedList<Double>();
      LinkedList<Double> thisYValues = new LinkedList<Double>();
      LinkedList<Double> otherYValues = new LinkedList<Double>();

      do
      {
         stepSize = (rightXBound - leftXBound) / steps;
         // Populate arrays
         //System.out.println("left " + leftXBound + " right " + rightXBound);
         for(int i = 0; i < steps; i++)
         {
           double x = leftXBound + stepSize * i;
           xValues.add(i, x);
           thisYValues.add(i, getValue(x));
           otherYValues.add(i, other.getValue(x));
         }
         
         // Search through the LinkedLists to find an exact intersection or 
         // a domain where and intersection occurred
         boolean intersectionDetected = false;
         foundValue = false;
         double originalDiff = 0;
         for(int i = 0; i < steps; i++)
         {
            
            double currentDiff = 
               thisYValues.get(i).doubleValue() 
               - otherYValues.get(i).doubleValue();
            //System.out.println(currentDiff);
            if(Math.abs(currentDiff) < E)
            {
               foundValue = true;
               intersectionDetected = true;
               intersectionXValue = xValues.get(i).doubleValue();
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
               leftXBound = xValues.get(i-1);
               rightXBound = xValues.get(i+1);      
               break;
            }
         }
     
         // If it found no evidence of an intersection, throw an exception
         if(!intersectionDetected)
         {
            throw new IntersectionNotFoundException();
         }
      } while(!foundValue);
      if(intersectionXValue>-E && intersectionXValue<E)
      {
         return 0;
      }
      return intersectionXValue;
   }
}
