package com.ml.toolbox.markov.internal;

import java.util.Random;

public class QLearningAgent implements MarkovProblemSolver
{
	private MarkovProblem problem;
	private static final int maxEpochs = 10000;
	private static final int maxActionsInEpoch = 10000;
	private static double gamma = .9;
	private double epsilon = .1;
	private static double learningRate = .9;
	private Random rand;
	private ActionExecuter executer;
	private int epochs;
	
	public QLearningAgent()
	{
		this.rand = new Random(System.currentTimeMillis());
		this.executer = new ActionExecuter();
	}
	
	public void accept(MarkovProblem problem)
	{
		this.problem = problem;
		
	}
	
	@Override
	public void solve()
	{
		int currentEpoch = 0;
		boolean policyHasChanged = true;
		while (policyHasChanged && currentEpoch < maxEpochs)
		{
			
			policyHasChanged = runEpoch();
			currentEpoch++;
			
			//System.out.println("[ q-learner epoch " + currentEpoch + " ]");
			//System.out.println(problem.toString());
		}
		epochs = currentEpoch;
	}
	
	private boolean runEpoch()
	{
		boolean policyHasChanged = false;
		int usedActions = 0;
		MarkovState current = problem.getStartState();
		while (!current.isTerminal() && usedActions < maxActionsInEpoch)
		{
			
			double oldEstimatedValue = current.getEstimatedValue();
			
			current.calculatePolicy();
			double bestActionValue = 0;
			for (MarkovActionResult result : current.getPolicy().getPossibleResults())
			{
				bestActionValue += result.getState().getEstimatedValue() * result.getProbability();
			}
			
			double newEstimatedValue = oldEstimatedValue + learningRate * (current.getValue() + gamma * bestActionValue - oldEstimatedValue);
			current.setNextEstimatedValue(newEstimatedValue);
			
			if (rand.nextDouble() > epsilon)
			{
				// Explore over Exploit
				
				int actionIndex = rand.nextInt(current.getActions().size());
				
				current = executer.getResult(current.getActions().get(actionIndex));
				
			} else
			{
				// Exploit over Explore
				current = executer.getResult(current.getPolicy());
			}
			
			if (oldEstimatedValue != newEstimatedValue)
			{
				policyHasChanged = true;
			}
			
			usedActions++;
		}
		
		problem.update();
		
		return policyHasChanged;
	}
	
	@Override
	public int getNumberOfItterations()
	{
		return epochs;
	}
}
