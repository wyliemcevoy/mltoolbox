package com.ml.toolbox.markov.internal;

public class GridValueMaximization
{
	private MarkovProblem problem;
	
	public void accept(MarkovProblem problem)
	{
		this.problem = problem;
	}
	
	public GridPolicy solve()
	{
		GridPolicy solution = new GridPolicy();
		
		return solution;
	}
}
