package com.ml.toolbox;


public class Main
{
	
	public static void main(String[] args)
	{
		
		int[][] arr = new int[5][6];
		
		for (int y = 0; y < 5; y++)
		{
			for (int x = 0; x < 6; x++)
			{
				arr[y][x] = y;
				System.out.print(y + " ");
			}
			System.out.println();
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
