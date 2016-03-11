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
            v /= parseFactor();
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
      //System.out.println(v);
      return v;
   }
   
   private static double findArgVal(int j, String s)
   {
      String replace = s.substring(j+2);
      boolean found = false;
      int parendCount = 1;
      int index = 0;
      for(int i = 0; i < replace.length() || !found; i++){
         if(parendCount == 0){
            found = true;
            index = i;
            break;
         }
         if(replace.charAt(i) == '('){
            parendCount++;
         }else if(replace.charAt(i) == ')'){
            parendCount--;
         }
      }
      String ex = s.substring(j+1,index+1);
      double val =(parse(ex));
      return val;
   }
   private static String replaceFunction(int j, String s, double v)
   {
      String replace = s.substring(j+2);
      boolean found = false;
      int parendCount = 1;
      int index = 0;
      for(int i = 0; i < replace.length() || !found; i++){
         if(replace.charAt(i) == '('){
            parendCount++;
         }else if(replace.charAt(i) == ')'){
            parendCount--;
            if(parendCount == 0){
               found = true;
               index = i;
               break;
            }
         }
      }
      //System.out.println(s.substring(index+3));
      return s.substring(0,j) + v + s.substring(index+3);
   }
   
   public static String sin(int i, String s)
   {  
      return replaceFunction(i,s,Math.sin(findArgVal(i,s)));
      
   }
   public static String cos(int i, String s)
   {
      return replaceFunction(i,s,Math.cos(findArgVal(i,s)));
   }
   public static String tan(int i, String s)
   {
      return replaceFunction(i,s,Math.tan(findArgVal(i,s)));
   }
   public static String arcsin(int i, String s)
   {
      return replaceFunction(i,s,Math.asin(findArgVal(i,s)));
   }
   public static String arccos(int i, String s)
   {
      return replaceFunction(i,s,Math.acos(findArgVal(i,s)));
   }
   public static String arctan(int i, String s)
   {
      return replaceFunction(i,s,Math.atan(findArgVal(i,s)));
   }
}
