package com.ml.toolbox.markov.internal;

import java.util.List;

public interface MarkovAction
{
	public List<MarkovActionResult> getPossibleResults();
	
	public void addPossible(MarkovActionResult result);
	
	public String getName();
}
