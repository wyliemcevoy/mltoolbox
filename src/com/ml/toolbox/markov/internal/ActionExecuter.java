package com.ml.toolbox.markov.internal;

import java.util.Iterator;
import java.util.Random;

public class ActionExecuter
{
	private Random rand;
	
	public ActionExecuter()
	{
		this.rand = new Random(System.currentTimeMillis());
	}
	
	public MarkovState getResult(MarkovAction action)
	{
		double value = rand.nextDouble();
		double current = 0.0;
		
		MarkovActionResult result = null;
		Iterator<MarkovActionResult> it = action.getPossibleResults().iterator();
		
		while (it.hasNext() && value > current)
		{
			result = it.next();
			current += result.getProbability();
			
		}
		
		return result.getState();
	}
	
}
