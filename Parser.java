import java.util.ArrayList;
public final class Parser{
   private static final double E = 1E-10;
   private static int pos;
   private static int c;
   private static String expression;
   public static double parse(String ex, double x){
      //parse variables and special equations first;
      ex = replaceVariables(x, ex);
      pos = -1;
      expression = ex;
      eatChar();
      double v = parseExpression();
      //if its super close to zero, it is zero
      if(v>-E && v<E)
      {
         return 0;
      }
      return v;
   } 
   private static String replaceVariables(double x, String function)
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
      //replace x
      for(int i = 0; i < s.length(); i++)
      {
         if(s.charAt(i) == 'x')
         {
            s = s.substring(0,i) + x + s.substring(i+1);
         }
      }
      
      //replace special functions with number values
      for(int i = 0; i < s.length(); i++)  //two loops because variable must 
                                           //be replaced first
      {
         if(supportedFunctions.contains(s.charAt(i)))
         {
            s = replaceWithFunction(i, s);
         }
      }
      //System.out.println(s);
      return s;
   }
   private static String replaceWithFunction(int i, String s)
   {
      //figures out what function to preform.
      char fchar = s.charAt(i); //trig
      if(fchar == 's')
      {
         s = sin(i,s);
      }
      else if(fchar == 'c')
      {
         s = cos(i,s);
      }
      else if(fchar == 't')
      {
         s = tan(i,s);
      }
      else if(fchar == 'S')
      {
         s = arcsin(i,s);
      }
      else if(fchar == 'C')
      {
         s = arccos(i,s);
      }
      else if(fchar == 'T')
      {
         s = arctan(i,s);
      }
      else if(fchar == 'L') //log
      {
         s = logBaseTwo(i,s);
      }
      else if(fchar == 'l')
      {
         s = logBase10(i,s);
      }
      else if(fchar == 'n')
      {
         s = naturalLog(i,s);
      }
      return s;
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
         while((c >='0' && c <='9') || c =='.' || c == 'E' || c == '-'){
            eatChar();
         }
         if(pos==startIndex){
            throw new SyntaxException("Error Char:" + (char)c);
            //return "Error Char:" + (char)c;
         }
         try
         {
            v = Double.parseDouble(expression.substring(startIndex, pos));
         }
         catch(NumberFormatException ne)
         {
            throw new SyntaxException("Error Char:" + (char)c);
            //return "Error Char:" + (char)c;
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
      String ex = s.substring(j+2,j+index+1);
      double val =(parse(ex, 0));
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
      //String stringVal = handleSciNotation(v);
      return s.substring(0,j) + v + s.substring(j+index+3);
   }  
   //this might be able to be removed, needs some testing first though
   private static String handleSciNotation(double v){
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
      
      int exponent = Integer.parseInt(exVal);
      if(exponent<=-10){
         return "0";
      }
      rawVal = rawVal.substring(0,1) + rawVal.substring(2);
      if(exponent<0){
         for(int i = 1; i < -1*exponent; i++){
            rawVal = "0" + rawVal;
         }
         return pref+"."+rawVal;
      }
      if(exponent>0){
         if(rawVal.length()-1 < exponent){
            for(int i = 0; i <= exponent-rawVal.length(); i++){
               rawVal = rawVal + "0";
            }
            return pref+rawVal;
         }
         return pref+rawVal.substring(0,exponent+1) + "." 
                     + rawVal.substring(exponent);
      }
      else{
         System.out.println("Error in handlescinotation");
         return "ERROR";
      }      
   }   
   private static String sin(int i, String s)
   {  
      return replaceFunction(i,s,Math.sin(findArgVal(i,s)));
      
   }
   private static String cos(int i, String s)
   {
      return replaceFunction(i,s,Math.cos(findArgVal(i,s)));
   }
   private static String tan(int i, String s)
   {
      return replaceFunction(i,s,Math.tan(findArgVal(i,s)));
   }
   private static String arcsin(int i, String s)
   {
      return replaceFunction(i,s,Math.asin(findArgVal(i,s)));
   }
   private static String arccos(int i, String s)
   {
      return replaceFunction(i,s,Math.acos(findArgVal(i,s)));
   }
   private static String arctan(int i, String s)
   {
      return replaceFunction(i,s,Math.atan(findArgVal(i,s)));
   }
   private static String logBaseTwo(int i, String s)
   {
      return replaceFunction(i,s,Math.log(findArgVal(i,s))/Math.log(2));
   }
   private static String logBase10(int i, String s)
   {
      return replaceFunction(i,s,Math.log10(findArgVal(i,s)));
   }
   private static String naturalLog(int i, String s)
   {
      return replaceFunction(i,s,Math.log(findArgVal(i,s)));
   }
}
