public class ParserTests {
   
   public static void main(String[] args){
      //System.out.println(Parser.parse("5 + 5 * 5 / 5 - 5") + " ---5");
      //System.out.println(Parser.parse("5 + 5 - 5") + " ---5");
      //System.out.println(Parser.parse("5 * 5 / 5") + " ---5");
      //System.out.println(Parser.parse("5 ^ ( 2 * 3 ) + 8") + "---15633");
      //System.out.println(Parser.parse("2^3 - 3 + 1 + 3 * ((4+4*4)/2) / 5 + -5") + "---7");
      //
      //String expression = "5 + x";
      //System.out.println(replaceVariables(22, expression)+"--- 5 + 22") ;
      //
      //Function f = new Function("2^x - x + 1 + x * ((4+4*4)/2) / 5 + -5");
      //System.out.println(f.getValue(6.1645));
      //
      //Function f = new Function("s(x)"); //sin of 
      //System.out.println(f.getValue(5));
      // f = new Function("c(x) + 5"); //cos of 
      //System.out.println(f.getValue(5));
      // f = new Function("t(x) + 5"); //tan of 
      //System.out.println(f.getValue(5));
      // f = new Function("S(x) + 5"); //arcsin of 
      //System.out.println(f.getValue(.5));
      // f = new Function("C(x) + 5"); //arccos of 
      //System.out.println(f.getValue(.5));
      //f = new Function("T(x) + 5"); //arctan of 
      //System.out.println(f.getValue(.5));
      //Function f = new Function("s(x)");
      //System.out.println(f.getValue(Math.PI));
      //System.out.println(f.getValue(Math.PI/2));
      //System.out.println(f.getValue(Math.PI/4));
      //
      //Function f = new Function("l(x)");
      //System.out.println(f.getValue(10));
      //f = new Function("L(x)");
      //System.out.println(f.getValue(10));
      //f = new Function("n(x)");
      //System.out.println(f.getValue(10000));
      //for(int i = 1; i<1000000; i++){
      //   System.out.println(f.getValue(i));
      //}
      
      Function f = new Function("s(s(x))");
      System.out.println(f.getValue(0));
      Function g = new Function("t(t(x))");
      System.out.println(g.getValue(0));
      //System.out.println(f.calculateIntersection(g, 10,  20));
      System.out.println(f.calculateIntersection(g, -1,  1));
      //System.out.println(Parser.parse("6.1E-6",0));
      
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
