package compiled;

import de.elbosso.dataflowframework.modules.filter.rules.RuleBase;
import de.netsysit.util.validator.rules.FloatingPointMinMaxRule;
import de.netsysit.util.beans.InterfaceFactory;
import de.netsysit.dataflowframework.modules.BeanContextChildModuleBase;

public class FloatingPointMinMaxFilter extends
		RuleBase<Number, FloatingPointMinMaxRule>
{
	static
	{
		InterfaceFactory.setSuperclassAssociationForEventDispatchThread
				(FloatingPointMinMaxFilter.class, BeanContextChildModuleBase.class);
	}
	public FloatingPointMinMaxFilter()
	{
		super(Number.class, new FloatingPointMinMaxRule());
	}
}