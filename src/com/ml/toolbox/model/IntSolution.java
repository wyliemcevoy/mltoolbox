package com.ml.toolbox.model;

import java.util.ArrayList;
import java.util.Iterator;

public class IntSolution implements Iterable<Integer>
{
	protected ArrayList<Integer> solution;
	
	public IntSolution()
	{
		this.solution = new ArrayList<Integer>();
	}
	
	@Override
	public Iterator<Integer> iterator()
	{
		return solution.iterator();
	}
	
}
