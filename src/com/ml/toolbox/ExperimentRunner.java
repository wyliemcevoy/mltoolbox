package com.ml.toolbox;

import com.ml.toolbox.markov.internal.MarkovProblem;
import com.ml.toolbox.markov.internal.PolicyAgent;
import com.ml.toolbox.markov.internal.PolicyItteration;
import com.ml.toolbox.markov.internal.QLearningAgent;
import com.ml.toolbox.markov.internal.ValueItteration;

public class ExperimentRunner
{
	private MarkovProblem problem;
	private QLearningAgent qLearner;
	private ValueItteration valueItteration;
	private PolicyItteration policyItteration;
	private String qLearnerPolicy;
	private String valueItterationPolicy;
	private String policyItterationPolicy;
	private int numberOfAgentRuns = 100;
	private double averageQLearnerScore;
	private double averageValueItterationScore;
	private double averagePolicyItterationScore;
	private PolicyAgent agent;
	private int viEpochs;
	private int piEpochs;
	private int qlEpochs;
	
	public ExperimentRunner()
	{
		this.qLearner = new QLearningAgent();
		this.valueItteration = new ValueItteration();
		this.policyItteration = new PolicyItteration();
	}
	
	public void accept(MarkovProblem problem)
	{
		this.problem = problem;
		this.qLearner.accept(problem);
		this.valueItteration.accept(problem);
		this.policyItteration.accept(problem);
		this.agent = new PolicyAgent(problem);
		
	}
	
	public void runExperiments()
	{
		qLearner.solve();
		qLearnerPolicy = problem.getPolicyAsString();
		this.averageQLearnerScore = runPolicy();
		qlEpochs = qLearner.getNumberOfItterations();
		problem.reset();
		
		valueItteration.solve();
		valueItterationPolicy = problem.getPolicyAsString();
		this.averageValueItterationScore = runPolicy();
		viEpochs = valueItteration.getNumberOfItterations();
		problem.reset();
		
		policyItteration.solve();
		policyItterationPolicy = problem.getPolicyAsString();
		this.averagePolicyItterationScore = runPolicy();
		piEpochs = policyItteration.getNumberOfItterations();
		
	}
	
	private double runPolicy()
	{
		double averageScore = 0;
		
		for (int i = 0; i < numberOfAgentRuns; i++)
		{
			agent.runPolicy();
			averageScore += agent.getScore();
			agent.reset();
		}
		averageScore = averageScore / numberOfAgentRuns;
		return averageScore;
	}
	
	public void printResults()
	{
		System.out.println("[VI itterations: " + viEpochs + " average score : " + this.averageValueItterationScore + "]");
		System.out.println(this.valueItterationPolicy + "\n");
		
		System.out.println("[PI itterations: " + piEpochs + " average score : " + this.averagePolicyItterationScore + "]");
		System.out.println(this.policyItterationPolicy + "\n");
		
		System.out.println("[QL epochs: " + qlEpochs + " average score : " + this.averageQLearnerScore + "]");
		System.out.println(this.qLearnerPolicy + "\n");
	}
	
	public String csvResults()
	{
		return "" + viEpochs + "," + this.averageValueItterationScore + "," + piEpochs + "," + averagePolicyItterationScore + "," + qlEpochs + "," + averageQLearnerScore;
	}
}
