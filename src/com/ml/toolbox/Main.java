package com.ml.toolbox;

import com.ml.toolbox.salesman.TravelingSalesmanProblem;

public class Main
{
	
	public static void main(String[] args)
	{
		
		TravelingSalesmanProblem problem = new TravelingSalesmanProblem(10, 100, 100);
		System.out.println("problem : " + problem.toString());
		problem.geneticPopulationSolution();
		
	}
	
}
