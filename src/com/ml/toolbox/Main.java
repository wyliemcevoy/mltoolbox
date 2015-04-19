package com.ml.toolbox;

import com.ml.toolbox.markov.internal.PolicyAgent;
import com.ml.toolbox.markov.internal.ValueItteration;
import com.ml.toolbox.markov.internal.MarkovGridProblem;

public class Main
{
	
	public static void main(String[] args)
	{
		
		MarkovGridProblem p = new MarkovGridProblem();
		ValueItteration vm = new ValueItteration();
		vm.accept(p);
		vm.solve();
		
		System.out.println(p.getPolicy());
		
		PolicyAgent agent = new PolicyAgent(p);
		
		for (int i = 0; i < 20; i++)
		{
			agent.runPolicy();
			System.out.println(agent.getResultString());
			agent.reset();
		}
		
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
