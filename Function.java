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
      // first replace special characters, then send it to the
      // parser to be evaluated.
      return Parser.parse(replaceVariables(x));
   }

   public String replaceVariables(double x)
   {
      //special character codes to look for and replace.
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
      
      //This loop replaces 'x' with the double x
      for(int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i) == 'x')
         {
            s = s.substring(0,i) + x + s.substring(i+1);
            //System.out.println(s);
         }
      }
      
      //this loop replaces the predefined functions with numbers.
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
      //figures out what function to preform.
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
