package com.ml.toolbox.markov.internal;

public class ValueItteration
{
	private MarkovProblem problem;
	private double gamma = .9;
	private static final int maxItterations = 100;
	
	public void accept(MarkovProblem problem)
	{
		this.problem = problem;
	}
	
	public void solve()
	{
		
		for (int i = 0; i < maxItterations; i++)
		{
			int delta = 0;
			for (MarkovState state : problem.getStates())
			{
				if (!state.isTerminal())
				{
					
					MarkovAction bestAction = null;
					double bestActionValue = Double.MIN_VALUE;
					
					for (MarkovAction action : state.getActions())
					{
						double actionValue = 0;
						
						for (MarkovActionResult result : action.getPossibleResults())
						{
							actionValue += result.getProbability() * result.getState().getEstimatedValue();
						}
						
						if (actionValue > bestActionValue)
						{
							bestAction = action;
							bestActionValue = actionValue;
						}
					}
					double oldValue = state.getEstimatedValue();
					double newValue = state.getValue() + gamma * bestActionValue;
					
					delta += Math.abs(oldValue - newValue);
					
					state.setNextEstimatedValue(newValue);
				}
			}
			
			problem.update();
			
			System.out.println("[  itteration = " + i + "    delta = " + delta + "   ]");
			System.out.println(problem);
			if (delta == 0)
			{
				break;
			}
		}
	}
}
