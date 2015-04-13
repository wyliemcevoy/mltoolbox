package com.ml.toolbox;

import com.ml.toolbox.markov.internal.GridValueMaximization;
import com.ml.toolbox.markov.internal.MarkovGridProblem;

public class Main
{
	
	public static void main(String[] args)
	{
		
		MarkovGridProblem p = new MarkovGridProblem();
		GridValueMaximization vm = new GridValueMaximization();
		vm.accept(p);
		vm.solve();
		
		/*
		TravelingSalesmanProblem problem = new TravelingSalesmanProblem(100, 500, 500);
		System.out.println("problem : " + problem.toString());
		
		for (int i = 0; i < 10; i++)
		{
			OrderSolution solution = new OrderSolution(3);
			solution.randomize();
			System.out.println(solution);
		}
		
		
		for (int i = 0; i < 10; i++)
		{
			
			OrderSolution solution = problem.geneticPopulationSolution();
			
			ConsoleFrame cf = new ConsoleFrame(520, 520, problem.getLocations(), solution);
		}
		*/
	}
	
}
