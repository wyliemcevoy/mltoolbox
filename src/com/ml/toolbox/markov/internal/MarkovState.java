package com.ml.toolbox.markov.internal;

import java.util.ArrayList;

public class MarkovState
{
	private boolean isTerminal;
	private double value;
	private double estimatedValue;
	private double nextEstimatedValue;
	private ArrayList<MarkovAction> actions;
	private MarkovAction policy;
	private int index;
	
	public MarkovState(int index, double value)
	{
		this.index = index;
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
	public ArrayList<MarkovAction> getActions()
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
	
	/**
	 * @return the bestAction
	 */
	public MarkovAction getPolicy()
	{
		return policy;
	}
	
	/**
	 * @param policy
	 *            the bestAction to set
	 */
	public void calculatePolicy()
	{
		double bestActionValue = -1000000000;
		
		for (MarkovAction action : actions)
		{
			double actionValue = 0;
			
			for (MarkovActionResult result : action.getPossibleResults())
			{
				actionValue += result.getProbability() * result.getState().getEstimatedValue();
			}
			
			if (actionValue > bestActionValue)
			{
				policy = action;
				bestActionValue = actionValue;
			}
		}
	}
	
	/**
	 * @return the index
	 */
	public int getIndex()
	{
		return index;
	}
	
	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index)
	{
		this.index = index;
	}
	
	public void setPolicy(MarkovAction policy)
	{
		this.policy = policy;
		
	}
	
	public void reset()
	{
		this.estimatedValue = value;
		this.nextEstimatedValue = value;
	}
}
