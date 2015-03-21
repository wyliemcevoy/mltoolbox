package com.ml.toolbox;

import com.ml.toolbox.model.OrderSolution;
import com.ml.toolbox.salesman.TravelingSalesmanProblem;

public class Main
{
	
	public static void main(String[] args)
	{
		
		TravelingSalesmanProblem problem = new TravelingSalesmanProblem(100, 500, 500);
		System.out.println("problem : " + problem.toString());
		
		for (int i = 0; i < 10; i++)
		{
			
			OrderSolution solution = problem.geneticPopulationSolution();
			
			ConsoleFrame cf = new ConsoleFrame(520, 520, problem.getLocations(), solution);
		}
		
	}
	
}
