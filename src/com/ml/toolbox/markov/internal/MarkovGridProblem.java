package com.ml.toolbox.markov.internal;

import java.util.ArrayList;
import java.util.List;

public class MarkovGridProblem implements MarkovProblem
{
	private MarkovState[][] grid;
	private List<MarkovState> states;
	private int width = 5;
	private int height = 6;
	
	public MarkovGridProblem()
	{
		
		intializeGrid();
	}
	
	private void intializeGrid()
	{
		this.states = new ArrayList<MarkovState>();
		this.grid = new MarkovState[6][5];
		
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				grid[y][x] = new MarkovState(0);
				states.add(grid[y][x]);
			}
		}
		
		grid[5][4].setTerminal(true);
		grid[5][4].setValue(-100);
		grid[4][4].setTerminal(true);
		grid[4][4].setValue(100);
		
		intializeActions();
	}
	
	private void intializeActions()
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				createActions(x, y);
			}
		}
	}
	
	private void createActions(int x, int y)
	{
		MarkovState state = get(x, y);
		state.addAction(createAction(Direction.up, x, y));
		state.addAction(createAction(Direction.down, x, y));
		state.addAction(createAction(Direction.left, x, y));
		state.addAction(createAction(Direction.right, x, y));
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
	
	private MarkovGridAction createAction(Direction direction, int x, int y)
	{
		MarkovGridAction action = new MarkovGridAction();
		
		MarkovActionResult up = new MarkovActionResult(get(x + 1, y));
		MarkovActionResult down = new MarkovActionResult(get(x - 1, y));
		MarkovActionResult left = new MarkovActionResult(get(x, y - 1));
		MarkovActionResult right = new MarkovActionResult(get(x, y - 1));
		
		switch (direction)
		{
			case down:
				// go down
				down.setProbability(.8);
				action.addPossible(down);
				
				// go left
				left.setProbability(.1);
				action.addPossible(left);
				
				// go right
				right.setProbability(.1);
				action.addPossible(right);
				
				break;
			case left:
				
				// go left
				left.setProbability(.8);
				action.addPossible(left);
				
				// go up
				up.setProbability(.1);
				action.addPossible(up);
				
				// go down
				down.setProbability(.1);
				action.addPossible(down);
				
				break;
			case right:
				// go right
				right.setProbability(.8);
				action.addPossible(right);
				
				// go up
				up.setProbability(.1);
				action.addPossible(up);
				
				// go down
				down.setProbability(.1);
				action.addPossible(down);
				
				break;
			case up:
				// go down
				up.setProbability(.8);
				action.addPossible(up);
				
				// go left
				left.setProbability(.1);
				action.addPossible(left);
				
				// go right
				right.setProbability(.1);
				action.addPossible(right);
				
				break;
			default:
				break;
		}
		
		return action;
	}
	
	private MarkovState get(int x, int y)
	{
		return grid[sanatizeY(y)][sanatizeX(x)];
	}
	
	private int sanatizeX(int x)
	{
		return Math.max(Math.min(x, width - 1), 0);
	}
	
	private int sanatizeY(int y)
	{
		return Math.max(Math.min(y, height - 1), 0);
		
	}
	
	@Override
	public String toString()
	{
		String build = "";
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				build += ((int) get(x, y).getValue()) + " ";
			}
			build += "\n";
		}
		return build;
	}
}
