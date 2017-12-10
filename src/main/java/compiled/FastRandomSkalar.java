package compiled;

import de.elbosso.dataflowframework.modules.StartStopModule;
import java.util.Random;
public class FastRandomSkalar extends StartStopModule
{
	public FastRandomSkalar()
	{
		super(FastRandomSkalar.class.getName());
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
			double old=getValue();
			value=rand.nextDouble();
			send("value",old,getValue());
		}
	}
}