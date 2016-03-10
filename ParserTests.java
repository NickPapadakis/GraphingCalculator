public class ParserTests{
   
   public static void main(String[] args){
      System.out.println(Parser.parse("5 + 5 * 5 / 5 - 5") + " ---5");
      System.out.println(Parser.parse("5 + 5 - 5") + " ---5");
      System.out.println(Parser.parse("5 * 5 / 5") + " ---5");
      System.out.println(Parser.parse("5 ^ ( 2 * 3 ) + 8") + "---15633");
      System.out.println(Parser.parse("2^3 - 3 + 1 + 3 * ((4+4*4)/2) / 5 + -5") + "---7");
   }
}