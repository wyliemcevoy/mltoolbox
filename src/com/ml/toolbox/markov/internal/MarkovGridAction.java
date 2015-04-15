package com.ml.toolbox.markov.internal;

import java.util.ArrayList;
import java.util.List;

public class MarkovGridAction implements MarkovAction
{
	private List<MarkovActionResult> results;
	private String name;
	
	public MarkovGridAction(String name)
	{
		this.results = new ArrayList<MarkovActionResult>();
		this.name = name;
	}
	
	@Override
	public List<MarkovActionResult> getPossibleResults()
	{
		return results;
	}
	
	@Override
	public void addPossible(MarkovActionResult result)
	{
		results.add(result);
		
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
}
