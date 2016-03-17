# GraphingCalculator
A simple java-based graphing calculator.

To run:
   javac *.java
   java Model
Syntax Pickiness:
   Close all parends. Always. Otherwise you will get an error.
   Multiplication without the * is not supported unless you use parends. 
      Ex: 5(cos(6)) will work. 5cos(6) will not.
   The variable button includes surrounding parrends so 5(x) works as expected.
   Uniary plus and minus will work.
Graph Pickiness:
   The graph graphs using dots as to avoid any issues with asymptotes.
   The toggle lines button adds in connecting lines but does not support 
   asymptotes.
Intersect Finder Usage:
   1. add functions to the graph
   2. determine the left and right x bounds you want to search for the 
      intersection in
   3. enter the syntax as follows
      firstFunctionIndex|secondFunctionIndex|leftXBound|rightXBound| so for the 
      first and second functions from -1 to 1 you would enter 0|1|-1|1| then 
      click the intersection button.
