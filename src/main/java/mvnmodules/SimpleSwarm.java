/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvnmodules;

import de.netsysit.util.lang.MiniMax;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elbosso
 */
public class SimpleSwarm extends de.elbosso.dataflowframework.modules.base.multiagent.Swarm<SimpleState,SimpleIndividuumConfiguration,SimplePopulationConfiguration,SimpleEnvironment>
{
	private de.netsysit.util.lang.MiniMax initialVelocityRange;
	private de.netsysit.util.lang.MiniMax dvAccelRange;
	private de.netsysit.util.lang.MiniMax dvBrakeRange;
	private final de.netsysit.util.lang.MiniMax lookAtRange;
	private int additionalSensorsCount;
	private double turnAtObstacle;
	private double minObstacleDist;
	private double sensorSpacing;
	private double minInterestingDistance;
	private double attraction;

	public SimpleSwarm()
	{
		super(SimpleState.class);
		initialVelocityRange=new de.netsysit.util.lang.MiniMax(0.05, 0.2);
		dvAccelRange=new de.netsysit.util.lang.MiniMax(0.01, 0.03);
		dvBrakeRange=new de.netsysit.util.lang.MiniMax(0.01, 0.03);
		lookAtRange=new de.netsysit.util.lang.MiniMax(0, java.lang.Math.PI*2);
		additionalSensorsCount=4;
		turnAtObstacle=java.lang.Math.PI*0.5;
		minObstacleDist=3;
		sensorSpacing=0.2;
		minInterestingDistance=6;
		attraction=0.7;
	}
	protected SimpleAgent createAgent(SimplePopulationConfiguration populationConfiguration)
	{
		de.netsysit.util.lang.MiniMax posRangeX=new de.netsysit.util.lang.MiniMax(0, environment.getDimension().width);
		de.netsysit.util.lang.MiniMax posRangeY=new de.netsysit.util.lang.MiniMax(0, environment.getDimension().height);
		double x=-1;
		double y=-1;
		while(environment.isBlocked((int)x,(int)y))
		{
			x=posRangeX.getRandomValue();
			y=posRangeY.getRandomValue();
		}
		SimpleIndividuumConfiguration ic=createIndividuumConfiguration();
		return new SimpleAgent(new java.awt.geom.Point2D.Double(x,y),initialVelocityRange.getRandomValue(),lookAtRange.getRandomValue(),ic,populationConfiguration);
	}
	protected SimplePopulationConfiguration createPopulationConfiguration()
	{
		return new SimplePopulationConfiguration(sensorSpacing, additionalSensorsCount, turnAtObstacle, minObstacleDist, minInterestingDistance, attraction);
	}
	protected SimpleIndividuumConfiguration createIndividuumConfiguration()
	{
		return new SimpleIndividuumConfiguration(dvAccelRange.getRandomValue(),dvBrakeRange.getRandomValue());
	}
	public double getMinInterestingDistance()
	{
		return minInterestingDistance;
	}

	public void setMinInterestingDistance(double minInterestingDistance)
	{
		double old=getMinInterestingDistance();
		this.minInterestingDistance = minInterestingDistance;
		send("minInterestingDistance",old,getMinInterestingDistance());
		createSwarm();
	}

	public double getAttraction()
	{
		return attraction;
	}

	public void setAttraction(double attraction)
	{
		double old=getAttraction();
		this.attraction = attraction;
		send("attraction",old,getAttraction());
		createSwarm();
	}

	public MiniMax getInitialVelocityRange()
	{
		return initialVelocityRange;
	}

	public void setInitialVelocityRange(MiniMax initialVelocityRange)
	{
		MiniMax old=getInitialVelocityRange();
		this.initialVelocityRange = initialVelocityRange;
		send("initialVelocityRange",old,getInitialVelocityRange());
		createSwarm();
	}

	public MiniMax getDvAccelRange()
	{
		return dvAccelRange;
	}

	public void setDvAccelRange(MiniMax dvAccelRange)
	{
		MiniMax old=getDvAccelRange();
		this.dvAccelRange = dvAccelRange;
		send("dvAccelRange",old,getDvAccelRange());
		createSwarm();
	}

	public MiniMax getDvBrakeRange()
	{
		return dvBrakeRange;
	}

	public void setDvBrakeRange(MiniMax dvBrakeRange)
	{
		MiniMax old=getDvBrakeRange();
		this.dvBrakeRange = dvBrakeRange;
		send("dvBrakeRange",old,getDvBrakeRange());
		createSwarm();
	}

	public int getAdditionalSensorsCount()
	{
		return additionalSensorsCount;
	}

	public void setAdditionalSensorsCount(int additionalSensorsCount)
	{
		int old=getAdditionalSensorsCount();
		this.additionalSensorsCount = additionalSensorsCount;
		send("additionalSensorsCount",old,getAdditionalSensorsCount());
		createSwarm();
	}

	public double getTurnAtObstacle()
	{
		return turnAtObstacle;
	}

	public void setTurnAtObstacle(double turnAtObstacle)
	{
		double old=getTurnAtObstacle();
		this.turnAtObstacle = turnAtObstacle;
		send("turnAtObstacle",old,getTurnAtObstacle());
		createSwarm();
	}

	public double getMinObstacleDist()
	{
		return minObstacleDist;
	}

	public void setMinObstacleDist(double minObstacleDist)
	{
		double old=getMinObstacleDist();
		this.minObstacleDist = minObstacleDist;
		send("minObstacleDist",old,getMinObstacleDist());
		createSwarm();
	}

	public double getSensorSpacing()
	{
		return sensorSpacing;
	}

	public void setSensorSpacing(double sensorSpacing)
	{
		double old=getSensorSpacing();
		this.sensorSpacing = sensorSpacing;
		send("sensorSpacing",old,getSensorSpacing());
		createSwarm();
	}
}
