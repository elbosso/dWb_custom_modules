package compiled;

import de.elbosso.dataflowframework.modules.VariableNumberofInputsVisualization;
import java.awt.Component;
import javax.swing.JLabel;
public class MultiLabelVis extends
		VariableNumberofInputsVisualization<Number,Number>
{
	public MultiLabelVis()
	{
		super();
	}
	public void addNumber(Number newNumber,String spec)
	{
		super.addInput(newNumber,spec,"addNumber");
	}
	protected Component addComponent(Number model)
	{
		JLabel label = new JLabel();
		label.setMinimumSize(new java.awt.Dimension(10, 10));
		label.setPreferredSize(new java.awt.Dimension(10, 10));
		return label;
	}

	@Override
	protected void handleAddition(Component comp, Number model, Number newdata, boolean isDataSlot)
	{
		if (isDataSlot)
		{
			if (newdata == null)
			{
				((JLabel) comp).setText("");
			}
			else
			{
				((JLabel) comp).setText(newdata.toString());
			}
		}
	}

	protected Number createInstanceToBeStoredAtBackingStore()
	{
		return new Double(0);
	}
}