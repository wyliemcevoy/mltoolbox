package com.ml.toolbox.markov.internal;

import java.util.Random;

public class PolicyItteration implements MarkovProblemSolver
{
	private MarkovProblem problem;
	private double gamma = .9;
	private static final int maxItterations = 1000;
	private Random rand;
	private int itterations;
	
	public PolicyItteration()
	{
		rand = new Random(System.currentTimeMillis());
	}
	
	@Override
	public void accept(MarkovProblem problem)
	{
		this.problem = problem;
	}
	
	private void randomPolicy()
	{
		for (MarkovState state : problem.getStates())
		{
			int index = rand.nextInt(state.getActions().size());
			state.setPolicy(state.getActions().get(index));
		}
		
	}
	
	@Override
	public void solve()
	{
		randomPolicy();
		int n = problem.getStates().size();
		
		boolean policyHasChanged = true;
		int currentItterations = 0;
		double delta = 0;
		
		while (policyHasChanged && currentItterations < maxItterations)
		{
			policyHasChanged = false;
			currentItterations++;
			
			double[][] mat = new double[n][n];
			double[][] constants = new double[n][1];
			
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					mat[j][i] = 0;
				}
				constants[i][0] = 0;
			}
			
			// build matrix 
			for (MarkovState state : problem.getStates())
			{
				int index = state.getIndex();
				
				MarkovAction action = state.getPolicy();
				
				mat[index][index] = -1;
				if (!state.isTerminal())
				{
					for (MarkovActionResult result : state.getPolicy().getPossibleResults())
					{
						int possibleIndex = result.getState().getIndex();
						mat[index][possibleIndex] += gamma * result.getProbability();
					}
				}
				constants[index][0] = -1 * state.getValue();
			}
			
			// solve matrix
			double[][] solution = MatrixSolver.solve(mat, constants);
			
			for (int index = 0; index < n; index++)
			{
				//System.out.println(solution[index][0] + " ");
				MarkovState state = problem.getState(index);
				
				if (!state.isTerminal())
				{
					double oldValue = state.getEstimatedValue();
					double newValue = solution[index][0];
					
					delta += Math.abs(oldValue - newValue);
					state.setEstimatedValue(newValue);
				}
			}
			
			for (int index = 0; index < n; index++)
			{
				MarkovAction oldAction = problem.getState(index).getPolicy();
				// calculate new action
				problem.getState(index).calculatePolicy();
				MarkovAction newAction = problem.getState(index).getPolicy();
				
				if (newAction != oldAction)
				{
					policyHasChanged = true;
				}
			}
			
			if (delta < .001)
			{
				policyHasChanged = false;
			} else
			{
				policyHasChanged = true;
			}
			delta = 0;
			
			//System.out.println("[ policy itteration : " + currentItterations + " ]");
			//System.out.println(problem.getPolicyAsString() + "\n");
		}
		
		itterations = currentItterations;
		/*
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
		
		*/
	}
	
	@Override
	public int getNumberOfItterations()
	{
		return itterations;
	}
}
