public class Parser{
   private static int pos;
   private static int c;
   private static String expression;
   public static double parse(String ex){
      pos = -1;
      expression = ex;
      eatChar();
      double v = parseExpression();
      return v;
   }
   private static void eatChar(){
      c = (++pos < expression.length()) ? expression.charAt(pos) : -1;
   }
   private static void eatSpace(){
      while(Character.isWhitespace(c)){
         eatChar();
      }
   }
   private static double parseExpression(){
      double v = parseTerm();
      while(true){
         eatSpace();
         if(c == '+'){
            eatChar();
            v+=parseTerm();
         }else if(c == '-'){
            eatChar();
            v-=parseTerm();
         } else {
            return v;
         }
      }
   }
   private static double parseTerm(){
      double v = parseFactor();
      while(true){
         eatSpace();
         if(c == '/'){
            eatChar();
            c /= parseFactor();
         } else if(c == '*' || c == '('){
            if(c == '*'){
               eatChar();
            }
            v*=parseFactor();
         }else{
            return v;
         }
      }
   }
   private static double parseFactor(){
      double v;
      boolean negate = false;
      eatSpace();
      if(c =='+' || c == '-'){
         negate = c == '-';
         eatChar();
         eatSpace();
      }
      if(c == '('){
         eatChar();
         v = parseExpression();
         if(c == ')'){
            eatChar();
         }
      }
      else{
         int startIndex = pos;
         while((c >='0' && c <='9') || c =='.'){
            eatChar();
         }
         if(pos==startIndex){
            System.out.println("ERROR------------------" + (char)c);
         }
         v = Double.parseDouble(expression.substring(startIndex, pos));
         
      }
      eatSpace();
      if(c == '^'){
         eatChar();
         v = Math.pow(v, parseFactor());
      }
      if(negate){
         v = -v;
      }
      return v;
   }
}
