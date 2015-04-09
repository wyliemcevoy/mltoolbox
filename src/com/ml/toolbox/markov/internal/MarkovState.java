package com.ml.toolbox.markov.internal;

import java.util.ArrayList;
import java.util.List;

public class MarkovState
{
	private boolean isTerminal;
	private double value;
	private List<MarkovAction> actions;
	
	public MarkovState(double value)
	{
		this.value = value;
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
	
}
