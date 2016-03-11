import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
public class Model
{
   private static ArrayList<Function> userFunctions;
   private static String currentFunction;
   
   public static void draw(Graphics pen)
   {
      
   }
   
   
   
   
   private static class ButtonHandler implements ActionListener
   {
      //incomplete, here is where the buttons call functions
      public void actionPerformed(ActionEvent e) {
			JButton src = (JButton)(e.getSource());
         if(src.getText().equals("+"))
         {
            System.out.println("+");
         }
      }
   }
   
   
   
   
   
   
   
   public static void main(String[] args)
   {
      View v = new View(new ButtonHandler());
   }
}