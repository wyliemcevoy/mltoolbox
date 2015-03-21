package com.ml.toolbox.salesman;

import java.util.ArrayList;
import java.util.Random;

import com.ml.toolbox.model.OrderSolution;
import com.ml.toolbox.model.Population;

public class TravelingSalesmanProblem
{
	private ArrayList<Location> locations;
	private int x, y;
	private int numLocations;
	private Random rand;
	private Population population;
	
	public TravelingSalesmanProblem(int numLocations, int x, int y)
	{
		this.locations = new ArrayList<Location>();
		this.x = x;
		this.y = y;
		this.numLocations = numLocations;
		rand = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < numLocations; i++)
		{
			locations.add(new Location(rand.nextInt(x), rand.nextInt(y)));
		}
		
		this.population = new Population(numLocations, 100, .1);
	}
	
	public double evaluate(int[] solution)
	{
		if (solution.length != numLocations)
		{
			return -1;
		} else
		{
			double cost = 0;
			
			for (int i = 1; i < numLocations; i++)
			{
				Location from = locations.get(solution[i - 1]);
				Location to = locations.get(solution[i]);
				cost += from.distanceTo(to);
			}
			return cost;
		}
	}
	
	public void evaluate(OrderSolution orderSolution)
	{
		double cost = 0;
		
		ArrayList<Integer> solution = orderSolution.toOrderedList();
		
		for (int i = 1; i < numLocations; i++)
		{
			
			Location from = locations.get(solution.get(i - 1) - 1);
			Location to = locations.get(solution.get(i) - 1);
			cost += from.distanceTo(to);
		}
		
		orderSolution.setScore(cost);
	}
	
	@Override
	public String toString()
	{
		String build = "";
		
		for (Location location : locations)
		{
			build += location.toString() + " ";
		}
		
		return build;
	}
	
	public int[] createRandomSolution()
	{
		int[] build = new int[numLocations];
		
		for (int i = 0; i < numLocations; i++)
		{
			build[i] = i;
		}
		
		for (int i = 0; i < numLocations; i++)
		{
			
			// SWAP
			
			int s = rand.nextInt(numLocations - 1);
			int j = rand.nextInt(numLocations - 1);
			
			int a = build[s];
			int b = build[j];
			
			build[j] = a;
			build[s] = b;
			
		}
		
		return build;
	}
	
	public String solutionToString(int[] solution)
	{
		String build = "[";
		for (int i = 0; i < solution.length; i++)
		{
			build += solution[i];
			if (i < solution.length - 1)
			{
				build += ",";
			}
		}
		build += "]";
		return build;
	}
	
	public int[] swapRandom(int[] solution)
	{
		
		return swap(rand.nextInt(solution.length), rand.nextInt(solution.length), solution);
	}
	
	public int[] swapRandomClone(int[] solution)
	{
		int[] clone = new int[solution.length];
		
		for (int i = 0; i < solution.length; i++)
		{
			clone[i] = solution[i];
		}
		return swapRandom(clone);
	}
	
	public int[] swap(int i, int j, int[] solution)
	{
		int a = solution[i];
		int b = solution[j];
		
		solution[j] = a;
		solution[i] = b;
		
		return solution;
	}
	
	public void geneticSolution()
	{
		System.out.println("problem : " + this.toString());
		
		int[] solution = createRandomSolution();
		
		int itt = 0;
		double cost = evaluate(solution);
		System.out.print(solutionToString(solution) + " Cost : " + evaluate(solution));
		
		while (cost > 50 && itt < 500)
		{
			int[] newSolution = swapRandomClone(solution);
			
			double newCost = evaluate(newSolution);
			
			if (newCost < cost)
			{
				solution = newSolution;
				cost = newCost;
				System.out.println("new solution : " + solutionToString(solution) + " Cost : " + cost);
			} else
			{
				System.out.print(".");
			}
			itt++;
		}
		
	}
	
	public OrderSolution geneticPopulationSolution()
	{
		System.out.println("problem : " + this.toString());
		
		this.population = new Population(numLocations, 100, .1);
		
		runItterations(1000);
		return population.getMostFit();
	}
	
	public void runItterations(int itterations)
	{
		while (population.getGeneration() < itterations)
		{
			System.out.println(population.toString());
			
			population.populationStep();
			
			for (OrderSolution solution : population)
			{
				evaluate(solution);
			}
			//population.showRandomSample(20);
			population.endStep();
		}
	}
	
	public ArrayList<Location> getLocations()
	{
		return locations;
	}
	
}
