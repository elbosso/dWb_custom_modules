/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvnmodules;

import java.awt.Point;

/**
 *
 * @author elbosso
 */
@de.elbosso.util.lang.annotations.BeanInfo
public class SimpleState extends de.elbosso.dataflowframework.modules.base.multiagent.State
{
	private java.awt.geom.Point2D position;
	private double v;
	private double lookAt;
	private int turndirection;

	public SimpleState(java.awt.geom.Point2D position, double v, double lookAt)
	{
		super();
		this.position = position;
		this.v = v;
		this.lookAt = lookAt;
	}
	SimpleState(SimpleState other)
	{
		position=new java.awt.geom.Point2D.Double(other.getPosition().getX(),other.getPosition().getY());
		v=other.getV();
		lookAt=other.getLookAt();
	}

	public Object clone() throws CloneNotSupportedException
	{
		return new SimpleState(this);
	}
	
	public double getLookAt()
	{
		return lookAt;
	}

	public java.awt.geom.Point2D getPosition()
	{
		return position;
	}

	public double getV()
	{
		return v;
	}

	public void setLookAt(double lookAt)
	{
		this.lookAt = lookAt;
		while(this.lookAt<0)
		{
			this.lookAt+=2*java.lang.Math.PI;
		}
		while(this.lookAt>2*java.lang.Math.PI)
		{
			this.lookAt-=2*java.lang.Math.PI;
		}
	}

	public void setPosition(java.awt.geom.Point2D position)
	{
		this.position = position;
	}

	public void setV(double v)
	{
		this.v = v;
	}	

	public int getTurndirection()
	{
		return turndirection;
	}

	public void setTurndirection(int turndirection)
	{
		this.turndirection = turndirection;
	}
	
}
