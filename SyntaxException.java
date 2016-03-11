public class SyntaxException extends RuntimeException
{
   private String text;
   public SyntaxException(String s)
   {
      super(s);
      this.text = s;
   }
   public String getText()
   {
      return text;
   }
}