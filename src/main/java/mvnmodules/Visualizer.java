/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvnmodules;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author elbosso
 */
public class Visualizer extends de.netsysit.dataflowframework.modules.ModuleBase
{
	private java.awt.image.BufferedImage img;
	private SimpleState[] states;
	private SimpleEnvironment environment;
	private int scaleFactor;
	private boolean displayFrontSensor;
	private boolean displayborder;

	public Visualizer()
	{
		super();
		scaleFactor=10;
	}

	public boolean isDisplayFrontSensor()
	{
		return displayFrontSensor;
	}

	public void setDisplayFrontSensor(boolean displayFrontSensor)
	{
		boolean old=isDisplayFrontSensor();
		this.displayFrontSensor = displayFrontSensor;
		send("displayFrontSensor",old,isDisplayFrontSensor());
	}

	public void setDisplayborder(boolean displayborder)
	{
		boolean old=isDisplayborder();
		this.displayborder = displayborder;
		send("displayborder",old,isDisplayborder());
	}

	public boolean isDisplayborder()
	{
		return displayborder;
	}

	public int getScaleFactor()
	{
		return scaleFactor;
	}

	public void setScaleFactor(int scaleFactor)
	{
		int old=getScaleFactor();
		this.scaleFactor = scaleFactor;
		send("scaleFactor",old,getScaleFactor());
	}

	public BufferedImage getImg()
	{
		return img;
	}
	
	public void putEnvironment(SimpleEnvironment environment)
	{
		this.environment=environment;
		updateImage();
	}
	public void putStates(SimpleState[] states)
	{
		this.states=states;
		updateImage();
	}
	private void updateImage()
	{
		if((states!=null)&&(environment!=null))
		{
			java.awt.image.BufferedImage old=getImg();
			img=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(scaleFactor*environment.getDimension().width, scaleFactor*environment.getDimension().height);
			final java.awt.Graphics2D g2=img.createGraphics();
			g2.setBackground(Color.black);
			g2.setPaint(g2.getBackground());
			g2.fillRect(0, 0, img.getWidth(), img.getHeight());
			g2.setPaint(java.awt.Color.white);
			if(isDisplayborder())
			{
				for(int x=0;x<environment.getDimension().width;++x)
				{
					for(int y=0;y<environment.getDimension().height;++y)
					{
						if(environment.isBlocked(x,y))
						{
							g2.fillRect(scaleFactor*x+1,scaleFactor*y+1,scaleFactor-2,scaleFactor-2);
						}
					}
				}
			}
			double sfh=(double)(scaleFactor-2)*0.5;
			de.elbosso.algorithms.geometry.Bresenham.Worker worker=new de.elbosso.algorithms.geometry.Bresenham.Worker()
			{
				public boolean doWork(java.awt.Point point)
				{
					boolean stopIt=environment.isBlocked(point.x,point.y);
					g2.fillRect(scaleFactor*point.x+1,scaleFactor*point.y+1,scaleFactor-2,scaleFactor-2);
					return stopIt;
				}
			};
			double len=java.lang.Math.sqrt(environment.getDimension().width*environment.getDimension().width+environment.getDimension().height*environment.getDimension().height);
			for (SimpleState state : states)
			{
				java.awt.geom.Point2D p=state.getPosition();
				g2.drawOval((int)(p.getX()*scaleFactor-sfh),(int)(p.getY()*scaleFactor-sfh),scaleFactor-2,scaleFactor-2);
				java.awt.geom.AffineTransform transform = new java.awt.geom.AffineTransform();
				transform.rotate(state.getLookAt(), (int)(p.getX()*scaleFactor),(int)(p.getY()*scaleFactor));
				java.awt.geom.AffineTransform latch = g2.getTransform();
				g2.transform(transform);
				g2.drawLine((int)(p.getX()*scaleFactor),(int)(p.getY()*scaleFactor),(int)(p.getX()*scaleFactor+scaleFactor), (int)(p.getY()*scaleFactor));
				g2.setTransform(latch);
				de.elbosso.algorithms.geometry.Bresenham bresenham=new de.elbosso.algorithms.geometry.Bresenham(1,worker);
				java.awt.geom.Point2D target=new java.awt.geom.Point2D.Double(p.getX()+len*java.lang.Math.cos(state.getLookAt()),p.getY()+len*java.lang.Math.sin(state.getLookAt()));
				if(isDisplayFrontSensor())
				{
					g2.setPaint(new java.awt.Color(255,255,255,64));
					bresenham.perform(p,target);
					double start=-state.getLookAt()-0.5;
					java.awt.geom.Arc2D arc=new java.awt.geom.Arc2D.Double();
					arc.setArcByCenter(state.getPosition().getX()*scaleFactor,state.getPosition().getY()*scaleFactor,len*scaleFactor, start/(2*java.lang.Math.PI)*360.0, 2.0/(2*java.lang.Math.PI)*360.0,java.awt.geom.Arc2D.PIE );
					g2.fill(arc);
					g2.setPaint(java.awt.Color.white);
				}
			}
			g2.dispose();
			send("img",old,getImg());
		}
	}
}
