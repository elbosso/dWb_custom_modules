package compiled;

import de.elbosso.dataflowframework.modules.StartStopModuleWithDoOnce;
import java.util.Random;
public class FastRandomSkalarWithDoOnce extends StartStopModuleWithDoOnce
{
	public FastRandomSkalarWithDoOnce()
	{
		super(FastRandomSkalarWithDoOnce.class.getName());
	}
	private double value;
	public double getValue()
	{
		return value;
	}
	public void setRunning(boolean newrunning)
	{
		super.setRunning(newrunning);
		if(isRunning()==true)
		{
			processData(java.lang.Boolean.TRUE);
		}
		else
		{
			processData(null);
		}
	}
	private final static Random rand=new Random(System.nanoTime());
	protected void doWork(java.lang.Object ref)
	{
		if(disposed==false)
		{
			performWork();
		}
	}
	private void performWork()
	{
		double old=getValue();
		value=rand.nextDouble();
		send("value",old,getValue());
	}
	public void doOnce()
	{
		performWork();
	}
}