package com.ml.toolbox;

import java.io.IOException;

import com.ml.toolbox.markov.internal.MarkovGridProblem;

public class Main
{
	
	public static void main(String[] args)
	{
		MarkovGridProblem problem = new MarkovGridProblem(4);
		
		for (int i = 7; i < 10; i++)
		{
			MarkovGridProblem p = new MarkovGridProblem(i);
			
			ExperimentRunner runner = new ExperimentRunner();
			
			runner.accept(p);
			runner.runExperiments();
			runner.printResults();
			System.out.println(runner.csvResults());
			
		}
		try
		{
			System.in.read();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		QLearningAgent pi = new QLearningAgent();
		
		pi.accept(p);
		
		pi.solve();
		
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
