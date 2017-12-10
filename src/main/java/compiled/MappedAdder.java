package compiled;

import de.netsysit.util.beans.InterfaceFactory;
import de.elbosso.dataflowframework.modules.MapMessageModule;
import de.netsysit.dataflowframework.modules.ThreadingBeanContextChildModuleBase;
import de.netsysit.util.threads.CubbyHole;
import de.netsysit.util.threads.SimpleNonBlockingCubbyHole;

public class MappedAdder extends MapMessageModule
{
	static
	{
		InterfaceFactory.setSuperclassAssociationForEventDispatchThread(
				MappedAdder.class, ThreadingBeanContextChildModuleBase.class);
	}
	private double result;
	public MappedAdder()
	{
		super(MappedAdder.class.getName());
		java.util.Properties props = new java.util.Properties();
		props.setProperty("a", "a");
		props.setProperty("b", "b");
		setProps(props);
	}
	@Override
	protected CubbyHole createCubbyHole()
	{
		return new SimpleNonBlockingCubbyHole();
	}
	@Override
	protected void doWork(Object ref) throws InterruptedException
	{
//Kopie der eingehenden Map
		java.util.Map map = (java.util.Map) ref;
		Number a = null;
		Number b = null;
//Versuch, die erwarteten Daten aus der Map zu holen
		a = (Number) getData(map, "a");
		b = (Number) getData(map, "b");
		if ((a != null) && (b != null))
		{
			result = a.doubleValue() + b.doubleValue();
//Hinzufügen der Ergebnisses zur Map und Versenden
			java.util.Map old = null;
			map.put("result", result);
			send("output", old, map);
		}
	}
}