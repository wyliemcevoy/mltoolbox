package com.ml.toolbox.markov.internal;

import java.util.ArrayList;
import java.util.List;

public class MarkovState
{
	private boolean isTerminal;
	private double value;
	private double estimatedValue;
	private double nextEstimatedValue;
	private List<MarkovAction> actions;
	private MarkovAction bestAction;
	
	public MarkovState(double value)
	{
		this.value = value;
		this.estimatedValue = value;
		this.nextEstimatedValue = value;
		this.isTerminal = false;
		this.actions = new ArrayList<MarkovAction>();
	}
	
	/**
	 * @return the isTerminal
	 */
	public boolean isTerminal()
	{
		return isTerminal;
	}
	
	/**
	 * @param isTerminal
	 *            the isTerminal to set
	 */
	public void setTerminal(boolean isTerminal)
	{
		this.isTerminal = isTerminal;
	}
	
	/**
	 * @return the value
	 */
	public double getValue()
	{
		return value;
	}
	
	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value)
	{
		this.value = value;
		this.estimatedValue = value;
	}
	
	/**
	 * @return the actions
	 */
	public List<MarkovAction> getActions()
	{
		return actions;
	}
	
	public void addAction(MarkovAction action)
	{
		this.actions.add(action);
	}
	
	/**
	 * @return the estimatedValue
	 */
	public double getEstimatedValue()
	{
		return estimatedValue;
	}
	
	/**
	 * @param estimatedValue
	 *            the estimatedValue to set
	 */
	public void setEstimatedValue(double estimatedValue)
	{
		this.estimatedValue = estimatedValue;
	}
	
	/**
	 * @return the nextEstimatedValue
	 */
	public double getNextEstimatedValue()
	{
		return nextEstimatedValue;
	}
	
	/**
	 * @param nextEstimatedValue
	 *            the nextEstimatedValue to set
	 */
	public void setNextEstimatedValue(double nextEstimatedValue)
	{
		this.nextEstimatedValue = nextEstimatedValue;
	}
	
	public void update()
	{
		
		if (isTerminal)
		{
			this.estimatedValue = this.value;
		} else
		{
			this.estimatedValue = nextEstimatedValue;
		}
	}
	
}
