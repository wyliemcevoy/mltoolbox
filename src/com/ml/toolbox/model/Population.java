package com.ml.toolbox.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class Population implements Iterable<OrderSolution>
{
	private ArrayList<OrderSolution> solutions;
	private int numAttributes;
	private int populationSize;
	private int deathThreshold;
	private int generation;
	private OrderSolution mostFit;
	private double averageFitness;
	private double mutationRate = .2;
	private double imigrationRate = 100;
	
	class CustomComparator implements Comparator<OrderSolution>
	{
		@Override
		public int compare(OrderSolution o1, OrderSolution o2)
		{
			return o1.compareTo(o2);
		}
	}
	
	public Population(int numAttributes, int populationSize, double mutationRate)
	{
		this.numAttributes = numAttributes;
		this.populationSize = populationSize;
		this.solutions = new ArrayList<OrderSolution>();
		this.generation = 0;
		
		for (int i = 0; i < populationSize; i++)
		{
			OrderSolution solution = new OrderSolution(numAttributes);
			solution.randomize();
			solutions.add(solution);
		}
	}
	
	public void populationStep()
	{
		generation++;
		int mutations = 0;
		Random rand = new Random(System.currentTimeMillis());
		ArrayList<OrderSolution> progeny = new ArrayList<OrderSolution>();
		imigration();
		
		for (int i = 0; i < solutions.size() / 2; i++)
		{
			OrderSolution male = solutions.get(i);
			OrderSolution female = solutions.get(rand.nextInt(solutions.size()));
			
			progeny.addAll(male.mate(rand.nextInt(numAttributes), female));
		}
		solutions.addAll(progeny);
		
		// Mutate step
		for (OrderSolution solution : solutions)
		{
			
			if (rand.nextInt(101) / 100.0 < mutationRate)
			{
				mutations++;
				solution.mutate(rand.nextInt(numAttributes - 1), 5 - rand.nextInt(10));
			}
		}
		
		//System.out.println("total mutations : " + mutations);
		
	}
	
	public void endStep()
	{
		
		sort();
		cullTheWeak();
		mostFit = solutions.get(0);
		
		averageFitness = 0;
		
		for (OrderSolution solution : solutions)
		{
			averageFitness += solution.getScore();
		}
		
		averageFitness /= solutions.size();
		
	}
	
	private void imigration()
	{
		for (int i = 0; i < imigrationRate; i++)
		{
			OrderSolution solution = new OrderSolution(numAttributes);
			solution.randomize();
			solutions.add(solution);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void sort()
	{
		Collections.sort(solutions, new Comparator()
		{
			@Override
			public int compare(Object one, Object two)
			{
				return ((OrderSolution) one).compareTo(((OrderSolution) two));
			}
		});
	}
	
	private void cullTheWeak()
	{
		sort();
		
		while (solutions.size() > populationSize)
		{
			solutions.remove(populationSize);
		}
		
		/*
		int maxDeaths = solutions.size() / 2;
		int deaths = 0;
		Iterator<OrderSolution> itt = solutions.iterator();
		
		while (itt.hasNext() && deaths < maxDeaths)
		{
			OrderSolution solution = itt.next();
			if (solution.getScore() > averageFitness)
			{
				itt.remove();
				deaths++;
			}
		}
		*/
	}
	
	@Override
	public Iterator<OrderSolution> iterator()
	{
		return solutions.iterator();
	}
	
	/**
	 * @return the generation
	 */
	public int getGeneration()
	{
		return generation;
	}
	
	public OrderSolution getMostFit()
	{
		return mostFit;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Population [gen = " + generation + ", mostFit = " + mostFit + ", average = " + ((int) averageFitness) + " size = " + solutions.size() + "]";
	}
	
	public void showRandomSample(int size)
	{
		Random rand = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < size; i++)
		{
			System.out.println("\t" + solutions.get(rand.nextInt(solutions.size())));
		}
	}
	
}
