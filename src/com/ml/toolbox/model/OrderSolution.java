package com.ml.toolbox.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class OrderSolution implements Iterable<Integer>, Comparable
{
	protected int[] permutations;
	
	/**
	 * @return the score
	 */
	public double getScore()
	{
		return score;
	}
	
	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score)
	{
		this.score = score;
	}
	
	protected double score;
	
	public OrderSolution(int size)
	{
		this.permutations = new int[size - 1];
		
		for (int i = 0; i < permutations.length; i++)
		{
			permutations[i] = i + 1;
		}
		initialize();
	}
	
	public OrderSolution(int[] permutations)
	{
		this.permutations = permutations;
		initialize();
	}
	
	public OrderSolution(OrderSolution clone)
	{
		int[] oldPerm = clone.getPermutations();
		
		this.permutations = new int[oldPerm.length];
		
		for (int i = 0; i < permutations.length; i++)
		{
			permutations[i] = oldPerm[i];
		}
		
		initialize();
	}
	
	private void initialize()
	{
		this.score = Double.MAX_VALUE;
	}
	
	public ArrayList<Integer> toOrderedList()
	{
		ArrayList<Integer> build = new ArrayList<>();
		
		build.add(1);
		
		for (int i = 0; i < permutations.length; i++)
		{
			
			build.add(permutations[i], i + 2);
		}
		
		return build;
	}
	
	/**
	 * @return copy of the permutations
	 */
	public int[] getPermutations()
	{
		int[] copy = new int[permutations.length];
		
		for (int i = 0; i < permutations.length; i++)
		{
			copy[i] = permutations[i];
		}
		
		return copy;
	}
	
	/**
	 * @param permutations
	 *            the permutations to set
	 */
	public void setPermutations(int[] permutations)
	{
		this.permutations = permutations;
	}
	
	@Override
	public String toString()
	{
		ArrayList<Integer> orderedList = this.toOrderedList();
		
		String build = "[score = " + ((int) score) + " (";
		
		for (int i = 0; i < orderedList.size(); i++)
		{
			build += orderedList.get(i);
			if (i < orderedList.size() - 1)
			{
				build += ",";
			}
		}
		
		build += ") (";
		
		for (int i = 0; i < permutations.length; i++)
		{
			build += permutations[i];
			if (i < permutations.length - 1)
			{
				build += ",";
			}
		}
		
		build += ")]";
		
		return build;
	}
	
	public void randomize()
	{
		Random rand = new Random();
		for (int i = 0; i < permutations.length; i++)
		{
			permutations[i] = rand.nextInt(i + 2);
		}
	}
	
	public List<OrderSolution> mate(int splitIndex, OrderSolution mate)
	{
		ArrayList<OrderSolution> children = new ArrayList<OrderSolution>();
		
		int[] male = this.getPermutations();
		int[] female = mate.getPermutations();
		
		int[] chromeOne = new int[permutations.length];
		int[] chromeTwo = new int[permutations.length];
		
		for (int i = 0; i < male.length; i++)
		{
			if (i < splitIndex)
			{
				chromeOne[i] = male[i];
				chromeTwo[i] = female[i];
			} else
			{
				// after split point
				chromeOne[i] = female[i];
				chromeTwo[i] = male[i];
			}
		}
		
		children.add(new OrderSolution(chromeOne));
		children.add(new OrderSolution(chromeTwo));
		
		return children;
	}
	
	@Override
	public Iterator<Integer> iterator()
	{
		return this.toOrderedList().iterator();
	}
	
	public void mutate(int index, int delta)
	{
		int value = permutations[index];
		int v = value;
		
		value += delta;
		
		value = Math.min(Math.max(value, 0), index);
		
		permutations[index] = value;
	}
	
	@Override
	public int compareTo(Object o)
	{
		if (o instanceof OrderSolution)
		{
			double otherScore = ((OrderSolution) o).getScore();
			
			if (otherScore == score)
			{
				return 0;
			} else if (otherScore < score)
			{
				return 1;
			} else
			{
				return -1;
			}
		}
		
		return 0;
	}
	
	public OrderSolution deepCopy()
	{
		return new OrderSolution(this);
	}
	
}
