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
public class SimpleIndividuumConfiguration extends de.elbosso.dataflowframework.modules.base.multiagent.IndividuumConfiguration
{
	private final double dvAccel;
	private final double dvBrake;

	public SimpleIndividuumConfiguration(double dvAccel, double dvBrake)
	{
		super();
		this.dvAccel = dvAccel;
		this.dvBrake = dvBrake;
	}

	public double getDvBrake()
	{
		return dvBrake;
	}

	public double getDvAccel()
	{
		return dvAccel;
	}

	
}
