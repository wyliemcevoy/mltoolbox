package com.ml.toolbox.markov.internal;

public class MarkovActionResult
{
	private double probability;
	private MarkovState state;
	
	public MarkovActionResult(MarkovState state)
	{
		this.state = state;
	}
	
	/**
	 * @return the probability
	 */
	public double getProbability()
	{
		return probability;
	}
	
	/**
	 * @param probability
	 *            the probability to set
	 */
	public void setProbability(double probability)
	{
		this.probability = probability;
	}
	
	/**
	 * @return the state
	 */
	public MarkovState getState()
	{
		return state;
	}
	
	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(MarkovState state)
	{
		this.state = state;
	}
	
}
