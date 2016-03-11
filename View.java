import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
public class View extends JFrame
{
   private JPanel p;
   private Graphics pen;
   private ArrayList<JButton> numberPad;
   private ArrayList<JButton> operations;
   private ArrayList<JButton> trig;
   private ArrayList<JButton> log;
   private JButton leftParend, rightParend, decimal, delete, showGraph, addToGraph, hideGraph, clearEquations, findIntersects;
   
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
      decimal.addActionListener(buttons);
      delete.addActionListener(buttons);
      leftParend.addActionListener(buttons);
      rightParend.addActionListener(buttons);
      showGraph.addActionListener(buttons);
      addToGraph.addActionListener(buttons);
      hideGraph.addActionListener(buttons);
      clearEquations.addActionListener(buttons);
      findIntersects.addActionListener(buttons);
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
      temp.setBounds(0,550,100,50);
      operations.add(temp);
      temp = new JButton("-");
      temp.setBounds(100,550,100,50);
      operations.add(temp);
      temp = new JButton("*");
      temp.setBounds(200,550,100,50);
      operations.add(temp);
      temp = new JButton("/");
      temp.setBounds(300,550,100,50);
      operations.add(temp);
      temp = new JButton("=");
      temp.setBounds(400,550,100,50);
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
      decimal.setBounds(0,750,166,50);
      
      delete = new JButton("del");
      delete.setBounds(332,750,166,50);
      
      leftParend = new JButton("(");
      leftParend.setBounds(0,400,250,50);
      rightParend = new JButton(")");
      rightParend.setBounds(250,400,250,50);
      
      showGraph = new JButton("Graph");
      showGraph.setBounds(0,350,100,50);
      addToGraph = new JButton("add to y=");
      addToGraph.setBounds(100,350,100,50);
      hideGraph = new JButton("Hide");
      hideGraph.setBounds(200,350,100,50);
      clearEquations = new JButton("Clear Y =");
      clearEquations.setBounds(300,350,100,50);
      findIntersects = new JButton("Intersect");
      findIntersects.setBounds(400,350,100,50);
      

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
      p.add(decimal);
      p.add(delete);
      p.add(leftParend);
      p.add(rightParend);
      p.add(showGraph);
      p.add(addToGraph);
      p.add(hideGraph);
      p.add(clearEquations);
      p.add(findIntersects);
   }
   public Graphics getPen()
   {
      return pen;
   }
}