/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//new de.elbosso.dataflowframework.modules.demo.multiagent.Environment(new java.awt.Dimension(30,20));
//new de.elbosso.dataflowframework.modules.demo.multiagent.SimpleEnvironment(new java.awt.Dimension(40,30));
package mvnmodules;

import java.awt.Dimension;

/**
 *
 * @author elbosso
 */
public class SimpleEnvironment extends de.elbosso.dataflowframework.modules.base.multiagent.Environment
{
	private final java.awt.Dimension dimension;
	private final int[][] floor;

@java.beans.ConstructorProperties("dimension")
	public SimpleEnvironment(Dimension dimension)
	{
		super();
		this.dimension = dimension;
		this.floor = new int[dimension.width][dimension.height];
	}

	public Dimension getDimension()
	{
		return dimension;
	}
	
	public boolean isBlocked(int x,int y)
	{
		boolean rv=false;
		rv=((x<=0)||(y<=0));
		if(rv==false)
		{
			rv=((x>=dimension.width-1)||(y>=dimension.height-1));
		}
		if(rv==false)
		{
			rv=floor[x][y]!=0;
		}
		return rv;
	}
}
