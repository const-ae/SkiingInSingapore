# SkiingInSingapore
My solution for the [coding challenge](http://geeks.redmart.com/2015/01/07/skiing-in-singapore-a-coding-diversion/) published by RedMart.

The solution is written entirely in Scala.

## Running the code

The main method is in the com.cae.test.Experiment class. Probably the simplest way to run the project would be to import it in IntelliJ IDEA and run from there.

## Possible Improvements

#####The central part of the algorithm is written as a recursive function
This means for long paths the code will throw an StackOverFlowException. The provided input did not lead to such problems but for bigger inputs one will probably have to refactor this part.

#####Performance
The code executes reasonable fast (about 2 minutes multithreaded and about 4 minutes on a single core) for this challenge. But if it were necessary one could improve the runtime by checking if the subsequent paths from an intermediate cell have already been calculated and simply prepend the previous path. This should heavily reduce the necessary computation time.
