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
         boolean small = false;
         while((c >='0' && c <='9') || c =='.'){
            eatChar();
         }
         if(pos==startIndex){
            throw new SyntaxException("Error Char:" + (char)c);
         }
         try
         {
            v = Double.parseDouble(expression.substring(startIndex, pos));
         }
         catch(NumberFormatException ne)
         {
            throw new SyntaxException("Error Char:" + (char)c);
         }
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
      //System.out.println(s);
      String replace = s.substring(j+2);
      boolean found = false;
      int parendCount = 1;
      int index = 0;
      for(int i = 0; i < replace.length() || !found; i++){
         //System.out.println(replace.charAt(i));
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
      //System.out.println("char at" + s.charAt(index));
      //System.out.println(j + " "  + index +" " + s);
      String ex = s.substring(j+2,j+index+1);
      //System.out.println(ex);
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
      String stringVal = handleSciNotation(v);
      return s.substring(0,j) + stringVal + s.substring(j+index+3);
   }
   public static String handleSciNotation(double v){
      String sval = String.valueOf(v);
      int index = sval.indexOf('E');
      if(index == -1){
         return sval;
      }
      String rawVal = sval.substring(0,index);
      String exVal = sval.substring(index+1);
      String pref = "";
      if(rawVal.charAt(0) == '-')
      {
         rawVal = rawVal.substring(1);
         pref = "-";
      }
      //System.out.println(rawVal);
      //System.out.println(exVal);
      
      int exponent = Integer.parseInt(exVal);
      if(exponent<=-10){
         //System.out.println("a");
         return "0";
      }
      rawVal = rawVal.substring(0,1) + rawVal.substring(2);
      if(exponent<0){
         for(int i = 1; i < -1*exponent; i++){
            rawVal = "0" + rawVal;
         }
         //System.out.println(rawVal);
         return pref+"."+rawVal;
      }
      if(exponent>0){
         if(rawVal.length()-1 < exponent){
            for(int i = 0; i <= exponent-rawVal.length(); i++){
               rawVal = rawVal + "0";
            }
            //System.out.println(rawVal);
            return pref+rawVal;
         }
         return pref+rawVal.substring(0,exponent+1) + "." + rawVal.substring(exponent);
      }
      else{
         System.out.println("Error in handlescinotation");
         return "ERROR";
      }
      
      
      
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
   public static String logBaseTwo(int i, String s)
   {
      return replaceFunction(i,s,Math.log(findArgVal(i,s))/Math.log(2));
   }
   public static String logBase10(int i, String s)
   {
      return replaceFunction(i,s,Math.log10(findArgVal(i,s)));
   }
   public static String naturalLog(int i, String s)
   {
      return replaceFunction(i,s,Math.log(findArgVal(i,s)));
   }
}
