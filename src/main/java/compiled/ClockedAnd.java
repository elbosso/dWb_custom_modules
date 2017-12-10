package compiled;

import de.netsysit.dataflowframework.modules.ModuleBase;
import de.elbosso.util.Latch;
public class ClockedAnd extends ModuleBase
{
	private Latch latch;
	public ClockedAnd()
	{
		super();
		latch=new Latch();
	}
	private boolean result;
	public boolean getResult()
	{
		return result;
	}
	public void inputA(boolean in)
	{
		latch.latch("inputA",in);
	}
	public void inputB(boolean in)
	{
		latch.latch("inputB",in);
	}
	public void clock(java.lang.Object in)
	{
		compute();
	}
	private void compute()
	{
		boolean old=getResult();
//inputA holen
		boolean a = latch.fetchBoolean("inputA");
//inputb holen
		boolean b = latch.fetchBoolean("inputB");
		result=a&&b;
//Events versenden!
		send("result",old,getResult());
	}
}