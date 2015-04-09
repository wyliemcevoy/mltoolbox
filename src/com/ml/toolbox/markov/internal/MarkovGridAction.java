package com.ml.toolbox.markov.internal;

import java.util.ArrayList;
import java.util.List;

public class MarkovGridAction implements MarkovAction
{
	private List<MarkovActionResult> results;
	
	public MarkovGridAction()
	{
		results = new ArrayList<MarkovActionResult>();
	}
	
	@Override
	public List<MarkovActionResult> getPossibleResults()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void addPossible(MarkovActionResult result)
	{
		results.add(result);
		
	}
	
}
