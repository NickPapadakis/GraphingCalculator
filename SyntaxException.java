/**
 * An exception that is thrown when the input does not follow correct syntax.
 */
public class SyntaxException extends RuntimeException
{
   private String text;
   /**
    * Constructs a SyntaxException.
    * @param s - the error message to be given.
    */
   public SyntaxException(String s)
   {
      super(s);
      this.text = s;
   }
   /**
    * returns the text attached witht the syntax exception.
    * @return String - the error message.
    */
   public String getText()
   {
      return text;
   }
}