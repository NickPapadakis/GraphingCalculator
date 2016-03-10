public class ParserTests {
   
   public static void main(String[] args){
      //System.out.println(Parser.parse("5 + 5 * 5 / 5 - 5") + " ---5");
      //System.out.println(Parser.parse("5 + 5 - 5") + " ---5");
      //System.out.println(Parser.parse("5 * 5 / 5") + " ---5");
      //System.out.println(Parser.parse("5 ^ ( 2 * 3 ) + 8") + "---15633");
      //System.out.println(Parser.parse("2^3 - 3 + 1 + 3 * ((4+4*4)/2) / 5 + -5") + "---7");
      
      //String expression = "5 + x";
      //System.out.println(replaceVariables(22, expression)+"--- 5 + 22") ;
      
      Function f = new Function("2^x - x + 1 + x * ((4+4*4)/2) / 5 + -5");
      System.out.println(f.getValue(6.1645));
   
   }
   
   private static String replaceVariables(double x, String s)
   {
      for(int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i) == 'x')
         {
            s = s.substring(0,i) + x + s.substring(i+1);
         }
      }
      return s;
   }
}