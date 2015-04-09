package com.ml.toolbox.markov.internal;

import java.util.ArrayList;
import java.util.List;

public class MarkovGridProblem implements MarkovProblem
{
	private MarkovState[][] grid;
	private List<MarkovState> states;
	private int width = 5;
	private int height = 6;
	
	private enum Direction {
		up, down, left, right
	}
	
	public MarkovGridProblem()
	{
		
		intializeGrid();
	}
	
	private void intializeGrid()
	{
		this.states = new ArrayList<MarkovState>();
		this.grid = new MarkovState[6][5];
		
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				grid[i][j] = new MarkovState(0);
				states.add(grid[i][j]);
			}
		}
		
		grid[6][5].setTerminal(true);
		grid[6][5].setValue(100);
		grid[5][5].setTerminal(true);
		grid[5][5].setValue(-100);
		
		intializeActions();
	}
	
	private void intializeActions()
	{
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				createActions(j, i);
			}
		}
	}
	
	private void createActions(int j, int i)
	{
		grid[j][i].addAction(createAction(Direction.up, j, i));
		
	}
	
	@Override
	public MarkovState getStartState()
	{
		return grid[0][0];
	}
	
	@Override
	public List<MarkovState> getStates()
	{
		return states;
	}
	
	private MarkovGridAction createAction(Direction direction, int j, int i)
	{
		MarkovGridAction action = new MarkovGridAction();
		switch (direction)
		{
			case down:
				
				// go down
				MarkovActionResult down = new MarkovActionResult(get(j - 1, i));
				down.setProbability(.8);
				action.addPossible(down);
				
				// go left
				MarkovActionResult left = new MarkovActionResult(get(j - 1, i));
				down.setProbability(.1);
				action.addPossible(left);
				// go right
				MarkovActionResult right = new MarkovActionResult(get(j - 1, i));
				down.setProbability(.1);
				action.addPossible(right);
				
				break;
			case left:
				break;
			case right:
				break;
			case up:
				break;
			default:
				break;
		
		}
		
		return action;
	}
	
	private MarkovState get(int j, int i)
	{
		return grid[sanatizeY(j)][sanatizeX(i)];
	}
	
	private int sanatizeX(int i)
	{
		return Math.max(Math.min(i, width), 0);
	}
	
	private int sanatizeY(int j)
	{
		return Math.max(Math.min(j, height), 0);
		
	}
}
