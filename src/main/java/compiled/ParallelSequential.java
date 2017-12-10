package compiled;

import de.netsysit.dataflowframework.modules.BeanContextChildModuleBase;
import de.netsysit.util.beans.context.service.BackgroundExecutor;

import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceRevokedEvent;

import de.elbosso.util.threads.Workload;
import de.elbosso.util.threads.ParallelSequentialManager;

public class ParallelSequential extends BeanContextChildModuleBase
{
	private BackgroundExecutor executor;
	private ParallelSequentialManager psm;

	public void serviceAvailable(BeanContextServiceAvailableEvent bcsae)
	{
		super.serviceAvailable(bcsae);
		if (executor == null)
		{
			if (bcsae.getServiceClass() == BackgroundExecutor.class)
			{
				try
				{
					executor =
							(BackgroundExecutor) (bcsae.getSourceAsBeanContextServices()).getService(
									this, this, BackgroundExecutor.class, this, this);
				} catch (Exception e)
				{
					executor = null;
				}
				if (executor != null)
					psm = new ParallelSequentialManager(executor);
			}
		}
	}

	@Override
	public void serviceRevoked(BeanContextServiceRevokedEvent bcsre)
	{
		super.serviceRevoked(bcsre);
		if (executor != null)
		{
			if (bcsre.getServiceClass() == BackgroundExecutor.class)
			{
				executor = null;
			}
		}
	}

	private static int runningWorkloadNumber;

	class MyWorkload extends Workload
	{
		private int sleep;
		private int id = runningWorkloadNumber++;

		MyWorkload(int input)
		{
			super();
			sleep = input;
		}

		public void run()
		{
			send("msg", null, id + " sleeping for " + sleep + "ms");
			try
			{
				java.lang.Thread.currentThread().sleep(sleep);
			} catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}
			send("msg", null, id + " awoke!");
		}

		public Runnable createSequential()
		{
			return new Runnable()
			{
				public void run()
				{
					send("msg", null, ("sequential " + id));
				}
			};
		}
	}

	public void input(Number input)
	{
		if ((psm != null) && (input != null))
		{
			psm.enqueue(new MyWorkload(input.intValue()));
		}
	}

	private String msg;

	public String getMsg()
	{
		return msg;
	}
}