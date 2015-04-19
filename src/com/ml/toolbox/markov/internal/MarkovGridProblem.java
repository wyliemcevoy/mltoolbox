package com.ml.toolbox.markov.internal;

import java.util.ArrayList;
import java.util.List;

public class MarkovGridProblem implements MarkovProblem
{
	private MarkovState[][] grid;
	private ArrayList<MarkovState> states;
	private int width = 10;
	private int height = 12;
	
	public MarkovGridProblem(int i)
	{
		
		this.width = i;
		this.height = i;
		
		intializeGrid();
	}
	
	public MarkovGridProblem()
	{
		intializeGrid();
	}
	
	private void intializeGrid()
	{
		this.states = new ArrayList<MarkovState>();
		this.grid = new MarkovState[height][width];
		int index = 0;
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				grid[y][x] = new MarkovState(index, 0);
				grid[y][x].setValue(-1);
				states.add(grid[y][x]);
				index++;
			}
		}
		
		grid[height / 2 - 1][width / 2 - 1].setTerminal(true);
		grid[height / 2 - 1][width / 2 - 1].setValue(-100);
		grid[height - 1][width - 1].setTerminal(true);
		grid[height - 1][width - 1].setValue(100);
		
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
		MarkovGridAction action = null;
		
		MarkovActionResult up = new MarkovActionResult(get(x, y - 1));
		MarkovActionResult down = new MarkovActionResult(get(x, y + 1));
		MarkovActionResult left = new MarkovActionResult(get(x - 1, y));
		MarkovActionResult right = new MarkovActionResult(get(x + 1, y));
		
		switch (direction)
		{
			case down:
				action = new MarkovGridAction("v");
				
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
				
				action = new MarkovGridAction("<");
				
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
				
				action = new MarkovGridAction(">");
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
				
				action = new MarkovGridAction("^");
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
				String num = "" + ((int) get(x, y).getEstimatedValue());
				
				String buf = " ";
				int size = 7 - num.length();
				
				for (int i = 0; i < size; i++)
				{
					buf += " ";
				}
				
				build += buf + num + " ";
				
			}
			build += "\n";
		}
		return build;
	}
	
	/*
	public String getOptimalPath()
	{
		boolean[][] path = new boolean[height][width];
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				path[y][x] = false;
			}
			
		}
		
		MarkovState currentState = getStartState();
		
		while (!currentState.isTerminal())
		{
			
		}
		
		String build = "";
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				String buf = " ";
				if (path[y][x])
				{
					build += "  O";
				} else
				{
					
				}
				
				build += buf + ((int) get(x, y).getEstimatedValue()) + " ";
			}
			build += "\n";
		}
		return build;
		
	}
	*/
	public void update()
	{
		for (MarkovState state : states)
		{
			state.update();
		}
	}
	
	@Override
	public void calculatePolicy()
	{
		for (MarkovState state : states)
		{
			state.calculatePolicy();
		}
	}
	
	@Override
	public String getPolicyAsString()
	{
		
		String build = "";
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				String num = "" + ((int) get(x, y).getEstimatedValue());
				
				String buf = " ";
				int size = 4 - num.length();
				
				for (int i = 0; i < size; i++)
				{
					buf += " ";
				}
				
				build += buf + num + " ";
				MarkovState state = get(x, y);
				state.calculatePolicy();
				if (state.getPolicy() != null)
				{
					
					build += state.getPolicy().getName() + " ";
					
				}
			}
			build += "\n";
		}
		return build;
	}
	
	public String getOptimalPath()
	{
		boolean[][] path = new boolean[height][width];
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				path[y][x] = false;
			}
			
		}
		
		MarkovState currentState = getStartState();
		
		while (!currentState.isTerminal())
		{
			
		}
		
		String build = "";
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				String buf = " ";
				if (path[y][x])
				{
					build += "  O";
				} else
				{
					
				}
				
				build += buf + ((int) get(x, y).getEstimatedValue()) + " ";
			}
			build += "\n";
		}
		return build;
	}
	
	@Override
	public MarkovState getState(int i)
	{
		return states.get(i);
	}
	
	@Override
	public void reset()
	{
		for (MarkovState state : states)
		{
			state.reset();
		}
		
	}
	
}
