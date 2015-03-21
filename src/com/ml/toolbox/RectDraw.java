package com.ml.toolbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.ml.toolbox.model.OrderSolution;
import com.ml.toolbox.salesman.Location;

public class RectDraw extends JPanel
{
	private int w, h;
	private int buffer = 10;
	private ArrayList<Location> locations;
	private OrderSolution fittest;
	
	public RectDraw(int width, int height, ArrayList<Location> locations, OrderSolution fittest)
	{
		this.w = width;
		this.h = height;
		this.locations = locations;
		this.fittest = fittest;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, w + 2 * buffer, h + 2 * buffer);
		
		g.setColor(Color.RED);
		for (Location location : locations)
		{
			g.drawRect(location.getX() - 2, location.getY() - 2, 4, 4);
		}
		
		ArrayList<Integer> order = fittest.toOrderedList();
		for (int i = 1; i < order.size(); i++)
		{
			
			Location start = locations.get(order.get(i - 1) - 1);
			Location destination = locations.get(order.get(i) - 1);
			
			g.drawLine(start.getX(), start.getY(), destination.getX(), destination.getY());
		}
		
		g.setColor(Color.WHITE);
		g.drawString("" + fittest.getScore(), 10, 10);
		
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(w, h);
	}
}