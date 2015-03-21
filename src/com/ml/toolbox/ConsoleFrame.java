package com.ml.toolbox;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.ml.toolbox.model.OrderSolution;
import com.ml.toolbox.salesman.Location;

public class ConsoleFrame extends JFrame
{
	/** uuid */
	private static final long serialVersionUID = 1L;
	
	public ConsoleFrame(int width, int height, ArrayList<Location> locations, OrderSolution fittest)
	{
		super();
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setSize(width + 20, height + 20);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.add(new RectDraw(width, height, locations, fittest));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}