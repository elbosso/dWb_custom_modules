/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvnmodules;

/**
 *
 * @author elbosso
 */
public class SimplePopulationConfiguration extends de.elbosso.dataflowframework.modules.base.multiagent.PopulationConfiguration
{
	private final double sensorSpacing;
	private final double additionalSensorCount;
	private final double turnAtObstacle;
	private final double minObstacleDist;
	private final double minInterestingDistance;
	private final double attraction;

	public SimplePopulationConfiguration(double sensorSpacing, double additionalSensorCount, double turnAtObstacle, double minObstacleDist, double minInterestingDistance, double attraction)
	{
		super();
		this.sensorSpacing = sensorSpacing;
		this.additionalSensorCount = additionalSensorCount;
		this.turnAtObstacle = turnAtObstacle;
		this.minObstacleDist = minObstacleDist;
		this.minInterestingDistance = minInterestingDistance;
		this.attraction = attraction;
	}

	public double getSensorSpacing()
	{
		return sensorSpacing;
	}

	public double getAdditionalSensorCount()
	{
		return additionalSensorCount;
	}

	public double getTurnAtObstacle()
	{
		return turnAtObstacle;
	}

	public double getMinObstacleDist()
	{
		return minObstacleDist;
	}

	public double getMinInterestingDistance()
	{
		return minInterestingDistance;
	}

	public double getAttraction()
	{
		return attraction;
	}

	
}
