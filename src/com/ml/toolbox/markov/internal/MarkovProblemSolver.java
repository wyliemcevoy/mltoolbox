package com.ml.toolbox.markov.internal;

public interface MarkovProblemSolver
{
	public void accept(MarkovProblem problem);
	
	public void solve();
	
	public int getNumberOfItterations();
}
