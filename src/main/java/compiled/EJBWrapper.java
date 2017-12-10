package compiled;

import de.elbosso.dataflowframework.modules.EJBModuleBase;
import java.util.Properties;
import de.netsysit.util.threads.CubbyHole;
import de.netsysit.util.threads.SimpleBufferingCubbyHole;

public class EJBWrapper extends EJBModuleBase<RemoteCalculator>
{
	public EJBWrapper()
	{
		super(RemoteCalculator.class, EJBWrapper.class.getName());
	}
	protected CubbyHole createCubbyHole()
	{
		return new SimpleBufferingCubbyHole();
	}
	protected Properties getEnvironment()
	{
		java.util.Properties env = new java.util.Properties();
		env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		env.put(javax.naming.Context.PROVIDER_URL,
				"http-remoting://da_host:da_port");
		env.put(javax.naming.Context.SECURITY_PRINCIPAL,
				"da_user");
		env.put(javax.naming.Context.SECURITY_CREDENTIALS,
				"da_password");
		env.put(javax.naming.Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		env.put("jboss.naming.client.ejb.context",
				true);
		return env;
	}
	protected String getJndiName()
	{
		return "ejb:/wildfly-ejb-remote-server-side/CalculatorBean!";
	}
	private int result;
	public int getResult()
	{
		return result;
	}
	private Number a;
	private Number b;
	public void inputA(Number in)
	{
		a = in;
		processData(in);
	}
	public void inputB(Number in)
	{
		b = in;
		processData(in);
	}
	protected void doWork(Object ref)
	{
		if ((a != null) && (b != null))
		{
			int old = getResult();
			result = ejb.add(a.intValue(), b.intValue());
			send("result", old, getResult());
		}
	}
}