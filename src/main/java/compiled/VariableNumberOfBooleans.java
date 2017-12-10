package compiled;

import de.elbosso.dataflowframework.modules.VariableNumberofParameters;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import de.netsysit.dataflowframework.modules.ModuleBase;

public class VariableNumberOfBooleans extends VariableNumberofParameters<Boolean>
{
	public VariableNumberOfBooleans()
	{
		super(java.lang.Boolean.class, false);
	}
	protected VariableNumberofParameters<Boolean>.Glue<Boolean>
	addComponent(String name)
	{return new Glue(name, this);
	}
	protected VariableNumberofParameters<Boolean>.Glue<Boolean>
	addComponent(Component comp, String string)
	{
		return new Glue(comp, string, this);
	}
	private class Glue extends VariableNumberofParameters<Boolean>.Glue<Boolean>
			implements ActionListener
	{
		private JCheckBox comp;
		Glue(Component comp, String propertyName, ModuleBase module)
		{
			super(propertyName, module);
			this.comp = (JCheckBox) comp;
			this.comp.addActionListener(this);
		}
		Glue(String propertyName, ModuleBase module)
		{
			super(propertyName, module);
		}
		public Component getComponent()
		{
			if (comp == null)
			{
				comp = new JCheckBox();
				comp.addActionListener(this);
			}
			return comp;
		}
		public Boolean performDataChange()
		{
			return comp.isSelected();
		}
		public void actionPerformed(ActionEvent e)
		{
			dataChange();
		}
	}
}