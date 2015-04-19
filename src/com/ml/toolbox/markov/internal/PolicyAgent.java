package com.ml.toolbox.markov.internal;

public class PolicyAgent
{
	private int maxNumActions = 100;
	private MarkovGridProblem problem;
	private double score;
	private int usedActions;
	private String path;
	private ActionExecuter executer;
	
	public PolicyAgent(MarkovGridProblem problem)
	{
		super();
		this.problem = problem;
		this.score = 0;
		this.usedActions = 0;
		this.path = "";
		this.executer = new ActionExecuter();
	}
	
	public double runPolicy()
	{
		MarkovState current = problem.getStartState();
		
		while (!current.isTerminal() && maxNumActions > usedActions)
		{
			MarkovAction action = current.getPolicy();
			path += action.getName() + " ";
			current = executer.getResult(action);
			usedActions++;
			score += current.getValue();
		}
		
		return score;
	}
	
	public void reset()
	{
		this.score = 0;
		this.usedActions = 0;
		this.path = "";
	}
	
	/**
	 * @return the score
	 */
	public double getScore()
	{
		return score;
	}
	
	public String getResultString()
	{
		return "score : " + score + "  " + path;
	}
	
}
