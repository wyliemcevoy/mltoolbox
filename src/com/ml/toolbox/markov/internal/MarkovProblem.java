package com.ml.toolbox.markov.internal;

import java.util.List;

public interface MarkovProblem
{
	public MarkovState getStartState();
	
	public List<MarkovState> getStates();
	
	public void update();
	
	public MarkovState getState(int i);
	
	public String getPolicyAsString();
	
	public void calculatePolicy();
	
	public void reset();
}
