import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
/**
 * A class to provide a GUI for the calculator and a way to input functions.
 * 
 */
public class View extends JFrame
{
   private JPanel p;
   private ArrayList<JButton> numberPad;
   private ArrayList<JButton> operations;
   private ArrayList<JButton> trig;
   private ArrayList<JButton> log;
   private ArrayList<JButton> scalars;
   private JButton leftParend, rightParend, decimal, delete, showGraph, 
                    addToGraph, hideGraph, clearEquations, findIntersects, 
                    variable, xScalePlus, xScaleMinus, yScalePlus, yScaleMinus,
                    clear, seperator, toggleLines;
   /**
    * Construts the calculator GUI.
    *
    *
    * @param buttons an actions listener to give functionality to the buttons.
    */
   public View(ActionListener buttons)
   {
      super("Graphing Calculator");
      p = new JPanel();
      p.setPreferredSize(new Dimension(500,800));
      add(p);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
      p.setLayout(null);
      createButtons();
      addButtons();
      addActionListenerToButtons(buttons);
      p.repaint();
   }
   private void addActionListenerToButtons(ActionListener buttons)
   {
      for(JButton b : numberPad)
      {
         b.addActionListener(buttons);
      }
      for(JButton b : operations)
      {
         b.addActionListener(buttons);
      }
      for(JButton b : trig)
      {
         b.addActionListener(buttons);
      }
      for(JButton b : log)
      {
         b.addActionListener(buttons);
      }
      for(JButton b : scalars)
      {
         b.addActionListener(buttons);
      }
      decimal.addActionListener(buttons);
      delete.addActionListener(buttons);
      clear.addActionListener(buttons);
      seperator.addActionListener(buttons);
      leftParend.addActionListener(buttons);
      rightParend.addActionListener(buttons);
      toggleLines.addActionListener(buttons);
      showGraph.addActionListener(buttons);
      addToGraph.addActionListener(buttons);
      hideGraph.addActionListener(buttons);
      clearEquations.addActionListener(buttons);
      findIntersects.addActionListener(buttons);
      variable.addActionListener(buttons);
   }
   private void createButtons()
   {
      numberPad = new ArrayList<JButton>();
      for(int i = 0; i <10; i++)
      {
         numberPad.add(new JButton(Integer.toString(i)));
      }
      numberPad.get(0).setBounds(166, 750, 166, 50);
      numberPad.get(1).setBounds(0, 600, 166, 50);
      numberPad.get(2).setBounds(166, 600, 166, 50);
      numberPad.get(3).setBounds(332, 600, 166, 50);
      numberPad.get(4).setBounds(0, 650, 166, 50);
      numberPad.get(5).setBounds(166, 650, 166, 50);
      numberPad.get(6).setBounds(332, 650, 166, 50);
      numberPad.get(7).setBounds(0, 700, 166, 50);
      numberPad.get(8).setBounds(166, 700, 166, 50);
      numberPad.get(9).setBounds(332, 700, 166, 50);
      operations = new ArrayList<JButton>();
      JButton temp = new JButton("+");
      temp.setBounds(0,550,83,50);
      operations.add(temp);
      temp = new JButton("-");
      temp.setBounds(83,550,83,50);
      operations.add(temp);
      temp = new JButton("*");
      temp.setBounds(166,550,83,50);
      operations.add(temp);
      temp = new JButton("/");
      temp.setBounds(249,550,83,50);
      operations.add(temp);
      temp = new JButton("^");
      temp.setBounds(332,550,83,50);
      operations.add(temp);
      temp = new JButton("=");
      temp.setBounds(415,550,83,50);
      operations.add(temp);
      
      trig = new ArrayList<JButton>();
      temp = new JButton("Sin(");
      temp.setBounds(0,500,83,50);
      trig.add(temp);
      temp = new JButton("Cos(");
      temp.setBounds(83,500,83,50);
      trig.add(temp);
      temp = new JButton("Tan(");
      temp.setBounds(166,500,83,50);
      trig.add(temp);
      temp = new JButton("ArcSin(");
      temp.setBounds(249,500,83,50);
      trig.add(temp);
      temp = new JButton("ArcCos(");
      temp.setBounds(332,500,83,50);
      trig.add(temp);
      temp = new JButton("ArcTan(");
      temp.setBounds(415,500,83,50);
      trig.add(temp);
      
      scalars = new ArrayList<JButton>();
      temp = new JButton("x scale +");
      temp.setBounds(0,320,125,25);
      scalars.add(temp);
      temp = new JButton("x scale -");
      temp.setBounds(125,320,125,25);
      scalars.add(temp);
      temp = new JButton("y scale +");
      temp.setBounds(250,320,125,25);
      scalars.add(temp);
      temp = new JButton("y scale -");
      temp.setBounds(375,320,125,25);
      scalars.add(temp);
      
      log = new ArrayList<JButton>();
      temp = new JButton("Log(2)");
      temp.setBounds(0,450,166,50);
      log.add(temp);
      temp = new JButton("ln");
      temp.setBounds(166,450,166,50);
      log.add(temp);
      temp = new JButton("Log(10)");
      temp.setBounds(332,450,166,50);
      log.add(temp);
      
      decimal = new JButton(".");
      decimal.setBounds(83,750,83,50);
      delete = new JButton("del");
      delete.setBounds(415,750,83,50);
      seperator = new JButton("|");
      seperator.setBounds(0,750,83,50);
      clear = new JButton("clear");
      clear.setBounds(332,750,83,50);
      
      leftParend = new JButton("(");
      leftParend.setBounds(0,400,166,50);
      rightParend = new JButton(")");
      rightParend.setBounds(332,400,166,50);
      toggleLines = new JButton("Toggle dots");
      toggleLines.setBounds(166,400,166,50);
      
      showGraph = new JButton("Graph");
      showGraph.setBounds(0,350,83,50);
      addToGraph = new JButton("add to y=");
      addToGraph.setFont(new Font(null,10,10));
      addToGraph.setBounds(83,350,83,50);
      hideGraph = new JButton("Hide");
      hideGraph.setBounds(166,350,83,50);
      clearEquations = new JButton("Clear Y =");
      clearEquations.setFont(new Font(null,0,10));
      clearEquations.setBounds(249,350,83,50);
      findIntersects = new JButton("Intersect");
      findIntersects.setBounds(332,350,83,50);
      variable = new JButton("x");
      variable.setBounds(415,350,83,50);
      
      

   }
   private void addButtons()
   {
      for(JButton b : numberPad)
      {
         p.add(b);
      }
      for(JButton b : operations)
      {
         p.add(b);
      }
      for(JButton b : trig)
      {
         p.add(b);
      }
      for(JButton b : log)
      {
         p.add(b);
      }
      for(JButton b : scalars)
      {
         p.add(b);
      }
      p.add(decimal);
      p.add(delete);
      p.add(seperator);
      p.add(clear);
      p.add(leftParend);
      p.add(rightParend);
      p.add(toggleLines);
      p.add(showGraph);
      p.add(addToGraph);
      p.add(hideGraph);
      p.add(clearEquations);
      p.add(findIntersects);
      p.add(variable);
   }
   /**
    * A method to provide a way to draw on the calculators screen.
    *
    * @return Graphics object to draw on the screen of the GUI.
    */
   public Graphics getPen()
   {
      return p.getGraphics();
   }
}